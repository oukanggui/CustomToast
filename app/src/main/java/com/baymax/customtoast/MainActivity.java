package com.baymax.customtoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.baymax.customtoast.utils.ToastHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.toast_origin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "系统toast", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.toast_custom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastHelper toastHelper = new ToastHelper(MainActivity.this);
                toastHelper.setText("I am baymax from custom toast");
                toastHelper.show();
            }
        });
    }
}
