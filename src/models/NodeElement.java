package models;

public class NodeElement<T, A> {
    private T info;
    private NodeRelacio<A> primerElem;

    public NodeElement(T info) {
        this.info = info;
        this.primerElem = null;
    }

    public T getInfo() {
        return this.info;
    }

    public NodeRelacio<A> getPrimerElem() {
        return this.primerElem;
    }

    public void setPrimerElem(NodeRelacio<A> primerElem) {
        this.primerElem = primerElem;
    }

}