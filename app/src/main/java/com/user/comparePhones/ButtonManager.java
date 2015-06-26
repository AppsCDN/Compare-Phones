package com.user.comparePhones;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ButtonManager implements Serializable {
	boolean isToggledAndroid;
	boolean isTogglediOS;
	boolean isToggledBBOS; 
	boolean isToggledWinOS;
	boolean isToggledOtherOS;
	
	boolean isToggledSamsung;
	boolean isToggledHTC;
	boolean isToggledApple;
	boolean isToggledBlackBerry;
	boolean isToggledNokia;
	
	boolean isToggledMotorola;
	boolean isToggledSony;
	boolean isToggledLG;
	
	boolean isToggledSmall;
	boolean isToggledMedium;
	boolean isToggledLarge;
	
	public ButtonManager(boolean isToggledAndroid, boolean isTogglediOS,
					     boolean isToggledBBOS, boolean isToggledWinOS,
						 boolean isToggledOtherOS, boolean isToggledSamsung,
						 boolean isToggledHTC,	 boolean isToggledApple,
						 boolean isToggledBlackBerry, boolean isToggledNokia,
						 boolean isToggledMotorola,	boolean isToggledSony,
						 boolean isToggledLG, 
						 boolean isToggledSmall, boolean isToggledMedium,
						 boolean isToggledLarge) 
	{
		
		this.isToggledAndroid = isToggledAndroid;
		this.isTogglediOS = isTogglediOS;
		this.isToggledBBOS = isToggledBBOS;
		this.isToggledWinOS = isToggledWinOS;
		this.isToggledOtherOS = isToggledOtherOS;
		 
		this.isToggledSamsung = isToggledSamsung;
		this.isToggledHTC = isToggledHTC;
		this.isToggledApple = isToggledApple;
		this.isToggledBlackBerry = isToggledBlackBerry;
		this.isToggledNokia = isToggledNokia;
		 
		this.isToggledMotorola = isToggledMotorola;
		this.isToggledSony = isToggledSony;
		this.isToggledLG = isToggledLG;

		this.isToggledSmall = isToggledSmall;
		this.isToggledMedium = isToggledMedium;
		this.isToggledLarge = isToggledLarge;
	}
}
