package ma.ensa.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AuthenticationActivity extends AppCompatActivity {

    Button btnLogin;
    EditText login;
    EditText password;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);
        sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnLogin = findViewById(R.id.login);
        login = findViewById(R.id.editLogin);
        password = findViewById(R.id.editPassword);

        if (!sharedPreferences.getString("login","default").equals("default") && !sharedPreferences.getString("password","default").equals("default")){
            Intent intent = new Intent(getApplicationContext(),TransactionActivity.class);
            startActivity(intent);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putString("login",login.getText().toString()).commit();
                sharedPreferences.edit().putString("password",password.getText().toString()).commit();
                Intent intent = new Intent(getApplicationContext(),TransactionActivity.class);
                startActivity(intent);
            }
        });
    }
}