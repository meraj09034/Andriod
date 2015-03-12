package com.ftfl.hospital.adapter;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import com.ftfl.hospital.R;
import com.ftfl.hospital.model.Camera;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class Customadapter extends ArrayAdapter<Camera>{

	Context context;
    int layoutResourceId;   
   // BcardImage data[] = null;
    ArrayList<Camera> data=new ArrayList<Camera>();
    
    public Customadapter(Context context, int layoutResourceId, ArrayList<Camera> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ImageHolder holder = null;
       
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
           
            holder = new ImageHolder();
            // Set valu in text field
            holder.tv_txtTitle = (TextView)row.findViewById(R.id.txtTitle);
            holder.tv_imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            row.setTag(holder);
        }
        else
        {
            holder = (ImageHolder)row.getTag();
        }
       
        Camera picture = data.get(position);
        holder.tv_txtTitle.setText(picture.getName());
        //convert byte to bitmap take from contact class
        
        byte[] outImage=picture.getImage();
        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        holder.tv_imgIcon.setImageBitmap(theImage);
       return row;
       
    }
   
    /// Create image holder
    static class ImageHolder
    {
        ImageView tv_imgIcon;
        TextView tv_txtTitle;
    }
}
