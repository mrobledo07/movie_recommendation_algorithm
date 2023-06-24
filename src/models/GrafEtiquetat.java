package models;

import exceptions.ElementNoTrobat;
import exceptions.ElementRepetit;
import java.util.List;

public class GrafEtiquetat<A extends Comparable<A> ,B extends Comparable<B> ,T extends Comparable<T>> implements IGraf<A,B,T> {
    IMultiLlistaGenerica<A,B,T> relacions = new MultiLlista<>();

    @Override
    public void inserirRelacio(T info, A a, B b) throws ElementRepetit {
        try{
            relacions.inserir(info, a, b);
        }catch(ElementRepetit e){}
    }

    @Override
    public void esborrarRelacio(A a, B b) throws ElementNoTrobat {
            relacions.esborrar(a,b);
    }

    @Override
	public List<B> fila(A a) throws ElementNoTrobat{
        return relacions.fila(a);
    }

    @Override
    public List<A> columna(B b) throws ElementNoTrobat{
        return relacions.columna(b);
    }

    @Override
    public boolean existeixRelacio(A a, B b) {
        return relacions.existeix(a,b);
    }

    @Override
    public T obtenirInfoRelacio(A a, B b) throws ElementNoTrobat{
        return relacions.obtenirInfo(a,b);
    }

    @Override
    public int obtenirNumRelacions(){
        return relacions.obtenirNumRelacions();
    }

}
