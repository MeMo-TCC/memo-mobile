package com.example.memo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityLogin extends AppCompatActivity {
    EditText etEmailLogin, etSenhaLogin;
    Button btnEntrar;
    SharedPreferences logado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etEmailLogin=findViewById(R.id.etEmailLogin);
        etSenhaLogin=findViewById(R.id.etSenhaLogin);
        btnEntrar=findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String email=etEmailLogin.getText().toString();
                //String senha=etSenhaLogin.getText().toString();
                //fazer função para verificar no bd se informações estao corretas,
                //para somente depois logar
                //SharedPreferences.Editor editor=logado.edit();
                //editor.putBoolean("logado", true);
                //editor.apply();
                Intent i=new Intent(ActivityLogin.this, ActivityMenu.class);
                startActivity(i);
                finish();
            }
        });
    }
}