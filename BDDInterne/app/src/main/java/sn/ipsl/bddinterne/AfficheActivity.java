package sn.ipsl.bddinterne;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AfficheActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affiche_activity);

        Button affp = (Button) findViewById(R.id.btnaffprenom);
        Button affn = (Button) findViewById(R.id.btnaffnom);
        EditText prenom = (EditText) findViewById(R.id.editaffprenom);
        EditText nom = (EditText) findViewById(R.id.editaffnom);
        TextView textid = (TextView) findViewById(R.id.textid);
        TextView textprenom = (TextView) findViewById(R.id.textprenom);
        TextView textnom = (TextView) findViewById(R.id.textnom);

        PersonneBDD personneBdd = new PersonneBDD(this);

        affp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prenom.getText()!= null){
                    personneBdd.open();
                    Personne personneFromBdd = personneBdd.recupererPersonneAvecSonPrenom(prenom.getText().toString());
                    if (personneFromBdd != null) {
                        String id = String.valueOf(personneFromBdd.getId());
                        textid.setText(id);
                        textprenom.setText(personneFromBdd.getPrenom());
                        textnom.setText(personneFromBdd.getNom());
                    }else {
                        toast("Cette Personne n'existe dans la BDD");
                    }
                    personneBdd.close();
                }else {
                    toast("Entrée nulle");
                }
            }
        });
        affn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nom.getText()!= null){
                    personneBdd.open();
                    Personne personneFromBdd = personneBdd.recupererPersonneAvecSonNom(nom.getText().toString());
                    if (personneFromBdd != null) {
                        String id = String.valueOf(personneFromBdd.getId());
                        textid.setText(id);
                        textprenom.setText(personneFromBdd.getPrenom());
                        textnom.setText(personneFromBdd.getNom());
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
