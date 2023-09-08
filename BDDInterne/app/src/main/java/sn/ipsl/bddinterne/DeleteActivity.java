package sn.ipsl.bddinterne;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supp_activity);

        Button btnsuppprenom = (Button) findViewById(R.id.btnsuppprenom);
        Button btnsuppnom = (Button) findViewById(R.id.btnsuppnom);
        EditText prenom = (EditText) findViewById(R.id.editsuppprenom);
        EditText nom = (EditText) findViewById(R.id.editsuppprenom);


        PersonneBDD personneBdd = new PersonneBDD(this);

        btnsuppprenom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prenom.getText()!= null){
                    personneBdd.open();
                    Personne personneFromBdd = personneBdd.recupererPersonneAvecSonPrenom(prenom.getText().toString());
                    if (personneFromBdd != null) {
                        Personne supp = personneFromBdd;
                        personneBdd.suprimerPersonneAvecID(personneFromBdd.getId());
                        toast("Personne supprimé: \n"+supp.toString());
                    }else {
                        toast("Cette Personne n'existe dans la BDD");
                    }
                    personneBdd.close();
                }else {
                    toast("Entrée nulle");
                }
            }
        });
        btnsuppnom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nom.getText()!= null){
                    personneBdd.open();
                    Personne personneFromBdd = personneBdd.recupererPersonneAvecSonPrenom(nom.getText().toString());
                    if (personneFromBdd != null) {
                        Personne supp = personneFromBdd;
                        personneBdd.suprimerPersonneAvecID(personneFromBdd.getId());
                        toast("Personne supprimé: \n"+supp.toString());
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
