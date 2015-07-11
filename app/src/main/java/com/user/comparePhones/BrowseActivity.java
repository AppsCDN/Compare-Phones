package com.user.comparePhones;

import com.user.comparePhones.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.ToggleButton;

/* BrowseActivity
*
*  Activity to display browsed phones
*/
public class BrowseActivity extends Activity implements SearchView.OnQueryTextListener,
SearchView.OnCloseListener {
	private ListView listView;
	private SearchView searchView;
	
	private ToggleButton toggleAndroid;
	private ToggleButton toggleiOS;
	private ToggleButton toggleBBOS;
	private ToggleButton toggleWinOS;
	private ToggleButton toggleOtherOS;
	
	private ToggleButton toggleSamsung;
	private ToggleButton toggleHTC;
	private ToggleButton toggleApple;
	private ToggleButton toggleBlackBerry;
	private ToggleButton toggleNokia;
	private ToggleButton toggleMotorola;
	private ToggleButton toggleSony;
	private ToggleButton toggleLG;
	
	private ToggleButton toggleSmall;
	private ToggleButton toggleMedium;
	private ToggleButton toggleLarge;
	
	private ButtonManager mButtonManager = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse);
		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#33B5E5")));
		
		Intent intent = getIntent();

		//Deserialize the previous Button states so the user doesn't have to toggle them again
		mButtonManager = (ButtonManager) intent.getSerializableExtra("buttonManager");

		//Initialize and set SearchView Listeners
		searchView = (SearchView)findViewById(R.id.searchView);
		searchView.setOnQueryTextListener(this);
		searchView.setOnCloseListener(this);
		
		LinearLayout ll = (LinearLayout)findViewById(R.id.llSearch);
		listView = (ListView)ll.findViewById(R.id.list);

        TabHost tabHost=(TabHost)findViewById(R.id.tabHost);
        tabHost.setup();
      
        TabSpec spec1=tabHost.newTabSpec("TAB 1");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("OS");
        TabSpec spec2=tabHost.newTabSpec("TAB 2");
        spec2.setIndicator("Maker");
        spec2.setContent(R.id.tab2);
        TabSpec spec3=tabHost.newTabSpec("TAB 3");
        spec3.setContent(R.id.tab3);
        spec3.setIndicator("Screen Size");
        tabHost.addTab(spec1);
        tabHost.addTab(spec2);
        tabHost.addTab(spec3);
        
    	toggleAndroid = (ToggleButton)findViewById(R.id.toggleAndroid);
		toggleiOS = (ToggleButton)findViewById(R.id.toggleiOS);
		toggleBBOS = (ToggleButton)findViewById(R.id.toggleBBOS);
		toggleWinOS = (ToggleButton)findViewById(R.id.toggleWinOS);
		toggleOtherOS = (ToggleButton)findViewById(R.id.toggleOtherOS);
		
		toggleSamsung = (ToggleButton)findViewById(R.id.toggleSamsung);
		toggleHTC = (ToggleButton)findViewById(R.id.toggleHTC);
		toggleApple = (ToggleButton)findViewById(R.id.toggleApple);
		toggleBlackBerry = (ToggleButton)findViewById(R.id.toggleBlackBerry);
		toggleNokia = (ToggleButton)findViewById(R.id.toggleNokia);
		
		toggleMotorola = (ToggleButton)findViewById(R.id.toggleMotorola2);
		toggleSony = (ToggleButton)findViewById(R.id.toggleSony);
		toggleLG = (ToggleButton)findViewById(R.id.toggleLG);
		
		toggleSmall = (ToggleButton)findViewById(R.id.toggleSmall);
		toggleMedium = (ToggleButton)findViewById(R.id.toggleMedium);
		toggleLarge = (ToggleButton)findViewById(R.id.toggleLarge);
		
		if (mButtonManager != null) {
			setToggleButtonsFromPrevBM();
		}
	}
		
	public boolean onQueryTextChange(String newText) {
        showResults(newText);
        return false;
    }
 
    public boolean onQueryTextSubmit(String query) {
        showResults(query);
        return false;
    }
 
    public boolean onClose() {
        showResults("");
        return false;
    }
    
    private void setFromPrevBM(boolean isToggled, ToggleButton button) {
    	if(isToggled) {
    		button.setChecked(true);
    	} else {
    		button.setChecked(false);
    	}
    }
    
    private void setToggleButtonsFromPrevBM() {
    	setFromPrevBM(mButtonManager.isToggledAndroid, toggleAndroid);
    	setFromPrevBM(mButtonManager.isTogglediOS, toggleiOS);
    	setFromPrevBM(mButtonManager.isToggledBBOS, toggleBBOS);
    	setFromPrevBM(mButtonManager.isToggledWinOS, toggleWinOS);
    	setFromPrevBM(mButtonManager.isToggledOtherOS, toggleOtherOS);
    	
    	setFromPrevBM(mButtonManager.isToggledSamsung, toggleSamsung);
    	setFromPrevBM(mButtonManager.isToggledHTC, toggleHTC);
    	setFromPrevBM(mButtonManager.isToggledApple, toggleApple);
    	setFromPrevBM(mButtonManager.isToggledBlackBerry, toggleBlackBerry);
    	setFromPrevBM(mButtonManager.isToggledNokia, toggleNokia);
    	
    	setFromPrevBM(mButtonManager.isToggledMotorola, toggleMotorola);
    	setFromPrevBM(mButtonManager.isToggledSony, toggleSony);
    	setFromPrevBM(mButtonManager.isToggledLG, toggleLG);
    	
    	setFromPrevBM(mButtonManager.isToggledSmall, toggleSmall);
    	setFromPrevBM(mButtonManager.isToggledMedium, toggleMedium);
    	setFromPrevBM(mButtonManager.isToggledLarge, toggleLarge);
    }
    
    private void showResults(String query) {		
    	setNewButtonManager();
    	Cursor cursor = DBController.searchPhone(query, mButtonManager);
    	
    	if (cursor == null) {
			//nothing
		} else {
	        String[] from = new String[] { "Name" };   
	        int[] to = new int[] { R.id.projectionResult };
	           
			SimpleCursorAdapter phones = new SimpleCursorAdapter(this, R.xml.phoneprojection, cursor, from, to, 1);
			if (query.equals("")) {
				listView.setAdapter(null);
			} else {
				listView.setAdapter(phones);
				listView.setOnItemClickListener(new OnItemClickListener() {
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
					{
						Cursor cursor = (Cursor)listView.getItemAtPosition(position);
						
						String name = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
						
						Intent intent = new Intent();
						intent.putExtra("Phone", name);
						intent.putExtra("buttonManager", mButtonManager);
						setResult(Activity.RESULT_OK, intent);	
						cursor.close();
						finish();
					}
				});
			}
    	}
    }

	/* void setNewButtonManager()
	 *
	 * Set the default mButtonManager object to the current state of the buttons
	 */
    private void setNewButtonManager() {
    	mButtonManager = new ButtonManager(
        	toggleAndroid.isChecked(), toggleiOS.isChecked(), toggleBBOS.isChecked(), 
        	toggleWinOS.isChecked(), toggleOtherOS.isChecked(),
        	
        	toggleSamsung.isChecked(), toggleHTC.isChecked(), toggleApple.isChecked(), 
        	toggleBlackBerry.isChecked(), toggleNokia.isChecked(), toggleMotorola.isChecked(), 
        	toggleSony.isChecked(), toggleLG.isChecked(), 
        	
        	toggleSmall.isChecked(), toggleMedium.isChecked(), toggleLarge.isChecked());
    }

	/* void onBackPressed()
	 *
	 * Right now used for saving the state of the buttons so the user does not
	 * have to press each one again
	 */
    @Override
    public void onBackPressed() {
    	final int RESULT_BACK = 50;
		setNewButtonManager();
    	
    	Intent intent = new Intent();
		intent.putExtra("buttonManager", mButtonManager);
		setResult(RESULT_BACK, intent);	
		finish();
    }
}
