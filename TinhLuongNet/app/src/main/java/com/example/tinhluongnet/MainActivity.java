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
    ArrayList<NhanVien> arrList = null;
    ArrayAdapter<NhanVien> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtHoTen = (EditText) findViewById(R.id.HoTenFill);
        txtLuongGross = (EditText) findViewById(R.id.LuongGrossFill);

        listLuongNet = (ListView) findViewById(R.id.ListLuongNet);

        arrList = new ArrayList<NhanVien>();

        adapter = new ArrayAdapter<NhanVien>
                (MainActivity.this,
                        android.R.layout.simple_list_item_1,
                        arrList);

        listLuongNet.setAdapter(adapter);

        btnCalc = (Button) findViewById(R.id.CalcButton);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                NhanVien nv = new NhanVien();
                nv.setHoTen(txtHoTen.getText().toString());
                long LuongGross = Long.parseLong(txtLuongGross.getText().toString());
                nv.setLuongGross(LuongGross);

                arrList.add(nv);
                adapter.notifyDataSetChanged();
            }
        });
    }
}