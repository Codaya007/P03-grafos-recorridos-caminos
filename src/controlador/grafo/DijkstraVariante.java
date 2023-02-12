/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.grafo;

import controlador.listas.ListaEnlazada;

public class DijkstraVariante {

    private Double pesoTotal;
    private Adyacencia hasta;

    public Double getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(Double pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public Adyacencia getHasta() {
        return hasta;
    }

    public void setHasta(Adyacencia hasta) {
        this.hasta = hasta;
    }

    static public DijkstraVariante getVarianteMenorPeso(ListaEnlazada<DijkstraVariante> lista) throws Exception {
        // Por defecto pongo el primero
        DijkstraVariante menor = lista.obtener(0);

        for (int i = 1; i < lista.getSize(); i++) {
            DijkstraVariante current = lista.obtener(i);

            if (current.getPesoTotal() < menor.getPesoTotal()) {
                menor = current;
            }
        }

        return menor;
    }
}
