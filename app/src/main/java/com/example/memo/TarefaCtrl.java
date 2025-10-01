package com.example.memo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class TarefaCtrl {
    TarefaOpen conex;
    String nomeAnt, horaAnt;
    long datalongAnt;
    public TarefaCtrl(Context ctx){
        conex=new TarefaOpen(ctx);
    }
    public long inserir(Tarefa t){
        SQLiteDatabase bd=conex.getWritableDatabase();
        ContentValues ctv =new ContentValues();
        ctv.put("NOME", t.getNome());
        ctv.put("DATA", t.getData());
        ctv.put("HORA", t.getHora());
        ctv.put("DATALONG", t.getDataLong());
        ctv.put("STATUS", 0);
        long insercao=bd.insert("TAREFA", null, ctv);
        bd.close();
        return insercao;
    }
    public long deletar(int idTarefa){
        SQLiteDatabase bd=conex.getWritableDatabase();
        String[] args=new String[]{String.valueOf(idTarefa)};
        long remocao=bd.delete("TAREFA", "ID=?", args);
        bd.close();
        return remocao;
    }
    public long atualizarDados(Tarefa t){
        SQLiteDatabase bd=conex.getWritableDatabase();
        ContentValues ctv=new ContentValues();
        String[] args=new String[]{String.valueOf(t.getId())};
        ctv.put("NOME", t.getNome());
        ctv.put("DATA", t.getData());
        ctv.put("HORA", t.getHora());
        ctv.put("DATALONG", t.getDataLong());
        ctv.put("STATUS", t.getStatus());
        long atualizacao=bd.update("TAREFA", ctv, "ID=?", args);
        bd.close();
        return atualizacao;
    }
    public long atualizarStatus(int idTarefa){
        SQLiteDatabase bd=conex.getWritableDatabase();
        ContentValues ctv=new ContentValues();
        String[] args=new String[]{String.valueOf(idTarefa)};
        ctv.put("STATUS", 1);
        long atualizacao=bd.update("TAREFA", ctv, "ID=?", args);
        bd.close();
        return atualizacao;
    }
    public ArrayList<Tarefa> selecionarPendentes(){
        SQLiteDatabase bd=conex.getReadableDatabase();
        String[] args= new String[]{String.valueOf(0)};
        Cursor r=bd.rawQuery("SELECT * FROM TAREFA WHERE STATUS=?", args);
        ArrayList<Tarefa> listaPendentes =new ArrayList<Tarefa>();
        while (r.moveToNext()){
            int id=r.getInt(0);
            String nome=r.getString(1);
            String data=r.getString(2);
            String hora=r.getString(3);
            long datalong=r.getLong(4);
            int status=r.getInt(5);
            Tarefa t=new Tarefa(id, nome, data, hora, datalong, status);
            listaPendentes.add(t);
        }
        r.close();
        bd.close();
        return listaPendentes;
    }
    public ArrayList<Tarefa> selecionarFeitas(){
        SQLiteDatabase bd=conex.getReadableDatabase();
        String[] args={"1"};
        Cursor r=bd.rawQuery("SELECT * FROM TAREFA WHERE STATUS=?", args);
        ArrayList<Tarefa> listaFeitas =new ArrayList<Tarefa>();
        while (r.moveToNext()){
            int id=r.getInt(0);
            String nome=r.getString(1);
            String data=r.getString(2);
            String hora=r.getString(3);
            long datalong=r.getLong(4);
            int status=r.getInt(5);
            Tarefa t=new Tarefa(id, nome, data, hora, datalong, status);
            listaFeitas.add(t);
        }
        r.close();
        bd.close();
        return listaFeitas;
    }
    public Tarefa tarefaSelecionada(int id){
        Tarefa t;
        SQLiteDatabase bd=conex.getReadableDatabase();
        String[] args={String.valueOf(id)};
        Cursor r=bd.query("TAREFA", null, "ID=?", args, null, null, null, null);
        if(r!=null){
            if(r.moveToFirst()){
                nomeAnt=r.getString(r.getColumnIndexOrThrow("NOME"));
                horaAnt=r.getString(r.getColumnIndexOrThrow("HORA"));
                datalongAnt=r.getLong(r.getColumnIndexOrThrow("DATALONG"));
            }
        }
        t=new Tarefa(id, nomeAnt, horaAnt, datalongAnt);
        return t;
    }
}
