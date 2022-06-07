package com.example.wallpapers;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class MainActivity3 extends AppCompatActivity {

    ImageView left, right, set, download, share;
    ViewPager viewpager;
    int image[], poss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        viewpager = findViewById(R.id.viewpager);
        left = findViewById(R.id.left);
        right = findViewById(R.id.right);
        set = findViewById(R.id.set);
        download = findViewById(R.id.download);
        share = findViewById(R.id.share);

        image = getIntent().getIntArrayExtra("img");
        poss = getIntent().getIntExtra("poss", 0);

        adapter3 adapter = new adapter3(this, image);

        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(poss);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                int  yy = viewpager.getCurrentItem();

                File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/Wallpapers");

                if (!folder.exists()) {
                    folder.mkdirs();
                }

                // TODO :-->   Bitmap >> ByteArrayOutputStream >> Compress >> Byte[] >> FileOutputStream
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), image[viewpager.getCurrentItem()]);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                byte[] bytes = bos.toByteArray();

                int cnt = new Random().nextInt(100000);
                String imagename = "Image" + cnt + ".jpg";

                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/Wallpapers/" + imagename);

                try {
                    FileOutputStream fos = new FileOutputStream(file);

                    try {
                        file.createNewFile();
                        fos.write(bytes);
                        fos.close();

                        Toast.makeText(MainActivity3.this, "File Downloaded", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int cnt = new Random().nextInt(10000);

                Bitmap b = BitmapFactory.decodeResource(getResources(), image[viewpager.getCurrentItem()]);
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                String path = MediaStore.Images.Media.insertImage(getContentResolver(), b, "Image" + cnt, null);
                Uri imageUri = Uri.parse(path);
                share.putExtra(Intent.EXTRA_STREAM, imageUri);
                startActivity(Intent.createChooser(share, "Image" + cnt));

            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                PopupMenu popmenu = new PopupMenu(MainActivity3.this, view);
                popmenu.getMenu().add(Menu.NONE, 0, 0, "Lock Screen");
                popmenu.getMenu().add(Menu.NONE, 1, 1, "Home Screen");
                popmenu.getMenu().add(Menu.NONE, 2, 2, "Both");
                popmenu.show();

                popmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        int i = menuItem.getItemId();
                        if (i == 0) {
                            WallpaperManager myWallpaperManager
                                    = WallpaperManager.getInstance(getApplicationContext());
                            Toast.makeText(MainActivity3.this, "LockScreen Wallpaper Sat", Toast.LENGTH_SHORT).show();
                            try {
                                myWallpaperManager.setResource(image[viewpager.getCurrentItem()], WallpaperManager.FLAG_LOCK);
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            return true;
                        }
                        if (i == 1) {

                            WallpaperManager myWallpaperManager
                                    = WallpaperManager.getInstance(getApplicationContext());
                            Toast.makeText(MainActivity3.this, "HomeScreen Wallpaper Sat", Toast.LENGTH_SHORT).show();
                            try {
                                myWallpaperManager.setResource(image[viewpager.getCurrentItem()], WallpaperManager.FLAG_SYSTEM);
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            return true;
                        }
                        if (i == 2) {
                            WallpaperManager myWallpaperManager
                                    = WallpaperManager.getInstance(getApplicationContext());
                            Toast.makeText(MainActivity3.this, " Wallpaper Sat", Toast.LENGTH_SHORT).show();
                            try {
                                myWallpaperManager.setResource(image[viewpager.getCurrentItem()]);
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            return true;
                        } else {
                            return false;
                        }
                    }
                });

                left.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (poss > 0) {
                            poss--;
                            viewpager.setCurrentItem(poss);
                        }
                        if (poss == 0) {
                            Toast.makeText(MainActivity3.this, "This is First", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                right.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        try {
                            if (poss < image.length) {
                                poss++;
                                viewpager.setCurrentItem(poss);
                            }
                        } catch (Exception e) {
                            Toast.makeText(MainActivity3.this, "No More Available", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.option, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.set) {
            WallpaperManager myWallpaperManager
                    = WallpaperManager.getInstance(getApplicationContext());
            Toast.makeText(MainActivity3.this, "LockScreen Wallpaper Sat", Toast.LENGTH_SHORT).show();

            try {
                myWallpaperManager.setResource(image[viewpager.getCurrentItem()], WallpaperManager.FLAG_LOCK);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (item.getItemId() == R.id.down) {
            File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/Wallpapers");

            if (!folder.exists()) {
                folder.mkdirs();
            }

            // TODO :-->   Bitmap >> ByteArrayOutputStream >> Compress >> Byte[] >> FileOutputStream
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), image[viewpager.getCurrentItem()]);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            byte[] bytes = bos.toByteArray();

            int cnt = new Random().nextInt(100000);
            String imagename = "Image" + cnt + ".jpg";

            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/Wallpapers/" + imagename);

            try {
                FileOutputStream fos = new FileOutputStream(file);

                try {
                    file.createNewFile();
                    fos.write(bytes);
                    fos.close();

                    Toast.makeText(MainActivity3.this, "File Downloaded", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (item.getItemId() == R.id.sher) {

            int cnt = new Random().nextInt(10000);

            Bitmap b = BitmapFactory.decodeResource(getResources(), image[viewpager.getCurrentItem()]);
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("image/jpeg");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(getContentResolver(), b, "Image" + cnt, null);
            Uri imageUri = Uri.parse(path);
            share.putExtra(Intent.EXTRA_STREAM, imageUri);
            startActivity(Intent.createChooser(share, "Image" + cnt));
        }

        return super.onOptionsItemSelected(item);
    }

}