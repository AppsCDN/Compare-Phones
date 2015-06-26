package com.user.comparePhones;

import java.util.ArrayList;

import com.user.comparePhones.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class CompareActivity extends Activity {
	TextView phone1NameHeader;
	TextView phone2NameHeader;
	String phone1Uri = "img";
	String phone2Uri = "img";
	ArrayList<PhoneAttribute> attributeArray = new ArrayList<PhoneAttribute>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compare);
		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#33B5E5")));
		
		Intent intent = getIntent();
		//Phone1 = phoneNames[0], Phone2 = phoneNames[1]
		String[] phoneNames = intent.getStringArrayExtra("Phones");
		
		phone1NameHeader = (TextView)findViewById(R.id.phoneName1);
		phone2NameHeader = (TextView)findViewById(R.id.phoneName2);
		phone1NameHeader.setText(phoneNames[0]);
		phone2NameHeader.setText(phoneNames[1]);
		
		Cursor cursor = DBController.searchFullPhones(phoneNames[0], phoneNames[1]);
		
		if (cursor == null) {
			//Nothing
		} else {
			int numColumns = cursor.getColumnCount();
			
			//numColumns - 1 because we don't include ID
			for (int i = 0; i < numColumns - 1; i++) {
				PhoneAttribute attribute = new PhoneAttribute("N/A", "N/A", "N/A");
				attributeArray.add(attribute);
			}
			
			int phoneIndex = 0;
			cursor.moveToPosition(-1);
			boolean arePhonesInOrder = false;
			final int NAME_INDEX = 2;
			
			while (cursor.moveToNext()) {  			
				for (int i = 0; i < numColumns - 1; i++) {
					if (phoneIndex == 0) {
						if (cursor.getString(NAME_INDEX).equals(phoneNames[0])) {
							arePhonesInOrder = true;
						}
						//i+1 because we don't want the first column (the id)
						attributeArray.get(i).attribute =
								AttFilter.makeAttPresentable(cursor.getColumnName(i + 1));
					}
					setAttributes(arePhonesInOrder, phoneIndex, i, cursor.getString(i + 1));
				}
				setPhoneUri(arePhonesInOrder, phoneIndex, cursor.getString(0));
				phoneIndex++;
			}
			cursor.close();
			
			//Can be turned into a method
			int imageRes = getResources().getIdentifier(phone1Uri, "drawable", getPackageName());
			ImageView phone1ImgView = (ImageView)findViewById(R.id.comparePhoneImg1);
			Drawable res = getResources().getDrawable(imageRes);
			phone1ImgView.setImageDrawable(res);
			
			imageRes = getResources().getIdentifier(phone2Uri, "drawable", getPackageName());	
			ImageView phone2ImgView = (ImageView)findViewById(R.id.comparePhoneImg2);
			res = getResources().getDrawable(imageRes);
			phone2ImgView.setImageDrawable(res);
			
			AttributeAdapter adapter = new AttributeAdapter(this, attributeArray);
			
			// Attach the adapter to a ListView
			ListView compareListView = (ListView)findViewById(R.id.compareListView);
			compareListView.setAdapter(adapter);   
		}
	}

	private void setPhoneUri(boolean arePhonesInOrder, int phoneIndex, String cursorString) {
		if ((arePhonesInOrder && phoneIndex == 0) || (!arePhonesInOrder && phoneIndex != 0)) {
			phone1Uri += cursorString;
		} else {
			phone2Uri += cursorString;
		}
	}

	private void setAttributes(boolean arePhonesInOrder, int phoneIndex, int i, String cursorString) {
		if ((arePhonesInOrder && phoneIndex == 0) || (!arePhonesInOrder && phoneIndex != 0)) {
			attributeArray.get(i).phone1attribute =
					AttFilter.filterChars(cursorString, attributeArray.get(i).attribute);
		} else {
			attributeArray.get(i).phone2attribute =
					AttFilter.filterChars(cursorString, attributeArray.get(i).attribute);
		}
	}
}
