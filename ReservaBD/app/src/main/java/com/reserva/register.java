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


public class register extends Activity implements View.OnClickListener{

    EditText txt_username;
    EditText txt_password;
    Button btn_register;
    Button btn_login;
    Button btn_reservation;
    usersDataSource usersDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        txt_username = (EditText)findViewById(R.id.username_layout);
        txt_password = (EditText)findViewById(R.id.password_layout);
        btn_register = (Button)findViewById(R.id.login_button);
        btn_login = (Button)findViewById(R.id.register_button);
        btn_reservation = (Button)findViewById(R.id.reservation_button);
        btn_register.setText("REGISTER");
        btn_login.setText("LOGIN");
        btn_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_reservation.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.login_button){
            String username = txt_username.getText().toString();
            String password = txt_password.getText().toString();
            registerInDB(username, password);
        } else if(view.getId() == R.id.register_button){
            Intent loginActivity = new Intent(this, login.class);
            startActivity(loginActivity);
            finish();
        } else if(view.getId() == R.id.reservation_button){
            Intent mainActivity = new Intent(this, MainActivity.class);
            startActivity(mainActivity);
            finish();
        }
    }

    public void registerInDB(String username, String password){
        usersDataSource = new usersDataSource(this);
        usersDataSource.createUser(username, password);
        Toast.makeText(this, "User successfuly created", Toast.LENGTH_LONG).show();
    }
}
