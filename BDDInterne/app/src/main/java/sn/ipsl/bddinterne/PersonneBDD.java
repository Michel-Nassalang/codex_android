package sn.ipsl.bddinterne;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PersonneBDD {
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "etudiantugb.db";

    private static final String TABLE_PERSONNES = "table_personnes";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_PRENOM = "PRENOM";
    private static final int NUM_COL_PRENOM = 1;
    private static final String COL_NOM = "NOM";
    private static final int NUM_COL_NOM = 2;

    private SQLiteDatabase bdd;


    private PersonneSQLite personneSQLite;

    public PersonneBDD(Context context) {
        // On cree la BDD et sa table
        personneSQLite = new PersonneSQLite(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open() {
        // on ouvre la BDD en ecriture
        bdd = personneSQLite.getWritableDatabase();
    }

    public void close() {
        // on ferme l'acces a la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD() {
        return bdd;
    }

    public long insertPersonne(Personne personne) {
        // Creation d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        // on lui ajoute une valeur associee a une cle (qui est le nom de la
        // colonne dans laquelle on veut mettre la valeur)
        values.put(COL_PRENOM, personne.getPrenom());
        values.put(COL_NOM, personne.getNom());
        // on insere l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_PERSONNES, null, values);
    }

    public int updatePersonne(int id, Personne personne) {

        ContentValues values = new ContentValues();
        values.put(COL_PRENOM, personne.getPrenom());
        values.put(COL_NOM, personne.getNom());
        return bdd.update(TABLE_PERSONNES, values, COL_ID + " " +
                " = " + id, null);
    }

    public int suprimerPersonneAvecID(int id) {
        // Suppression d'une Personne de la BDD grace � l'ID
        return bdd.delete(TABLE_PERSONNES, COL_ID + " = " + id, null);
    }

    public Personne recupererPersonneAvecSonNom(String nom) {
        // Recupere dans un Cursor les valeurs correspondant a un Personne contenu
        // dans la BDD (ici on selectionne la Personne grace a son nom)
        Cursor c = bdd.query(TABLE_PERSONNES, new String[] { COL_ID, COL_PRENOM,
                        COL_NOM }, COL_NOM + " LIKE \"" + nom + "\"",
                null, null, null, null);
        return cursorAPersonne(c);
    }
    public Personne recupererPersonneAvecSonPrenom(String prenom) {
        // Recupere dans un Cursor les valeurs correspondant a un Personne contenu
        // dans la BDD (ici on selectionne la Personne grace a son nom)
        Cursor c = bdd.query(TABLE_PERSONNES, new String[] { COL_ID, COL_PRENOM,
                        COL_NOM }, COL_PRENOM + " LIKE \"" + prenom + "\"",
                null, null, null, null);
        return cursorAPersonne(c);
    }

    // Cette methode permet de convertir un cursor en une Personne
    private Personne cursorAPersonne(Cursor c) {
        // si aucun element n'a ete retourne dans la requete, on renvoie null
        if (c.getCount() == 0)
            return null;

        c.moveToFirst();
        // On cree une Personne
        Personne personne = new Personne();
        // on lui affecte toutes les infos grace aux infos contenues dans le Cursor
        personne.setId(c.getInt(NUM_COL_ID));
        personne.setPrenom(c.getString(NUM_COL_PRENOM));
        personne.setNom(c.getString(NUM_COL_NOM));
        // On ferme le cursor
        c.close();

        // On retourne la Personne
        return personne;
    }
}
