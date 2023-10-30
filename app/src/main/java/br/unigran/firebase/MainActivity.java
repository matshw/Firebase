package br.unigran.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.unigran.firebase.Model.AdapterR;
import br.unigran.firebase.Model.Pessoa;

public class MainActivity extends AppCompatActivity {
    TextView nome;
    TextView contato;
    RatingBar avaliacao;
    ImageButton salvar;
    AdapterR adapter;
    List<Pessoa> listaPessoa;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.idRecycle);
        nome= findViewById(R.id.idNome);
        contato = findViewById(R.id.idContato);
        avaliacao = findViewById(R.id.idRating);
        salvar = findViewById(R.id.idBotao);
        listaPessoa = new ArrayList<>();
        adapter = new AdapterR(listaPessoa);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listar();
    }

    public void salvar(View view) {
        Pessoa p = new Pessoa();
        String nomeC = nome.getText().toString().trim();
        String contatoC = contato.getText().toString().trim();
        float avaliacaoC = avaliacao.getRating();
        DatabaseReference pessoas = databaseReference.child("pessoas");

        if(nomeC.isEmpty() || contatoC.isEmpty() || avaliacaoC == 0.0){
            Toast.makeText(this, "Preencha os campos corretamente!", Toast.LENGTH_SHORT).show();
        }else {
            p.setNome(nomeC);
            p.setContato(contatoC);
            p.setAvaliacao(avaliacaoC);
            pessoas.push().setValue(p);
            Toast.makeText(getApplicationContext(), "Salvo", Toast.LENGTH_SHORT).show();
            limpar();
        }
    }

    public void listar(){
        DatabaseReference pessoas = databaseReference.child("pessoas");
        listaPessoa.clear();
        pessoas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot item:snapshot.getChildren()){
                    Pessoa pessoa = item.getValue(Pessoa.class);
                    listaPessoa.add(pessoa);
                }
                recyclerView.setAdapter(new AdapterR(listaPessoa));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"erro de conexão",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void limpar(){
        nome.setText(" ");
        contato.setText(" ");
        avaliacao.setRating(0);
    }
}

