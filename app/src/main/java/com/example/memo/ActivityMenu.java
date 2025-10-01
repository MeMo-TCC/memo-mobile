package com.example.memo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityMenu extends AppCompatActivity {
    Button btnTarefasProgamadas, btnPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnTarefasProgamadas=findViewById(R.id.btnTarefasProgramadas);
        btnPerfil=findViewById(R.id.btnPerfil);

    }
    public void vizualizarTarefas(View v){
        Intent i=new Intent(this, ActivityVizualizarTarefas.class);
        startActivity(i);
    }

    public void vizualizarPerfil(View v){
        Intent i=new Intent(this, ActivityPerfil.class);
        startActivity(i);
    }
}