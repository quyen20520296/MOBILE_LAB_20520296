package com.example.ketnoidatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText txtHoTen;
    EditText txtPhone;
    Button btnAddFS;
    ListView listData;
    ArrayList<Object> arrList = null;
    ArrayAdapter<Object> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtHoTen = findViewById(R.id.HoTenFill);
        txtPhone = findViewById(R.id.PhoneFill);

        listData = findViewById(R.id.ListLuongNet);

        arrList = new ArrayList<Object>();

        adapter = new ArrayAdapter<Object>
                (this,
                        android.R.layout.simple_list_item_1,
                        arrList);

        listData.setAdapter(adapter);

        btnAddFS = (Button) findViewById(R.id.AddFSButton);

        btnAddFS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                DocSnippets dn = new DocSnippets(FirebaseFirestore.getInstance());
                dn.addAdaLovelace();

                /*arrList.add(dn);
                adapter.notifyDataSetChanged();*/
            }
        });
    }
}