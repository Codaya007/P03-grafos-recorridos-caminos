/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

//import controlador.grafo.GrafoDirigidoEtiquetado;
//import controlador.listas.ListaEnlazada;
//import java.util.Arrays;
import vista.FrmCrearGrafo;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            new FrmCrearGrafo().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
