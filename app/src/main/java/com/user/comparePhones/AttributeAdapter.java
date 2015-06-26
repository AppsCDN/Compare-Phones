package com.user.comparePhones;

import java.util.ArrayList;

import com.user.comparePhones.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


/* AttributeAdapter
 *
 * This adapter is attached to a ListView to display the data
 * and attributes of each phone.
 */
public class AttributeAdapter extends ArrayAdapter<PhoneAttribute> {
    public AttributeAdapter(Context context, ArrayList<PhoneAttribute> attributes) {
       super(context, R.layout.compareattribute, attributes);
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PhoneAttribute attribute = getItem(position);
       
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
             convertView = LayoutInflater.from(
                 getContext()).inflate(R.layout.compareattribute, parent, false);
        }

        TextView tvAttributeName = (TextView) convertView.findViewById(R.id.phoneAttributeName);
        TextView tvData1 = (TextView) convertView.findViewById(R.id.phoneData1);
        TextView tvData2 = (TextView) convertView.findViewById(R.id.phoneData2);

        tvAttributeName.setText(attribute.attribute);
        tvData1.setText(attribute.data1);
        tvData2.setText(attribute.data2);

        //Zebra stripes for the ListView
        final int gray = 0xEE363636;
        final int darkGray = 0xEE282828;

        if (position % 2 == 0) {
    	    convertView.setBackgroundColor(gray);
        } else {
    	    convertView.setBackgroundColor(darkGray);
        }
       
        return convertView;
    }
}
