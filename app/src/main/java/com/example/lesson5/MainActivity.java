package com.example.lesson5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import model.Order;

public class MainActivity extends AppCompatActivity implements View.OnClickListener , TextWatcher {

    private EditText edClientNumber, edNbSlices;
    private RadioGroup rgPizzaType;
    private TextView tvAmount;

    private Button btnSave, btnShowAll, btnQuit;

    private RadioButton rbCheese, rbMexican, rbVegetarian;

    private Order oneOrder;
    private String pizzaType="";
    private float amount;
    private float price;


    private ArrayList<Order> listOfOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        edClientNumber = findViewById(R.id.edClientNumber);
        edNbSlices = findViewById(R.id.edNbSlices);

//        you gotta add this
        edNbSlices.addTextChangedListener(this);


        rgPizzaType = findViewById(R.id.rgPizzaType);
        btnSave = findViewById(R.id.btnSave);
        btnShowAll = findViewById(R.id.btnShowAll);
        btnQuit = findViewById(R.id.btnQuit);
        rbCheese = findViewById(R.id.rbCheese);
        rbMexican = findViewById(R.id.rbMexican);
        rbVegetarian = findViewById(R.id.rbVegetarian);
        tvAmount = findViewById(R.id.tvAmount);
        listOfOrders = new ArrayList<Order>();
        btnSave.setOnClickListener(this);
        btnShowAll.setOnClickListener(this);
        btnQuit.setOnClickListener(this);

        rbCheese.setOnClickListener(this);
        rbMexican.setOnClickListener(this);
        rbVegetarian.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch(id)
        {
            case R.id.btnSave:
                saveOrder(view);
                break;
            case R.id.btnQuit:
                System.exit(0);
                break;
            case R.id.btnShowAll:
                showAllOrders();
                break;
            case R.id.rbCheese:
                pizzaType="Cheese";
                showAmount();
                break;
            case R.id.rbMexican:
                pizzaType="Mexican";
                showAmount();
                break;
            case R.id.rbVegetarian:
                pizzaType="Vegetarian";
                showAmount();
                break;

        }

    }

    private void showAllOrders() {
//        call the other activity
        //the class Order must implement the interface
        //Serializable
        try{
            Intent intent = new Intent(this,SecondActivity.class);
            intent.putExtra("orders",listOfOrders);
            startActivity(intent);
        }
        catch(Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    private void saveOrder(View view) {
        try {
            int clientNumber = Integer.valueOf(edClientNumber.getText().toString());
            int nbSlices = Integer.valueOf(edNbSlices.getText().toString());

            oneOrder = new Order(clientNumber,pizzaType,nbSlices);
            listOfOrders.add(oneOrder);
            Snackbar.make(view,"the order for the client " + clientNumber
                    +" is created successfully", Snackbar.LENGTH_LONG).show();

            edClientNumber.setText(null);
            edNbSlices.setText(null);
            rgPizzaType.clearCheck();
            edClientNumber.requestFocus();
            tvAmount.setText("Amount: ");

        }
        catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }

        private void showAmount(){
            try{
                float price = getPrice(pizzaType);
                int nbSlices = Integer.valueOf(edNbSlices.getText().toString());
                float amount = nbSlices * price;
                tvAmount.setText("Amount: " +amount);
            }
            catch (Exception e)
            {
                Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
            }



        }



    public float getPrice(String pizzaType) throws Exception {
        float price = 0f;
        if(pizzaType.equals("Vegetarian"))
            price=2.5f;
        else if(pizzaType.equals("Mexican"))
            price=2.4f;
        else if(pizzaType.equals("Cheese"))
            price=2.0f;
             else
                 throw new Exception("Please select a pizza");



        return price;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        showAmount();
    }
}