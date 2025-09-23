package com.ventas;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            // Leer información de productos
            Map<String, Integer> preciosProductos = leerProductos("productos.txt");

            // Leer información de vendedores
            Map<String, String> vendedores = leerVendedores("vendedores.txt");

            // Calcular las ventas totales
            Map<String, Integer> ventasPorVendedor = new HashMap<>();
            Map<String, Integer> ventasPorProducto = new HashMap<>();

            File folder = new File(".");
            File[] files = folder.listFiles((dir, name) -> name.startsWith("ventas_"));

            if (files != null) {
                for (File file : files) {
                    procesarArchivoVentas(file, preciosProductos, ventasPorVendedor, ventasPorProducto);
                }
            }

            // Generar reporte de vendedores
            generarReporteVendedores("reporte_vendedores.txt", vendedores, ventasPorVendedor);

            // Generar reporte de productos
            generarReporteProductos("reporte_productos.txt", ventasPorProducto, preciosProductos);

            System.out.println("✅ Reportes generados correctamente.");

        } catch (Exception e) {
            System.out.println("❌ Error en la ejecución: " + e.getMessage());
        }
    }


    public static Map<String, Integer> leerProductos(String archivo) throws IOException {
        Map<String, Integer> productos = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(";");
            if (partes.length >= 3) {
                String id = partes[0];
                int precio = Integer.parseInt(partes[2]);
                productos.put(id, precio);
            }
        }
        br.close();
        return productos;
    }

    // Lee el archivo de vendedores
    public static Map<String, String> leerVendedores(String archivo) throws IOException {
        Map<String, String> vendedores = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(";");
            if (partes.length >= 4) {
                String documento = partes[1]; // Número de documento
                String nombreCompleto = partes[2] + " " + partes[3];
                vendedores.put(documento, nombreCompleto);
            }
        }
        br.close();
        return vendedores;
    }

    // Procesa cada archivo de ventas
    public static void procesarArchivoVentas(File archivo, Map<String, Integer> preciosProductos,
                                             Map<String, Integer> ventasPorVendedor,
                                             Map<String, Integer> ventasPorProducto) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea = br.readLine(); // primera línea -> documento vendedor
        String[] datosVendedor = linea.split(";");
        String documento = datosVendedor[1]; // usamos el número de documento como clave

        int totalVendedor = ventasPorVendedor.getOrDefault(documento, 0);

        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(";");
            if (partes.length >= 2) {
                String idProducto = partes[0];
                int cantidad = Integer.parseInt(partes[1]);
                int precio = preciosProductos.getOrDefault(idProducto, 0);

                // sumar al vendedor
                totalVendedor += cantidad * precio;

                // sumar al producto
                int totalProducto = ventasPorProducto.getOrDefault(idProducto, 0);
                ventasPorProducto.put(idProducto, totalProducto + cantidad);
            }
        }

        ventasPorVendedor.put(documento, totalVendedor);
        br.close();
    }

    // Genera el reporte de vendedores
    public static void generarReporteVendedores(String archivo, Map<String, String> vendedores,
                                                Map<String, Integer> ventasPorVendedor) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
        // Ordenar por dinero recaudado (descendente)
        ventasPorVendedor.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .forEach(entry -> {
                    try {
                        String documento = entry.getKey();
                        String nombre = vendedores.getOrDefault(documento, "Desconocido");
                        bw.write(nombre + ";" + entry.getValue());
                        bw.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        bw.close();
    }

    // Genera el reporte de productos
    public static void generarReporteProductos(String archivo, Map<String, Integer> ventasPorProducto,
                                               Map<String, Integer> preciosProductos) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
        // Ordenar por cantidad vendida (descendente)
        ventasPorProducto.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .forEach(entry -> {
                    try {
                        String id = entry.getKey();
                        int cantidad = entry.getValue();
                        int precio = preciosProductos.getOrDefault(id, 0);
                        bw.write(id + ";" + cantidad + ";" + precio);
                        bw.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        bw.close();
    }
}


