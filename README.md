# Sistema de Gestión de Inventarios y Entregas

## Descripción

Aplicación desarrollada en Java para la gestión integral de inventarios, carritos de compras, atención de clientes por prioridad y cálculo de rutas de entrega para una tienda en línea.

Este proyecto corresponde a la **Proyecto de Estructuras de Datos (SOFT-10) - Gestión de Inventario** de la Universidad CENFOTEC.

La aplicación evoluciona desde una lista enlazada simple hasta la implementación de **múltiples estructuras de datos complejas**, logrando un sistema robusto que administra productos, simula la atención de clientes mediante prioridades y calcula las rutas de entrega más eficientes utilizando teoría de grafos.

---

## Objetivos del Proyecto

- Implementar e integrar múltiples estructuras de datos dinámicas complejas (Listas, Árboles, Colas, Grafos).
- Optimizar la búsqueda y gestión de inventarios utilizando un **Árbol Binario de Búsqueda (ABB)**.
- Gestionar el orden de atención de usuarios mediante una **Cola de Prioridad**.
- Calcular rutas de entrega óptimas implementando el **Algoritmo de Dijkstra** en un **Grafo Ponderado no Dirigido**.
- Desarrollar un menú interactivo mediante consola (CLI) amigable e intuitivo.
- Aplicar buenas prácticas de programación orientada a objetos (POO).

---

## Funcionalidades Implementadas

### 1. Gestión del Inventario (Administrador)
Administrado a través de un **Árbol Binario de Búsqueda (ABB)** para mayor eficiencia.
- Insertar, buscar, modificar y eliminar productos.
- Agregar rutas de imágenes a los productos.
- Mostrar catálogo completo ordenado alfabéticamente.

### 2. Gestión de Clientes y Carrito de Compras
El carrito de cada cliente funciona mediante una **Lista Enlazada Simple**.
- Registro de clientes con diferentes niveles de prioridad (Básico, Afiliado, Premium) y ubicación geográfica.
- Añadir, modificar cantidades y eliminar productos del carrito (vinculado dinámicamente al stock del inventario principal).
- Reporte detallado de costos y subtotales por cliente.

### 3. Cola de Atención (Cajas)
Implementada mediante una **Cola de Prioridad**.
- Encolado automático de clientes al finalizar sus compras.
- Desencolado y facturación respetando el nivel de prioridad de cada cliente (y el orden de llegada en caso de empate).
- Visualización de los clientes en espera.

### 4. Mapa de Entregas (Grafos)
El mapa de la ciudad está estructurado en un **Grafo Ponderado No Dirigido**.
- Creación automática de vértices según la ubicación del cliente.
- Menú de gestión para agregar nuevas ubicaciones aisladas y rutas (aristas) con distancias (pesos).
- Validación de conectividad: el sistema bloquea la facturación si la ubicación del cliente no está conectada al mapa.
- Cálculo e impresión de la ruta de entrega más corta utilizando el **Algoritmo de Dijkstra**.

---

## Estructura del Proyecto

```text
src/
│
├── Main.java
│
├── gestionInventario/
│   ├── ArbolProductos.java    (Inventario de la Tienda)
│   ├── Arista.java            (Conexiones del mapa)
│   ├── Cliente.java           (Entidad Cliente con su carrito)
│   ├── ColaClientes.java      (Cola de prioridad para atención)
│   ├── Grafo.java             (Mapa de entregas y Dijkstra)
│   ├── ListaProductos.java    (Carrito de compras)
│   ├── Producto.java          (Entidad Producto)
│   ├── Tienda.java            (Estructura central que unifica el sistema)
│   └── Vertice.java           (Ubicaciones del mapa)
