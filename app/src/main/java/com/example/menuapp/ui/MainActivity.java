package com.example.menuapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.menuapp.R;
import com.example.menuapp.model.MenuItem;

import java.util.ArrayList;

import static com.example.menuapp.Helper.getMenu;

public class MainActivity extends AppCompatActivity {

    TextView tvCost;
    CardView cvSend;
    TextView tvSend;

    RecyclerView rvMenu;

    MenuAdapter adapter;
    ArrayList<MenuItem> data;
    int[] orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        data = getMenu(this);
        orders = new int[data.size()];
        adapter.setData(data);
    }

    private void initUI() {
        tvCost = findViewById(R.id.tv_cost);
        cvSend = findViewById(R.id.cv_send);
        tvSend = findViewById(R.id.tv_send);
        rvMenu = findViewById(R.id.rv_menu);
        cvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                intent.putExtra("cost", getCost());
                startActivity(intent);
            }
        });
        adapter = new MenuAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMenu.setLayoutManager(layoutManager);
        rvMenu.setAdapter(adapter);
    }

    public void countOrder(int[] orders) {
        this.orders = orders;
        int cost = getCost();
        if (cost == 0)
            cvSend.setVisibility(View.GONE);
        else {
            cvSend.setVisibility(View.VISIBLE);
            tvSend.setText(getResources().getString(R.string.continue_order) + "(KZT " + cost + ")" );
        }
        tvCost.setText("KTZ " + cost);
    }

    public int getCost() {
        int cost = 0;
        if (orders != null) {
            for (int i = 0; i < orders.length; i++) {
                cost += orders[i] * data.get(i).getCost();
            }
        }
        return cost;
    }
}