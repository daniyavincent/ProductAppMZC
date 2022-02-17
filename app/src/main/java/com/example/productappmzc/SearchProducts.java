package com.example.productappmzc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SearchProducts extends AppCompatActivity {

    AppCompatButton btn1,btn2;
    EditText txt1,txt2,txt3;
    String getProductCode,getProductName,getPrice;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_products);
        databaseHelper=new DatabaseHelper(this);

        txt1=(EditText) findViewById(R.id.txt1);
        txt2=(EditText) findViewById(R.id.txt2);
        txt3=(EditText) findViewById(R.id.txt3);
        btn1=(AppCompatButton) findViewById(R.id.btn1);
        btn2=(AppCompatButton) findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to retrive table values from db
                getProductCode=txt1.getText().toString();
                Cursor c=databaseHelper.searchData(getProductCode);
                if (c.getCount()==0)
                {
                    txt2.setText("");
                    txt3.setText("");
                    Toast.makeText(getApplicationContext(), "Invalid Product Code", Toast.LENGTH_SHORT).show();
                }else{
                    while(c.moveToNext()){
                        getProductName=c.getString(2);
                        getPrice=c.getString(3);
                        txt2.setText(getProductName);
                        txt3.setText(getPrice);
                    }
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