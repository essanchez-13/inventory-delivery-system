package gestionInventario;

import java.util.*;

//Grafo ponderado y no dirigido de ubicaciones (el mapa de entregas de la Tienda).
//Guarda las conexiones en una lista de adyacencia: cada ubicacion apunta a sus aristas.
public class Grafo {

    private final Map<String, List<Arista>> listaAdyacencia;

    public Grafo() {
        listaAdyacencia = new HashMap<>();
    }

    public void agregarVertice(String nuevaUbicacion) {
        listaAdyacencia.putIfAbsent(nuevaUbicacion, new ArrayList<>());
    }

    //Como es no dirigido, la arista se guarda en los dos sentidos con el mismo peso
    public void agregarArista(String origen, String destino, int peso) {
        agregarVertice(origen);
        agregarVertice(destino);
        listaAdyacencia.get(origen).add(new Arista(destino, peso));
        listaAdyacencia.get(destino).add(new Arista(origen, peso));
    }

    public void mostrarGrafo() {
        for (Map.Entry<String, List<Arista>> entry : listaAdyacencia.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            for (Arista arista : entry.getValue()) {
                System.out.print("(" + arista.getDestino() + ", " + arista.getPeso() + ") ");
            }
            System.out.println();
        }
    }

    //Dijkstra: recorre desde "inicio" y llena distancias y predecesores con el mejor camino a cada ubicacion
    public void algoritmoDijkstra(String inicio,
                                  Map<String, Integer> distancias,
                                  Map<String, String> predecesores) {
        PriorityQueue<Vertice> colaVertices = new PriorityQueue<>(Comparator.comparingInt(Vertice::getDistancia));

        for (String vertice : listaAdyacencia.keySet()) {
            distancias.put(vertice, Integer.MAX_VALUE);
            predecesores.put(vertice, null);
        }
        distancias.put(inicio, 0);
        colaVertices.add(new Vertice(inicio, 0));

        while (!colaVertices.isEmpty()) {
            Vertice v = colaVertices.poll();
            String verticeActual = v.getNombre();
            for (Arista arista : listaAdyacencia.get(verticeActual)) {
                String vecino = arista.getDestino();
                int nuevaDistancia = distancias.get(verticeActual) + arista.getPeso();
                if (nuevaDistancia < distancias.get(vecino)) {
                    distancias.put(vecino, nuevaDistancia);
                    predecesores.put(vecino, verticeActual);
                    colaVertices.add(new Vertice(vecino, nuevaDistancia));
                }
            }
        }
    }

    //Rearma el camino de inicio a destino siguiendo los predecesores. Devuelve vacio si no hay conexion.
    public List<String> reconstruirCamino(String inicio, String destino, Map<String, String> predecesores) {
        List<String> camino = new ArrayList<>();
        for (String actual = destino; actual != null; actual = predecesores.get(actual)) {
            camino.add(actual);
        }
        Collections.reverse(camino);
        if (!camino.isEmpty() && camino.get(0).equals(inicio)) {
            return camino;
        }
        return new ArrayList<>();
    }

    //Camino mas corto entre dos ubicaciones como lista de vertices. Vacio si estan desconectadas.
    public List<String> caminoMasCorto(String origen, String destino) {
        if (!listaAdyacencia.containsKey(origen) || !listaAdyacencia.containsKey(destino)) {
            return new ArrayList<>();
        }
        Map<String, Integer> distancias = new HashMap<>();
        Map<String, String> predecesores = new HashMap<>();
        algoritmoDijkstra(origen, distancias, predecesores);
        return reconstruirCamino(origen, destino, predecesores);
    }

    //Distancia total del camino mas corto. Integer.MAX_VALUE si estan desconectadas.
    public int distanciaMasCorta(String origen, String destino) {
        if (!listaAdyacencia.containsKey(origen) || !listaAdyacencia.containsKey(destino)) {
            return Integer.MAX_VALUE;
        }
        Map<String, Integer> distancias = new HashMap<>();
        Map<String, String> predecesores = new HashMap<>();
        algoritmoDijkstra(origen, distancias, predecesores);
        return distancias.get(destino);
    }
}
