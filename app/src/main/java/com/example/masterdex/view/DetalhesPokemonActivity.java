package com.example.masterdex.view;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.masterdex.R;
import com.example.masterdex.adapter.AdapterHabilidades;
import com.example.masterdex.database.CapturadosDao;
import com.example.masterdex.database.CapturadosDb;
import com.example.masterdex.database.FavoritosDao;
import com.example.masterdex.database.FavoritosDb;
import com.example.masterdex.models.Pokemon;
import com.example.masterdex.models.SlotHabilidade;
import com.example.masterdex.viewmodel.DetalhesPokemonViewModel;
import com.google.android.material.card.MaterialCardView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.ViewPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.ViewPagerItems;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class DetalhesPokemonActivity extends AppCompatActivity {

    public static final String FAVORITOS_DB = "favoritos_Db";
    public static final String CAPTURADOS_DB = "capturados_Db";

    private ToggleButton botaoFavorito;
    private ToggleButton botaoCapturado;
    private FavoritosDb favoritosDb;
    private CapturadosDb capturadosDb;
    private ConstraintLayout backgroundPokemon;
    private ImageView tipoUnicoImageView;
    private ImageView tipoPrimarioImageView;
    private ImageView tipoSecundarioImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_pokemon);

        ImageView botaoVoltar = findViewById(R.id.detalhes_pokemon_voltar);
        botaoFavorito = findViewById(R.id.toggle_favorito_Button);
        botaoCapturado = findViewById(R.id.toggle_capturado_Button);
        ImageView imagemPokemon = findViewById(R.id.detalhes_pokemon_image_view);
        TextView nomePokemon = findViewById(R.id.detalhes_pokemon_nome);
        ImageView tipoPokemon = findViewById(R.id.detalhes_pokemon_tipo1_image_view);
        TextView descricaoPokemon = findViewById(R.id.detalhes_pokemon_descricao_text_view);
        backgroundPokemon = findViewById(R.id.background_constraint_detalhe_pokemon);
        tipoUnicoImageView = findViewById(R.id.detalhes_pokemon_tipo_unico_image_view);
        tipoPrimarioImageView = findViewById(R.id.detalhes_pokemon_tipo1_image_view);
        tipoSecundarioImageView = findViewById(R.id.detalhes_pokemon_tipo2_image_view);


        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltarHome();
            }
        });

        favoritosDb = Room.databaseBuilder(this,
                FavoritosDb.class, FAVORITOS_DB).build();

        capturadosDb = Room.databaseBuilder(this,
                CapturadosDb.class, CAPTURADOS_DB).build();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Pokemon pokemon = (Pokemon) bundle.getSerializable("POKEMON");


        DetalhesPokemonViewModel detalhesPokemonViewModel = ViewModelProviders.of(this).get(DetalhesPokemonViewModel.class);
        detalhesPokemonViewModel.getPokemonByName(pokemon.getName());

        detalhesPokemonViewModel.getPokemonLiveData()
                .observe(this, pokemonApi -> {
                    switchBackground(pokemonApi);
                    setupViewPager(pokemonApi);
                    switchImageTypePokemon(pokemonApi);
                });

        String pok = pokemon.getName();
        pok = pok.substring(0, 1).toUpperCase().concat(pok.substring(1));
        nomePokemon.setText(pok);

        Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemon.getNumber() + ".png").into(imagemPokemon);
        //como o proprio nome ja diz kkk


        System.out.println(pokemon.getName());
        System.out.println("****************");
        consultaPokemonFavoritado(pokemon);
        consultaPokemonCapturado(pokemon);

        botaoFavorito.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    inserirPokemonFavorito(pokemon);
                } else {
                    deletarPokemonFavorito(pokemon);
                }
            }
        });
        botaoCapturado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    inserirPokemonCapturado(pokemon);
                } else {
                    deletarPokemonCapturado(pokemon);
                }
            }
        });

    }

    private void switchImageTypePokemon(Pokemon pokemon) {

        if (pokemon.getTypes().size() == 1){
            setarTipoUnico(pokemon);
        } else {
            setarTipoHibrido(pokemon);
        }



    }

    private void setarTipoHibrido(Pokemon pokemon) {

        String tipoPrimario = pokemon.getTypes().get(1).getType().getName();
        String tipoSecundario = pokemon.getTypes().get(0).getType().getName();

        switch (tipoPrimario) {
            case "steel":
                tipoPrimarioImageView.setBackground(getDrawable(R.drawable.ic_type_steel));
                break;
            case "water":
                tipoPrimarioImageView.setBackground(getDrawable(R.drawable.ic_type_water));
                break;
            case "dragon":
                tipoPrimarioImageView.setBackground(getDrawable(R.drawable.ic_type_dragon));
                break;
            case "electric":
                tipoPrimarioImageView.setBackground(getDrawable(R.drawable.ic_type_elctr));
                break;
            case "fairy":
                tipoPrimarioImageView.setBackground(getDrawable(R.drawable.ic_type_fairy));
                break;
            case "ghost":
                tipoPrimarioImageView.setBackground(getDrawable(R.drawable.ic_type_ghost));
                break;
            case "fire":
                tipoPrimarioImageView.setBackground(getDrawable(R.drawable.ic_type_fire));
                break;
            case "ice":
                tipoPrimarioImageView.setBackground(getDrawable(R.drawable.ic_type_ice));
                break;
            case "grass":
                tipoPrimarioImageView.setBackground(getDrawable(R.drawable.ic_type_grass));
                break;
            case "bug":
                tipoPrimarioImageView.setBackground(getDrawable(R.drawable.ic_type_bug));
                break;
            case "fighting":
                tipoPrimarioImageView.setBackground(getDrawable(R.drawable.ic_type_fight));
                break;
            case "normal":
                tipoPrimarioImageView.setBackground(getDrawable(R.drawable.ic_type_normal));
                break;
            case "rock":
                tipoPrimarioImageView.setBackground(getDrawable(R.drawable.ic_type_rock));
                break;
            case "psychic":
                tipoPrimarioImageView.setBackground(getDrawable(R.drawable.ic_type_psychic));
                break;
            case "dark":
                tipoPrimarioImageView.setBackground(getDrawable(R.drawable.ic_type_dark));
                break;
            case "ground":
                tipoPrimarioImageView.setBackground(getDrawable(R.drawable.ic_type_ground));
                break;
            case "poison":
                tipoPrimarioImageView.setBackground(getDrawable(R.drawable.ic_type_poison));
                break;
            case "flying":
                tipoPrimarioImageView.setBackground(getDrawable(R.drawable.ic_type_flying));
                break;

        }

        switch (tipoSecundario) {
            case "steel":
                tipoSecundarioImageView.setBackground(getDrawable(R.drawable.ic_type_steel));
                break;
            case "water":
                tipoSecundarioImageView.setBackground(getDrawable(R.drawable.ic_type_water));
                break;
            case "dragon":
                tipoSecundarioImageView.setBackground(getDrawable(R.drawable.ic_type_dragon));
                break;
            case "electric":
                tipoSecundarioImageView.setBackground(getDrawable(R.drawable.ic_type_elctr));
                break;
            case "fairy":
                tipoSecundarioImageView.setBackground(getDrawable(R.drawable.ic_type_fairy));
                break;
            case "ghost":
                tipoSecundarioImageView.setBackground(getDrawable(R.drawable.ic_type_ghost));
                break;
            case "fire":
                tipoSecundarioImageView.setBackground(getDrawable(R.drawable.ic_type_fire));
                break;
            case "ice":
                tipoSecundarioImageView.setBackground(getDrawable(R.drawable.ic_type_ice));
                break;
            case "grass":
                tipoSecundarioImageView.setBackground(getDrawable(R.drawable.ic_type_grass));
                break;
            case "bug":
                tipoSecundarioImageView.setBackground(getDrawable(R.drawable.ic_type_bug));
                break;
            case "fighting":
                tipoSecundarioImageView.setBackground(getDrawable(R.drawable.ic_type_fight));
                break;
            case "normal":
                tipoSecundarioImageView.setBackground(getDrawable(R.drawable.ic_type_normal));
                break;
            case "rock":
                tipoSecundarioImageView.setBackground(getDrawable(R.drawable.ic_type_rock));
                break;
            case "psychic":
                tipoSecundarioImageView.setBackground(getDrawable(R.drawable.ic_type_psychic));
                break;
            case "dark":
                tipoSecundarioImageView.setBackground(getDrawable(R.drawable.ic_type_dark));
                break;
            case "ground":
                tipoSecundarioImageView.setBackground(getDrawable(R.drawable.ic_type_ground));
                break;
            case "poison":
                tipoSecundarioImageView.setBackground(getDrawable(R.drawable.ic_type_poison));
                break;
            case "flying":
                tipoSecundarioImageView.setBackground(getDrawable(R.drawable.ic_type_flying));
                break;

        }

    }

    private void setarTipoUnico(Pokemon pokemon) {

        String tipo = pokemon.getTypes().get(0).getType().getName();
        switch (tipo) {
            case "steel":
                tipoUnicoImageView.setBackground(getDrawable(R.drawable.ic_type_steel));
                break;
            case "water":
                tipoUnicoImageView.setBackground(getDrawable(R.drawable.ic_type_water));
                break;
            case "dragon":
                tipoUnicoImageView.setBackground(getDrawable(R.drawable.ic_type_dragon));
                break;
            case "electric":
                tipoUnicoImageView.setBackground(getDrawable(R.drawable.ic_type_elctr));
                break;
            case "fairy":
                tipoUnicoImageView.setBackground(getDrawable(R.drawable.ic_type_fairy));
                break;
            case "ghost":
                tipoUnicoImageView.setBackground(getDrawable(R.drawable.ic_type_ghost));
                break;
            case "fire":
                tipoUnicoImageView.setBackground(getDrawable(R.drawable.ic_type_fire));
                break;
            case "ice":
                tipoUnicoImageView.setBackground(getDrawable(R.drawable.ic_type_ice));
                break;
            case "grass":
                tipoUnicoImageView.setBackground(getDrawable(R.drawable.ic_type_grass));
                break;
            case "bug":
                tipoUnicoImageView.setBackground(getDrawable(R.drawable.ic_type_bug));
                break;
            case "fighting":
                tipoUnicoImageView.setBackground(getDrawable(R.drawable.ic_type_fight));
                break;
            case "normal":
                tipoUnicoImageView.setBackground(getDrawable(R.drawable.ic_type_normal));
                break;
            case "rock":
                tipoUnicoImageView.setBackground(getDrawable(R.drawable.ic_type_rock));
                break;
            case "psychic":
                tipoUnicoImageView.setBackground(getDrawable(R.drawable.ic_type_psychic));
                break;
            case "dark":
                tipoUnicoImageView.setBackground(getDrawable(R.drawable.ic_type_dark));
                break;
            case "ground":
                tipoUnicoImageView.setBackground(getDrawable(R.drawable.ic_type_ground));
                break;
            case "poison":
                tipoUnicoImageView.setBackground(getDrawable(R.drawable.ic_type_poison));
                break;
            case "flying":
                tipoUnicoImageView.setBackground(getDrawable(R.drawable.ic_type_flying));
                break;

        }



    }

    private void setupViewPager(Pokemon pokemonApi) {
        ViewPagerItemAdapter adapter = new ViewPagerItemAdapter(ViewPagerItems.with(this)
                .add("HABILIDADES", R.layout.fragment_habilidades)
                .add("STATS", R.layout.fragment_stats)
                .add("SOBRE", R.layout.fragment_evolucoes)
                .create());

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = findViewById(R.id.smart);
        viewPagerTab.setViewPager(viewPager);

        setupHabilidadesTab(pokemonApi, adapter.getPage(0));
        setupStatsTab(pokemonApi, adapter.getPage(1));
        setupSobreTab(pokemonApi, adapter.getPage(2));

    }

    private void setupSobreTab(Pokemon pokemonApi, View view) {
        TextView flavorTextTextView = findViewById(R.id.flavor_text_text_view);
        //flavorTextTextView.setText("" + pokemonApi.getFlavorTextEntries().get(9).getFlavorText());
    }

    private void setupHabilidadesTab(Pokemon pokemonApi, View view) {

        List<SlotHabilidade> habilidadeList = pokemonApi.getMoves();

        RecyclerView habilidadesRecycler = view.findViewById(R.id.habilidades_recycler_view);
        AdapterHabilidades adapterHabilidades = new AdapterHabilidades(habilidadeList, pokemonApi);

        habilidadesRecycler.setAdapter(adapterHabilidades);
        habilidadesRecycler.setLayoutManager(new LinearLayoutManager(this));

//        if (pokemonApi.getTypes().get(0).getType().getName().equals("fire")){
//            habilidadeNome.setTextColor(getResources().getColor(R.color.fogo));
//        }
    }

    private void setupStatsTab(Pokemon pokemonApi, View view) {
        TextView speedTextView = view.findViewById(R.id.stats_text_view_valor_speed);
        TextView spDefTextView = view.findViewById(R.id.stats_text_view_valor_spd);
        TextView spAtkTextView = view.findViewById(R.id.stats_text_view_valor_spa);
        TextView atkTextView = view.findViewById(R.id.stats_text_view_valor_atk);
        TextView defTextView = view.findViewById(R.id.stats_text_view_valor_def);
        TextView hpTextView = view.findViewById(R.id.stats_text_view_valor_hp);
        TextView totalStats = findViewById(R.id.stats_text_view_valor_total);

        switchStatsColor(pokemonApi);

        speedTextView.setText("" + pokemonApi.getStats().get(0).getValorStats());
        spDefTextView.setText("" + pokemonApi.getStats().get(1).getValorStats());
        spAtkTextView.setText("" + pokemonApi.getStats().get(2).getValorStats());
        defTextView.setText("" + pokemonApi.getStats().get(3).getValorStats());
        atkTextView.setText("" + pokemonApi.getStats().get(4).getValorStats());
        hpTextView.setText("" + pokemonApi.getStats().get(5).getValorStats());

        int somaStatus = pokemonApi.getStats().get(0).getValorStats()
                + pokemonApi.getStats().get(1).getValorStats()
                + pokemonApi.getStats().get(2).getValorStats()
                + pokemonApi.getStats().get(3).getValorStats()
                + pokemonApi.getStats().get(4).getValorStats()
                + pokemonApi.getStats().get(5).getValorStats();

        totalStats.setText("" + somaStatus);


    }

    private void deletarPokemonFavorito(Pokemon pokemon) {
        Completable.fromAction(() -> favoritosDb.favoritosDao().deleteByName(pokemon.getName()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    private void inserirPokemonFavorito(Pokemon pokemon) {
        Completable.fromAction(() -> favoritosDb.favoritosDao().insert(pokemon))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    private void deletarPokemonCapturado(Pokemon pokemon) {
        Completable.fromAction(() -> capturadosDb.capturadosDao().deleteByName(pokemon.getName()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    private void inserirPokemonCapturado(Pokemon pokemon) {
        Completable.fromAction(() -> capturadosDb.capturadosDao().insert(pokemon))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    private void consultaPokemonCapturado(Pokemon pokemon) {
        CapturadosDao capturadosDao = capturadosDb.capturadosDao();
        capturadosDao.getName(pokemon.getName())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(pokemonEncontrado -> {
                    System.out.println(pokemonEncontrado.getName());
                    if (pokemonEncontrado.getName().equals(pokemon.getName())) {
                        System.out.println("pokemon foi encontrado no banco $$$");
                        botaoCapturado.setChecked(true);
                    } else {
                        System.out.println("pokemon nao encontrado no banco $$$");
                        botaoCapturado.setChecked(false);
                    }
                });
    }

    private void consultaPokemonFavoritado(Pokemon pokemon) {
        FavoritosDao favoritosDao = favoritosDb.favoritosDao();
        favoritosDao.getName(pokemon.getName())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(pokemonEncontrado -> {
                    System.out.println(pokemonEncontrado.getName());
                    if (pokemonEncontrado.getName().equals(pokemon.getName())) {
                        System.out.println("pokemon foi encontrado no banco $$$");
                        botaoFavorito.setChecked(true);
                    } else {
                        System.out.println("pokemon nao encontrado no banco $$$");
                        botaoFavorito.setChecked(false);
                    }
                });
    }

    private void voltarHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void switchBackground(Pokemon pokemon) {

        Log.d("Status", " " + pokemon.getStats().get(0).getValorStats());
        Log.d("Tipo", pokemon.getTypes().get(0).getType().getName());
        int valorPosicao = 0;
        if (pokemon.getTypes().size() == 1) {
            valorPosicao = 0;
        } else {
            valorPosicao = 1;
        }

        String tipo = pokemon.getTypes().get(valorPosicao).getType().getName();
        switch (tipo) {
            case "steel":
                backgroundPokemon.setBackground(getDrawable(R.drawable.detalhes_background_aco));
                break;
            case "water":
                backgroundPokemon.setBackground(getDrawable(R.drawable.detalhes_background_agua));
                break;
            case "dragon":
                backgroundPokemon.setBackground(getDrawable(R.drawable.detalhes_background_dragao));
                break;
            case "electric":
                backgroundPokemon.setBackground(getDrawable(R.drawable.detalhes_background_eletrico));
                break;
            case "fairy":
                backgroundPokemon.setBackground(getDrawable(R.drawable.detalhes_background_fada));
                break;
            case "ghost":
                backgroundPokemon.setBackground(getDrawable(R.drawable.detalhes_background_fantasma));
                break;
            case "fire":
                backgroundPokemon.setBackground(getDrawable(R.drawable.detalhes_background_fogo));
                break;
            case "ice":
                backgroundPokemon.setBackground(getDrawable(R.drawable.detalhes_background_gelo));
                break;
            case "grass":
                backgroundPokemon.setBackground(getDrawable(R.drawable.detalhes_background_grama));
                break;
            case "bug":
                backgroundPokemon.setBackground(getDrawable(R.drawable.detalhes_background_inseto));
                break;
            case "fighting":
                backgroundPokemon.setBackground(getDrawable(R.drawable.detalhes_background_lutador));
                break;
            case "normal":
                backgroundPokemon.setBackground(getDrawable(R.drawable.detalhes_background_normal));
                break;
            case "rock":
                backgroundPokemon.setBackground(getDrawable(R.drawable.detalhes_background_pedra));
                break;
            case "psychic":
                backgroundPokemon.setBackground(getDrawable(R.drawable.detalhes_background_psiquico));
                break;
            case "dark":
                backgroundPokemon.setBackground(getDrawable(R.drawable.detalhes_background_sombrio));
                //botaoCapturado.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_launcher_background));
                break;
            case "ground":
                backgroundPokemon.setBackground(getDrawable(R.drawable.detalhes_background_terra));
                break;
            case "poison":
                backgroundPokemon.setBackground(getDrawable(R.drawable.detalhes_background_venenoso));
                break;
            case "flying":
                backgroundPokemon.setBackground(getDrawable(R.drawable.detalhes_background_voador));
                break;

        }
    }

    public void mostrarTipo(Pokemon pokemon) {
        boolean qtdTipo = true;
        if (pokemon.getTypes().size() == 1) {
            qtdTipo = true;
        } else {
            qtdTipo = false;
        }

    }

    public void switchStatsColor(Pokemon pokemon) {

        MaterialCardView speedCard = findViewById(R.id.stats_speed_card);
        MaterialCardView spDefCard = findViewById(R.id.stats_spd_card);
        MaterialCardView spAtkCard = findViewById(R.id.stats_spa_card);
        MaterialCardView defCard = findViewById(R.id.stats_def_card);
        MaterialCardView atkCard = findViewById(R.id.stats_atk_card);
        MaterialCardView hpCard = findViewById(R.id.stats_hp_card);
        MaterialCardView totalStatsCard = findViewById(R.id.stats_total_card);

        ImageView speedBackground = findViewById(R.id.stats_color_background_speed);
        ImageView spDefBackground = findViewById(R.id.stats_color_background_spd);
        ImageView spAtkBackground = findViewById(R.id.stats_color_background_spa);
        ImageView defBackground = findViewById(R.id.stats_color_background_def);
        ImageView atkBackground = findViewById(R.id.stats_color_background_atk);
        ImageView hpBackground = findViewById(R.id.stats_color_background_hp);

        int valorPosicao = 0;
        if (pokemon.getTypes().size() == 1) {
            valorPosicao = 0;
        } else {
            valorPosicao = 1;
        }

        String tipo = pokemon.getTypes().get(valorPosicao).getType().getName();
        switch (tipo) {
            case "steel":
                speedCard.setStrokeColor(getColor(R.color.aco));
                spDefCard.setStrokeColor(getColor(R.color.aco));
                spAtkCard.setStrokeColor(getColor(R.color.aco));
                defCard.setStrokeColor(getColor(R.color.aco));
                atkCard.setStrokeColor(getColor(R.color.aco));
                hpCard.setStrokeColor(getColor(R.color.aco));

                speedBackground.setBackground(getDrawable(R.color.aco));
                spDefBackground.setBackground(getDrawable(R.color.aco));
                spAtkBackground.setBackground(getDrawable(R.color.aco));
                defBackground.setBackground(getDrawable(R.color.aco));
                atkBackground.setBackground(getDrawable(R.color.aco));
                hpBackground.setBackground(getDrawable(R.color.aco));

                totalStatsCard.setCardBackgroundColor(getResources().getColor(R.color.aco));

                break;
            case "water":
                speedCard.setStrokeColor(getColor(R.color.agua));
                spDefCard.setStrokeColor(getColor(R.color.agua));
                spAtkCard.setStrokeColor(getColor(R.color.agua));
                defCard.setStrokeColor(getColor(R.color.agua));
                atkCard.setStrokeColor(getColor(R.color.agua));
                hpCard.setStrokeColor(getColor(R.color.agua));

                speedBackground.setBackground(getDrawable(R.color.agua));
                spDefBackground.setBackground(getDrawable(R.color.agua));
                spAtkBackground.setBackground(getDrawable(R.color.agua));
                defBackground.setBackground(getDrawable(R.color.agua));
                atkBackground.setBackground(getDrawable(R.color.agua));
                hpBackground.setBackground(getDrawable(R.color.agua));

                totalStatsCard.setCardBackgroundColor(getResources().getColor(R.color.agua));

                break;
            case "dragon":
                speedCard.setStrokeColor(getColor(R.color.dragao));
                spDefCard.setStrokeColor(getColor(R.color.dragao));
                spAtkCard.setStrokeColor(getColor(R.color.dragao));
                defCard.setStrokeColor(getColor(R.color.dragao));
                atkCard.setStrokeColor(getColor(R.color.dragao));
                hpCard.setStrokeColor(getColor(R.color.dragao));

                speedBackground.setBackground(getDrawable(R.color.dragao));
                spDefBackground.setBackground(getDrawable(R.color.dragao));
                spAtkBackground.setBackground(getDrawable(R.color.dragao));
                defBackground.setBackground(getDrawable(R.color.dragao));
                atkBackground.setBackground(getDrawable(R.color.dragao));
                hpBackground.setBackground(getDrawable(R.color.dragao));

                totalStatsCard.setCardBackgroundColor(getResources().getColor(R.color.dragao));

                break;
            case "electric":
                speedCard.setStrokeColor(getColor(R.color.eletrico));
                spDefCard.setStrokeColor(getColor(R.color.eletrico));
                spAtkCard.setStrokeColor(getColor(R.color.eletrico));
                defCard.setStrokeColor(getColor(R.color.eletrico));
                atkCard.setStrokeColor(getColor(R.color.eletrico));
                hpCard.setStrokeColor(getColor(R.color.eletrico));

                speedBackground.setBackground(getDrawable(R.color.eletrico));
                spDefBackground.setBackground(getDrawable(R.color.eletrico));
                spAtkBackground.setBackground(getDrawable(R.color.eletrico));
                defBackground.setBackground(getDrawable(R.color.eletrico));
                atkBackground.setBackground(getDrawable(R.color.eletrico));
                hpBackground.setBackground(getDrawable(R.color.eletrico));

                totalStatsCard.setCardBackgroundColor(getResources().getColor(R.color.eletrico));

                break;
            case "fairy":
                speedCard.setStrokeColor(getColor(R.color.fada));
                spDefCard.setStrokeColor(getColor(R.color.fada));
                spAtkCard.setStrokeColor(getColor(R.color.fada));
                defCard.setStrokeColor(getColor(R.color.fada));
                atkCard.setStrokeColor(getColor(R.color.fada));
                hpCard.setStrokeColor(getColor(R.color.fada));

                speedBackground.setBackground(getDrawable(R.color.fada));
                spDefBackground.setBackground(getDrawable(R.color.fada));
                spAtkBackground.setBackground(getDrawable(R.color.fada));
                defBackground.setBackground(getDrawable(R.color.fada));
                atkBackground.setBackground(getDrawable(R.color.fada));
                hpBackground.setBackground(getDrawable(R.color.fada));

                totalStatsCard.setCardBackgroundColor(getResources().getColor(R.color.fada));

                break;
            case "ghost":
                speedCard.setStrokeColor(getColor(R.color.fantasma));
                spDefCard.setStrokeColor(getColor(R.color.fantasma));
                spAtkCard.setStrokeColor(getColor(R.color.fantasma));
                defCard.setStrokeColor(getColor(R.color.fantasma));
                atkCard.setStrokeColor(getColor(R.color.fantasma));
                hpCard.setStrokeColor(getColor(R.color.fantasma));

                speedBackground.setBackground(getDrawable(R.color.fantasma));
                spDefBackground.setBackground(getDrawable(R.color.fantasma));
                spAtkBackground.setBackground(getDrawable(R.color.fantasma));
                defBackground.setBackground(getDrawable(R.color.fantasma));
                atkBackground.setBackground(getDrawable(R.color.fantasma));
                hpBackground.setBackground(getDrawable(R.color.fantasma));

                totalStatsCard.setCardBackgroundColor(getResources().getColor(R.color.fantasma));

                break;
            case "fire":
                speedCard.setStrokeColor(getColor(R.color.fogo));
                spDefCard.setStrokeColor(getColor(R.color.fogo));
                spAtkCard.setStrokeColor(getColor(R.color.fogo));
                defCard.setStrokeColor(getColor(R.color.fogo));
                atkCard.setStrokeColor(getColor(R.color.fogo));
                hpCard.setStrokeColor(getColor(R.color.fogo));

                speedBackground.setBackground(getDrawable(R.color.fogo));
                spDefBackground.setBackground(getDrawable(R.color.fogo));
                spAtkBackground.setBackground(getDrawable(R.color.fogo));
                defBackground.setBackground(getDrawable(R.color.fogo));
                atkBackground.setBackground(getDrawable(R.color.fogo));
                hpBackground.setBackground(getDrawable(R.color.fogo));

                totalStatsCard.setCardBackgroundColor(getResources().getColor(R.color.fogo));

                break;
            case "ice":
                speedCard.setStrokeColor(getColor(R.color.gelo));
                spDefCard.setStrokeColor(getColor(R.color.gelo));
                spAtkCard.setStrokeColor(getColor(R.color.gelo));
                defCard.setStrokeColor(getColor(R.color.gelo));
                atkCard.setStrokeColor(getColor(R.color.gelo));
                hpCard.setStrokeColor(getColor(R.color.gelo));

                speedBackground.setBackground(getDrawable(R.color.gelo));
                spDefBackground.setBackground(getDrawable(R.color.gelo));
                spAtkBackground.setBackground(getDrawable(R.color.gelo));
                defBackground.setBackground(getDrawable(R.color.gelo));
                atkBackground.setBackground(getDrawable(R.color.gelo));
                hpBackground.setBackground(getDrawable(R.color.gelo));

                totalStatsCard.setCardBackgroundColor(getResources().getColor(R.color.gelo));

                break;
            case "grass":
                speedCard.setStrokeColor(getColor(R.color.grama));
                spDefCard.setStrokeColor(getColor(R.color.grama));
                spAtkCard.setStrokeColor(getColor(R.color.grama));
                defCard.setStrokeColor(getColor(R.color.grama));
                atkCard.setStrokeColor(getColor(R.color.grama));
                hpCard.setStrokeColor(getColor(R.color.grama));

                speedBackground.setBackground(getDrawable(R.color.grama));
                spDefBackground.setBackground(getDrawable(R.color.grama));
                spAtkBackground.setBackground(getDrawable(R.color.grama));
                defBackground.setBackground(getDrawable(R.color.grama));
                atkBackground.setBackground(getDrawable(R.color.grama));
                hpBackground.setBackground(getDrawable(R.color.grama));

                totalStatsCard.setCardBackgroundColor(getResources().getColor(R.color.grama));

                break;
            case "bug":
                speedCard.setStrokeColor(getColor(R.color.inseto));
                spDefCard.setStrokeColor(getColor(R.color.inseto));
                spAtkCard.setStrokeColor(getColor(R.color.inseto));
                defCard.setStrokeColor(getColor(R.color.inseto));
                atkCard.setStrokeColor(getColor(R.color.inseto));
                hpCard.setStrokeColor(getColor(R.color.inseto));

                speedBackground.setBackground(getDrawable(R.color.inseto));
                spDefBackground.setBackground(getDrawable(R.color.inseto));
                spAtkBackground.setBackground(getDrawable(R.color.inseto));
                defBackground.setBackground(getDrawable(R.color.inseto));
                atkBackground.setBackground(getDrawable(R.color.inseto));
                hpBackground.setBackground(getDrawable(R.color.inseto));

                totalStatsCard.setCardBackgroundColor(getResources().getColor(R.color.inseto));

                break;
            case "fighting":
                speedCard.setStrokeColor(getColor(R.color.lutador));
                spDefCard.setStrokeColor(getColor(R.color.lutador));
                spAtkCard.setStrokeColor(getColor(R.color.lutador));
                defCard.setStrokeColor(getColor(R.color.lutador));
                atkCard.setStrokeColor(getColor(R.color.lutador));
                hpCard.setStrokeColor(getColor(R.color.lutador));

                speedBackground.setBackground(getDrawable(R.color.lutador));
                spDefBackground.setBackground(getDrawable(R.color.lutador));
                spAtkBackground.setBackground(getDrawable(R.color.lutador));
                defBackground.setBackground(getDrawable(R.color.lutador));
                atkBackground.setBackground(getDrawable(R.color.lutador));
                hpBackground.setBackground(getDrawable(R.color.lutador));

                totalStatsCard.setCardBackgroundColor(getResources().getColor(R.color.lutador));

                break;
            case "normal":
                speedCard.setStrokeColor(getColor(R.color.normal));
                spDefCard.setStrokeColor(getColor(R.color.normal));
                spAtkCard.setStrokeColor(getColor(R.color.normal));
                defCard.setStrokeColor(getColor(R.color.normal));
                atkCard.setStrokeColor(getColor(R.color.normal));
                hpCard.setStrokeColor(getColor(R.color.normal));

                speedBackground.setBackground(getDrawable(R.color.normal));
                spDefBackground.setBackground(getDrawable(R.color.normal));
                spAtkBackground.setBackground(getDrawable(R.color.normal));
                defBackground.setBackground(getDrawable(R.color.normal));
                atkBackground.setBackground(getDrawable(R.color.normal));
                hpBackground.setBackground(getDrawable(R.color.normal));

                totalStatsCard.setCardBackgroundColor(getResources().getColor(R.color.normal));

                break;
            case "rock":
                speedCard.setStrokeColor(getColor(R.color.pedra));
                spDefCard.setStrokeColor(getColor(R.color.pedra));
                spAtkCard.setStrokeColor(getColor(R.color.pedra));
                defCard.setStrokeColor(getColor(R.color.pedra));
                atkCard.setStrokeColor(getColor(R.color.pedra));
                hpCard.setStrokeColor(getColor(R.color.pedra));

                speedBackground.setBackground(getDrawable(R.color.pedra));
                spDefBackground.setBackground(getDrawable(R.color.pedra));
                spAtkBackground.setBackground(getDrawable(R.color.pedra));
                defBackground.setBackground(getDrawable(R.color.pedra));
                atkBackground.setBackground(getDrawable(R.color.pedra));
                hpBackground.setBackground(getDrawable(R.color.pedra));

                totalStatsCard.setCardBackgroundColor(getResources().getColor(R.color.pedra));

                break;
            case "psychic":
                speedCard.setStrokeColor(getColor(R.color.psiquico));
                spDefCard.setStrokeColor(getColor(R.color.psiquico));
                spAtkCard.setStrokeColor(getColor(R.color.psiquico));
                defCard.setStrokeColor(getColor(R.color.psiquico));
                atkCard.setStrokeColor(getColor(R.color.psiquico));
                hpCard.setStrokeColor(getColor(R.color.psiquico));

                speedBackground.setBackground(getDrawable(R.color.psiquico));
                spDefBackground.setBackground(getDrawable(R.color.psiquico));
                spAtkBackground.setBackground(getDrawable(R.color.psiquico));
                defBackground.setBackground(getDrawable(R.color.psiquico));
                atkBackground.setBackground(getDrawable(R.color.psiquico));
                hpBackground.setBackground(getDrawable(R.color.psiquico));

                totalStatsCard.setCardBackgroundColor(getResources().getColor(R.color.psiquico));

                break;
            case "dark":
                speedCard.setStrokeColor(getColor(R.color.sombrio));
                spDefCard.setStrokeColor(getColor(R.color.sombrio));
                spAtkCard.setStrokeColor(getColor(R.color.sombrio));
                defCard.setStrokeColor(getColor(R.color.sombrio));
                atkCard.setStrokeColor(getColor(R.color.sombrio));
                hpCard.setStrokeColor(getColor(R.color.sombrio));

                speedBackground.setBackground(getDrawable(R.color.sombrio));
                spDefBackground.setBackground(getDrawable(R.color.sombrio));
                spAtkBackground.setBackground(getDrawable(R.color.sombrio));
                defBackground.setBackground(getDrawable(R.color.sombrio));
                atkBackground.setBackground(getDrawable(R.color.sombrio));
                hpBackground.setBackground(getDrawable(R.color.sombrio));

                totalStatsCard.setCardBackgroundColor(getResources().getColor(R.color.sombrio));
                //botaoCapturado.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_launcher_background));
                break;
            case "ground":
                speedCard.setStrokeColor(getColor(R.color.terra));
                spDefCard.setStrokeColor(getColor(R.color.terra));
                spAtkCard.setStrokeColor(getColor(R.color.terra));
                defCard.setStrokeColor(getColor(R.color.terra));
                atkCard.setStrokeColor(getColor(R.color.terra));
                hpCard.setStrokeColor(getColor(R.color.terra));

                speedBackground.setBackground(getDrawable(R.color.terra));
                spDefBackground.setBackground(getDrawable(R.color.terra));
                spAtkBackground.setBackground(getDrawable(R.color.terra));
                defBackground.setBackground(getDrawable(R.color.terra));
                atkBackground.setBackground(getDrawable(R.color.terra));
                hpBackground.setBackground(getDrawable(R.color.terra));

                totalStatsCard.setCardBackgroundColor(getResources().getColor(R.color.terra));

                break;
            case "poison":
                speedCard.setStrokeColor(getColor(R.color.venenoso));
                spDefCard.setStrokeColor(getColor(R.color.venenoso));
                spAtkCard.setStrokeColor(getColor(R.color.venenoso));
                defCard.setStrokeColor(getColor(R.color.venenoso));
                atkCard.setStrokeColor(getColor(R.color.venenoso));
                hpCard.setStrokeColor(getColor(R.color.venenoso));

                speedBackground.setBackground(getDrawable(R.color.venenoso));
                spDefBackground.setBackground(getDrawable(R.color.venenoso));
                spAtkBackground.setBackground(getDrawable(R.color.venenoso));
                defBackground.setBackground(getDrawable(R.color.venenoso));
                atkBackground.setBackground(getDrawable(R.color.venenoso));
                hpBackground.setBackground(getDrawable(R.color.venenoso));

                totalStatsCard.setCardBackgroundColor(getResources().getColor(R.color.venenoso));

                break;
            case "flying":
                speedCard.setStrokeColor(getColor(R.color.voador));
                spDefCard.setStrokeColor(getColor(R.color.voador));
                spAtkCard.setStrokeColor(getColor(R.color.voador));
                defCard.setStrokeColor(getColor(R.color.voador));
                atkCard.setStrokeColor(getColor(R.color.voador));
                hpCard.setStrokeColor(getColor(R.color.voador));

                speedBackground.setBackground(getDrawable(R.color.voador));
                spDefBackground.setBackground(getDrawable(R.color.voador));
                spAtkBackground.setBackground(getDrawable(R.color.voador));
                defBackground.setBackground(getDrawable(R.color.voador));
                atkBackground.setBackground(getDrawable(R.color.voador));
                hpBackground.setBackground(getDrawable(R.color.voador));

                totalStatsCard.setCardBackgroundColor(getResources().getColor(R.color.voador));

                break;

        }
    }

    public HabilidadesFragment enviarPokemonParaHabilidades(Pokemon pokemon) {

        HabilidadesFragment habilidadesFragment = new HabilidadesFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("POKEMON", pokemon);
        habilidadesFragment.setArguments(bundle);

        return habilidadesFragment;
    }

    public StatsFragment enviarPokemonParaStats(Pokemon pokemon) {
        StatsFragment statsFragment = new StatsFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("POKEMON", pokemon);
        statsFragment.setArguments(bundle);

        return statsFragment;

    }
}

