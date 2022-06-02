package com.example.wallpapers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class adapter3 extends PagerAdapter {

    MainActivity3 mainActivity3;
    int[] image;

    public adapter3(MainActivity3 mainActivity3, int[] image) {

        this.mainActivity3 = mainActivity3;
        this.image = image;
    }

    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(mainActivity3).inflate(R.layout.viewimagelayout, container, false);

        ImageView imageView = view.findViewById(R.id.viewimage);

        imageView.setImageResource(image[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//type casting
        container.removeView((View) object);
    }
}
