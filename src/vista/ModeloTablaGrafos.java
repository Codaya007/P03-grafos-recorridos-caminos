/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.grafo.GrafoDirigidoEtiquetado;
import controlador.listas.ListaEnlazada;
import javax.swing.table.AbstractTableModel;


public class ModeloTablaGrafos<E> extends AbstractTableModel {
    ListaEnlazada<E> grafos;

    public ModeloTablaGrafos() {
        this.grafos = new ListaEnlazada<>();
    }
    
    public void setGrafos(ListaEnlazada<E> grafos){
        this.grafos = grafos;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }
    @Override
    public int getRowCount() {
        return grafos.getSize();
    }

    @Override
    public Object getValueAt(int i, int i1) {
         GrafoDirigidoEtiquetado current = null;
         
        try {
            current = (GrafoDirigidoEtiquetado) grafos.obtener(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
         
         switch (i1) {
            case 0:
                return (i + 1);
            case 1:
                return (current != null) ? current.getNombre() : "NO DEFINIDO";
            case 2:
                return (current != null) ? current.numVertices(): "NO DEFINIDO";
            case 3:
                return (current != null) ? current.numAristas(): "NO DEFINIDO";
            case 4:
                return (current != null) ? current.getEtiquetasString(): "NO DEFINIDO";
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
       switch(column){
           case 0: return "ID";
           case 1: return "Nombre";
           case 2: return "N vértices";
           case 3: return "N aristas";
           case 4: return "Etiquetas";
           default: return null;
       }
    }
}
