package com.example.masterdex.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.masterdex.R;
import com.example.masterdex.models.Habilidade;
import com.example.masterdex.models.Pokemon;
import com.example.masterdex.models.SlotHabilidade;

import java.util.List;

public class AdapterHabilidades extends RecyclerView.Adapter<AdapterHabilidades.ViewHolder> {

    private List<SlotHabilidade> slotHabilidadeList;
    private Pokemon pokemon;

    public AdapterHabilidades (List<SlotHabilidade> slotHabilidadeList, Pokemon pokemon){
        this.slotHabilidadeList = slotHabilidadeList;
        this.pokemon = pokemon;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.habilidades_celula, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SlotHabilidade habilidade = slotHabilidadeList.get(position);
        holder.setupHabilidade(habilidade);

    }

    @Override
    public int getItemCount() {
        return slotHabilidadeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView habilidadeTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            habilidadeTextView = itemView.findViewById(R.id.habilidades_text_view);
        }

        public void setupHabilidade (SlotHabilidade slotHabilidade){

            habilidadeTextView.setText(slotHabilidade.getMove().getName());
        }
    }
}
