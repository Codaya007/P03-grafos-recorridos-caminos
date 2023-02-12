/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import modelo.ClassTypeAdapterFactory;
import modelo.ClassTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controlador.grafo.GrafoDirigidoEtiquetado;
import controlador.listas.ListaEnlazada;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author vivic
 */
public class CtrlGrafos {

    private ListaEnlazada<GrafoDirigidoEtiquetado> grafos = new ListaEnlazada<>();
    private static final String DIRDATA = "data";
    final String FULLPATH = DIRDATA + File.separatorChar + "grafos" + ".json";

    public CtrlGrafos() {
        try {
            cargar(this.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ListaEnlazada<GrafoDirigidoEtiquetado> getGrafos() {
        return grafos;
    }

    public void setGrafos(ListaEnlazada<GrafoDirigidoEtiquetado> grafos) {
        this.grafos = grafos;
    }

    public GrafoDirigidoEtiquetado buscarGrafoPosicion(Integer posicion) throws Exception {
        try {
            return grafos.obtener(posicion);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Grafo no encontrado!");
        }
    }

    public void crearGrafo(String nombreGrafo, Integer numVertices) {
        GrafoDirigidoEtiquetado nuevo = new GrafoDirigidoEtiquetado(numVertices, String.class);
        nuevo.setNombre(nombreGrafo);

        grafos.insertar(nuevo);
        guardar(this.getClass());
    }

    public void crearEtiqueta(String nombreEtiqueta, Integer destino, Integer posGrafo) throws Exception {
        GrafoDirigidoEtiquetado gde = grafos.obtener(posGrafo);

        gde.etiquetarVertice(destino, nombreEtiqueta);
        
//        System.out.println("ETIQUETAS");
//        System.out.println(Arrays.toString(gde.getEtiquetas()));
        
        guardar(this.getClass());
    }

    public void crearArista(Integer origen, Integer destino, Integer posGrafo, Double peso) throws Exception {
        GrafoDirigidoEtiquetado gde = grafos.obtener(posGrafo);

        System.out.println(origen + " " + destino + " " + peso + " " + posGrafo);

        gde.insertarArista(origen, destino, peso);
        guardar(this.getClass());
    }

    public void cargar(Class clazz) {
        try {
            System.out.println("Cargando grafos...");

            final GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapterFactory(new ClassTypeAdapterFactory());
            gsonBuilder.registerTypeAdapter(clazz, new ClassTypeAdapter());
            Gson json = gsonBuilder.create();
            FileReader fr = new FileReader(FULLPATH);
            StringBuilder jsons = new StringBuilder();
            int valor = fr.read();

            while (valor != -1) {
                jsons.append((char) valor);
                valor = fr.read();
            }

            GrafoDirigidoEtiquetado[] aux = json.fromJson(jsons.toString(), GrafoDirigidoEtiquetado[].class);
            for (int i = 0; i < aux.length; i++) {
                grafos.insertar(aux[i]);
            }

            System.out.println("SE CARGARON LOS GRAFOS DE " + FULLPATH);
        } catch (Exception e) {
            System.out.println("No se encontraron objetos guardados en el json!");
            System.out.println(e.getMessage());
        }
    }

    public void guardar(Class clazz) {
        try {
            final GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapterFactory(new ClassTypeAdapterFactory());
            gsonBuilder.registerTypeAdapter(clazz, new ClassTypeAdapter());

            GrafoDirigidoEtiquetado[] grafosArray = new GrafoDirigidoEtiquetado[grafos.getSize()];

            for (int i = 0; i < grafos.getSize(); i++) {
                grafosArray[i] = grafos.obtener(i);
            }

            String jsons = gsonBuilder.create().toJson(grafosArray);
            FileWriter fw = new FileWriter(FULLPATH);
            fw.write(jsons);
            fw.flush();

            System.out.println("GRAFOS GUARDADOS EN " + FULLPATH);
        } catch (Exception e) {
            System.out.println("No se pudieron guardar los grafos");
            System.out.println(e.getMessage());
//            e.printStackTrace();
        }
    }
}
