package com.user.comparePhones;

import java.util.ArrayList;

//Temp hardcoded
public class PopPhoneManager {
    public static ArrayList<String> getStrings() {
        ArrayList<String> phones = new ArrayList<String>();

        phones.add("LG Nexus 5");
        phones.add("Motorola Nexus 6");
        phones.add("LG G3");
        phones.add("Apple iPhone 6");
        phones.add("Apple iPhone 6 Plus");

        phones.add("Samsung Galaxy S6");
        phones.add("Samsung Galaxy Note 4");
        phones.add("HTC One (M8)");
        phones.add("HTC One");
        phones.add("BlackBerry Z30");

        phones.add("BlackBerry Q10");
        phones.add("BlackBerry Passport");
        phones.add("Motorola Moto X (2014)");
        phones.add("Sony Xperia Z3");

        return phones;
    }

    public static ArrayList<Integer> getIndexes() {
        ArrayList<Integer> indexes = new ArrayList<Integer>();

        indexes.add(919);
        indexes.add(603);
        indexes.add(891);
        indexes.add(1086);
        indexes.add(1085);

        indexes.add(265);
        indexes.add(289);
        indexes.add(1144);
        indexes.add(1169);
        indexes.add(1260);

        indexes.add(1264);
        indexes.add(1256);
        indexes.add(613);
        indexes.add(814);

        return indexes;
    }
}
