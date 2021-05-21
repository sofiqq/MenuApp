package com.example.menuapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.menuapp.R;

public class OrderActivity extends AppCompatActivity {

    TextView tvCost;
    EditText etAddress;
    CardView cvSend;
    LinearLayout llOrder;
    FrameLayout flSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        initUI();

        Intent intent = getIntent();
        int cost = intent.getIntExtra("cost", 0);
        tvCost.setText("KZT " + cost);
    }

    private void initUI() {
        tvCost = findViewById(R.id.tv_cost);
        etAddress = findViewById(R.id.et_address);
        cvSend = findViewById(R.id.cv_send);
        llOrder = findViewById(R.id.ll_order);
        flSuccess = findViewById(R.id.fl_success);
        cvSend.setOnClickListener(v -> {
            if (!etAddress.getText().toString().isEmpty()) {
                llOrder.setVisibility(View.GONE);
                flSuccess.setVisibility(View.VISIBLE);
                hideKeyboardFrom(this, etAddress);
            } else
                Toast.makeText(this, "Введите адрес доставки", Toast.LENGTH_SHORT).show();
        });
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}