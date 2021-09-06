package com.example.windowmanagertest;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

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

        binding.btnTest.setOnTouchListener(new View.OnTouchListener() {
            float firstEventX, firstEventY, firstRawX, firstRawY;

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        firstEventX = event.getX();
                        firstEventY = event.getY();
                        firstRawX = event.getRawX();
                        firstRawY = event.getRawY();
                    /*    int[] buttonTestLocationScreen = new int[2];
                        binding.btnTest.getLocationOnScreen(buttonTestLocationScreen);
                        Log.d("Arezoo", "screen0:" + buttonTestLocationScreen[0]);
                        Log.d("Arezoo", "screen1:" + buttonTestLocationScreen[1]);
                        int[] buttonTestLocationWindow = new int[2];
                        binding.btnTest.getLocationInWindow(buttonTestLocationWindow);
                        Log.d("Arezoo", "window0:" + buttonTestLocationWindow[0]);
                        Log.d("Arezoo", "window1:" + buttonTestLocationWindow[1]);
                        Log.d("Arezoo", "btnX:" + binding.btnTest.getX());
                        Log.d("Arezoo", "btnY:" + binding.btnTest.getY());*/
                        Log.d("Arezoo", "viewDownX:" + view.getX());
                        Log.d("Arezoo", "viewDownY:" + view.getY());
                        Log.d("Arezoo", "viewDownLeftX:" + view.getLeft());
                        Log.d("Arezoo", "eventDownX:" + event.getX());
                        Log.d("Arezoo", "eventDownY:" + event.getY());
                        Log.d("Arezoo", "eventRawDownX:" + event.getRawX());
                        Log.d("Arezoo", "eventRawDownY:" + event.getRawY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d("Arezoo", "viewMoveX:" + view.getX());
                        Log.d("Arezoo", "viewMoveY:" + view.getY());
                        Log.d("Arezoo", "viewDownLeftX:" + view.getLeft());
                        Log.d("Arezoo", "eventMoveX:" + event.getX());
                        Log.d("Arezoo", "eventMoveY:" + event.getY());
                        Log.d("Arezoo", "eventRawMoveX:" + event.getRawX());
                        Log.d("Arezoo", "eventRawMoveY:" + event.getRawY());
                        float dx = event.getRawX() - firstRawX;
                        float dy = event.getRawY() - firstRawY;
                        view.animate().x(firstEventX + dx).y(firstEventY + dy).setDuration(0).start();
                        break;
                }
                return true;
            }
        });
/*
        if (hasPermission()) {
            createFloatWindow();
        } else {
            getPermission();
        }*/
    }

   /* @Override
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
        windowManager.addView(view, windowParams);

        *//*ImageButton imageButtonClose = view.findViewById(R.id.ib_window_close);
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
*//*
        binding.edTest.addTextChangedListener(new TextWatcher() {
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

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("Arezoo", "DownView");
                        break;
                }
                return true;
            }
        });

        binding.btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent starter = DestinationActivity.start(MainActivity.this);
                startActivity(starter);
            }
        });

        binding.btnTest.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("Arezoo", "btnTestDown");
                        break;
                }
                return false;
            }
        });
    }

    private DisplayMetrics getCurrentDisplayMetrics() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    private void calculateSizeAndPosition(WindowManager.LayoutParams windowParams) {
        DisplayMetrics displayMetrics = getCurrentDisplayMetrics();
        windowParams.gravity = Gravity.TOP | Gravity.LEFT;
        windowParams.width = (int) (300 * displayMetrics.density);
        windowParams.height = (int) (80 * displayMetrics.density);
        windowParams.x = (displayMetrics.widthPixels - windowParams.width) / 2;
        windowParams.y = (displayMetrics.heightPixels - windowParams.height) / 2;
    }*/
}