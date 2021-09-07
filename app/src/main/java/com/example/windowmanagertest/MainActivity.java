package com.example.windowmanagertest;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.windowmanagertest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private WindowManager windowManager;
    private static final int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.ivTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasPermission()) {
                    createFloatWindow();
                } else {
                    getPermission();
                }
            }
        });

 /*       binding.btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent starter = TestTouchEvent.start(MainActivity.this);
                startActivity(starter);
            }
        });*/

        /*binding.btnTest.setOnTouchListener(new View.OnTouchListener() {
            float xCoordinate, yCoordinate;

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        xCoordinate = view.getX() - event.getRawX();
                        yCoordinate = view.getY() - event.getRawY();
                       *//* Log.d("Arezoo", "xCoordinate:" + xCoordinate);
                        Log.d("Arezoo", "yCoordinate:" + yCoordinate);*//*
         *//*           int[] buttonTestLocationScreen = new int[2];
                        binding.btnTest.getLocationOnScreen(buttonTestLocationScreen);
                        Log.d("Arezoo", "negativeOne: " + buttonTestLocationScreen[0] + view.getWidth());*//*
         *//*Log.d("Arezoo", "screen0:" + buttonTestLocationScreen[0]);
                        Log.d("Arezoo", "screen1:" + buttonTestLocationScreen[1]);*//*
         *//*    int[] buttonTestLocationWindow = new int[2];
                        binding.btnTest.getLocationInWindow(buttonTestLocationWindow);*//*
         *//*  Log.d("Arezoo", "window0:" + buttonTestLocationWindow[0]);
                        Log.d("Arezoo", "window1:" + buttonTestLocationWindow[1]);
                        Log.d("Arezoo", "btnX:" + binding.btnTest.getX());
                        Log.d("Arezoo", "btnY:" + binding.btnTest.getY());
                        Log.d("Arezoo", "viewX1:" + view.getX());
                        Log.d("Arezoo", "viewY1:" + view.getY());
                        Log.d("Arezoo", "viewLeftX1:" + view.getLeft());
                        Log.d("Arezoo", "eventX1:" + event.getX());
                        Log.d("Arezoo", "eventY1:" + event.getY());
                        Log.d("Arezoo", "eventRawX1:" + event.getRawX());
                        Log.d("Arezoo", "eventRawY1:" + event.getRawY());*//*
                        break;
                    case MotionEvent.ACTION_MOVE:
                        view.animate().x(event.getRawX() + xCoordinate).y(event.getRawY() + yCoordinate).setDuration(0).start();
                      *//*  int[] buttonTestLocationScreen1 = new int[2];
                        binding.btnTest.getLocationOnScreen(buttonTestLocationScreen1);
                        Log.d("Arezoo", "negativeTwo: " + buttonTestLocationScreen1[0] + view.getWidth());*//*
         *//*Log.d("Arezoo", "viewX2:" + view.getX());
                        Log.d("Arezoo", "viewY2:" + view.getY());
                        Log.d("Arezoo", "viewLeftX2:" + view.getLeft());
                        Log.d("Arezoo", "eventX2:" + event.getX());
                        Log.d("Arezoo", "eventY2:" + event.getY());
                        Log.d("Arezoo", "eventRawX2:" + event.getRawX());
                        Log.d("Arezoo", "eventRawY2:" + event.getRawY());*//*
                        break;
                }
                return true;
            }
        });*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Settings.canDrawOverlays(this)) {
                    createFloatWindow();
                } else {
                    Toast.makeText(this, "دسترسی داده نشد", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private boolean hasPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.canDrawOverlays(this);
        }
        return false;
    }

    private void getPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            Intent starter = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
            startActivityForResult(starter, REQUEST_CODE);
        }
    }

    private void createFloatWindow() {
        int[] btnScreenLocation = new int[2];
        binding.ivTest.getLocationInWindow(btnScreenLocation);
        Log.d("Arezoo", "btnScreenLocation[0]:" + btnScreenLocation[0]);
        Log.d("Arezoo", "btnScreenLocation[1]:" + btnScreenLocation[1]);
        binding.ivTest.setVisibility(View.GONE);
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.test, null);
        int w = 0;
        int h = 0;
        int xpos = 0;
        int ypos = 0;
        int _type;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            _type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            _type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        int _flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        int _format = PixelFormat.TRANSLUCENT;
        WindowManager.LayoutParams windowParams = new WindowManager.LayoutParams(w, h, xpos, ypos, _type, _flags, _format);
        calculateSizeAndPosition(windowParams);
        windowParams.x = btnScreenLocation[0];
        windowParams.y = btnScreenLocation[1];
        windowParams.x = (int) binding.ivTest.getX();
        windowParams.y = (int) binding.ivTest.getY();
        /*windowParams.alpha = 1;*/
        windowParams.windowAnimations = android.R.style.Animation_Toast;
        windowManager.addView(view, windowParams);
        int[] viewScreenLocation = new int[2];
        view.getLocationInWindow(viewScreenLocation);
        Log.d("Arezoo", "viewScreenLocation[0]:" + viewScreenLocation[0]);
        Log.d("Arezoo", "viewScreenLocation[1]:" + viewScreenLocation[1]);
        Log.d("Arezoo", "paramX:" + windowParams.x);
        Log.d("Arezoo", "paramY:" + windowParams.y);
        Log.d("Arezoo", "viewX:" + view.getX());
        Log.d("Arezoo", "viewY:" + view.getY());
        view.setOnTouchListener(new View.OnTouchListener() {
            private float xCoordinate, yCoordinate;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        xCoordinate = windowParams.x - event.getRawX();
                        yCoordinate = windowParams.y - event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        /*view.animate().x(event.getRawX() + xCoordinate).y(event.getRawY() + yCoordinate).setDuration(0).start();*/
                        windowParams.x = (int) (event.getRawX() + xCoordinate);
                        windowParams.y = (int) (event.getRawY() + yCoordinate);
                        windowManager.updateViewLayout(view, windowParams);
                        break;
                }
                return true;
            }
        });

       /* ImageButton imageButtonClose = view.findViewById(R.id.ib_window_close);
        imageButtonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Arezoo", "Close");
            }
        });

        TextView textView = view.findViewById(R.id.tv_window_title);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Arezoo", "Title");
            }
        });

        ImageButton imageButtonSend = view.findViewById(R.id.ib_send);
        imageButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Arezoo", "Send");
            }
        });

        EditText editTextContent = view.findViewById(R.id.ed_content_text);
        editTextContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("Arezoo", "contentBefore");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("Arezoo", "contentOn");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("Arezoo", "contentAfter");
            }
        });

        editTextContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Arezoo", "Content");
            }
        });
*/
        /*binding.edTest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("Arezoo", "mainBefore");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("Arezoo", "mainOn");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("Arezoo", "mainAfter");
            }
        });

        binding.getRoot().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("Arezoo", "DownRoot");
                        break;
                }
                return true;
            }
        });
*/
       /* binding.btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent starter = DestinationActivity.start(MainActivity.this);
                startActivity(starter);
            }
        });*/

/*        binding.btnTest.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("Arezoo", "btnTestDown");
                        break;
                }
                return false;
            }
        });*/
    }

    private DisplayMetrics getCurrentDisplayMetrics() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    private void calculateSizeAndPosition(WindowManager.LayoutParams windowParams) {
        /*  DisplayMetrics displayMetrics = getCurrentDisplayMetrics();*/
        windowParams.gravity = Gravity.TOP | Gravity.LEFT;
       /* windowParams.width = (int) (300 * displayMetrics.density);
        windowParams.height = (int) (80 * displayMetrics.density);*/
        windowParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        /*windowParams.x = (displayMetrics.widthPixels - windowParams.width) / 2;
        windowParams.y = (displayMetrics.heightPixels - windowParams.height) / 2;*/
    }
}