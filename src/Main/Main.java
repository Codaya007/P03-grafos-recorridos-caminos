/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import controlador.grafo.GrafoDirigidoEtiquetado;
import controlador.listas.ListaEnlazada;
import java.util.Arrays;
import vista.FrmCrearGrafo;
import vista.FrmGrafo;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            GrafoDirigidoEtiquetado gde = new GrafoDirigidoEtiquetado(7, String.class);

            gde.etiquetarVertice(1, "A");
            gde.etiquetarVertice(2, "B");
            gde.etiquetarVertice(3, "C");
            gde.etiquetarVertice(4, "D");

            gde.insertarAristaE(gde.obtenerEtiqueta(1), gde.obtenerEtiqueta(2), 3.0);
            gde.insertarAristaE(gde.obtenerEtiqueta(2), gde.obtenerEtiqueta(4), 5.0);
            gde.insertarAristaE(gde.obtenerEtiqueta(3), gde.obtenerEtiqueta(4), 3.0);
            gde.insertarAristaE(gde.obtenerEtiqueta(4), gde.obtenerEtiqueta(1), 8.0);
            gde.insertarAristaE(gde.obtenerEtiqueta(1), gde.obtenerEtiqueta(3), 4.0);

            System.out.println("\nLista de adyacencias");
            System.out.println(Arrays.toString(gde.getListaAdycente()));
            
            ListaEnlazada<Integer> recorridoAnchura = gde.recorridoAnchura(1);
            ListaEnlazada<Integer> recorridoProfundidad = gde.recorridoProfundidad(1);

            System.out.println("\nRECORRIDO EN ANCHURA");
            for (int i = 0; i < recorridoAnchura.getSize(); i++) {
                System.out.print(gde.obtenerEtiqueta(recorridoAnchura.obtener(i)) + " ");
            }

            System.out.println("\nRECORRIDO EN PROFUNDIDAD");
            for (int i = 0; i < recorridoProfundidad.getSize(); i++) {
                System.out.print(gde.obtenerEtiqueta(recorridoProfundidad.obtener(i)) + " ");
            }
            System.out.println("\nDIJKSTRA");
            gde.caminoMasCortoDijkstra(1, 4).imprimir();
            System.out.println("\nFLOYD");
            gde.caminoMasCortoFloyd(1, 4).imprimir();

            new FrmCrearGrafo().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
