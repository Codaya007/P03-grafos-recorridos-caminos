/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.grafo.exception;


public class VerticeOfSizeException extends Exception {
    public VerticeOfSizeException(String msg) {
        super(msg);
    }

    public VerticeOfSizeException() {
        super("No se puede sobrepasar el numero de vertices");
    }
}
