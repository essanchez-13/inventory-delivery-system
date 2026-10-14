# Inventory & Delivery Management System

A Java console application for an online store. It manages the product inventory, customer shopping carts, priority-based checkout and shortest-path delivery routing. The core structures (linked list, binary search tree and priority queue) are implemented from scratch with hand-built nodes.

Team project for *Data Structures (SOFT-10)* at Universidad CENFOTEC, 2026.

**Stack:** Java · OOP · custom linked list, binary search tree, priority queue and weighted graph · Dijkstra's algorithm

## Features

| Module | Data structure | What it does |
|---|---|---|
| Inventory (admin) | **Binary search tree** (`ArbolProductos`) | Insert, search, update and delete products in O(log n) on average; in-order traversal lists the catalog alphabetically |
| Shopping cart | **Singly linked list** (`ListaProductos`) | Add, update quantities and remove items; stock stays in sync with the inventory |
| Checkout | **Priority queue** (`ColaClientes`) | Serves Premium, then Affiliate, then Basic customers, using FIFO order to break ties |
| Delivery map | **Weighted undirected graph** (`Grafo`) | Add locations and routes, block checkout when a customer's location is unreachable, and print the shortest delivery route with **Dijkstra's algorithm** |

## Structure

```text
src/
├── Main.java                 interactive CLI menu (admin / customer)
└── gestionInventario/
    ├── Tienda.java           facade tying all modules together
    ├── Producto.java         product entity / BST node
    ├── ArbolProductos.java   inventory BST
    ├── ListaProductos.java   shopping cart linked list
    ├── Cliente.java          customer with cart, priority and location
    ├── ColaClientes.java     checkout priority queue
    ├── Grafo.java            delivery map + Dijkstra
    ├── Vertice.java          map location
    └── Arista.java           weighted route
```

## Running

Open the project in IntelliJ IDEA and run `Main`, or compile from the command line:

```bash
javac -d out $(find src -name "*.java")
java -cp out Main
```

## Team and contributions

- **Esteban Sánchez**: the `Producto` model, the original linked list (insertion and search, inserting at a chosen position), and the **binary search tree inventory** that replaced the list.
- **Azarela Fernández**: list operations and the admin/customer menus, the customer priority queue, and customer locations with Dijkstra routing at checkout.
- **Andrés Meléndez**: menu integration, documentation and the final merge.

Mirrored from the original team repository, [essanchezs/GestionInventario](https://github.com/essanchezs/GestionInventario), with the full commit history preserved.
