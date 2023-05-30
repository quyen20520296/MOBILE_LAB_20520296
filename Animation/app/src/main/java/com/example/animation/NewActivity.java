package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewActivity extends AppCompatActivity {

    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        btnBack = (Button) findViewById(R.id.ButtonBack);

        //chuyen doi giua 2 activtity bang truong hop fade in / fade out
/*        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iNewActivity = new Intent(NewActivity.this, MainActivity.class);
                startActivity(iNewActivity);

                overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out);
            }
        });*/

        //lam theo yeu cau doi thanh truong hop tu phai qua trai
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iNewActivity = new Intent(NewActivity.this, MainActivity.class);
                startActivity(iNewActivity);

                overridePendingTransition(R.anim.anim_enter_left_to_right, R.anim.anim_leave_left_to_right);
            }
        });
    }
}