package com.example.myappbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PersonneBDD {
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "etudiantugb.db";

    private static final String TABLE_PERSONNES = "tables_personnes";

    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_PRENOM = "PRENOM";
    private static final int NUM_COL_PRENOM = 1;
    private static final String COL_NOM = "NOM";
    private static final int NUM_COL_NOM = 2;

    private PersonneSQLite personneSQLite;
    private SQLiteDatabase bdd;

    public PersonneBDD (Context context){
        personneSQLite = new PersonneSQLite(context, NOM_BDD, null, VERSION_BDD);
    }
    public void open(){
        bdd = personneSQLite.getWritableDatabase();
    }
    public void close(){
        bdd.close();
    }
    public SQLiteDatabase getBdd(){
        return bdd;
    }
    public long insertPersonne(Personne personne){
        ContentValues values = new ContentValues();
        values.put(COL_PRENOM, personne.getPrenom());
        values.put(COL_NOM, personne.getNom());
        return bdd.insert(TABLE_PERSONNES, null, values);
    }

    public int updatePersonne(int id, Personne personne){
        ContentValues values = new ContentValues();
        values.put(COL_PRENOM, personne.getPrenom());
        values.put(COL_NOM, personne.getNom());
        return bdd.update(TABLE_PERSONNES, values, COL_ID + " = " + id, null);
    }

    public int deletePersonne(int id){
        return bdd.delete(TABLE_PERSONNES, COL_ID + " = " + id, null);
    }

    public Personne getPersonneByNom(String nom){
        Cursor c = bdd.query(TABLE_PERSONNES, new String[] { COL_ID, COL_PRENOM,
                        COL_NOM }, COL_NOM + " LIKE \"" + nom + "\"",
                null, null, null, null);
        return cursorPersonne(c);
    }
    private Personne cursorPersonne(Cursor c){
        if(c.getCount() == 0) return null;
        c.moveToFirst();
        Personne personne = new Personne();
        personne.setId(c.getInt(NUM_COL_ID));
        personne.setPrenom(c.getString(NUM_COL_PRENOM));
        personne.setNom(c.getString(NUM_COL_NOM));
        c.close();
        return personne;
    }
}
