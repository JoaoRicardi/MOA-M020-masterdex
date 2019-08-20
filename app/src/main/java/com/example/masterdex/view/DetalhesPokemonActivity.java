package com.example.masterdex.view;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.masterdex.R;
import com.example.masterdex.database.CapturadosDao;
import com.example.masterdex.database.CapturadosDb;
import com.example.masterdex.database.FavoritosDao;
import com.example.masterdex.database.FavoritosDb;
import com.example.masterdex.models.Pokemon;
import com.example.masterdex.viewmodel.DetalhesPokemonViewModel;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.ViewPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.ViewPagerItems;
import com.squareup.picasso.Picasso;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_pokemon);


        ViewPagerItemAdapter adapter = new ViewPagerItemAdapter(ViewPagerItems.with(this)
                .add("HABILIDADES", R.layout.fragment_habilidades)
                .add("STATS", R.layout.fragment_stats)
                .add("EVOLUÇÕES", R.layout.fragment_evolucoes)
                .create());

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = findViewById(R.id.smart);
        viewPagerTab.setViewPager(viewPager);

        ImageView botaoVoltar = findViewById(R.id.detalhes_pokemon_voltar);
        botaoFavorito = findViewById(R.id.toggle_favorito_Button);
        botaoCapturado = findViewById(R.id.toggle_capturado_Button);
        ImageView imagemPokemon = findViewById(R.id.detalhes_pokemon_image_view);
        TextView nomePokemon = findViewById(R.id.detalhes_pokemon_nome);
        ImageView tipoPokemon = findViewById(R.id.detalhes_pokemon_tipo1_image_view);
        TextView descricaoPokemon = findViewById(R.id.detalhes_pokemon_descricao_text_view);
        backgroundPokemon = findViewById(R.id.background_constraint_detalhe_pokemon);

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
                .observe(this, pokemon1 -> switchBackground(pokemon1));

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

        enviarPokemonParaHabilidades(pokemon);
        enviarPokemonParaStats(pokemon);


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

    public void enviarPokemonParaHabilidades(Pokemon pokemon) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        HabilidadesFragment habilidadesFragment = new HabilidadesFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("POKEMON", pokemon);
        habilidadesFragment.setArguments(bundle);

        transaction.replace(R.id.viewPager, habilidadesFragment);
        transaction.commit();

    }

    public void enviarPokemonParaStats(Pokemon pokemon) {


        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        StatsFragment statsFragment = new StatsFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("POKEMON", pokemon);
        statsFragment.setArguments(bundle);

        transaction.replace(R.id.viewPager, statsFragment);
        transaction.commit();

    }
}

