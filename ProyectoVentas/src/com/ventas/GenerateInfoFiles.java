package com.ventas;

import java.io.*;
import java.util.*;

public class GenerateInfoFiles {

    public static void main(String[] args) {
        try {
            // 1. Generar vendedores
            createSalesManInfoFile(3);   // crea 3 vendedores

            // 2. Generar productos
            createProductsFile(5);       // crea 5 productos

            // 3. Leer los vendedores recién creados
            List<String[]> listaVendedores = leerVendedores("vendedores.txt");

            // 4. Crear un archivo de ventas para cada vendedor
            for (String[] vendedor : listaVendedores) {
                String tipoDoc = vendedor[0];
                String numeroDoc = vendedor[1];
                String nombre = vendedor[2];
                createSalesMenFile(5, nombre, numeroDoc); // 5 ventas aleatorias por vendedor
            }

            System.out.println("✅ Archivos generados correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al generar archivos: " + e.getMessage());
        }
    }

    // ---------------- MÉTODOS ----------------

    // Genera archivo de vendedores
    public static void createSalesManInfoFile(int salesmanCount) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("vendedores.txt"));
        String[] nombres = {"Laura", "Andres", "Maria", "Carlos", "Luisa"};
        String[] apellidos = {"Perez", "Gomez", "Lopez", "Martinez", "Rodriguez"};

        Random rand = new Random();
        for (int i = 0; i < salesmanCount; i++) {
            String tipoDoc = "CC";
            int numeroDoc = 10000 + rand.nextInt(90000);
            String nombre = nombres[rand.nextInt(nombres.length)];
            String apellido = apellidos[rand.nextInt(apellidos.length)];
            writer.write(tipoDoc + ";" + numeroDoc + ";" + nombre + ";" + apellido);
            writer.newLine();
        }
        writer.close();
    }

    // Genera archivo de productos
    public static void createProductsFile(int productsCount) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("productos.txt"));
        String[] nombresProductos = {"Shampoo", "Jabon", "Crema", "Perfume", "Labial"};
        Random rand = new Random();

        for (int i = 0; i < productsCount; i++) {
            String id = "P" + (i + 1);
            String nombre = nombresProductos[rand.nextInt(nombresProductos.length)];
            int precio = 1000 + rand.nextInt(20000);
            writer.write(id + ";" + nombre + ";" + precio);
            writer.newLine();
        }
        writer.close();
    }

    // Genera archivo de ventas de un vendedor
    public static void createSalesMenFile(int randomSalesCount, String name, String id) throws IOException {
        String fileName = "ventas_" + id + ".txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        Random rand = new Random();

        // Primera línea con documento del vendedor
        writer.write("CC;" + id);
        writer.newLine();

        // Ventas aleatorias
        for (int i = 0; i < randomSalesCount; i++) {
            String idProducto = "P" + (1 + rand.nextInt(5)); // IDs de productos P1...P5
            int cantidad = 1 + rand.nextInt(10);
            writer.write(idProducto + ";" + cantidad);
            writer.newLine();
        }
        writer.close();
    }

    // Método para leer vendedores y devolver lista
    public static List<String[]> leerVendedores(String archivo) throws IOException {
        List<String[]> lista = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(";");
            if (partes.length >= 4) {
                lista.add(partes); // partes[0]=tipoDoc, [1]=numDoc, [2]=nombre, [3]=apellido
            }
        }
        br.close();
        return lista;
    }
}


