package com.example.memo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ActivityAddTarefa extends AppCompatActivity {
    Spinner spinnerHorarios;
    CalendarView calendarView;
    EditText edtNomeTarefa;
    ArrayList<String> listaHorarios;
    ArrayAdapter<String> adpHorarios;

    //variáveis para montar a tarefa
    private String nomeTarefa, dataString, horarioSelecionado;
    private long dataLong;
    private SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    TarefaCtrl tarefaCtrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_tarefa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tarefaCtrl=new TarefaCtrl(this);

        spinnerHorarios=findViewById(R.id.spinnerHorarios2);
        calendarView=findViewById(R.id.calendarView2);
        edtNomeTarefa=findViewById(R.id.edtNomeTarefa2);

        listaHorarios=new ArrayList<String>();
        listaHorarios.add("00:00"); listaHorarios.add("01:00"); listaHorarios.add("02:00");
        listaHorarios.add("03:00"); listaHorarios.add("04:00"); listaHorarios.add("05:00");
        listaHorarios.add("06:00"); listaHorarios.add("07:00"); listaHorarios.add("08:00");
        listaHorarios.add("09:00"); listaHorarios.add("10:00"); listaHorarios.add("11:00");
        listaHorarios.add("12:00"); listaHorarios.add("13:00"); listaHorarios.add("14:00");
        listaHorarios.add("15:00"); listaHorarios.add("16:00"); listaHorarios.add("17:00");
        listaHorarios.add("18:00"); listaHorarios.add("19:00"); listaHorarios.add("20:00");
        listaHorarios.add("21:00"); listaHorarios.add("22:00"); listaHorarios.add("23:00");

        adpHorarios=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listaHorarios);
        spinnerHorarios.setAdapter(adpHorarios);

        spinnerHorarios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                horarioSelecionado=spinnerHorarios.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dataLong=calendarView.getDate();
        dataString=sdf.format(new Date(dataLong));
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
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
    public void salvar(View v){
        //salvando tarefa no bd
        nomeTarefa=edtNomeTarefa.getText().toString();
        horarioSelecionado=spinnerHorarios.getSelectedItem().toString();
        if(nomeTarefa.isEmpty()){
            Toast.makeText(this, "Por favor, preencha o nome da tarefa", Toast.LENGTH_SHORT).show();
            return;
        }

        long insercao=tarefaCtrl.inserir(new Tarefa(nomeTarefa, dataString, horarioSelecionado, dataLong, 0));
        if(insercao!=-1) Toast.makeText(this, "Tarefa adicionada!", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "Erro ao adicionar tarefa!", Toast.LENGTH_SHORT).show();
        finish();
    }
}