package br.unigran.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperarActivity extends AppCompatActivity {
    TextView email;
    Button recuperar;
    FirebaseAuth mAuth;
    ProgressBar carregarR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);
        email = findViewById(R.id.idEmailR);
        recuperar = findViewById(R.id.idRecuperarR);
        carregarR = findViewById(R.id.idCarregarR);
        mAuth = FirebaseAuth.getInstance();
        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recuperar();
            }
        });
    }
    private void recuperar(){
        String emailR = email.getText().toString().trim();
        if(emailR.isEmpty()){
            Toast.makeText(this, "Preencha os dados corretamente!", Toast.LENGTH_SHORT).show();
        }else{
            recuperarFirebase(emailR);
        }
    }
    private void recuperarFirebase(String email){
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RecuperarActivity.this, "Verifique seu email!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }else{
                    Toast.makeText(RecuperarActivity.this, "Insira um email válido!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}