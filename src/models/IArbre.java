package models;

public interface IArbre<A extends Comparable<A>> {

    /**
     * Insereix un node a l'arbre
     * @param info
     */
    public void inserir(A info);

    /**
     * Retorna arrel de l'arbre
     * @return
     */
    public A getArrel();

    /**
     * Esborra arrel de l'arbre
     */
    public void esborrarArrel();

    /**
     * Retorna si l'arbre té elements o no
     * @return
     */
    public boolean esBuit();

    /**
     * Retorna el següent element més gran
     * @return
     */
    public A getSeguent();
}
