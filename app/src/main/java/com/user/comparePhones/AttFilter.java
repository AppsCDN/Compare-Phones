package com.user.comparePhones;

//Takes the names from the database and makes the names prettier for the view
public class AttFilter {
	public static String makeAttPresentable(String attribute) {
		if (attribute.equals("CurrentStatus")) {
			return "Current Status";
		} else if (attribute.equals("DisplaySize")) {
			return "Display Size";
		} else if (attribute.equals("FirstCamera")) {
			return "First Camera";
		} else if (attribute.equals("SecondCamera")) {
			return "Second Camera";
		} else if (attribute.equals("Cardslot")) {
			return "Card Slot";
		} else if (attribute.equals("InternalMemory")) {
			return "Internal Memory";
		} else if (attribute.equals("BatteryType")) {
			return "Battery Type";
		} else if (attribute.equals("Standby")) {
			return "Standby Time";
		} else if (attribute.equals("TalkTime")) {
			return "Talk Time";
		} else if (attribute.equals("Musicplay")) {
			return "Music Play Time";
		} else if (attribute.equals("USBPort")) {
			return "USB Port";
		} else if (attribute.equals("Camerafeatures")) {
			return "Camera Features";
		} else if (attribute.equals("EUROPrice")) {
			return "Price (EURO)";
		} else if (attribute.equals("USDPrice")) {
			return "Price (USD)";
		}
		return attribute;
	}
	
	public static String filterChars(String desc, String attribute) {
		if (attribute.equals("Price (EURO)")) {
			return desc;
		}
		if (attribute.equals("Price (USD)")) {
			return "$" + desc;
		}
		
		desc.replace("&#", "x ");
		return desc;
	}
}
