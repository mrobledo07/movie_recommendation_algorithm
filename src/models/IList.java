package models;

import exceptions.ElementNoTrobat;

import java.util.Iterator;

public interface IList<A> extends Iterable<A> {
    /**
     * This method adds a new element at the end of the list.
     * @param element
     */
    public void add(A element);

    /**
     * Removes a certain element, throws an ElementNotFound exception if it is not in the list.
     * @param element
     */
    public void remove(A element) throws ElementNoTrobat;
    
    /**
     * Proves if a certain element exists in the list.
     * @param element
     * @return boolean that indicates the existance of certain element in the list.
     */
    public boolean contains(A element);

    /**
     * Method to know the number of elements in the list.
     * @return number of elements in the list.
     */
    public int size();

    /**
     * Gets the element located in a certain position
     * @param index, position
     * @return element
     * @throws ElementNotFound
     */
    public A get(int index) throws ElementNoTrobat;

    /**
     * Prints the list.
     */
    public void print();

    /**
     * Returns an iterator for the list.
     * @return iterator
     */
    public Iterator<A> iterator();
}