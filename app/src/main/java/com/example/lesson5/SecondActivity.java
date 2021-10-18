package com.example.lesson5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import model.Order;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvAllOrders;
    Button btnReturn;
// method on click listener
    ArrayList<Order> listOfOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        initialize();
    }

    private void initialize() {
        tvAllOrders = findViewById(R.id.tvAllOrders);
        btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(this);

        listOfOrders =(ArrayList<Order>) getIntent().getExtras().getSerializable("orders");

        StringBuilder list = new StringBuilder("");
        float totalOrders=0;
        for (Order oneOrder : listOfOrders){
            list.append(oneOrder+"\n");
            totalOrders+=oneOrder.getAmount();
        }
        tvAllOrders.setText(list+"Total orders: "+ totalOrders);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}