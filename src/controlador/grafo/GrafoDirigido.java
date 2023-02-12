/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.grafo;

import controlador.grafo.exception.VerticeOfSizeException;
import controlador.listas.ListaEnlazada;
import controlador.listas.NodoLista;

public class GrafoDirigido extends Grafo {
    
    private Integer numVertices;
    private Integer numAristas;
    private ListaEnlazada<Adyacencia> listaAdycente[];
    
    public GrafoDirigido(Integer numVertices) {
        this.numVertices = numVertices;
        numAristas = 0;
        listaAdycente = new ListaEnlazada[numVertices + 1];
        for (int i = 1; i <= this.numVertices; i++) {
            listaAdycente[i] = new ListaEnlazada<>();
        }
    }
    
    public ListaEnlazada recorridoAnchura(Integer origen) throws Exception {
        ListaEnlazada<Integer> recorrido = new ListaEnlazada<>();
        
        recorrido.insertar(origen);
        ListaEnlazada<Adyacencia> currentAdyacencias = this.adycentes(origen);
        recorrido = concatenarListas(recorrido, currentAdyacencias);
        
        while (currentAdyacencias.getSize() > 0) {
            ListaEnlazada<Adyacencia> currentAdyacenciasNew = new ListaEnlazada<>();
            
            for (int i = 0; i < currentAdyacencias.getSize(); i++) {
                //  FILTRO LAS QUE YA ESTÁN EN EL RECORRIDO
                ListaEnlazada<Adyacencia> adyacenciasNoAnadidas = obtenerAdyacentesNoVisitadas(recorrido, this.adycentes(currentAdyacencias.obtener(i).getDestino()));
                // FILTRO LAS QUE YA ESTÉN EN LA LISTA DE ADYACENCIAS A EXAMINARSE
                currentAdyacenciasNew = concatenarListasAdyacencias(currentAdyacenciasNew, adyacenciasNoAnadidas);
            }
            currentAdyacenciasNew = obtenerAdyacentesNoVisitadas(recorrido, currentAdyacenciasNew);
            recorrido = concatenarListas(recorrido, currentAdyacenciasNew);
            currentAdyacencias = currentAdyacenciasNew;
        }
        
        return recorrido;
    }
    
    public ListaEnlazada recorridoProfundidad(Integer origen) throws Exception {
        ListaEnlazada<Integer> recorrido = new ListaEnlazada<>();
        
        recorrido.insertar(origen);
        ListaEnlazada<Adyacencia> pila = this.adycentes(origen).copiar(); // BC

        while (pila.getSize() > 0) {
            // Obtengo el último elemento
            Adyacencia current = pila.eliminarFinal();         // C
            recorrido.insertar(current.getDestino());                       // DC
            ListaEnlazada<Adyacencia> adyacenciasNoAnadidas = obtenerAdyacentesNoVisitadas(recorrido, this.adycentes(current.getDestino())); // R
            pila = concatenarListasAdyacencias(pila, adyacenciasNoAnadidas);    // B R
            pila.imprimir();
        }
        
        return recorrido;
    }
    
    public ListaEnlazada<Integer> concatenarListas(ListaEnlazada<Integer> listaA, ListaEnlazada<Adyacencia> listaB) {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        
        NodoLista aux = listaA.getCabecera();
        
        while (aux != null) {
            lista.insertar((Integer) aux.getDato());
            aux = aux.getSiguiente();
        }
        
        aux = listaB.getCabecera();
        
        while (aux != null) {
            Adyacencia temp = (Adyacencia) aux.getDato();
            lista.insertar(temp.getDestino());
            aux = aux.getSiguiente();
        }
        
        return lista;
    }
    
    public ListaEnlazada<Adyacencia> concatenarListasAdyacencias(ListaEnlazada<Adyacencia> listaA, ListaEnlazada<Adyacencia> listaB) throws Exception {
        ListaEnlazada<Adyacencia> lista = new ListaEnlazada<>();
        
        NodoLista aux = listaA.getCabecera();
        
        while (aux != null) {
            Adyacencia temp = (Adyacencia) aux.getDato();
            lista.insertar(temp);
            aux = aux.getSiguiente();
        }
        
        aux = listaB.getCabecera();
        
        while (aux != null) {
            Adyacencia temp = (Adyacencia) aux.getDato();
            if (!destinoRepetido(lista, temp)) {
                lista.insertar(temp);
            }
            aux = aux.getSiguiente();
            
        }
        
        return lista;
    }
    
