package com.example.devoir_contacts;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class ModifyActivity extends AppCompatActivity {
    FirebaseFirestore db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        Button btn_nom = (Button) findViewById(R.id.btn_nom_m);
        Button btn_num = (Button) findViewById(R.id.btn_num_m);
        Button btn_modif = (Button) findViewById(R.id.btn_modif);
        EditText nom = (EditText) findViewById(R.id.editnom_m);
        EditText num = (EditText) findViewById(R.id.editnum_m);

        EditText nomm = (EditText) findViewById(R.id.edit_nom_m);
        EditText numm = (EditText) findViewById(R.id.edit_num_m);
        EditText addrm = (EditText) findViewById(R.id.edit_m_a);

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
                                    DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
                                    String documentId = documentSnapshot.getId();
                                    Contact c = documentSnapshot.toObject(Contact.class);
                                    nomm.setText(c.getNom());
                                    numm.setText(c.getNumero());
                                    addrm.setText(c.getAdresse());
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
                                    DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
                                    String documentId = documentSnapshot.getId();
                                    contactsCollection.document(documentId).delete();
                                    toast("Le contact avec ce numéro est désormais supprimé");
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
