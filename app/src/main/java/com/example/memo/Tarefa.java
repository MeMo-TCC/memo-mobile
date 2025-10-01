package com.example.memo;

public class Tarefa {
    private int id;
    private String nome;
    private String data="";
    private String hora;
    private long datalong; //variável para mostrar data no calendarView
    private int status=0;

    /*public Tarefa(int id, String nome, String data, String hora, long datalong){
        this.id=id;
        this.nome=nome;
        this.data=data;
        this.hora=hora;
        this.datalong=datalong;
        status=0; //status já será pré-definido como não feito
    }*/
    public Tarefa(int id, String nome, String data, String hora, long datalong, int status){
        //metodo construtor para ser usado apos select no bd
        this.id=id;
        this.nome=nome;
        this.data=data;
        this.hora=hora;
        this.datalong=datalong;
        this.status=status;
    }
    public Tarefa(String nome, String data, String hora, long datalong, int status){
        //metodo construtor para ser inserir tarefa no bd
        this.nome=nome;
        this.data=data;
        this.hora=hora;
        this.datalong=datalong;
        this.status=status;
    }
    public Tarefa(int id, String nome, String data, String hora, long datalong){
        //metodo construtor para atualizar tarefa no bd
        this.id=id;
        this.nome=nome;
        this.data=data;
        this.hora=hora;
        this.datalong=datalong;
    }
    public Tarefa(int id, String nome, String hora, long datalong){
        this.id=id;
        this.nome=nome;
        this.hora=hora;
        this.datalong=datalong;
    }
    public int getId(){
        return id;
    }
    public String getNome(){
        return nome;
    }
    public String getData(){
        return data;
    }
    public String getHora(){
        return hora;
    }
    public long getDataLong() {
        return datalong;
    }
    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return getData()+" - "+getHora()+": "+getNome();
    }
}
