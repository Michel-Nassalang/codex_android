package sn.ipsl.bddinterne;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout_activity);

        Button addp = (Button) findViewById(R.id.btnaddadd);
        EditText prenom = (EditText) findViewById(R.id.editprenom);
        EditText nom = (EditText) findViewById(R.id.editnom);

        PersonneBDD personneBdd = new PersonneBDD(this);
        addp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prenom.getText()!= null & nom.getText() != null){
                    Personne personne = new Personne(prenom.getText().toString(), nom.getText().toString());
                    personneBdd.open();
                    personneBdd.insertPersonne(personne);
                    Personne personneFromBdd = personneBdd.recupererPersonneAvecSonNom(personne.getNom());
                    if (personneFromBdd != null) {
                        toast(personneFromBdd.toString());
                    }else {
                        toast("Cette Personne existe dans la BDD");
                    }
                    personneBdd.close();
                }
            }
        });
    }
    public void toast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
