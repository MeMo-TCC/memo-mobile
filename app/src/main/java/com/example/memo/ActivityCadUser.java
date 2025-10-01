package com.example.memo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityCadUser extends AppCompatActivity {

    EditText etNome;
    EditText etEmail;
    EditText etDataNasc;
    EditText etNumTel;
    Button btnEnviarCad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cad_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNome = findViewById(R.id.etNomeCad);
        etEmail = findViewById(R.id.etEmailCad);
        etDataNasc = findViewById(R.id.etDataNascCad);
        etNumTel = findViewById(R.id.etNumTelCad);
        btnEnviarCad = findViewById(R.id.btnCadastrar);
    }
}