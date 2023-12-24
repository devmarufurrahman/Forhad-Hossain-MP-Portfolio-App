package com.example.forhadhossainmp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.forhadhossainmp.R;

import java.util.List;

public class ImageAdapter extends BaseAdapter {

    private List<Integer> imgThumbIds;
    private Context context;

    public ImageAdapter(List<Integer> imgThumbIds, Context context) {
        this.imgThumbIds = imgThumbIds;
        this.context = context;
    }

    @Override
    public int getCount() {
        return imgThumbIds.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return imgThumbIds.get(position);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ImageView imageView = (ImageView) view;

        if (imageView == null){
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(300,400));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setBackgroundResource(R.drawable.image_shape);
            imageView.setElevation(15);
        }

        imageView.setImageResource(imgThumbIds.get(position));
        return imageView;
    }

}
