package com.example.sonu.travelguide;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by shrawankeshari on 12/12/17.
 * PlaceAdapter class is used to display the image & text's in the each listItem of listView.
 */

public class PlaceAdapter extends ArrayAdapter<Place> {
    Context context;
    List<Place> placeList;

    public PlaceAdapter(Context context,int resource,List<Place> placeList){
        super(context,resource,placeList);
        this.context = context;
        this.placeList = placeList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = ((Activity) getContext()).getLayoutInflater()
                    .inflate(R.layout.item_list,parent,false);
        }

        ImageView im = (ImageView) convertView.findViewById(R.id.imageView);
        TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);
        TextView tv_location = (TextView) convertView.findViewById(R.id.tv_location);

        Place place = getItem(position);


        if (place != null) {
            boolean isPlaceName = place.getPlaceName() != null;
            boolean isLocation = place.getLocation() != null;
            boolean isImage = place.getImage() != null;

            if(isPlaceName){
                tv_title.setText(place.getPlaceName());
            }
            if(isLocation){
                tv_location.setText(place.getLocation());
            }
            if(isImage){
                int imageResource = context.getResources().getIdentifier(
                        place.getImage(),"drawable", context.getPackageName());
                Glide.with(im.getContext())
                        .load(imageResource)
                        .into(im);
            }
        }

        return convertView;
    }
}
