package models;

public class NodeRelacio<A> {
    private int a;                          //referencia (no punter) a element a 
    private int b;                          //referencia (no punter) a element b
    private NodeRelacio<A> segFila;            //punter a node següent fila
    private NodeRelacio<A> segColumna;         //punter a node següent columna
    private A info;

    public NodeRelacio(A info, int a,  int b) {
        this.a = a;
        this.b = b;
        this.segFila = null;
        this.segColumna = null;
        this.info = info;
    }
    
    public int getA() {
        return a;
    }
    
    public int getB() {
        return b;
    }
    
    public NodeRelacio<A> getSegFila() {
        return segFila;
    }

    public NodeRelacio<A> getSegColumna() {
        return segColumna;
    }

    public A getInfo(){
        return this.info;
    }

    public void setInfo(A info){
        this.info = info;
    }
    
    public void setSegFila(NodeRelacio<A> segFila) {
        this.segFila = segFila;
    }

    public void setSegColumna(NodeRelacio<A> segColumna) {
        this.segColumna = segColumna;
    }
}
