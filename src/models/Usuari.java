package models;

public class Usuari implements Comparable<Usuari>{
    private int id;
    private int diferencia_valoracio;
    private int aparicions;

    public Usuari(int id){
        this.id = id;
    }

    public Usuari(int id, int diferencia, int aparicions){
        this.id = id;
        this.diferencia_valoracio = diferencia;
        this.aparicions = aparicions;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setdiferencia_valoracio(int diferencia_valoracio){
        this.diferencia_valoracio = diferencia_valoracio;
    }

    public int getdiferencia_valoracio(){
        return this.diferencia_valoracio;
    }
    
    public void setAparicions(int aparicions){
        this.aparicions = aparicions;
    }

    public int getAparicions(){
        return this.aparicions;
    }

    @Override
    public int compareTo(Usuari u){
        int comp = this.getAparicions() - u.getAparicions();
        if (comp == 0)
            comp = u.getdiferencia_valoracio() - this.getdiferencia_valoracio();

        return comp;
    }
}
