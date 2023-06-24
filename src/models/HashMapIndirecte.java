package models;

import java.util.Iterator;

import exceptions.ElementNoTrobat;

public class HashMapIndirecte<K,V> implements IHashMap<K,V> {

    private NodeHash<K,V>[] taula;
    private int numElem;
    @SuppressWarnings("unchecked")
    public HashMapIndirecte(int mida) {
        taula = new NodeHash[mida];
        numElem = 0;
    }

    // Metode per insertar un element a la taula. Si existeix un element amb aquesta clau s'actualitza el valor
    @Override
    public void inserir(K key, V value){
       //redimensionem la taula si el factor de carrega es superior a 0.75 amb una taula de longitud doble
        if(factorCarrega() > 0.75f)
            redimensionar();
        
        int posicio = calcularPosicio(key, taula);
        if (taula[posicio] == null) {
            taula[posicio] = new NodeHash<K,V>(key, value);
        } else {
            NodeHash<K,V> aux = taula[posicio];
            NodeHash<K,V> node;
            if ((node = buscarNode(key)) != null)
                node.setV(value);
            else{
                node = new NodeHash<K,V>(key, value);
                node.setNext(aux);
                taula[posicio] = node;
            }
        }
        numElem++;
    }

    @SuppressWarnings("unchecked")
    private void redimensionar(){
            NodeHash<K,V>[] taulaAux = new NodeHash[taula.length*2];
            NodeHash<K,V> aux2, node;
            int posicio;
            for(NodeHash<K,V> aux : taula){
                if (aux != null){
                    node = new NodeHash<K,V>(aux.getKey(), aux.getValue());
                    posicio = calcularPosicio(aux.getKey(), taulaAux);
                    if(taulaAux[posicio] == null)
                        taulaAux[posicio] = node;
                    else{
                        aux2 = taulaAux[posicio];
                        node.setNext(aux2);
                        taulaAux[posicio] = node;
                    }
                }      
            }
            taula = taulaAux;
    }

    private int calcularPosicio(K key, NodeHash<K,V> taula[]){
        int posicio = key.hashCode() % taula.length;
        if(posicio < 0)
            posicio = posicio * -1;
        
        return posicio;
    }

    private NodeHash<K,V> buscarNode(K key){
        int posicio = calcularPosicio(key, taula);
        if (taula[posicio] == null) {
            return null;
        } else {
            NodeHash<K,V> aux = taula[posicio];
            while (aux != null) {
                if (aux.getKey().equals(key))
                    return aux;
                aux = aux.getNext();
            }
            return null;
        }
    }

    // Metode per a esborrar un element de la taula de hash
    @Override
    public void esborrar(K key) throws ElementNoTrobat{
        int posicio = calcularPosicio(key, taula);
        if (taula[posicio] == null) {
            throw new ElementNoTrobat("No existeix cap element amb aquesta clau");
        } else {
            NodeHash<K,V> aux = taula[posicio];
            if (aux.getKey().equals(key)) {
                taula[posicio] = aux.getNext();
                numElem--;
                return;
            }
            while (aux.getNext() != null) {
                if (aux.getNext().getKey().equals(key)) {
                    aux.setNext(aux.getNext().getNext());
                    numElem--;
                    return;
                }
                aux = aux.getNext();
            }
            throw new ElementNoTrobat("No existeix cap element amb aquesta clau");
        }
    }

    // Metode per a comprovar si un element esta a la taula de hash
    @Override
    public boolean buscar(K key){
        int posicio = calcularPosicio(key, taula);
        if (taula[posicio] == null) {
            return false;
        } else {
            NodeHash<K,V> aux = taula[posicio];
            while (aux != null) {
                if (aux.getKey().equals(key))
                    return true;
                aux = aux.getNext();
            }
            return false;
        }
    }

    // Metode per a comprovar si la taula te elements
    @Override
    public boolean esBuida(){
        return numElem == 0;
    }

    // Metode per a obtenir el nombre d'elements de la taula
    @Override
    public int longitud(){
        return numElem;
    }


    // Metode per a obtenir les claus de la taula
    @Override
    public Object[] obtenirClaus(){
        Object[] claus = new Object[longitud()];
        int i = 0;
        for (int j = 0; j < taula.length; j++) {
            if (taula[j] != null) {
                NodeHash<K,V> aux = taula[j];
                while (aux != null) {
                    claus[i] = aux.getKey();
                    i++;
                    aux = aux.getNext();
                }
            }
        }
        return claus;
    }

    // Metode per a obtenir un array amb tots els elements de K
    @Override
    public V element(K key) throws ElementNoTrobat{
        int posicio = calcularPosicio(key, taula);
        if (taula[posicio] == null) {
            throw new ElementNoTrobat("No existeix cap element amb aquesta clau");
        } else {
            NodeHash<K,V> aux = taula[posicio];
            while (aux != null) {
                if (aux.getKey().equals(key)) 
                    return aux.getValue();
                aux = aux.getNext();
            }
            throw new ElementNoTrobat("No existeix cap element amb aquesta clau");
        }
    }

    // Metode per a saber el factor de carrega actual de la taula
    @Override
    public float factorCarrega(){
        return (float) this.longitud() / (float) taula.length;
    }

    // Metode per a saber la mida actual de la taula
    @Override
    public int midaTaula(){
        return taula.length;
    }   

    //Metode per a poder iterar pels elements de la taula
    @Override
    public Iterator<V> iterator(){
       return new HashMapIndirecteIterator();
    }

    // Classe interna per a poder iterar pels elements de la taula
    private class HashMapIndirecteIterator implements Iterator<V> {
        private int posicio = 0;
        private NodeHash<K,V> aux = null;

        public HashMapIndirecteIterator(){
            while(posicio < taula.length && taula[posicio] == null)
                posicio++;
            
            if (posicio < taula.length)
                aux = taula[posicio];
            else    
                aux = null;
        }

        public boolean hasNext() {
            if (aux != null)
                return true;
            else
                return false;
        }

        public V next() {
            V valor = aux.getValue();
            aux = aux.getNext();
            if(aux == null){
                posicio++;
                while(posicio < taula.length && taula[posicio] == null)
                    posicio++;
                
                if (posicio < taula.length)
                    aux = taula[posicio];
                else    
                    aux = null;
            }
            return valor;
        }
    }
}
