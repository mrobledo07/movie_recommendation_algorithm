package models;

import java.util.ArrayList;
import java.util.List;
import exceptions.ElementNoTrobat;
import exceptions.ElementRepetit;



public class MultiLlista<A extends Comparable<A>, B extends Comparable<B>, T extends Comparable<T>> implements IMultiLlistaGenerica<A,B,T> {
    
    private HashMapIndirecte<Integer, NodeElement<A,T>> conjuntA;
    private HashMapIndirecte<Integer, NodeElement<B,T>> conjuntB;
    private int numRelacions;

    public MultiLlista() {
        conjuntA = new HashMapIndirecte<>(100);
        conjuntB = new HashMapIndirecte<>(100);
    }

    @Override
    public void inserir(T info, A a, B b) throws ElementRepetit {
        if(this.existeix(a, b))
            throw new ElementRepetit("ja existeix");
    
        NodeElement<A,T> nA;
        NodeElement<B,T> nB;

        try{
            nA = conjuntA.element(a.hashCode());
        }catch(ElementNoTrobat e){
            nA = new NodeElement<>(a);
            conjuntA.inserir(a.hashCode(), nA);
        }


        try{
            nB = conjuntB.element(b.hashCode());
        }catch(ElementNoTrobat e){
            nB = new NodeElement<>(b);
            conjuntB.inserir(b.hashCode(), nB);
        }
        
        NodeRelacio<T> nR = new NodeRelacio<T>(info, a.hashCode(), b.hashCode());

        //inserim a la columna
        if(nA.getPrimerElem() == null){
            nA.setPrimerElem(nR);
        }
        else{
            nR.setSegFila(nA.getPrimerElem());
            nA.setPrimerElem(nR);
        }

        //inserim a la fila
        if(nB.getPrimerElem() == null){
            nB.setPrimerElem(nR);
        }
        else{
            nR.setSegColumna(nB.getPrimerElem());
            nB.setPrimerElem(nR);
        }

        numRelacions++;
    }
    
    @Override
    public void esborrar(A a, B b) throws ElementNoTrobat {
        if(!this.existeix(a, b))
            throw new ElementNoTrobat("no existeix");
        
        NodeElement<A,T> nA = conjuntA.element(a.hashCode());
        NodeElement<B,T> nB = conjuntB.element(b.hashCode());


        //eliminem relació de la columna
        NodeRelacio<T> aux = nA.getPrimerElem();
        if(aux.getA() == a.hashCode() && aux.getB() == b.hashCode()){
            nA.setPrimerElem(aux.getSegFila());
        }else{                                  
            while(aux.getSegFila().getA() != a.hashCode() || aux.getSegFila().getB() != b.hashCode())  //sabem que la relació existeix, de manera que quan es surtigue del while obligatoriament és perquè s'ha trobat la relació
                aux = aux.getSegFila();
            
            aux.setSegFila(aux.getSegFila().getSegFila());
        }
        

        //eliminem relació de la fila
        aux = nB.getPrimerElem();
        if(aux.getA() == a.hashCode() && aux.getB() == b.hashCode()){
            nB.setPrimerElem(aux.getSegColumna());
        }else{
            while(aux.getSegColumna().getA() != a.hashCode() || aux.getSegColumna().getB() != b.hashCode())
                aux = aux.getSegColumna();

            aux.setSegColumna(aux.getSegColumna().getSegColumna());
        }

       numRelacions--;
    }

    @Override
    public List<B> fila(A a) throws ElementNoTrobat {
        List<B> llistaFila = new ArrayList<>();
        NodeElement<A,T> nA;
        try{
            nA = conjuntA.element(a.hashCode());
        }catch(ElementNoTrobat e){
            return llistaFila;
        }
        
        NodeRelacio<T> aux = nA.getPrimerElem();
        while(aux != null){
            try{
                llistaFila.add(conjuntB.element(aux.getB()).getInfo());
            }catch(ElementNoTrobat e){}
            aux = aux.getSegFila();
        }

        return llistaFila;
    }

    @Override
    public List<A> columna(B b) throws ElementNoTrobat {
        List<A> llistaColumna = new ArrayList<>();
        NodeElement<B,T> nB;
        try{
            nB = conjuntB.element(b.hashCode());
        }catch(ElementNoTrobat e){
            return llistaColumna;
        }

        NodeRelacio<T> aux = nB.getPrimerElem();
        while(aux != null){
            try{
                llistaColumna.add(conjuntA.element(aux.getA()).getInfo());
            }catch(ElementNoTrobat e){}
            aux = aux.getSegColumna();
        }
        return llistaColumna;
    }

    @Override
    public boolean existeix(A a, B b){
        NodeElement<A,T> nA;
        try{
            nA = conjuntA.element(a.hashCode());
            conjuntB.element(b.hashCode());
        }catch(ElementNoTrobat e){
            return false;
        }

        NodeRelacio<T> aux = nA.getPrimerElem();
        while(aux != null){
            if(aux.getA() == a.hashCode() && aux.getB() == b.hashCode())
                return true;
            aux = aux.getSegFila();
        }

        return false;

    }

    @Override
    public T obtenirInfo(A a, B b) throws ElementNoTrobat{
        if (!this.existeix(a, b))
            throw new ElementNoTrobat("no existeix");

        NodeElement<A,T> nA = conjuntA.element(a.hashCode());
        NodeRelacio<T> aux = nA.getPrimerElem();
        while(aux != null){
            if(aux.getA() == a.hashCode() && aux.getB() == b.hashCode())
                return aux.getInfo();
            aux = aux.getSegFila();
        }

        return null;
    }

    @Override
    public int obtenirNumRelacions(){
        return numRelacions;
    }
}