    public boolean destinoRepetido(ListaEnlazada<Adyacencia> adyacencias, Adyacencia adyacencia) throws Exception {
        for (int i = 0; i < adyacencias.getSize(); i++) {
            Adyacencia adyacenciaI = adyacencias.obtener(i);
            if (adyacenciaI.getDestino() == adyacencia.getDestino()) {
                return true;
            }
        }
        
        return false;
    }
    
    public ListaEnlazada<Adyacencia> obtenerAdyacentesNoVisitadas(ListaEnlazada<Integer> recorrido, ListaEnlazada<Adyacencia> currentAdyacencias) throws Exception {
        ListaEnlazada<Adyacencia> result = new ListaEnlazada<>();
        
        for (int i = 0; i < currentAdyacencias.getSize(); i++) {
            if (!visitado(recorrido, currentAdyacencias.obtener(i).getDestino())) {
                result.insertar(currentAdyacencias.obtener(i));
            }
        }
        
        return result;
    }
    
    public Boolean visitado(ListaEnlazada<Integer> recorrido, Integer valor) throws Exception {
        for (int i = 0; i < recorrido.getSize(); i++) {
            if (valor.intValue() == recorrido.obtener(i)) {
                return true;
            }
        }
        
        return false;
    }
    
    public ListaEnlazada caminoMasCortoDijkstra(Integer origen, Integer destino) throws Exception {
        ListaEnlazada<Integer> camino = new ListaEnlazada<>();
        Double pesoTotal = 0.0;
        camino.insertar(origen);
        Boolean llegoAFinal = false;
        
        ListaEnlazada<Adyacencia> adyacentes = this.adycentes(origen);
        
        if (adyacentes != null) {
            while (adyacentes.getSize() > 0) {
                ListaEnlazada<DijkstraVariante> variablesItems = new ListaEnlazada<>();
                for (int i = 0; i < adyacentes.getSize(); i++) {
                    Adyacencia current = adyacentes.obtener(i);
                    DijkstraVariante nuevaVariante = new DijkstraVariante();
                    
                    nuevaVariante.setPesoTotal(pesoTotal + current.getPeso());
                    nuevaVariante.setHasta(current);
                    
                    variablesItems.insertar(nuevaVariante);
                }
                DijkstraVariante menor = DijkstraVariante.getVarianteMenorPeso(variablesItems);
                camino.insertar(menor.getHasta().getDestino());
                pesoTotal += menor.getPesoTotal();
                if (menor.getHasta().getDestino() == destino) {
                    llegoAFinal = true;
                    break;
                }
                adyacentes = obtenerAdyacentesNoVisitadas(camino, this.adycentes(menor.getHasta().getDestino()));
            }
        }
        
        setPesoCaminoMasCorto(pesoTotal);
        
        if (!llegoAFinal) {
            throw new Exception("No se ha encontrado un camino de" + origen + " a " + destino);
        }
        
        return camino;
    }
    
