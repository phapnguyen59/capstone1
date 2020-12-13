package com.example.covid_19;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ForgetPasswordSuccessMessage extends AppCompatActivity {

    Button next_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);

        next_btn = findViewById(R.id.next_btn);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgetPasswordSuccessMessage.this,Login.class);
                startActivity(intent);
            }
        });
    }
//    @Override
//    public void onClick(View v){
//        switch (v.getId()){
//            case R.id.next_btn:
//                startActivity(new Intent(ForgetPasswordSuccessMessage.this, Login.class));
//                break;
//        }
//
//    }
}