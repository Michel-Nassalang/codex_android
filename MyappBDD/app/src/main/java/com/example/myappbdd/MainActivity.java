package com.example.myappbdd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PersonneBDD personneBdd = new PersonneBDD(this);

        Personne personne = new Personne("Rosine", "Boissy");

        personneBdd.open();

        // Insertion personne
        personneBdd.insertPersonne(personne);


        // Mise à jour d'une personne
        Personne personneFromBdd = personneBdd.getPersonneByNom(personne.getNom());
        if(personneFromBdd != null){
            Toast.makeText(this, personneFromBdd.toString(), Toast.LENGTH_LONG).show();

            personneFromBdd.setNom("BelArt");
            personneBdd.updatePersonne(personneFromBdd.getId(), personneFromBdd);
        }

        // Suppression d'une personne
        personneFromBdd = personneBdd.getPersonneByNom("BelArt");
        if(personneFromBdd != null){
            Toast.makeText(this, personneFromBdd.toString(), Toast.LENGTH_LONG).show();
            personneBdd.deletePersonne(personneFromBdd.getId());
        }

        // Verification de l'existence d'une personne
        personneFromBdd = personneBdd.getPersonneByNom("BelArt");
        if(personneFromBdd == null){
            Toast.makeText(this, "Cette Personne n'existe pas dans la BDD",
                    Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Cette Personne existe dans la BDD",
                    Toast.LENGTH_LONG).show();
        }

        personneBdd.close();

    }
}