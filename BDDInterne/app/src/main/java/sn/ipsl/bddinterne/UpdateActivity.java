package sn.ipsl.bddinterne;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_activity);

        Button up = (Button) findViewById(R.id.btnupdate);
        Button search = (Button) findViewById(R.id.search);
        EditText prenom = (EditText) findViewById(R.id.editupprenom);
        EditText nom = (EditText) findViewById(R.id.editupnom);
        EditText prenomup = (EditText) findViewById(R.id.prenomup);
        EditText nomup = (EditText) findViewById(R.id.nomup);

        PersonneBDD personneBdd = new PersonneBDD(this);
        
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prenom.getText()!= null){
                    personneBdd.open();
                    Personne personneFromBdd = personneBdd.recupererPersonneAvecSonPrenom(prenom.getText().toString());
                    if (personneFromBdd != null) {
                        prenomup.setText(personneFromBdd.getPrenom());
                        nomup.setText(personneFromBdd.getNom());
                    }else {
                        toast("Cette Personne n'existe dans la BDD");
                    }
                    personneBdd.close();
                } else if (nom.getText()!= null) {
                    personneBdd.open();
                    Personne personneFromBdd = personneBdd.recupererPersonneAvecSonNom(nom.getText().toString());
                    if (personneFromBdd != null) {
                        prenomup.setText(personneFromBdd.getPrenom());
                        nomup.setText(personneFromBdd.getNom());
                    }else {
                        toast("Cette Personne n'existe dans la BDD");
                    }
                    personneBdd.close();
                } else {
                    toast("Entrée nulle");
                }
            }
        });

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prenom.getText()!= null){
                    personneBdd.open();
                    Personne personneFromBdd = personneBdd.recupererPersonneAvecSonPrenom(prenom.getText().toString());
                    if (personneFromBdd != null) {
                        personneFromBdd.setNom(nomup.getText().toString());
                        personneFromBdd.setPrenom(nomup.getText().toString());
                        personneBdd.updatePersonne(personneFromBdd.getId(), personneFromBdd);
                    }else {
                        toast("Cette Personne n'existe dans la BDD");
                    }
                    personneBdd.close();
                }else {
                    toast("Entrée nulle");
                }
            }
        });
    }
    public void toast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
