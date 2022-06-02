package com.example.wallpapers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

public class adapter1 extends BaseAdapter {

    MainActivity mainActivity;
    String[] title;
    int[] back;

    public adapter1(MainActivity mainActivity, String[] title, int[] back) {

        this.mainActivity=mainActivity;
        this.title=title;
        this.back=back;
    }

    @Override
    public int getCount() {
        return title.length;
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

        view= LayoutInflater.from(mainActivity).inflate(R.layout.textlist,viewGroup,false);

        ImageView imageView;
        TextView textView;

        imageView=view.findViewById(R.id.titleimglist);
        textView=view.findViewById(R.id.titlelist);

        textView.setText(title[i]);
        imageView.setBackgroundResource(back[i]);

        return view;
    }
}
