package com.example.masterdex.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.masterdex.R;
import com.example.masterdex.models.Pokemon;
import com.example.masterdex.viewmodel.DetalhesPokemonViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatsFragment extends Fragment {

    private TextView speedTextView;
    private Pokemon pokemon;


//    public StatsFragment(Pokemon pokemon) {
//        this.pokemon = pokemon;
//    }

    public StatsFragment (){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        speedTextView = view.findViewById(R.id.stats_text_view_valor_speed);

        Bundle bundle = getArguments();
        Pokemon pokemon = (Pokemon) bundle.getSerializable("POKEMON");

        speedTextView.setText(""+pokemon.getStats().get(0).getValorStats());
        speedTextView.setText("999");






        return view;
    }


}
