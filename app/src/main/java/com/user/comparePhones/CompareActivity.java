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

/* CompareActivity
 *
 * An activity to compare phones.
 */
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
			int numAttributes = cursor.getColumnCount();
			
			//numColumns - 1 because we don't include ID
			for (int i = 0; i < numAttributes - 1; i++) {
				PhoneAttribute attribute = new PhoneAttribute("N/A", "N/A", "N/A");
				attributeArray.add(attribute);
			}
			
			int phoneIndex = 0;
			boolean arePhonesInOrder = false;
			final int NAME_INDEX = 2;

			//Initialize the cursor
			cursor.moveToPosition(-1);

			//This while loop will only loop twice currently, once for each phone.
			while (cursor.moveToNext()) {

				// This for loop will loop over all the attributes in the database.
				for (int i = 0; i < numAttributes - 1; i++) {
					if (phoneIndex == 0) {
						if (cursor.getString(NAME_INDEX).equals(phoneNames[0])) {
							arePhonesInOrder = true;
						}
						//i+1 because we don't want the first column (the id)
						attributeArray.get(i).setAttribute(
								AttFilter.makeAttPresentable(cursor.getColumnName(i + 1)));
					}
					setAttributes(arePhonesInOrder, phoneIndex, i, cursor.getString(i + 1));
				}
				setPhoneUri(arePhonesInOrder, phoneIndex, cursor.getString(0));
				phoneIndex++;
			}
			cursor.close();
			
			//Setting phone imgs
			int imageResPhone1 = getResources().getIdentifier(phone1Uri, "drawable", getPackageName());
			setPhoneImage(imageResPhone1, 0);
			int imageResPhone2 = getResources().getIdentifier(phone2Uri, "drawable", getPackageName());
			setPhoneImage(imageResPhone2, 1);

			//Creating the adapter with the array of attributes
			AttributeAdapter adapter = new AttributeAdapter(this, attributeArray);
			
			// Attach the adapter to a ListView
			ListView compareListView = (ListView)findViewById(R.id.compareListView);
			compareListView.setAdapter(adapter);   
		}
	}

	/* void setPhoneImage(int imageRes, int phoneIndex)
	 *
	 * Sets the phone image by finding the appropriate ImageView and
	 * setting the drawable.
	 */
	private void setPhoneImage(int imageRes, int phoneIndex) {
		ImageView imageView;
		if (phoneIndex == 0) {
			imageView = (ImageView) findViewById(R.id.comparePhoneImg1);
		} else {
			imageView = (ImageView) findViewById(R.id.comparePhoneImg2);
		}

		Drawable res = getResources().getDrawable(imageRes);
		imageView.setImageDrawable(res);
	}

	/* void setPhoneUri(boolean arePhonesInOrder, int phoneIndex, String cursorString)
	 *
	 * Sets the Phone Uri, involves checking which phone is grabbed first from the database
	 * so that it can assign the correct image to each phone.
	 */
	private void setPhoneUri(boolean arePhonesInOrder, int phoneIndex, String cursorString) {
		if ((arePhonesInOrder && phoneIndex == 0) || (!arePhonesInOrder && phoneIndex != 0)) {
			phone1Uri += cursorString;
		} else {
			phone2Uri += cursorString;
		}
	}

	/* void setAttributes(boolean arePhonesInOrder, int phoneIndex, int i, String cursorString
	 *
	 * Sets the phone data attributes, involves checking which phone is grabbed first from the database
	 * so that it can assign the correct attributes to each phone.
 	 */
	private void setAttributes(boolean arePhonesInOrder, int phoneIndex, int i, String cursorString) {
		if ((arePhonesInOrder && phoneIndex == 0) || (!arePhonesInOrder && phoneIndex != 0)) {
			attributeArray.get(i).setData1(AttFilter.filterChars(cursorString,
					attributeArray.get(i).getAttribute()));

		} else {
			attributeArray.get(i).setData2(AttFilter.filterChars(cursorString,
					attributeArray.get(i).getAttribute()));
		}
	}
}
