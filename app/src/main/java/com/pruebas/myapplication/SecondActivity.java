package com.pruebas.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.pruebas.myapplication.POJO.User;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent i = getIntent();
        User user = (User) i.getSerializableExtra("usuario");
        TextView tvInfo = (TextView) findViewById(R.id.tvInfo);
        tvInfo.setText("Nombre: " + String.valueOf(user.getName()) + ", edad: " + user.getAge() + " años");
    }
}