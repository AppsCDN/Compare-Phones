package com.user.comparePhones;

import java.util.ArrayList;

import com.user.comparePhones.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AttributeAdapter extends ArrayAdapter<PhoneAttribute> {
    public AttributeAdapter(Context context, ArrayList<PhoneAttribute> attributes) {
       super(context, R.layout.compareattribute, attributes);
    }
    
    final int gray = 0xEE363636;
    final int darkGray = 0xEE282828; 
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       PhoneAttribute attribute = getItem(position);    
       
       // Check if an existing view is being reused, otherwise inflate the view
       if (convertView == null) {
          convertView = LayoutInflater.from(
                  getContext()).inflate(R.layout.compareattribute, parent, false);
       }

       TextView tvAttributeName = (TextView) convertView.findViewById(R.id.phoneAttributeName);
       TextView tvAttribute1 = (TextView) convertView.findViewById(R.id.phoneAttribute1);
       TextView tvAttribute2 = (TextView) convertView.findViewById(R.id.phoneAttribute2);

       tvAttributeName.setText(attribute.attribute);
       tvAttribute1.setText(attribute.phone1attribute);
       tvAttribute2.setText(attribute.phone2attribute);

       if (position % 2 == 0) {
    	   convertView.setBackgroundColor(gray);   
       } else {
    	   convertView.setBackgroundColor(darkGray);
       }
       
       return convertView;
   }
}
