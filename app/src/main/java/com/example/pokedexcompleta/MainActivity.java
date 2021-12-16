package com.example.pokedexcompleta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    ListView listView;
    //Jsoup --> JSON.
    ArrayList<Pokemon> pokemons = new ArrayList<>();
    ArrayList<String> nombres = new ArrayList<>();
    static ArrayList<String> urlsImg = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        //---------PRE-EJECUCION---------//
        //CustomAdapter adapter = new CustomAdapter(pokemons, this);
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                //conectamos con una página
                try
                {
                    Document resultadoCompleto = Jsoup.connect("https://www.pokemon.com/es/pokedex/").get();

                    //aqui hacemos el scraping de la pagina web cogiendo toda la lista
                    /*Elements elementos = resultadoCompleto.selectXpath("/html/body/div[4]/section[5]/ul");

                    //el for each funciona introduciendo una variable nueva
                    //representando cada elemento de la lista, TODOS, porque es un bucle
                    //entonces por cada elemento hace algo
                    for (Element elemento: elementos)
                    {
                        System.out.println(elemento);
                    }*/

                    //casteamos el Elements a ArrayList<String> con un eachText()
                    nombres = (ArrayList<String>) resultadoCompleto.select("[href^=/es/pokedex/]").eachText();
                    nombres.remove(0); //en esta página el elemento 0 no funciona. Lo eliminamos

                    //por cada uno de los elementos que tenemos dentro del arrayList
                    //en cada elemento le damos el nombre de manera individual
                    /*for (String elemento: nombres)
                    {
                        urlsImg.add("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + + ".png");
                        System.out.println(elemento);
                    }*/

                    for (int i = 0; i < nombres.size(); i++)
                    {
                        //nombrePkmSinNum estamos haciendo un nombre del pokemon quitantole los numeros de delante
                        //lo conseguimos asi: por cada posicion del elemento del array hago un substring
                                                            //un substring acorta la cadana de caracteres desde el principio
                                                            //la reduce tantos caracteres como le indiques en el indice
                        //reduce desde el caracter que le indiques hacia la izquierda
                            //por si quisiera la categoria...
                        //String nombrePkmSinNum = nombres.get(i).substring(nombres.get(i).lastIndexOf("-") + 2);
                        //con este string consigo que el numero tenga los correspondientes "0" delante
                        String numPkm = String.format("%03d", (i + 1));
                        urlsImg.add("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + numPkm + ".png"); //lista de urls
                        /*System.out.println(nombres.get(i));
                        System.out.println(urlsImg.get(i));*/
                        //Document caracteristicasPkm = Jsoup.connect("https://www.pokemon.com/es/pokedex/" + nombrePkmSinNum).get();
                        pokemons.add(new Pokemon(nombres.get(i)));  //una vez conformada la lista hacemos el listado de nombres
                        System.out.println("Isma es guapetón");
                        System.out.println("HHHHola");

                    }

                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

                //----POST-EJECUCION----//
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        CustomAdapter adapter = new CustomAdapter(pokemons, MainActivity.this);
                        listView.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }
}