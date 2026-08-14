package gestionInventario;

//Arista de la lista de adyacencia: a que ubicacion llega y la distancia (peso) hasta ella
public class Arista {

    private final String destino;
    private final int peso;

    public Arista(String destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }

    public String getDestino() {
        return destino;
    }

    public int getPeso() {
        return peso;
    }
}
