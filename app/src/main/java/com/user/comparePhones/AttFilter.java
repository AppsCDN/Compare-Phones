package com.user.comparePhones;

/* AttFilter
 *
 * Filters raw data from the database into presentable data for the user
 */
public class AttFilter {

	/* String filterChars(String desc, String attribute)
	 *
	 * Fixes an odd sequence of chars that pops up in the dimensions column of
	 * the database.
	 */
	public static String filterChars(String desc, String attribute) {
		return desc.replace("&#", "x ");
	}

	/* String makeAttPresentable(String attribute)
	 *
	 * Fixes some attribute names because the raw string from the database column
	 * header might not have a space if there are more than 2 words.
	 */
	public static String makeAttPresentable(String attribute) {
		switch (attribute) {
			case "CurrentStatus":
				return "Current Status";
			case "DisplaySize":
				return "Display Size";
			case "FirstCamera":
				return "First Camera";
			case "SecondCamera":
				return "Second Camera";
			case "Cardslot":
				return "Card Slot";
			case "InternalMemory":
				return "Internal Memory";
			case "BatteryType":
				return "Battery Type";
			case "Standby":
				return "Standby Time";
			case "TalkTime":
				return "Talk Time";
			case "Musicplay":
				return "Music Play Time";
			case "USBPort":
				return "USB Port";
			case "Camerafeatures":
				return "Camera Features";
			case "EUROPrice":
				return "Price (EURO)";
		}
		return attribute;
	}
}
