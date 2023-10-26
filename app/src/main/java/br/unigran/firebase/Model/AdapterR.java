package br.unigran.firebase.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.unigran.firebase.R;

public class AdapterR extends RecyclerView.Adapter<AdapterR.MeuViewHolder> {
    List<Pessoa> dados;
    public AdapterR(List dados){
        this.dados=dados;
    }
    @NonNull
    @Override
    public MeuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_recycle,parent,false);
        return new MeuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeuViewHolder holder, int position) {
        Pessoa pessoa = dados.get(position);
        holder.nome.setText(pessoa.getNome());
        holder.contato.setText(pessoa.getContato());
        holder.avaliacao.setText(String.valueOf(pessoa.getAvaliacao()));
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class MeuViewHolder extends RecyclerView.ViewHolder{
        TextView nome;
        TextView avaliacao;
        TextView contato;

        public MeuViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.idNomeL);
            avaliacao = itemView.findViewById(R.id.idAvaliacaoL);
            contato = itemView.findViewById(R.id.idContatoL);
        }
    }
}
