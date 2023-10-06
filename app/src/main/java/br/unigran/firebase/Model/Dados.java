package br.unigran.firebase.Model;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Dados {
    DatabaseReference databaseReference;
    public Dados() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }
    public String salvar(Pessoa p){
    
    }
}
