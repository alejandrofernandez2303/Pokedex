package com.example.pokedexcompleta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter
{
    ArrayList<Pokemon> pokemons;
    Context ctx;

    public CustomAdapter(ArrayList<Pokemon> pokemons, Context ctx)
    {
        this.pokemons = pokemons;
        this.ctx = ctx;
    }

    @Override
    public int getCount()
    {
        return pokemons.size();
    }

    @Override
    public Pokemon getItem(int i)
    {
        return pokemons.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup)
    {
        //genera un view nuevo a partir del layout de "item_pkm" generado.
        //básicamente en un listado, cada uno de los item es un pokemon
        //entonces linkamos este layout con el pokemon del listview.
        View viewInflado = LayoutInflater.from(ctx).inflate(R.layout.item_pkm, null);

        //estas 3 lineas ya nos están devolviendo la pantalla del siguiente layout INFLADA
        //inflada con los datos del pokemon pultado
        TextView txtNombre = viewInflado.findViewById(R.id.nombrePKM);
        //TextView txtCategoria = viewInflado.findViewById(R.id.categoriaPKM);
        ImageView imgPKM = viewInflado.findViewById(R.id.imagenPKM);

        //coger todos los datos de la posicion del pokemon seleccionado
        txtNombre.setText(pokemons.get(position).getNombre());
        //txtCategoria.setText(pokemons.get(position).getCategoria());
        Picasso.get().load(MainActivity.urlsImg.get(position)).into(imgPKM);

        return viewInflado;
    }
}
