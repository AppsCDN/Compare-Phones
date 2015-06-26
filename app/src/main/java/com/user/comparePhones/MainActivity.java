package com.user.comparePhones;

import java.util.ArrayList;

import com.user.comparePhones.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import com.google.android.gms.ads.*;

public class MainActivity extends Activity {
	private int currentPhone;
	private ListView listView;
	private Button buttonPhone1;
	private Button buttonPhone2;
	private Button browseButton;
	private Button compareButton;
	private LinearLayout mainView;

	private InterstitialAd interstitial;
	
	private ButtonManager mButtonManager = null;
	
	final int lightBlue = 0xff6DDAEC;
	final int androidBlue = 0xff33B5E5;
	
	final int lightGray = 0xFF606060;
	final int darkGray = 0xFF4A4A4A;
	final int darkestGray = 0xFF353535;
	
	final int BROWSE_REQUEST = 42;
	final int PHONE_ONE = 1;
	final int PHONE_TWO = 2;


	/* onCreate()
	*
	*  Entry point of the app.
	*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		//Set action bar blue
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#33B5E5")));
		
		interstitial = new InterstitialAd(this);
		interstitial.setAdUnitId("ca-app-pub-1856830082036668/2923187038");
		AdRequest adRequest = new AdRequest.Builder().build();
		interstitial.loadAd(adRequest);
			
		listView = (ListView)findViewById(R.id.popPhoneListView);
    	buttonPhone1 = (Button)findViewById(R.id.phone1Title);
    	buttonPhone2 = (Button)findViewById(R.id.phone2Title);
    	browseButton = (Button)findViewById(R.id.browseButton);
    	compareButton = (Button)findViewById(R.id.compareButton);
    	mainView = (LinearLayout)findViewById(R.id.mainView);
    	
    	currentPhone = PHONE_ONE;

		setTouchListeners();

		PopPhoneAdapter popPhones = new PopPhoneAdapter(this,
				PopPhoneManager.getStrings(),
				PopPhoneManager.getIndexes());
		
		if (popPhones != null) {
			listView.setAdapter(popPhones);
			listView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					String phoneName = (String)listView.getItemAtPosition(position);
					setPhoneName(currentPhone, phoneName);
				}
			});
		}
	}

	/* setTouchListeners()
	*
	*  Sets the touch listeners and assigns the appropriate logic to each button
	*  including setting background colors/setting which button is the currently
	*  selected phone.
	*/
	private void setTouchListeners() {
		buttonPhone1.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch (View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					buttonPhone1.setBackgroundColor(lightGray);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					buttonPhone1.setBackgroundColor(darkGray);
					buttonPhone2.setBackgroundColor(darkestGray);
				}
				currentPhone = PHONE_ONE;
				return true;
			}
		});

		buttonPhone2.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch (View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					buttonPhone2.setBackgroundColor(lightGray);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					buttonPhone2.setBackgroundColor(darkGray);
					buttonPhone1.setBackgroundColor(darkestGray);
				}
				currentPhone = PHONE_TWO;
				return true;
			}
		});

		browseButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch (View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					browseButton.setBackgroundColor(lightBlue);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					browseButton.setBackgroundColor(androidBlue);
					browsePhones(mainView);
				}
				return true;
			}
		});

		compareButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch (View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					compareButton.setBackgroundColor(lightBlue);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					compareButton.setBackgroundColor(androidBlue);
					comparePhones(mainView);
				}
				return true;
			}
		});
	}

	/* setPhoneName()
	*
	*  Changes a phone button's string.
    */
	private void setPhoneName(int currentPhone, String phoneName) {
		switch(currentPhone) {
			case PHONE_ONE:
				buttonPhone1.setText(phoneName);
				break;
			case PHONE_TWO:
				buttonPhone2.setText(phoneName);
				break;	
		}	
	}

	/* browsePhones()
	*
	*  Navigates to the Browse activity.
	*/
	private void browsePhones(View view) {
		Intent browseIntent = new Intent(this, BrowseActivity.class);
		if (mButtonManager != null) {
			browseIntent.putExtra("buttonManager", mButtonManager);
		}
		startActivityForResult(browseIntent, BROWSE_REQUEST);
	}

	/* comparePhones()
	*
	*  Navigates to the Compare activity.
	*/
	private void comparePhones(View view) {
		Intent compareIntent = new Intent(this, CompareActivity.class);
		TextView phone1TV = (TextView)findViewById(R.id.phone1Title);
		TextView phone2TV = (TextView)findViewById(R.id.phone2Title);
		
		String phone1 = phone1TV.getText().toString();
		String phone2 = phone2TV.getText().toString();
		
		compareIntent.putExtra("Phones", new String[]{phone1, phone2});
		startActivity(compareIntent);
		displayInterstitial();
 		interstitial.loadAd(new AdRequest.Builder().build());
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == BROWSE_REQUEST) {
			final int RESULT_BACK = 50;

			if (resultCode == Activity.RESULT_OK) {
				String phoneName = data.getStringExtra("Phone");
				setPhoneName(currentPhone, phoneName);
				mButtonManager = (ButtonManager)data.getSerializableExtra("buttonManager");
			} else if (resultCode == RESULT_BACK) {
				mButtonManager = (ButtonManager)data.getSerializableExtra("buttonManager");
			}
		}
	}
	
	public void displayInterstitial() {
		if (interstitial.isLoaded()) {
			interstitial.show();
		}
	}
}
