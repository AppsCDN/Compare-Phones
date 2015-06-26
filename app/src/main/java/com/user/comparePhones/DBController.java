package com.user.comparePhones;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


//Meant to be a static class.
public class DBController {
	public static SQLiteDatabase phoneDB;
	
	private DBController() {
	}
	
	 public static Cursor searchPhone(String searchText) throws SQLException {
		 String fullQuery = "SELECT _id, Name FROM PhoneTable WHERE Name LIKE '%" +searchText+ "%'";

		 Cursor mCursor = phoneDB.rawQuery(fullQuery, null);
		 
		 if (mCursor != null) {
			 mCursor.moveToFirst();
		 }
		 return mCursor;
	 }
	 	
	 public static Cursor searchPhone(String searchText, ButtonManager buttonManager) throws SQLException {
		 String rawQuery = queryBuilder(buttonManager);
		 String queryStart = "SELECT _id, Name FROM PhoneTable WHERE Name LIKE '%" +searchText+ "%'";
		 String fullQuery = queryStart + rawQuery;
		 
		 Cursor mCursor = phoneDB.rawQuery(fullQuery, null);
		 
		 if (mCursor != null) {
			 mCursor.moveToFirst();
		 }
		 return mCursor;
	 }
	 
	 public static Cursor searchFullPhones(String searchText, String searchText2) throws SQLException {
		 String fullQuery = "SELECT * FROM PhoneTable WHERE Name='" 
				 +searchText+ "' OR Name='" +searchText2+ "'";

		 Cursor mCursor = phoneDB.rawQuery(fullQuery, null);
		 
		 if (mCursor != null) {
			 mCursor.moveToFirst();
		 }
		 return mCursor;
	 }
	 
	 public static String checkFirstCondition(boolean isFirst) {
		 if (isFirst) {
			 return "AND(";
		 }
		 return "OR ";
	 }
	 
	 public static String queryBuilder(ButtonManager buttonManager) {
		 StringBuilder sb = new StringBuilder();
		 String SPACE = " ";
		 String END = ";";
		 boolean isFirstOS = true;
		 boolean isFirstManu = true;
		 boolean isFirstScreenSize = true;
		 
		 if (buttonManager.isToggledAndroid) { 
			 sb.append(SPACE);
			 sb.append(checkFirstCondition(isFirstOS));
			 sb.append("OS LIKE '%Android%'");
			 
			 isFirstOS = false;
		 }	 
		 if (buttonManager.isTogglediOS) { 
			 sb.append(SPACE);
			 sb.append(checkFirstCondition(isFirstOS));
			 sb.append("OS LIKE '%iOS%'");
			 
			 isFirstOS = false;
		 }
		 if (buttonManager.isToggledBBOS) {
			 sb.append(SPACE);
			 sb.append(checkFirstCondition(isFirstOS));
			 sb.append("OS LIKE '%BlackBerry%'");
			 
			 isFirstOS = false;
		 }
		 if (buttonManager.isToggledWinOS) { 
			 sb.append(SPACE);
			 sb.append(checkFirstCondition(isFirstOS));
			 sb.append("OS LIKE '%Windows%'");
			 
			 isFirstOS = false;
		 }
		 
		 if (buttonManager.isToggledOtherOS) { 
			 sb.append(SPACE);
			 sb.append(checkFirstCondition(isFirstOS));
			 sb.append("(OS NOT LIKE '%Android%' AND OS NOT LIKE '%iOS%' AND OS NOT LIKE '%BlackBerry%' AND OS NOT LIKE '%Windows%')");
			 
			 isFirstOS = false;
		 }
		 if (!isFirstOS) {
			 sb.append(")");
		 }
		 
		 if (buttonManager.isToggledSamsung) { 
			 sb.append(SPACE);
			 sb.append(checkFirstCondition(isFirstManu));
			 sb.append("Maker='Samsung'");
			 
			 isFirstManu = false;
		 }
		 if (buttonManager.isToggledHTC) {
			 sb.append(SPACE);
			 sb.append(checkFirstCondition(isFirstManu));
			 sb.append("Maker='HTC'");
			 
			 isFirstManu = false;
		 }
		 if (buttonManager.isToggledApple) { 
			 sb.append(SPACE);
			 sb.append(checkFirstCondition(isFirstManu));
			 sb.append("Maker='Apple'");
			 
			 isFirstManu = false;
		 }
		 if (buttonManager.isToggledBlackBerry) { 
			 sb.append(SPACE);
			 sb.append(checkFirstCondition(isFirstManu));
			 sb.append("Maker='BlackBerry'");
			 
			 isFirstManu = false;
		 }
		 if (buttonManager.isToggledNokia) { 
			 sb.append(SPACE);
			 sb.append(checkFirstCondition(isFirstManu));
			 sb.append("Maker='Nokia'");
			 
			 isFirstManu = false;
		 }
		 if (buttonManager.isToggledMotorola) { 
			 sb.append(SPACE);
			 sb.append(checkFirstCondition(isFirstManu));
			 sb.append("Maker='Motorola'");
			 
			 isFirstManu = false;
		 }
		 if (buttonManager.isToggledSony) { 
			 sb.append(SPACE);
			 sb.append(checkFirstCondition(isFirstManu));
			 sb.append("Maker='Sony'");
			 
			 isFirstManu = false;
		 }
		 if (buttonManager.isToggledLG) { 
			 sb.append(SPACE);
			 sb.append(checkFirstCondition(isFirstManu));
			 sb.append("Maker='LG'");
			 
			 isFirstManu = false;
		 }
		 if (!isFirstManu) {
			 sb.append(")");
		 }
		 
		 if (buttonManager.isToggledSmall) { 
			 sb.append(SPACE);
			 sb.append(checkFirstCondition(isFirstScreenSize));
			 
			 sb.append("(DisplaySize LIKE '%1._ in%' OR DisplaySize LIKE '%1.__ in%')");
			 sb.append(SPACE);
			 sb.append("OR (DisplaySize LIKE '%2._ in%' OR DisplaySize LIKE '%2.__ in%')");
			 sb.append(SPACE);
			 sb.append("OR (DisplaySize LIKE '%3._ in%' OR DisplaySize LIKE '%3.__ in%')");
			 
			 isFirstScreenSize = false;
		 }
		 if (buttonManager.isToggledMedium) { 
			 sb.append(SPACE);
			 sb.append(checkFirstCondition(isFirstScreenSize));
			 sb.append("(DisplaySize LIKE '%4._ in%' OR DisplaySize LIKE '%4.__ in%')");
			 
			 isFirstScreenSize = false;
		 }
		 if (buttonManager.isToggledLarge) { 
			 sb.append(SPACE);
			 sb.append(checkFirstCondition(isFirstScreenSize));
			 
			 sb.append("(DisplaySize LIKE '%5._ in%' OR DisplaySize LIKE '%5.__ in%')");
			 sb.append(SPACE);
			 sb.append("OR (DisplaySize LIKE '%6._ in%' OR DisplaySize LIKE '%6.__ in%')");
			 
			 isFirstScreenSize = false;
		 }	 
		 //No phones are allowed if no screen size.
		 if (isFirstScreenSize) {
			 sb.append(SPACE);
			 sb.append("AND 1 = 2");
		 }
		 else {
			 sb.append(")");
		 }
		 
		 sb.append(END);
		 
		 return sb.toString();
	 }
}
