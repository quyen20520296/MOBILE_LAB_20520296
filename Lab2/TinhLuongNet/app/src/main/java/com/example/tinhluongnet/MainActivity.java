package com.example.tinhluongnet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText txtHoTen;
    EditText txtLuongGross;
    Button btnCalc;
    ListView listLuongNet;
    ArrayList<Object> arrList = null;
    ArrayAdapter<Object> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtHoTen = findViewById(R.id.HoTenFill);
        txtLuongGross = findViewById(R.id.LuongGrossFill);

        listLuongNet = findViewById(R.id.ListLuongNet);

        arrList = new ArrayList<Object>();

        adapter = new ArrayAdapter<Object>
                (this,
                        android.R.layout.simple_list_item_1,
                        arrList);

        listLuongNet.setAdapter(adapter);

        btnCalc = (Button) findViewById(R.id.CalcButton);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                long LuongGross = Long.parseLong(txtLuongGross.getText().toString());
                long LuongNet;
                long a = (long) (LuongGross - LuongGross * 0.105);
                if(a <= 11000000)
                {
                    LuongNet = a;
                }
                else
                {
                    LuongNet = (long) (LuongGross - LuongGross * 0.105 - (a - 11000000) * 0.05);
                }
                arrList.add("Họ tên: " + txtHoTen.getText() + "\n" + "Lương net: " + LuongNet);
                adapter.notifyDataSetChanged();
            }
        });
    }
}