    public ListaEnlazada caminoMasCortoFloyd(Integer origen, Integer destino) throws Exception {
        ListaEnlazada<Integer> camino = new ListaEnlazada<>();
        camino.insertar(origen);
        Double[][] ponderaciones = getMatrizPonderaciones();
        Integer[][] recorridos = getMatrizRecorrido();
        
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                for (int k = 0; k < numVertices; k++) {
                    Double sumatoria = ponderaciones[j][i] + ponderaciones[i][k];
                    // System.out.println("Sumatoria [" + j + ", " + i + " ] + [" + i + ", " + k + "] = " + sumatoria);
                    // System.out.println(ponderaciones[j][i] + " + " + ponderaciones[i][k] + " = " + sumatoria);
                    if (sumatoria < ponderaciones[j][k]) {
                        ponderaciones[j][k] = sumatoria;
                        recorridos[j][k] = i + 1;
                    }
                }
            }
        }
        
        Integer origenAux = origen;
        
        setPesoCaminoMasCorto(ponderaciones[origen - 1][destino - 1]);
        while (true) {
//            Double peso = ponderaciones[origenAux - 1][destino - 1];
            Double peso = ponderaciones[origenAux - 1][destino - 1];
            
            if (peso == Double.POSITIVE_INFINITY) {
                throw new Exception("No hay un camino entre " + origen + " y " + destino);
            }
            
            Integer siguientePaso = recorridos[origenAux - 1][destino - 1];
            camino.insertar(siguientePaso);
            System.out.println("Siguiente paso: " + siguientePaso);
            
            if (siguientePaso == destino) {
                break;
            }
            origenAux = siguientePaso;
        }
        
        imprimirMatriz(ponderaciones, "PONDERACIONES SUMADAS");
        imprimirMatriz(recorridos, "RECORRIDOS FINALES");
        return camino;
    }
    
    private void imprimirMatriz(Object[][] matriz, String title) {
        System.out.println(title);
        
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                System.out.print(matriz[i][j] + "\t\t");
            }
            System.out.println();
        }
    }
    
    private Double[][] getMatrizPonderaciones() throws Exception {
        Double ponderaciones[][] = new Double[numVertices][numVertices];

        // Llenamos la diagonal principal de ceros y lo demás de infinitos
        for (int i = 0; i < ponderaciones.length; i++) {
            for (int j = 0; j < ponderaciones.length; j++) {
                if (i == j) {
                    ponderaciones[i][j] = 0.0;
                } else {
                    ponderaciones[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }
        
        for (int i = 1; i < ponderaciones.length + 1; i++) {
            ListaEnlazada<Adyacencia> adyacenciasFila = this.adycentes(i);
            
            for (int j = 0; j < adyacenciasFila.getSize(); j++) {
                Adyacencia current = adyacenciasFila.obtener(j);
                ponderaciones[i - 1][current.getDestino() - 1] = current.getPeso();
            }
        }
        
        return ponderaciones;
    }
    
    private Integer[][] getMatrizRecorrido() {
        Integer recorridos[][] = new Integer[numVertices][numVertices];
        
        for (int i = 0; i < recorridos.length; i++) {
            for (int j = 0; j < recorridos.length; j++) {
                recorridos[i][j] = j + 1;
            }
        }
        
        return recorridos;
    }
    
    @Override
    public Integer numVertices() {
        return numVertices;
    }
    
    @Override
    public Integer numAristas() {
        return numAristas;
    }
    
    @Override
    public Boolean existeArista(Integer o, Integer d) throws Exception {
        Boolean existe = false;
        if (o.intValue() <= numVertices && d.intValue() <= numVertices) {
            ListaEnlazada<Adyacencia> lista = listaAdycente[o];
            for (int i = 0; i < lista.getSize(); i++) {
                Adyacencia a = lista.obtener(i);
                if (a.getDestino().intValue() == d.intValue()) {
                    existe = true;
                    break;
                }
            }
        } else {
            //TODO Exception VerticeOfSize}    
            throw new VerticeOfSizeException();
        }
        return existe;
    }
    
    @Override
    public Double pesoArista(Integer o, Integer d) {
        Double peso = Double.NaN;
        try {
            if (existeArista(o, d)) {
                ListaEnlazada<Adyacencia> adycentes = listaAdycente[o];
                for (int i = 0; i < adycentes.getSize(); i++) {
                    Adyacencia a = adycentes.obtener(i);
                    if (a.getDestino().intValue() == d.intValue()) {
                        peso = a.getPeso();
                        break;
                    }
                }
            }
        } catch (Exception e) {
        }
        return peso;
    }
    
    @Override
    public void insertarArista(Integer o, Integer d, Double peso) throws Exception {
        
        if (o.intValue() <= numVertices && d.intValue() <= numVertices) {
            if (!existeArista(o, d)) {
                numAristas++;
                listaAdycente[o].insertar(new Adyacencia(d, peso, o.intValue()));
            }
        } else {
            throw new VerticeOfSizeException();
        }
        
    }
    
    @Override
    public void insertarArista(Integer o, Integer d) throws Exception {
        insertarArista(o, d, Double.NaN);
    }
    
    @Override
    public ListaEnlazada<Adyacencia> adycentes(Integer v) {
        return listaAdycente[v];
    }
    
    public Integer getNumVertices() {
        return numVertices;
    }
    
    public Integer getNumAristas() {
        return numAristas;
    }
    
    public void setNumAristas(Integer numAristas) {
        this.numAristas = numAristas;
    }
    
    public ListaEnlazada<Adyacencia>[] getListaAdycente() {
        return listaAdycente;
    }
    
}
