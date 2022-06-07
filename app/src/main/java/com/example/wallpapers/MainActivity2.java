package com.example.wallpapers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    GridView gridView;
    String cattitle;
    TextView text;
    int images[]={R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img6,
            R.drawable.img7,R.drawable.img8,R.drawable.img9,R.drawable.img10,R.drawable.img11,R.drawable.img12,
            R.drawable.img13,R.drawable.img14,R.drawable.img15,R.drawable.img16,R.drawable.img17,R.drawable.img18,
            R.drawable.img19,R.drawable.img20,R.drawable.img21,R.drawable.img22,R.drawable.img23,R.drawable.img24,
            R.drawable.img25,R.drawable.img26,R.drawable.img27,R.drawable.img28,R.drawable.img29,R.drawable.img30,
            R.drawable.img31,R.drawable.img32,R.drawable.img33,R.drawable.img34,R.drawable.img35,R.drawable.img36,
            R.drawable.img37,R.drawable.img38,R.drawable.img39,R.drawable.img40,R.drawable.img41,R.drawable.img42,
            R.drawable.img43,R.drawable.img44,R.drawable.img45,R.drawable.img46,R.drawable.img47,R.drawable.img48,
            R.drawable.img49,R.drawable.img50};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        gridView=findViewById(R.id.imglist);
        text=findViewById(R.id.titlecat);

        cattitle=getIntent().getStringExtra("key");

        text.setText(cattitle);

        adapter2 adapter = new adapter2(this,images);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent =new Intent(MainActivity2.this,MainActivity3.class);
                intent.putExtra("img",images);
                intent.putExtra("poss",i);
                startActivity(intent);
            }
        });

    }
}