package gestionInventario;

//Vertice auxiliar que Dijkstra mete en la cola de prioridad para ordenar por distancia
public class Vertice {

    private final String nombre;
    private final int distancia;

    public Vertice(String nombre, int distancia) {
        this.nombre = nombre;
        this.distancia = distancia;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDistancia() {
        return distancia;
    }
}
