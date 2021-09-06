package com.example.windowmanagertest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.windowmanagertest.databinding.ActivityDestinationBinding;

public class DestinationActivity extends AppCompatActivity {
    private ActivityDestinationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_destination);

        binding.getRoot().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("Arezoo", "destinationDown");
                        break;
                }
                return true;
            }
        });

        binding.edTest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("Arezoo", "destinationBefore");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("Arezoo", "destinationOn");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("Arezoo", "destinationAfter");
            }
        });
    }

    public static Intent start(Context context) {
        return new Intent(context, DestinationActivity.class);
    }
}