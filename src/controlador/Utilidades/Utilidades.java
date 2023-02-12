/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.Utilidades;

import java.io.FileWriter;


public class Utilidades {
    public static Double carcularDistancia(Double y, Double y1, Double x, Double x1) {
        Double yy = y - y1;
        Double xx = x - x1;
        return redondear(Math.sqrt((yy*yy)+(xx*xx)));
    }
    
    public static Double redondear(Double dato) {
        return Math.round(dato * 100.0)/100.0;
    }
    
    public static void guardarArchivo(String datos, String ubicacion) throws Exception {
        FileWriter file = new FileWriter(ubicacion);
        file.append(datos);
        file.flush();
        file.close();
    }
    
}
