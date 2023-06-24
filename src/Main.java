
import models.*;
import exceptions.*;
import io.*;

import java.util.List;
import java.util.Iterator;
import java.util.Scanner;

import java.lang.Math;



public class Main {
    public static void main(String[] args) throws ElementRepetit, ElementNoTrobat {
        IHashMap<Integer, Pelicula> pelicules = new HashMapIndirecte<>(100);
        IHashMap<Integer, Integer> valoracionsUsuari = new HashMapIndirecte<>(100);
        IHashMap<Integer, Usuari> usuaris = new HashMapIndirecte<>(100);
        IGraf<Integer, Integer, Integer> valoracions = new GrafEtiquetat<>();
        IArbre<Usuari> usuarisSimilars = new MaxHeap<>();
        Scanner teclat = new Scanner(System.in);

        int idUsuariRecomanacio = teclat.nextInt();

        teclat.close();
        FileLoader.carregarFitxerPelicules("movie_titles.txt", pelicules);
        FileLoader.carregarValoracionsUsuariARecomanar("training_set/", idUsuariRecomanacio, valoracionsUsuari);
        int ajustAparicions = FileLoader.carregarFitxerValoracions("training_set/", idUsuariRecomanacio, valoracions, usuaris, valoracionsUsuari);
        
        IList<Integer> recomanades = algorismeRecomanacio(idUsuariRecomanacio, valoracions, usuarisSimilars, usuaris, valoracionsUsuari, ajustAparicions);
        System.out.println("Recomanacions per l'usuari " + idUsuariRecomanacio + ":");
        for (int i = 0; i < recomanades.size(); i++) {
            System.out.println(pelicules.element(recomanades.get(i)).getTitol());
        }
    }

    public static IList<Integer> algorismeRecomanacio(int idUsuari, IGraf<Integer,Integer,Integer> graf, IArbre<Usuari> maxheap, IHashMap<Integer,Usuari> hashmap, IHashMap<Integer,Integer> hashmapUsuari, Integer ajust) throws ElementNoTrobat{
        // Declaració variables
        int valoracioUsuariSimilar;
        Usuari u;
        float ajustAparicions = (float) Math.log10(ajust);

        Iterator<Usuari> it = hashmap.iterator();
        while (it.hasNext()){
            u = it.next();
            if ((float) u.getAparicions() >= ajustAparicions)
                maxheap.inserir(u); //inserim l'usuari a l'arbre ja que és similar
        }

        // ara ja tenim tots els usuaris similars a l'arbre

        u = maxheap.getSeguent();  //obtenim l'usuari més similar
        List<Integer> peliculesSimilars;
        IList<Integer> recomanades =  new ArrayList<>();
        Usuari aux = null;
        while(recomanades.size() < 3 && u != aux && u != null){
                peliculesSimilars = graf.fila(u.getId()); //obtenim les pelicules d'aquest usuari
                for(int k = 5; k >= 4; k--){
                    for(int idPelicula : peliculesSimilars){
                        if (recomanades.size() == 3) break;        //si ja en portem 3, acabem
                        valoracioUsuariSimilar = graf.obtenirInfoRelacio(u.getId(), idPelicula);
                        if (!recomanades.contains(idPelicula) && valoracioUsuariSimilar == k)
                            recomanades.add(idPelicula);  //si la valoració que ha fet el similar sobre la pelicula es bona, la recomanem
                    }
                }
                aux = u;
                u = maxheap.getSeguent();  //obtenim següent usuari amb mes similitud

        } //repetim mentres no portessem 3 i i hagui un altre usuari similar
        
        return recomanades;

    }

}

