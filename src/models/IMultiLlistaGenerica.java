package models;

import java.util.List;

import exceptions.ElementNoTrobat;
import exceptions.ElementRepetit;

public interface IMultiLlistaGenerica<A, B, T> {
	
	/**
	 * Afegeix la relació d'elements
	 * @param A - element a de la relació
	 * @param B - element b de la relació
	 * @throws ElementRepetit - la relació ja existeix
	 */
	public void inserir(T info, A a, B b) throws ElementRepetit;

    /**
	 * Esborrar la relació d'elements
	 * @param A - element a de la relació
	 * @param B - element b de la relació
	 * @throws ElementNoTrobat - algun dels elements no s'ha trobat
	 */
	public void esborrar(A a, B b) throws ElementNoTrobat;
	
	/**
	 * Retorna la llista de relacións amb l'element a
	 * @param A - element a de la relació
	 * @return List<B> - llista dels elements de B relacionats amb a
	 * @throws ElementNoTrobat - Element A no trobat
	 */
	public List<B> fila(A a) throws ElementNoTrobat;
	
	/**
	 * Retorna la llista de relacións amb l'element b
	 * @param B - element b de la relació
	 * @return List<A> - llista dels elements de A relacionats amb B
	 * @throws ElementNoTrobat - Element B no trobat
	 */
	public List<A> columna(B b) throws ElementNoTrobat;
	
	/**
	 * Retorna si existeix relació entre l'element a i l'element b
	 * @param a - element a de la relacio
	 * @param b - element b de la relacio
	 * @return boolean - existencia de la relació
	 */
	public boolean existeix(A a, B b);


	/**
	 * Retorna la informacio emmagatzemada a la relació
	 * @param a - element a de la relacio
	 * @param b - element b de la relacio
	 * @return boolean - existencia de la relació
	 */
	public T obtenirInfo(A a, B b) throws ElementNoTrobat;

	/**
	 * Retorna el nombre de relacions total
	 * @return
	 */
	public int obtenirNumRelacions();
}

