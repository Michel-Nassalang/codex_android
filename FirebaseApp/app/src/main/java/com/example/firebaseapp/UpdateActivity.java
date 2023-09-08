package com.example.firebaseapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class UpdateActivity extends AppCompatActivity {

    FirebaseFirestore db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        db = FirebaseFirestore.getInstance();
        Button btn = (Button) findViewById(R.id.btn);
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
                    // Récupérer une liste de tous les documents dans la collection
                    Query query = personneCollection.whereEqualTo("prenom", "John")
                            .whereEqualTo("nom", "Doe");
                    query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                QuerySnapshot querySnapshot = task.getResult();
                                if (querySnapshot.isEmpty()) {
                                    // Le document n'existe pas
                                } else {
                                    // Le document existe
                                    DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
                                    String documentId = documentSnapshot.getId();
                                    // Faire quelque chose avec l'identifiant du document
                                }
                            } else {
                                // La requête a échoué
                            }
                        }
                    });
                }else{
                    toast("Tous les champs ne sont pas remplis");
                }

            }
        });

    }
    public void toast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
