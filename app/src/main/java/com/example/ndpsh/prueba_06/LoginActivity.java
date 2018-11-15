package com.example.ndpsh.prueba_06;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    private EditText textEmail;
    private EditText textPassword;
    private Switch switchRemember;
    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindUI();

        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        setCredentialsIfExist();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textEmail.getText().toString();
                String password = textPassword.getText().toString();

               if (login(email, password)) {
                   goToMain();
                   saveOnPreferences(email, password);
               }
            }
        });

    }


    private void bindUI() {

        textEmail = findViewById(R.id.editTextEmail);
        textPassword = findViewById(R.id.editTextPassword);
        switchRemember = findViewById(R.id.switchRemenber);
        btnlogin = findViewById(R.id.buttonLogin);

    }

    private void setCredentialsIfExist() {
        String email = getUserMailPrefs();
        String password = getUserPassPrefs();
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            textEmail.setText(email);
            textPassword.setText(password);
        }
    }

    private boolean login(String email, String password) {
        if (!isValidEmail(email)){
            Toast.makeText(this, "Email is not valid, please try again", Toast.LENGTH_LONG).show();
            return false;
        }else if (!isValidPassword(password)) {
            Toast.makeText(this, "Password is not valid, 8 characters or  more please try again", Toast.LENGTH_LONG).show();
            return false;
        }else{
            return true;
        }
    }

    private void saveOnPreferences(String email, String password) {
        if (switchRemember.isChecked()) {
           SharedPreferences.Editor editor = prefs.edit();
           editor.putString("email", email);
           editor.putString("pass", password);
           editor.commit();
           editor.apply();
        }
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private boolean isValidPassword(String password) {
        return password.length() >= 8;
    }

    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private String getUserMailPrefs(){
       return prefs.getString("email","");
    }
    private String getUserPassPrefs(){
        return prefs.getString("pass", "");
    }
}
