/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.grafo;


public class Adyacencia {

    private Integer origen;
    private Integer destino;
    private Double peso;
    //1 ---   2 ---- 3

    public Adyacencia(Integer destino, Double peso, Integer origen) {
        this.destino = destino;
        this.peso = peso;
        this.origen = origen;
    }

    public Integer getDestino() {
        return destino;
    }

    public void setDestino(Integer destino) {
        this.destino = destino;
    }

    public Integer getOrigen() {
        return origen;
    }

    public void setOrigen(Integer origen) {
        this.origen = origen;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

//    @Override
//    public String toString() {
//        return "Destino => " + destino + " Peso => " + peso;
//    }
    @Override
    public String toString() {
        return "Desde " + origen + " a " + destino + " (Peso: " + peso + ")";
    }

}
