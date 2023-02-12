package controlador.listas;

//import javax.xml.bind.annotation.XmlAnyElement;
//import javax.xml.bind.annotation.XmlElement;

public class NodoLista <E> {
    private E dato;
    private NodoLista<E> siguiente;

    public NodoLista (E dato, NodoLista<E> sig) {
        this.dato = dato;
        this.siguiente = sig;
    }

    public NodoLista () {
        this.dato = null;
        this.siguiente = null;
    }
    //@XmlAnyElement(lax = true)
    //@XmlElement
    public E getDato() {
        return this.dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }
    
    public NodoLista<E> getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(NodoLista<E> siguiente) {
        this.siguiente = siguiente;
    }

}