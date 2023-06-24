    package models;

    public class MaxHeap<A extends Comparable<A>> implements IArbre<A> {
        private int numElem;
        private A[] taula;

        @SuppressWarnings("unchecked")
        public MaxHeap(){
            this.numElem = 0;
            this.taula = (A[]) new Comparable[10];
        }

        @Override @SuppressWarnings("unchecked")
        public void inserir(A element){
        if (numElem == taula.length - 1){
                A[] aux = (A[])new Comparable[taula.length*2];
                for (int i = 0; i < numElem; i++)
                    aux[i] = taula[i];
                taula = aux;
        }

        numElem++;
        int i = numElem;
        int pi = i / 2;

        while (pi != 0 && taula[pi] != null && taula[pi].compareTo(element) < 0){
                taula[i] = taula[pi];
                i = pi;
                pi = i / 2;
        }
        taula[i] = element;
        }

        @Override
        public A getArrel() {
            return taula[1];
        }

        @Override
        public void esborrarArrel(){
            if (numElem == 0)
                return;

            // Darrer element del heap
            A x = taula[numElem];
            numElem--;

            int i = 1;
            int fi = 2 * i;

            // Si existeix fill dret i aquest és major que l'esquerre
            if (fi < numElem && taula[fi+1].compareTo(taula[fi]) > 0)
                fi++;

            try{
                while (fi <= numElem && taula[fi].compareTo(x) > 0){
                    taula[i] = taula[fi];
                    i = fi;
                    fi = 2 * i;

                    if (fi < numElem && taula[fi+1].compareTo(taula[fi]) > 0)
                        fi++;

                }
            }catch(NullPointerException e){}

            taula[i] = x;
        
        }

        @Override
        public boolean esBuit(){
            return numElem == 0;
        }

        @Override
        public A getSeguent() {
            if (numElem == 0) {
                return null; 
            }

            A seguent = getArrel();
            esborrarArrel();
            return seguent;
        }


    }
