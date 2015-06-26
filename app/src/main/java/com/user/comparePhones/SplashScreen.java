package com.user.comparePhones;

import java.io.IOException;

import com.user.comparePhones.R;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.os.Handler;
 
public class SplashScreen extends Activity {
    private static int SPLASH_TIME_OUT = 1000;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	getActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setupDB();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
			Intent i = new Intent(SplashScreen.this, MainActivity.class);
			startActivity(i);
			finish();
            }
        }, SPLASH_TIME_OUT);
    }
    
	private void setupDB() {
		DBInitializer DBi = new DBInitializer(getApplicationContext());
		
		try {
			DBi.createDB();	
		} catch (IOException io) {
			throw new Error("Unable to create DB");
		}
		
		try {
			DBController.phoneDB = DBi.openDataBase();
		} catch (SQLException sqle) {
			throw sqle;
		}
	}
 
}