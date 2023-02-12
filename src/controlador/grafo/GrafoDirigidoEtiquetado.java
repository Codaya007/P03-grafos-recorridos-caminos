/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.grafo;

import controlador.listas.ListaEnlazada;
import controlador.utililes.Utilidades;
import java.lang.reflect.Array;
import java.util.HashMap;

public class GrafoDirigidoEtiquetado<E> extends GrafoDirigido {

    protected E etiquetas[];
    protected HashMap<E, Integer> dicVertices;
    private Class<E> clazz;

    public HashMap<E, Integer> getDicVertices() {
        return dicVertices;
    }

    public E[] getEtiquetas() {
        return this.etiquetas;
    }

    public String getEtiquetasString() {
        String result = "[ ";

        for (int i = 0; i < etiquetas.length; i++) {
            if (etiquetas[i] == null) {
                result += " " + " - " + " ,";
            } else {
                result += " " + etiquetas[i].toString() + " ,";
            }
        }

        result = Utilidades.removeLastChar(result);
        result += " ]";

        return result;
    }

    public GrafoDirigidoEtiquetado(Integer numVert, Class clazz) {
        super(numVert);
        this.clazz = clazz;
        etiquetas = (E[]) Array.newInstance(clazz, numVert);
        dicVertices = new HashMap(numVert);
    }

    public Boolean existeAristaE(E o, E d) throws Exception {
        return this.existeArista(obtenerCodigoE(o), obtenerCodigoE(d));
    }

    public void insertarAristaE(E o, E d, Double peso) throws Exception {
        insertarArista(obtenerCodigoE(o), obtenerCodigoE(d), peso);
    }

    public void insertarAristaE(E o, E d) throws Exception {
        insertarArista(obtenerCodigoE(o), obtenerCodigoE(d));
    }

    public ListaEnlazada<Adyacencia> adycentesE(E o) {
        return adycentes(obtenerCodigoE(o));
    }

    private Integer obtenerCodigoE(E etiqueta) {
        return dicVertices.get(etiqueta);
    }

    public E obtenerEtiqueta(Integer codigo) {
        return etiquetas[codigo - 1];
    }

    public void etiquetarVertice(Integer codigo, E etiqueta) throws Exception {
        if (Utilidades.arrayIncludes(getEtiquetas(), etiqueta)) {
            throw new Exception("Ya existe la etiqueta " + etiqueta.toString());
        }

        etiquetas[codigo] = etiqueta;
        dicVertices.put(etiqueta, codigo);
    }

    @Override
    public String toString() {
        StringBuffer grafo = new StringBuffer("");
        try {
            for (int i = 1; i <= numVertices(); i++) {
                grafo.append("Vertice " + obtenerEtiqueta(i).toString());
                ListaEnlazada<Adyacencia> lista = adycentes(i);
                for (int j = 0; j < lista.getSize(); j++) {
                    Adyacencia a = lista.obtener(j);

                    if (a.getPeso().toString().equalsIgnoreCase(String.valueOf(Double.NaN))) {
                        grafo.append("-- Vertice destino " + obtenerEtiqueta(a.getDestino()).toString() + "  -- SP");
                    } else {
                        grafo.append("-- Vertice destino " + obtenerEtiqueta(a.getDestino()).toString() + "  -- Peso " + a.getPeso());
                    }
                }
                grafo.append("\n");
            }
        } catch (Exception e) {
            grafo.append(e.getMessage());
        }
        return grafo.toString(); //To change body of generated methods, choose Tools | Templates.
    }

}
