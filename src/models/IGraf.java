package models;

import exceptions.ElementNoTrobat;
import exceptions.ElementRepetit;
import java.util.List;

public interface IGraf<A,B,T> {
    /**
	 * Afegeix relació entre dos elements
	 * @param A - element a de la relació (Usuari)
	 * @param B - element b de la relació (Pelicula)
	 * @throws ElementRepetit - la relació ja existeix
	 */
	public void inserirRelacio(T info, A a, B b) throws ElementRepetit;

    /**
	 * Esborrar relació entre dos elements
	 * @param A - element a de la relació (Usuari)
	 * @param B - element b de la relació (Pelicula)
	 * @throws ElementNoTrobat - algun dels elements no s'ha trobat
	 */
	public void esborrarRelacio(A a, B b) throws ElementNoTrobat;
	
	/**
	 * Retorna la llista de pelicules que ha valorat un usuari
	 * @param A - element a de la relació (Usuari)
	 * @return List<B> - llista dels elements de B relacionats amb a (Pelicules)
	 * @throws ElementNoTrobat - Element A no trobat
	 */
	public List<B> fila(A a) throws ElementNoTrobat;
	
	/**
	 * Retorna la llista d'usuaris que han valorat una película
	 * @param B - element b de la relació (Pelicula)
	 * @return List<A> - llista dels elements de A relacionats amb B (Usuaris)
	 * @throws ElementNoTrobat - Element B no trobat
	 */
	public List<A> columna(B b) throws ElementNoTrobat;
	
	/**
	 * Retorna si existeix relació entre l'element a i l'element b 
	 * @param a - element a de la relacio (Usuari)
	 * @param b - element b de la relacio (Pelicula)
	 * @return boolean - existencia de la relació
	 */
	public boolean existeixRelacio(A a, B b);


    /**
	 * Retorna la informacio emmagatzemada a la relació
	 * @param a - element a de la relacio
	 * @param b - element b de la relacio
	 * @return boolean - existencia de la relació
	 */
	public T obtenirInfoRelacio(A a, B b) throws ElementNoTrobat;

	/**
	 * Retorna el nombre de relacions (arestes) del graf (mida del graf)
	 * @return
	 */
	public int obtenirNumRelacions();


}
