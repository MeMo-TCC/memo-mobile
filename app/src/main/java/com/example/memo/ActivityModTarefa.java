package com.example.memo;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ActivityModTarefa extends AppCompatActivity {
    CalendarView calendarView2;
    Spinner spinnerHorarios2;
    EditText edtNomeTarefa2;
    Button btnAttTarefa, btnTarefaFeita;
    ArrayList<String> listaHorarios2;
    ArrayAdapter<String> adpHorarios2;
    private int idTarefa;
    private String nomeTarefa, dataString, horarioSelecionado;
    private long dataLong;
    private SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    TarefaCtrl tarefaCtrl;
    Tarefa t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mod_tarefa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tarefaCtrl=new TarefaCtrl(this);

        calendarView2=findViewById(R.id.calendarView2);
        spinnerHorarios2=findViewById(R.id.spinnerHorarios2);
        edtNomeTarefa2=findViewById(R.id.edtNomeTarefa2);
        btnAttTarefa=findViewById(R.id.btnAttTarefa);
        btnTarefaFeita=findViewById(R.id.btnTarefaFeita);

        if(getIntent().hasExtra("idTarefa")){
            idTarefa=getIntent().getIntExtra("idTarefa", -1);
            if(idTarefa!=-1){
                t=tarefaCtrl.tarefaSelecionada(idTarefa);
            }
        }

        listaHorarios2=new ArrayList<String>();
        listaHorarios2.add("00:00"); listaHorarios2.add("01:00"); listaHorarios2.add("02:00");
        listaHorarios2.add("03:00"); listaHorarios2.add("04:00"); listaHorarios2.add("05:00");
        listaHorarios2.add("06:00"); listaHorarios2.add("07:00"); listaHorarios2.add("08:00");
        listaHorarios2.add("09:00"); listaHorarios2.add("10:00"); listaHorarios2.add("11:00");
        listaHorarios2.add("12:00"); listaHorarios2.add("13:00"); listaHorarios2.add("14:00");
        listaHorarios2.add("15:00"); listaHorarios2.add("16:00"); listaHorarios2.add("17:00");
        listaHorarios2.add("18:00"); listaHorarios2.add("19:00"); listaHorarios2.add("20:00");
        listaHorarios2.add("21:00"); listaHorarios2.add("22:00"); listaHorarios2.add("23:00");

        adpHorarios2=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listaHorarios2);
        spinnerHorarios2.setAdapter(adpHorarios2);

        edtNomeTarefa2.setText(t.getNome());
        calendarView2.setDate(t.getDataLong());
        int pos=adpHorarios2.getPosition(t.getHora());
        spinnerHorarios2.setSelection(pos);

        spinnerHorarios2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                horarioSelecionado=spinnerHorarios2.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dataLong=calendarView2.getDate();
        dataString=sdf.format(new Date(dataLong));
        calendarView2.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                java.util.Calendar cal=java.util.Calendar.getInstance();
                cal.set(year, month, dayOfMonth, 0, 0, 0);
                cal.set(java.util.Calendar.MILLISECOND, 0);
                dataLong=cal.getTimeInMillis();
                dataString=sdf.format(new Date(dataLong));
            }
        });
    }
    public void attTarefa(View v){
        //função para atualizar tarefa no banco de dados
        idTarefa=getIntent().getIntExtra("idTarefa", -1);
        if(idTarefa==-1){
            return;
        }

        nomeTarefa=edtNomeTarefa2.getText().toString();
        if(nomeTarefa.isEmpty()){
            Toast.makeText(this, "Por favor, preencha o nome da tarefa", Toast.LENGTH_SHORT).show();
            return;
        }

        long atualizacao=tarefaCtrl.atualizarDados(new Tarefa(idTarefa, nomeTarefa, dataString, horarioSelecionado, dataLong));
        if(atualizacao!=-1) Toast.makeText(this, "Tarefa atualizada", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "Erro ao adicionar tarefa!", Toast.LENGTH_SHORT).show();
        finish();
    }
    public void feita(View v){
        //função para marcar tarefa como feita
        idTarefa=getIntent().getIntExtra("idTarefa", -1);
        if(idTarefa==-1){
            return;
        }
        long atualizacao=tarefaCtrl.atualizarStatus(idTarefa);
        if(atualizacao!=-1) Toast.makeText(this, "Tarefa atualizada", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "Erro ao adicionar tarefa!", Toast.LENGTH_SHORT).show();
        finish();
    }
    public void excluirTarefa(View v){
        idTarefa=getIntent().getIntExtra("idTarefa", -1);
        new AlertDialog.Builder(this)
                .setTitle("Excluir tarefa")
                .setMessage("Você tem certeza que deseja excluir essa tarefa?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        long remocao=tarefaCtrl.deletar(idTarefa);
                        if(remocao!=-1) Toast.makeText(v.getContext(), "Tarefa excluir!", Toast.LENGTH_SHORT).show();
                        else Toast.makeText(v.getContext(), "Erro ao excluir tarefa!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }
    public void voltar(View v){
        finish();
    }
}