package com.reserva;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.reserva.database.usersDataSource;

public class login extends Activity implements View.OnClickListener {

    EditText txt_username;
    EditText txt_password;
    Button btn_login;
    Button btn_register;
    Button btn_reservation;
    usersDataSource usersDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        txt_username = (EditText)findViewById(R.id.username_layout);
        txt_password = (EditText)findViewById(R.id.password_layout);
        btn_login = (Button)findViewById(R.id.login_button);
        btn_register = (Button)findViewById(R.id.register_button);
        btn_reservation = (Button)findViewById(R.id.reservation_button);
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        btn_reservation.setOnClickListener(this);
        usersDataSource = new usersDataSource(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.login_button){
            String username = txt_username.getText().toString();
            String password = txt_password.getText().toString();
            Boolean login = isValidLogin(username, password);
            Toast.makeText(this, String.valueOf(login) + " Login", Toast.LENGTH_SHORT).show();
        } else if(view.getId() == R.id.register_button){
            Intent registerActivity = new Intent(this, register.class);
            startActivity(registerActivity);
            finish();
        } else if(view.getId() == R.id.reservation_button){
            Intent mainActivity = new Intent(this, MainActivity.class);
            startActivity(mainActivity);
            finish();
        }
    }

    public boolean isValidLogin(String username, String password){
        usersDataSource.open();
        if(usersDataSource.getUser(username, password) != null){
            return false;
        }
        return true;
    }
}
