package com.example.memo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    Button btnCad;
    SharedPreferences logado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnLogin = findViewById(R.id.btnLogin);
        btnCad = findViewById(R.id.btnCad);

        logado=getSharedPreferences("logado", MODE_PRIVATE);
        if(logado.getBoolean("logado", false)){
            Intent i=new Intent(this, ActivityVizualizarTarefas.class);
            startActivity(i);
            finish();
        }
    }
    public void irCad(View v){
        Intent i = new Intent(MainActivity.this, ActivityCadUser.class);
        startActivity(i);
    }
    public void irLogin(View v){
        Intent i = new Intent(MainActivity.this, ActivityLogin.class);
        startActivity(i);
    }
}