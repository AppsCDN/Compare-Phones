package com.user.comparePhones;

public class PhoneAttribute {
	private String attribute;
	private String data1;
	private String data2;
	
	public PhoneAttribute(String attribute, String data1, String data2) {
		this.attribute = attribute;
		this.data1 = data1;
		this.data2 = data2;
	}

	public String getAttribute() {
		return attribute;
	}

	public String getData1() {
		return data1;
	}

	public String getData2() {
		return data2;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public void setData1(String data1) {
		this.data1 = data1;
	}

	public void setData2(String data2) {
		this.data2 = data2;
	}
}
