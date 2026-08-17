package gestionInventario;

import java.util.List;

public class Tienda {
    // Atributos de la tienda: inventario (arbol), cola de clientes, y ahora tambien
    // la ubicacion de la tienda y el mapa de entregas (grafo ponderado no dirigido).
    private ArbolProductos inventario;
    private ColaClientes colaClientes;
    private String ubicacion;        // ubicacion de la tienda (vertice del Grafo, destino de las entregas)
    private Grafo mapaEntregas;      // grafo de ubicaciones para calcular rutas de entrega

    // Constructor que inicializa las estructuras
    public Tienda() {
        this.inventario = new ArbolProductos();
        this.colaClientes = new ColaClientes();
        this.mapaEntregas = new Grafo();
        this.ubicacion = "SanPedro"; // la tienda esta ubicada en este vertice

        cargarMapaPredefinido();
    }

    // Getters para que puedas usarlas desde el Main
    public ArbolProductos getInventario() {
        return inventario;
    }

    public ColaClientes getColaClientes() {
        return colaClientes;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Grafo getMapaEntregas() {
        return mapaEntregas;
    }

    // Procesa la entrega del cliente: calcula e imprime el camino mas corto (Dijkstra)
    // entre la ubicacion del cliente y la de la tienda, con su distancia total.
    // Se llama al ATENDER, justo despues de imprimir la factura del carrito.
    public void procesarEntrega(Cliente cliente) {
        System.out.println("\n--- RUTA DE ENTREGA (algoritmo de Dijkstra) ---");

        String origen = cliente.getUbicacion();
        String destino = this.ubicacion;

        // Si el cliente no tiene ubicacion asignada, no se puede calcular la ruta
        if (origen == null) {
            System.out.println("El cliente no tiene una ubicacion asignada, no se puede calcular la ruta.");
            return;
        }

        // Pedimos al grafo el camino mas corto y la distancia (Dijkstra ya esta implementado en Grafo)
        List<String> camino = mapaEntregas.caminoMasCorto(origen, destino);
        int distancia = mapaEntregas.distanciaMasCorta(origen, destino);

        // Si no hay camino (lista vacia) o la distancia es "infinita", estan desconectados
        if (camino.isEmpty() || distancia == Integer.MAX_VALUE) {
            System.out.println("No hay una ruta disponible entre " + origen + " y la tienda (" + destino + ").");
            System.out.println("La ubicacion del cliente esta desconectada del mapa de entregas.");
            return;
        }

        // Mostramos el camino como  Origen -> ... -> Tienda  y la distancia total
        System.out.println("Ubicacion del cliente: " + origen);
        System.out.println("Ubicacion de la tienda: " + destino);
        System.out.println("Camino mas corto: " + String.join(" -> ", camino));
        System.out.println("Distancia total del recorrido: " + distancia + " km");
    }

    //Este va a ser un mapa predefinido con ciertas rutas y con distancias para poder hacer pruebas con datos ya establecidos y no tener que ingresar todo desde 0
    // Un mapa basico de ubicaciones con distancias, para probar las rutas de entrega.
    private void cargarMapaPredefinido() {
        mapaEntregas.agregarArista("SanPedro", "SanJose", 4);
        mapaEntregas.agregarArista("SanJose", "Heredia", 8);
        mapaEntregas.agregarArista("SanJose", "Escazu", 9);
        mapaEntregas.agregarArista("SanJose", "Cartago", 20);
        mapaEntregas.agregarArista("Heredia", "Alajuela", 12);
        mapaEntregas.agregarArista("Alajuela", "Escazu", 15);
        mapaEntregas.agregarArista("Cartago", "TresRios", 6);
        mapaEntregas.agregarArista("TresRios", "SanPedro", 10);
    }
}
