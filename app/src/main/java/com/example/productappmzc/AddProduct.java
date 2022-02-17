package com.example.productappmzc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddProduct extends AppCompatActivity {

AppCompatButton btn1,btn2;
EditText txt1,txt2,txt3;
String getProductCode,getProductName,getPrice;
DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        dbHelper=new DatabaseHelper(this);
        dbHelper.getWritableDatabase();

        txt1=(EditText) findViewById(R.id.txt1);
        txt2=(EditText) findViewById(R.id.txt2);
        txt3=(EditText) findViewById(R.id.txt3);
        btn1=(AppCompatButton) findViewById(R.id.btn1);
        btn2=(AppCompatButton) findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProductCode=txt1.getText().toString();
                getProductName=txt2.getText().toString();
                getPrice=txt3.getText().toString();
                boolean result=dbHelper.insertData(getProductCode,getProductName,getPrice);
                if (result==true){
                    Toast.makeText(getApplicationContext(), "successfully added", Toast.LENGTH_SHORT).show();
                    txt1.setText("");
                    txt2.setText("");
                    txt3.setText("");
                }else{
                    Toast.makeText(getApplicationContext(), "failed to insert data", Toast.LENGTH_SHORT).show();
                }


            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}