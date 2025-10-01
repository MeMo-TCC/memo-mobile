package com.example.memo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ActivityVizualizarTarefas extends AppCompatActivity {
    Spinner spinnerFeitas, spinnerPendentes;
    Button btnAdicionar, btnModTarefa, btnExcluirTarefaFeita;
    ArrayList<Tarefa> listaPendentes, listaFeitas;
    ArrayAdapter<Tarefa> adpPendentes, adpFeitas;
    Tarefa tarefaFeitaSelecionada, tarefaPendenteSelecionada;
    TarefaCtrl tarefaCtrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vizualizar_tarefas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        spinnerFeitas=findViewById(R.id.spinnerFeitas);
        spinnerPendentes=findViewById(R.id.spinnerPendentes);
        btnAdicionar=findViewById(R.id.btnAdicionar);
        btnModTarefa=findViewById(R.id.btnModTarefa);
        btnExcluirTarefaFeita=findViewById(R.id.btnExcluirTarefaFeita);
        tarefaCtrl=new TarefaCtrl(this);

        listaPendentes=tarefaCtrl.selecionarPendentes();
        listaFeitas=tarefaCtrl.selecionarFeitas();

        if(listaPendentes==null) listaPendentes=new ArrayList<>();
        if(listaFeitas==null) listaFeitas=new ArrayList<>();

        adpPendentes=new ArrayAdapter<Tarefa>(this, android.R.layout.simple_spinner_dropdown_item, listaPendentes);
        adpFeitas=new ArrayAdapter<Tarefa>(this, android.R.layout.simple_spinner_dropdown_item, listaFeitas);

        spinnerPendentes.setAdapter(adpPendentes);
        spinnerFeitas.setAdapter(adpFeitas);

        spinnerPendentes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tarefaPendenteSelecionada=(Tarefa)spinnerPendentes.getSelectedItem();
                btnModTarefa.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                btnModTarefa.setVisibility(View.INVISIBLE);
            }
        });

        spinnerFeitas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tarefaFeitaSelecionada=(Tarefa)spinnerFeitas.getSelectedItem();
                btnExcluirTarefaFeita.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                btnExcluirTarefaFeita.setVisibility(View.INVISIBLE);
            }
        });
    }
    protected void onResume(){
        super.onResume();
        listaPendentes=tarefaCtrl.selecionarPendentes();
        listaFeitas=tarefaCtrl.selecionarFeitas();

        adpPendentes=new ArrayAdapter<Tarefa>(this, android.R.layout.simple_spinner_dropdown_item, listaPendentes);
        adpFeitas=new ArrayAdapter<Tarefa>(this, android.R.layout.simple_spinner_dropdown_item, listaFeitas);

        spinnerPendentes.setAdapter(adpPendentes);
        spinnerFeitas.setAdapter(adpFeitas);

        if(spinnerPendentes.getSelectedItem()==null) btnModTarefa.setVisibility(View.INVISIBLE);
        if(spinnerFeitas.getSelectedItem()==null) btnExcluirTarefaFeita.setVisibility(View.INVISIBLE);
    }
    public void addTarefa(View v){
        Intent i=new Intent(this, ActivityAddTarefa.class);
        startActivity(i);
    }
    public void modTarefa(View v){
        tarefaPendenteSelecionada=(Tarefa)spinnerPendentes.getSelectedItem();
        Intent i=new Intent(this, ActivityModTarefa.class);
        i.putExtra("idTarefa", tarefaPendenteSelecionada.getId());
        startActivity(i);
    }
    public void voltar(View v){
        finish();
    }
    public void excluirTarefaFeita(View v){
        tarefaFeitaSelecionada=(Tarefa)spinnerFeitas.getSelectedItem();
        int idTarefa=tarefaFeitaSelecionada.getId();
        AlertDialog dialog;
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Excluir tarefa");
        b.setMessage("Tem certeza que deseja excluir essa tarefa?");
        b.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                long remocao=tarefaCtrl.deletar(idTarefa);
                if(remocao!=-1){
                    Toast.makeText(v.getContext(), "Tarefa excluida!", Toast.LENGTH_SHORT).show();
                    listaFeitas=tarefaCtrl.selecionarFeitas();
                    adpFeitas=new ArrayAdapter<Tarefa>(b.getContext(), android.R.layout.simple_spinner_dropdown_item, listaFeitas);
                    spinnerFeitas.setAdapter(adpFeitas);
                    if(spinnerFeitas.getSelectedItem()==null) btnExcluirTarefaFeita.setVisibility(View.INVISIBLE);
                }
                else Toast.makeText(v.getContext(), "Erro ao excluir tarefa!", Toast.LENGTH_SHORT).show();
            }
        });
        b.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog=b.create();
        dialog.show();
    }
}