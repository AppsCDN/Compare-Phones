package com.user.comparePhones;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.database.Cursor;
import android.database.SQLException;

public class DBInitializer extends SQLiteOpenHelper {
	
	private static String TAG = "DBInitalizer";
	private static String DB_PATH = "";
	private static String DB_NAME = "PhoneScrapeDB6";
	private SQLiteDatabase database;
	private Context context;

	public DBInitializer(Context context) {
		super(context, DB_NAME, null, 1);
		
		if (android.os.Build.VERSION.SDK_INT >= 17) {
			DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
		} else {
			DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";		
		}
		this.context = context;
	}
	
	public void createDB() throws IOException {
		boolean doesDBExist = checkDB();
		
		if (!doesDBExist) { 
			this.getReadableDatabase();
			this.close();
			
			try {
				copyDB();
				Log.e(TAG, "PhoneDBCreated");
			} catch (IOException mIOException) {
				throw new Error("Error copying DB");
			}
		}
	}
	
	private boolean checkDB() {
		File dbFile = new File(DB_PATH + DB_NAME);
		return dbFile.exists();
	}
	
	private void copyDB() throws IOException {
		InputStream input = context.getAssets().open(DB_NAME);
		String outFileName = DB_PATH + DB_NAME;
		OutputStream output = new FileOutputStream(outFileName);
		
		byte[] mBuffer = new byte[1024];
		int mLength;
		while ((mLength = input.read(mBuffer)) > 0) {
			output.write(mBuffer, 0, mLength);
		}
		
		output.flush();
		output.close();
		input.close();
	}
	
	public SQLiteDatabase openDataBase() throws SQLException {
		String path = DB_PATH + DB_NAME;
		database = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY | SQLiteDatabase.NO_LOCALIZED_COLLATORS);
		return database;
	}

	@Override
	public synchronized void close() {
		if (database != null) {
			database.close();
		}
		super.close();
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
}
