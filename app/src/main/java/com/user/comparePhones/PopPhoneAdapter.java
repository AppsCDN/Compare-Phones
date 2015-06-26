package com.user.comparePhones;

import java.util.ArrayList;

import com.user.comparePhones.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PopPhoneAdapter extends ArrayAdapter<String> {
	private Context context;
	private LayoutInflater inflater;
	private ArrayList<Integer> indexes;
    public PopPhoneAdapter(Context context, ArrayList<String> phones, ArrayList<Integer> indexes) {
       super(context, R.layout.popularphone, phones);
       inflater = LayoutInflater.from(context);
       this.context = context;
       this.indexes = indexes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       String phone = getItem(position);    
       
       // Check if an existing view is being reused, otherwise inflate the view
       if (convertView == null) {
          convertView = LayoutInflater.from(getContext()).inflate(R.layout.popularphone, parent, false);
       }

       TextView popPhoneTv = (TextView) convertView.findViewById(R.id.popPhoneText);
       ImageView popPhoneIv = (ImageView) convertView.findViewById(R.id.popPhoneImg);

       popPhoneTv.setText(phone);
       
       String phoneuri = "img" + indexes.get(position);

       int imageResource = context.getResources().getIdentifier(phoneuri, "drawable", context.getPackageName());
	   Drawable res = context.getResources().getDrawable(imageResource);
	   popPhoneIv.setImageDrawable(res);

       if (position % 2 == 0) {
    	   convertView.setBackgroundColor(0xEE363636);   
       } else {
    	   convertView.setBackgroundColor(0xEE282828);
       }
       
       return convertView;
   }
}
