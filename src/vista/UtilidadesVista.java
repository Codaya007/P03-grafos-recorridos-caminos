/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.grafo.GrafoDirigidoEtiquetado;
import controlador.listas.ListaEnlazada;
import controlador.grafo.Grafo;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


public class UtilidadesVista {

    private static Integer inicial;

    public static void cargarComboNumeros(JComboBox cbx, Integer max) throws Exception {
        cbx.removeAllItems();

        for (int i = 0; i < max; i++) {
            cbx.addItem(i + 1);
        }
    }

    public static void cargarComboGrafos(JComboBox cbx, Grafo grafo[]) throws Exception {
        cbx.removeAllItems();

        if (grafo != null) {
            for (int i = 0; i < grafo.length; i++) {
                cbx.addItem(i + 1 + " - " + grafo[i].getNombre());
            }
        }

    }

    public static void cargarComboAlgoritmos(JComboBox cbx) throws Exception {
        cbx.removeAllItems();

        cbx.addItem("Algoritmo Dijkstra");
        cbx.addItem("Algoritmo Floyd");
    }

    public static void cargarComboRecorridos(JComboBox cbx) throws Exception {
        cbx.removeAllItems();

        cbx.addItem("Recorrido en anchura");
        cbx.addItem("Recorrido en profundidad");
    }

    public static void resizeColumnWidth(JTable table) {
        //Se obtiene el modelo de la columna
        TableColumnModel columnModel = table.getColumnModel();
        //Se obtiene el total de las columnas
        for (int column = 0; column < table.getColumnCount(); column++) {
            //Establecemos un valor minimo para el ancho de la columna
            int width = 150; //Min Width
            //Obtenemos el numero de filas de la tabla
            for (int row = 0; row < table.getRowCount(); row++) {
                //Obtenemos el renderizador de la tabla
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                //Creamos un objeto para preparar el renderer
                Component comp = table.prepareRenderer(renderer, row, column);
                //Establecemos el width segun el valor maximo del ancho de la columna
                width = Math.max(comp.getPreferredSize().width + 1, width);

            }
            //Se establece una condicion para no sobrepasar el valor de 300
            //Esto es Opcional
            if (width > 300) {
                width = 300;
            }
            //Se establece el ancho de la columna
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

}
