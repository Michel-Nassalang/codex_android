package sn.ipsl.bddinterne;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Creation d'une instance de ma classe PersonneBDD
        PersonneBDD personneBdd = new PersonneBDD(this);

        // Creation d'une Personne
        Personne personne = new Personne("FALLOU", "DIOP");

        // On ouvre la base de donnees pour ecrire dedans
        personneBdd.open();
        // On insere la Personne que l'on vient de creer
        personneBdd.insertPersonne(personne);

        // Pour verifier que l'on a bien cree notre Personne dans la BDD
        // on extrait la Personne de la BDD grace au nom de le Personne que l'on a cree
        // precedemment
        Personne personneFromBdd = personneBdd.recupererPersonneAvecSonNom(personne.getNom());
        // Si une Personne est retourne (donc si la Personne a bien ete ajoute a la
        // BDD)
        if (personneFromBdd != null) {
            // On affiche les infos de la Personne dans un Toast
            Toast.makeText(this, personneFromBdd.toString(), Toast.LENGTH_LONG)
                    .show();
            // On modifie le nom de la Personne
            personneFromBdd.setNom("Bambey");
            // Puis on met a jour la BDD
           personneBdd.updatePersonne(personneFromBdd.getId(), personneFromBdd);
        }

        // On extrait la Personne de la BDD grace au nouveau Personne
        personneFromBdd = personneBdd
                .recupererPersonneAvecSonNom("Bambey");
        // S'il existe une Personne possedant ce nom dans la BDD
        if (personneFromBdd != null) {
            // On affiche les nouvelles informations de la Personne pour verifier que
            // le nom de la Personne  a bien ete mis a jour
            Toast.makeText(this, personneFromBdd.toString(), Toast.LENGTH_LONG)
                    .show();
            // on supprime la Personne de la BDD grace a son ID
          //  personneBdd.suprimerPersonneAvecID(personneFromBdd.getId());
        }

        // On essaye d'extraire de nouveau la Personne de la BDD toujours grace a
        // son nouveau nom
        personneFromBdd = personneBdd
                .recupererPersonneAvecSonNom("Bambey");
        // Si aucune Personne n'est retourne
        if (personneFromBdd == null) {
            // On affiche un message indiquant que la Personne n'existe pas dans la
            // BDD
            Toast.makeText(this, "Cette Personne n'existe pas dans la BDD",
                    Toast.LENGTH_LONG).show();
        }
        // Si la Personne existe (mais normalement il ne devrait pas)
        else {
            // on affiche un message indiquant que la Personne existe dans la BDD
            Toast.makeText(this, "Cette Personne existe dans la BDD",
                    Toast.LENGTH_LONG).show();
        }

        personneBdd.close();

        //-------------------------------
        Button btnadd = (Button) findViewById(R.id.btnadd);
        Button btnaff = (Button) findViewById(R.id.btnaff);
        Button btndel = (Button) findViewById(R.id.btndel);
        Button btnup = (Button) findViewById(R.id.btnup);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(intent);
            }
        });
        btnaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AfficheActivity.class);
                startActivity(intent);
            }
        });
        btnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UpdateActivity.class);
                startActivity(intent);
            }
        });
        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DeleteActivity.class);
                startActivity(intent);
            }
        });
    }
}