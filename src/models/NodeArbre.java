package models;

public class NodeArbre<A> {
    private A info;
    private NodeArbre<A> fillEsquerre;
    private NodeArbre<A> fillDret;

    public NodeArbre(A info) {
        this.info = info;
        this.fillEsquerre = null;
        this.fillDret = null;
    }

    public A getInfo() {
        return this.info;
    }

    public NodeArbre<A> getFillEsquerre() {
        return this.fillEsquerre;
    }

    public NodeArbre<A> getFillDret() {
        return this.fillDret;
    }

    public void setFillEsquerre(NodeArbre<A> fillEsquerre) {
        this.fillEsquerre = fillEsquerre;
    }

    public void setFillDret(NodeArbre<A> fillDret) {
        this.fillDret = fillDret;
    }

}
