/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.grafo;

import controlador.grafo.exception.VerticeOfSizeException;

public class GrafoNoDirijidoEtiquetado<E> extends GrafoDirigidoEtiquetado<E> {

    public GrafoNoDirijidoEtiquetado(Integer numVer, Class clazz) {
        super(numVer, clazz);
    }

    @Override
    public void insertarArista(Integer o, Integer d, Double peso) throws Exception {
        System.out.println(o + " " + d + " " + peso);
        if (o.intValue() <= getNumVertices() && d.intValue() <= getNumVertices()) {
            if (!existeArista(o, d)) {
                setNumAristas(getNumAristas() + 1);
                //numAristas++;
                // System.out.println("Origen: " + o + " Destino: " + d);
                getListaAdycente()[o].insertar(new Adyacencia(d, peso, o));
                getListaAdycente()[d].insertar(new Adyacencia(o, peso, d));
            }
        } else {
            throw new VerticeOfSizeException();
        }
    }

    @Override
    public String toString() {
        return super.getNombre();
    }

}
