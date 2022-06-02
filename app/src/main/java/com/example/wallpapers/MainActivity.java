package com.example.wallpapers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    String title[];
    int back[] = {R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i4, R.drawable.i5, R.drawable.i6,
            R.drawable.i7, R.drawable.i8, R.drawable.i9, R.drawable.i10, R.drawable.i11, R.drawable.i12,
            R.drawable.i13, R.drawable.i14, R.drawable.i15, R.drawable.i16, R.drawable.i17, R.drawable.i18,
            R.drawable.i19, R.drawable.i20, R.drawable.i21, R.drawable.i22, R.drawable.i23, R.drawable.i24, R.drawable.img25};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.list1);

        title = getResources().getStringArray(R.array.title);

        adapter1 adapter = new adapter1(this, title,back);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("key", title[i]);
                startActivity(intent);
            }
        });

    }
}