package com.example.memo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityPerfil extends AppCompatActivity {
    ImageView imgPerfil;
    EditText etNomePerfil, etEmailPerfil, etDataNascPerfil, etNumTelPerfil;
    Button btnSairDaConta, btnVoltar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imgPerfil=findViewById(R.id.imgPerfil);
        etNomePerfil=findViewById(R.id.etNomePerfil);
        etEmailPerfil=findViewById(R.id.etEmailPerfil);
        etDataNascPerfil=findViewById(R.id.etDataNascPerfil);
        etNumTelPerfil=findViewById(R.id.etNumTelPerfil);
        btnSairDaConta=findViewById(R.id.btnSairDaConta);
        btnVoltar2=findViewById(R.id.btnVoltar2);

        //buscar dados do user no bd remoto e mostrá-los aqui
    }
    public void voltarAoMenu(View v){
        Intent i=new Intent(this, ActivityMenu.class);
        startActivity(i);
        finish();
    }
}