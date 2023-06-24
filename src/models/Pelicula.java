package models;

public class Pelicula implements Comparable<Pelicula> {
    private int idP;
    private String titol;
    private int any;

    public Pelicula(int idP, String titol, int any) {
        this.idP = idP;
        this.titol = titol;
        this.any = any;
    }

    public int getID() {
        return this.idP;
    }

    public String getTitol() {
        return this.titol;
    }

    public int getAny() {
        return this.any;
    }

    // Comparem amb tres criteris, primer el títol, després l'any i per últim l'id
    @Override
    public int compareTo(Pelicula peliculaAComparar) {

        int comp = this.getTitol().compareTo(peliculaAComparar.getTitol());

        if(comp == 0)
            comp = this.getAny() - peliculaAComparar.getAny();

        if(comp == 0)
            comp = this.getID() - peliculaAComparar.getID();

        return comp;
    }

} 