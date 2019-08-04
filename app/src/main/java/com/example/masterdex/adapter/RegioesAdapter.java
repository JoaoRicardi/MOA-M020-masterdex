package com.example.masterdex.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.masterdex.R;
import com.example.masterdex.interfaces.RegioesListener;
import com.example.masterdex.models.Regiao;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegioesAdapter extends RecyclerView.Adapter<RegioesAdapter.ViewHolder> {

    private List<Regiao> listaRegioes;
    private RegioesListener regioesListener;

    public RegioesAdapter(List<Regiao> listaRegioes, RegioesListener regioesListener) {
        this.listaRegioes = listaRegioes;
        this.regioesListener = regioesListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.regioes_celula, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Regiao regiao = listaRegioes.get(i);
                viewHolder.setupRegiao(regiao);

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        regioesListener.onRegiaoClicada(regiao);
                    }
                });


    }

    @Override
    public int getItemCount() {
        return listaRegioes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView regiaoCircleImageView;
        private TextView nomeRegiaoTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            regiaoCircleImageView = itemView.findViewById(R.id.regiao_circle_image_view);
            nomeRegiaoTextView = itemView.findViewById(R.id.nome_regiao_text_view);
        }

        public void setupRegiao(Regiao regiao){

            nomeRegiaoTextView.setText(regiao.getNomeRegiao());


            Picasso.get().load(regiao.getImagemRegiao()).into(regiaoCircleImageView);
        }


    }
}
