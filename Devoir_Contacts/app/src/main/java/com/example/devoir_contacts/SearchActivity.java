package com.example.devoir_contacts;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    FirebaseFirestore db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        db = FirebaseFirestore.getInstance();
        Button btn_nom = (Button) findViewById(R.id.btn_nom_s);
        Button btn_num = (Button) findViewById(R.id.btn_num_s);
        EditText nom = (EditText) findViewById(R.id.editnom_s);
        EditText num = (EditText) findViewById(R.id.editnum_s);
        ListView listView = (ListView) findViewById(R.id.list_s);
        List<String> contactsList = new ArrayList<>();

        btn_nom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nom.getText().toString()!=""){
                    CollectionReference contactsCollection = db.collection("contacts");
                    Query query = contactsCollection.whereEqualTo("nom", nom.getText().toString());
                    query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                QuerySnapshot querySnapshot = task.getResult();
                                if (querySnapshot.isEmpty()) {
                                    toast("Le contact avec ce nom n'existe pas");
                                } else {
                                    // Le document existe
                                    //DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
                                    //String documentId = documentSnapshot.getId();
                                    for (QueryDocumentSnapshot document : querySnapshot) {
                                        Contact c = document.toObject(Contact.class);
                                        contactsList.add(c._toString());
                                    }
                                    ArrayAdapter<String> adapter = new ArrayAdapter<>(SearchActivity.this, android.R.layout.simple_list_item_1, contactsList);
                                    listView.setAdapter(adapter);
                                    // Faire quelque chose avec l'identifiant du document
                                }
                            } else {
                                // La requête a échoué
                            }
                        }
                    });
                }else{
                    toast("Le champ nom n'est pas rempli");
                }
            }
        });

        btn_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(num.getText().toString()!=""){
                    CollectionReference contactsCollection = db.collection("contacts");
                    Query query = contactsCollection.whereEqualTo("numero", num.getText().toString());
                    query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                QuerySnapshot querySnapshot = task.getResult();
                                if (querySnapshot.isEmpty()) {
                                    toast("Le contact avec ce numéro n'existe pas");
                                } else {
                                    // Le document existe
                                    //DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
                                    //String documentId = documentSnapshot.getId();
                                    for (QueryDocumentSnapshot document : querySnapshot) {
                                        Contact c = document.toObject(Contact.class);
                                        contactsList.add(c._toString());
                                    }
                                    ArrayAdapter<String> adapter = new ArrayAdapter<>(SearchActivity.this, android.R.layout.simple_list_item_1, contactsList);
                                    listView.setAdapter(adapter);
                                    // Faire quelque chose avec l'identifiant du document
                                }
                            } else {
                                // La requête a échoué
                            }
                        }
                    });
                }else{
                    toast("Le champ numéro n'est pas rempli");
                }
            }
        });

    }

    public void toast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
