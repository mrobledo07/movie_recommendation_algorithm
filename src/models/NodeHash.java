package models;

public class NodeHash<K,V> {

    private K clau;
    private V valor;
    private NodeHash<K,V> seguent;

    public NodeHash(K clau, V valor) {
        this.clau = clau;
        this.valor = valor;
        this.seguent = null;        
    }

    public void setNext(NodeHash<K,V> seguent){
        this.seguent = seguent;
    }

    public void setV(V valor){
        this.valor = valor;
    }

    public NodeHash<K,V> getNext(){
        return this.seguent;
    }

    public K getKey(){
        return this.clau;
    }

    public V getValue(){
        return this.valor;
    }
    
}