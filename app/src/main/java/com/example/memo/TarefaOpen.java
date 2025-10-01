package com.example.memo;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TarefaOpen extends SQLiteOpenHelper {
    public TarefaOpen(Context ctx){
        super(ctx, "MEMO_DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String x="CREATE TABLE TAREFA (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NOME TEXT NOT NULL, " +
                "DATA TEXT NOT NULL, " +
                "HORA TEXT NOT NULL, " +
                "DATALONG LONG NOT NULL, " +
                "STATUS INTEGER NOT NULL)"; //0 para não feito, 1 para feito
        db.execSQL(x);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}