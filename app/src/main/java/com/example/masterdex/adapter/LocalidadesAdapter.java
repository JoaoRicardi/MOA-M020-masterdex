package com.example.masterdex.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.masterdex.R;
import com.example.masterdex.models.Cidade;
import com.example.masterdex.models.Regiao;

import java.util.List;

public class LocalidadesAdapter extends RecyclerView.Adapter<LocalidadesAdapter.ViewHolder> {

    private List<Cidade> cidadeList;

    public LocalidadesAdapter(List<Cidade> cidadeList) {
        this.cidadeList = cidadeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.regioes_celula, viewGroup, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Cidade cidade = cidadeList.get(position);
        holder.setupLocalidades(cidade);

    }

    @Override
    public int getItemCount() {
        return cidadeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nomeLocalidadeTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeLocalidadeTextView = itemView.findViewById(R.id.nome_local_regiao_id);
        }

        public void setupLocalidades(Cidade cidade) {

            nomeLocalidadeTextView.setText(cidade.getNome());

        }
    }
}
