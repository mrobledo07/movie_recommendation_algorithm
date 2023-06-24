package models;


import java.util.Iterator;

import exceptions.ElementNoTrobat;

public class ArrayList<A> implements IList<A> {
    private A[] list;
    private int numElem;

    @SuppressWarnings("unchecked")
    public ArrayList(){
        this.list = (A[])new Object[10];
        this.numElem = 0;
    }
    
    @Override    @SuppressWarnings("unchecked")
    public void add(A elem){
        if (numElem == list.length){
            A[] aux = (A[])new Object[list.length + 10];
            for (int i = 0; i < numElem; i++)
                aux[i] = list[i];

            list = aux;
        }
        list[numElem] = elem;
        numElem++;
    }

    @Override
    public void remove(A element) throws ElementNoTrobat{
        Object elem = (Object) element;
        for (int i = 0; i < numElem; i++)
            if (list[i].equals(elem)){
                if (i != numElem - 1)
                    rotate(i);
                return;
            }
        
        throw new ElementNoTrobat("The element was not found");
    }

    private void rotate(int index){
        for (int i = index; i < numElem - 1; i++)
            list[i] = list[i+1];
    }

    @Override
    public boolean contains(A elem){
        for (int i = 0; i < numElem; i++)
            if (list[i].equals(elem))
                return true;
        
        return false;
    }

    @Override
    public int size(){
        return numElem;
    }

    public A get(int index) throws ElementNoTrobat{
        A ret = null;
        try{
           ret = list[index];
        }catch (ArrayIndexOutOfBoundsException e){
            throw new ElementNoTrobat("The index was to high");
        }
        return ret;
    }

    @Override
    public void print(){
        for (int i = 0; i < numElem; i++)
            System.out.println(list[i]);
    }

    @Override
    public Iterator<A> iterator(){
        return new ArrayListIterator(list, numElem);
    }

    private class ArrayListIterator implements Iterator<A>{
        private int position;
        private A[] list;
        private int numElem;
        
        public ArrayListIterator(A[] list, int numElem){
            position = 0;
            this.list = list;
            this.numElem = numElem;
        }

        @Override
        public boolean hasNext(){
            if (position < numElem)
                return true;
            else
                return false;
        }

        @Override
        public A next(){
            A ret = list[position];
            position++;
            return ret;
        }
    }
}

