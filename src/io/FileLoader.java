package io;

import java.io.*;

import exceptions.ElementNoTrobat;
import exceptions.ElementRepetit;
import models.IGraf;
import models.IHashMap;
import models.Pelicula;
import models.Usuari;



public class FileLoader {

    /**
     * Carreguem només les valoracions de l'usuari al que volem fer recomanacions
     * @param directoriPath
     * @param idUsuari
     * @param graf
     * @throws ElementRepetit
     */
    public static void carregarValoracionsUsuariARecomanar(String directoriPath, int idUsuari, IHashMap<Integer,Integer> valoracions) throws ElementRepetit{
        String line;
        String[] dades;
        int idP = -1, idU, rating;
        File directori = new File(directoriPath);
        if (directori.exists() && directori.isDirectory()) {
            File[] arxius = directori.listFiles();
            for(File arxiu : arxius){
                try {
                    BufferedReader br = new BufferedReader(new FileReader(arxiu));
                    while ((line = br.readLine()) != null) {
                        try{
                            if(line.contains(":")){
                                dades = line.split(":");
                                idP = Integer.parseInt(dades[0]);
                            }else{
                                dades = line.split(",");
                                idU = Integer.parseInt(dades[0]);
                                if (idU == idUsuari){
                                    rating = Integer.parseInt(dades[1]);
                                    valoracions.inserir(idP, rating);
                                }
                            }
                        }catch (NumberFormatException e){}
                    }
                    br.close();
                }catch (FileNotFoundException e) {
                        System.out.println("No s'ha trobat el fitxer");
                } catch (IOException e) {
                        System.out.println("Ha hi hagut un error E/S");
                }
            } 
        }else{
            System.out.println("No s'ha trobat el directori");
        }
    }

    /**
     * Carrega les relacions entre usuaris i pelicules
     * @param directoriPath, path del directori
     * @param grafRelacions, graf que emmagatzema les relacions
     * @throws ElementRepetit
     */
    public static int carregarFitxerValoracions(String directoriPath, int idUsuari, IGraf<Integer, Integer, Integer> grafRelacions, IHashMap<Integer,Usuari> usuarisSimilars, IHashMap<Integer,Integer> valoracionsUsuari) throws ElementRepetit, ElementNoTrobat {
        String line;
        String[] dades;
        int idP = -1, idU, rating, diferencia, ajust = 0;
        Usuari u;
        File directori = new File(directoriPath);
        if (directori.exists() && directori.isDirectory()) {
            File[] arxius = directori.listFiles();
            for(File arxiu : arxius){
                try {
                    BufferedReader br = new BufferedReader(new FileReader(arxiu));
                    while ((line = br.readLine()) != null) {
                        try{
                            if(line.contains(":")){
                                dades = line.split(":");
                                idP = Integer.parseInt(dades[0]);
                            }else{
                                dades = line.split(",");
                                idU = Integer.parseInt(dades[0]);
                                rating = Integer.parseInt(dades[1]);
                                if (valoracionsUsuari.buscar(idP)){
                                    diferencia = valoracionsUsuari.element(idP) - rating;  //si l'usuari inicial ha valorat la pelicula
                                    if (diferencia < 0) diferencia *= -1;           //valor absolut
                                    if (diferencia <= 1 && idU != idUsuari){        //calculem diferencia, si es <=1 l'usuari auxiliar es similar per a aquesta pelicula
                                        if (usuarisSimilars.buscar(idU)){
                                                u = usuarisSimilars.element(idU);                   //si l'usuari ja existeix al hashmap
                                                u.setAparicions(u.getAparicions() + 1);             //augmentem nombre aparicions
                                                u.setdiferencia_valoracio(u.getdiferencia_valoracio() + diferencia); //incrementem diferencia
                                        }else{  //si l'usuari no existeix al hashmap, s'afegeix
                                                usuarisSimilars.inserir(idU, new Usuari(idU, diferencia, 1));
                                        }
                                        ajust++;
                                    }
                                }else{
                                      if (rating >= 4)        //si l'usuari inicial no ha valorat aquesta pelicula, l'usuari ja no serà similar per aquesta pelicula
                                          grafRelacions.inserirRelacio(rating, idU, idP); //pero si la valoracio es superior a 4, es una pelicula que pot ser recomanada
                                }
                            }
                         }catch (NumberFormatException e){}
                    }
                    br.close();
                }catch (FileNotFoundException e) {
                        System.out.println("No s'ha trobat el fitxer");
                } catch (IOException e) {
                        System.out.println("Ha hi hagut un error E/S");
                }
            } 
        }else{
            System.out.println("No s'ha trobat el directori");
        }

        return ajust;
    }

    /**
     * Carrega les dades de les pelicules del fitxer
     * @param dataPath, path del fitxer
     * @param map, hashmap on es guarden les pelicules
     */
    public static void carregarFitxerPelicules(String dataPath, IHashMap<Integer, Pelicula> map) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(dataPath));
            String line;
            while ((line = br.readLine()) != null) {
                String[] spliting = line.split(",");
                try{
                    int idP = Integer.parseInt(spliting[0]);
                    int anyP = Integer.parseInt(spliting[1]);
                    Pelicula novaPel = new Pelicula(idP, spliting[2], anyP);
                    map.inserir(idP, novaPel);
                }catch (NumberFormatException e){}
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("No s'ha trobat el fitxer");
        } catch (IOException e) {
            System.out.println("Ha hi hagut un error E/S");
        }
    }

}
