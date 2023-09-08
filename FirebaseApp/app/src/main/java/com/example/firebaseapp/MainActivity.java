package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();
        Button btn = (Button) findViewById(R.id.btn);
        Button btnprsn = (Button) findViewById(R.id.btnprsn);
        EditText prenom = (EditText) findViewById(R.id.editprenom);
        EditText nom = (EditText) findViewById(R.id.editnom);
        EditText sexe = (EditText) findViewById(R.id.editsexe);
        EditText age = (EditText) findViewById(R.id.editage);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prenom!=null & nom!=null & sexe!=null & age!=null){
                    Personne personne = new Personne(prenom.getText().toString(), nom.getText().toString(), sexe.getText().toString(), age.getText().toString());
                    CollectionReference personneCollection = db.collection("personnes");
                    personneCollection.get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot querySnapshot) {
                                    int nombreDocuments = querySnapshot.size();
                                    personne.setId(nombreDocuments+1);
                                    personneCollection.document(String.valueOf(nombreDocuments+1)).set(personne).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            toast("La personne a été ajouté avec succès");
                                            prenom.setText("");
                                            nom.setText("");
                                            sexe.setText("");
                                            age.setText("");
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            toast("La personne n'a pas été ajouté");
                                        }
                                    });
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // La récupération des documents a échoué
                                }
                            });

                }else{
                    toast("Tous les champs ne sont pas remplis");
                }

            }
        });

        btnprsn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent affichePage = new Intent(getApplicationContext(), AfficheActivity.class);
                startActivity(affichePage);
            }
        });


    }
    public void toast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}