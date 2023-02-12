package controlador.listas.excepciones;

public class PosicionNoEncontradaException extends Exception {
    public PosicionNoEncontradaException(String msg) {
        super(msg);
    }
    public PosicionNoEncontradaException() {
        super("La posicion dada esta fuera de los limites de la lista");
    }
}
