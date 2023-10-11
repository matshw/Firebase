package br.unigran.firebase.Model;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.unigran.firebase.R;

public class ListaCAdapter extends BaseAdapter {
    private Context context;
    private List<Pessoa> listaPessoa;

    public ListaCAdapter(Context context, List<Pessoa> ListaPessoa) {
        this.context = context;
        listaPessoa = ListaPessoa;
    }

    @Override
    public int getCount() {
        return listaPessoa.size();
    }

    @Override
    public Object getItem(int position) {
        return listaPessoa.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.linha,null);
        TextView textNome = (TextView)v.findViewById(R.id.idNomeE);
        TextView textContato = (TextView)v.findViewById(R.id.idContatoE);
        TextView textAvaliacao = (TextView)v.findViewById(R.id.idAvaliacaoE);

        String avaliacaoText = String.valueOf(listaPessoa.get(position).getAvaliacao());
        textAvaliacao.setText(avaliacaoText);
        textNome.setText(listaPessoa.get(position).getNome());
        textContato.setText(listaPessoa.get(position).getContato());
        v.setTag(listaPessoa.get(position).getId());
        return v;
    }
}
