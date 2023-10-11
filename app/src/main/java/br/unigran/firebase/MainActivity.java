package br.unigran.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

import br.unigran.firebase.Model.ListaCAdapter;
import br.unigran.firebase.Model.Pessoa;

public class MainActivity extends AppCompatActivity {
    EditText nome;
    EditText contato;
    RatingBar avaliacao;
    List<Pessoa> listaPessoa;
    ListView listaC;
    DatabaseReference databaseReference;
    Button pesquisar;
    EditText pesquisa;
    ListaCAdapter arrayPessoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        nome= findViewById(R.id.idNome);
        contato = findViewById(R.id.idContato);
        avaliacao = findViewById(R.id.idRating);
        pesquisa = findViewById(R.id.idNomePesquisa);
        pesquisar = findViewById(R.id.idPesquisar);
        listaPessoa = new LinkedList();
        listaC = findViewById(R.id.idLista);
        arrayPessoa = new ListaCAdapter(getApplicationContext(),listaPessoa);
        listaC.setAdapter(arrayPessoa);
        listar();
    }

    public void salvar(View view) {
        Pessoa p = new Pessoa();
        String nomeC = nome.getText().toString().trim();
        String contatoC = contato.getText().toString().trim();
        float avaliacaoC = avaliacao.getRating();
        p.nome = nomeC;
        p.contato = contatoC;
        p.avaliacao = avaliacaoC;
        DatabaseReference pessoas = databaseReference.child("pessoas");
        if(nomeC.isEmpty() || contatoC.isEmpty() || avaliacaoC == 0.0){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Campo em branco")
                    .setMessage("Por favor, preencha todos os campos!")
                    .setPositiveButton("OK",null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }else {
            pessoas.push().setValue(p);
            Toast.makeText(getApplicationContext(), "Salvo", Toast.LENGTH_SHORT).show();
            limpar();
        }
    }
    public void pesquisar(){
        listar();
    }
    public void listar(){
        DatabaseReference pessoas = databaseReference.child("pessoas");
        Query query = pessoas.orderByChild("nome").startAt(pesquisa.getText().toString());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaPessoa.clear();
                for(DataSnapshot item:snapshot.getChildren()){
                    Pessoa pessoa = item.getValue(Pessoa.class);
                    listaPessoa.add(pessoa);
                    arrayPessoa.notifyDataSetChanged();
                }
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
        avaliacao.setNumStars(0);
    }
}

