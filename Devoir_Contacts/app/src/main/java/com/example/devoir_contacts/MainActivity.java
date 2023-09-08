package com.example.devoir_contacts;

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
        Button btn_contacts = (Button) findViewById(R.id.btncontact);
        Button btn_rech = (Button) findViewById(R.id.btn_rech);
        Button btn_supp = (Button) findViewById(R.id.btn_supp);
        EditText nom = (EditText) findViewById(R.id.editnom);
        EditText num = (EditText) findViewById(R.id.editnum);
        EditText addr = (EditText) findViewById(R.id.editadrr);

        // Création de contact
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nom.getText().toString()!=null && num.getText().toString()!=null){
                    Contact contact;
                    if(addr.getText().toString()!=""){
                        contact = new Contact(nom.getText().toString(), num.getText().toString(), addr.getText().toString());
                    }else{
                        contact = new Contact(nom.getText().toString(), num.getText().toString());
                    }
                    CollectionReference contactsCollection = db.collection("contacts");
                    contactsCollection.get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot querySnapshot) {
                                    int nombreDocuments = querySnapshot.size();
                                    contact.setId(nombreDocuments+1);
                                    contactsCollection.document(String.valueOf(nombreDocuments+1)).set(contact).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            toast("Le contact a été ajouté avec succès");
                                            nom.setText("");
                                            num.setText("");
                                            addr.setText("");
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            toast("Le contact n'a pas été ajouté");
                                        }
                                    });
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    toast("La recupération du nombre de documents a échoué");
                                }
                            });

                }else{
                    toast("Tous les champs nécessaires n'ont pas été remplis");
                }
            }
        });
        // Fin de création de contact

        // Page recherche contact

        btn_rech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        // Page Affiche contact

        btn_contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AfficheActivity.class);
                startActivity(intent);
            }
        });

        // Page Suppression contact

        btn_supp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DeleteActivity.class);
                startActivity(intent);
            }
        });


    }
    public void toast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}