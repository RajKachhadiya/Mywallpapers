package com.example.wallpapers;

import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class adapter2 extends BaseAdapter {

    MainActivity2 mainActivity2;
    int[] images;

    public adapter2(MainActivity2 mainActivity2, int[] images) {

        this.mainActivity2=mainActivity2;
        this.images=images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view= LayoutInflater.from(mainActivity2).inflate(R.layout.imglist,viewGroup,false);

        ImageView imageView;

        imageView=view.findViewById(R.id.imglist);

        imageView.setImageResource(images[i]);

        return view;
    }
}
