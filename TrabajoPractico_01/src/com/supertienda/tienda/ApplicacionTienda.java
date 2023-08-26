package com.supertienda.tienda;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import com.supertienda.producto.ProductoEnvasado;
import com.supertienda.producto.ProductoLimpieza;
import com.supertienda.producto.ProductoLimpieza.TipoAplicacion;
import com.supertienda.producto.ProductoBebida;

public class ApplicacionTienda {

	public static void main(String[] args) {
		Map<String,Integer> ventaProductos1 = new HashMap<String, Integer>();
		Map<String,Integer> ventaProductos2 = new HashMap<String, Integer>();
		Map<String,Integer> ventaProductos3 = new HashMap<String, Integer>();
		
		// Crear instancia de la tienda
        Tienda miTienda = new Tienda("Mi Tienda", 50000, 2000000.0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        //Creamos productos envasados
        ProductoEnvasado envasado1 = new ProductoEnvasado("AB112", "leche", 100, 380.0f, 150.0f, true,10.0f, "Plástico", false, LocalDate.parse("2024-01-11", formatter), 100);
        ProductoEnvasado envasado2 = new ProductoEnvasado("AB123", "tomates enlatados", 35, 230.0f, 140.5f, true,21.0f, "Vidrio", true, LocalDate.parse("2023-09-11", formatter), 120);
        ProductoEnvasado envasado3 = new ProductoEnvasado("AB134", "sálmon enlatado", 200, 500.76f, 420.5f, true,5.0f, "Enlatado", false, LocalDate.parse("2023-10-11", formatter), 210);
        ProductoEnvasado envasado4 = new ProductoEnvasado("AB145", "pate", 60, 200.5f, 115.4f, true,30.0f, "Enlatado", false, LocalDate.parse("2023-12-11", formatter), 80);
        ProductoEnvasado envasado5 = new ProductoEnvasado("AB156", "granos de choclo", 45, 250.0f, 145.6f, true,21.0f, "Enlatado", false, LocalDate.parse("2024-01-01", formatter), 100);
        
        //Creamos productos de limpieza 
        ProductoLimpieza limpieza1 = new ProductoLimpieza("AZ212", "desengrasante", 90, 335.5f, 200.25f, true, 0f, TipoAplicacion.MULTIUSO);
        ProductoLimpieza limpieza2 = new ProductoLimpieza("AZ214", "lavandina", 50, 420.5f, 200.5f, true, 10f, TipoAplicacion.PISOS);
        ProductoLimpieza limpieza3 = new ProductoLimpieza("AZ225", "detergente", 100, 380.0f, 300.0f, true, 4f, TipoAplicacion.COCINA);
        ProductoLimpieza limpieza4 = new ProductoLimpieza("AZ256", "pántalon", 150, 300.5f, 180.0f, true, 5f, TipoAplicacion.ROPA);
        ProductoLimpieza limpieza5 = new ProductoLimpieza("AZ269", "varios", 460, 500.5f, 240.0f, true, 26f, TipoAplicacion.MULTIUSO);
        ProductoLimpieza limpieza6 = new ProductoLimpieza("AZ280", "remera", 200, 800.0f, 650.0f, true, 15f, TipoAplicacion.ROPA);
        ProductoLimpieza limpieza7 = new ProductoLimpieza("AZ291", "trapo de piso", 80, 450.5f, 250.0f, true, 0f, TipoAplicacion.PISOS);
        ProductoLimpieza limpieza8 = new ProductoLimpieza("AZ218", "virulana", 50, 300.5f, 180.0f, true, 40f, TipoAplicacion.COCINA);

        //Creamos producto Bebidas
        ProductoBebida bebida1 = new ProductoBebida("AC335", "jugo de manzana", 200, 335.5f, 200.7f, true, 10f, false, 0f, false, LocalDate.parse("2023-10-12"), 120);
        ProductoBebida bebida2 = new ProductoBebida("AC321", "cerveza", 300, 1000.8f, 755.0f, true, 5f, true, 25f, false, LocalDate.parse("2024-04-12"), 220);
        ProductoBebida bebida3 = new ProductoBebida("AC358", "licor", 200, 3300.5f, 2800.7f, true, 6f, true, 50f, true, LocalDate.parse("2025-02-24"), 120);
        ProductoBebida bebida4 = new ProductoBebida("AC362", "gaseosa", 100, 670.0f, 450.5f, true, 20f, false, 0f, true, LocalDate.parse("2024-02-12"), 250);
        ProductoBebida bebida5 = new ProductoBebida("AC390", "seven up", 50, 550.4f, 270.7f, true, 9f, false, 20f, true, LocalDate.parse("2023-12-12"), 300);
        
        
        // Realizar compra de productos
        System.out.println("Realizamos las compras");
        miTienda.comprarProducto(envasado1);
        miTienda.comprarProducto(envasado2);
        miTienda.comprarProducto(envasado3);
        miTienda.comprarProducto(envasado4);
        miTienda.comprarProducto(envasado5);
        
        miTienda.comprarProducto(limpieza1);
        miTienda.comprarProducto(limpieza2);
        miTienda.comprarProducto(limpieza3);
        miTienda.comprarProducto(limpieza4);
        miTienda.comprarProducto(limpieza5);
        miTienda.comprarProducto(limpieza6);
        miTienda.comprarProducto(limpieza7);
        miTienda.comprarProducto(limpieza8);
        
        miTienda.comprarProducto(bebida1);
        miTienda.comprarProducto(bebida2);
        miTienda.comprarProducto(bebida3);
        miTienda.comprarProducto(bebida4);
        miTienda.comprarProducto(bebida5);
        System.out.println("Las compras se realizaron con exito");
        
        System.out.println("---------------------------------------------------------------");
        //vendemos productos
        System.out.println("\nRealizamos las ventas de productos.\n");
        
        System.out.println("Venta n°1 productos que no cumplen la ganancia\n");
        ventaProductos1.put(envasado1.getIdentificacion(), 9);
        ventaProductos1.put(bebida2.getIdentificacion(), 4);
        ventaProductos1.put(limpieza4.getIdentificacion(), 10);
        miTienda.venderProductos(ventaProductos1);
        
        System.out.println("\nVenta n°2 que no cumplen el porcentaje de descuento\n");
        ventaProductos2.put(envasado5.getIdentificacion(), 2);
        ventaProductos2.put(bebida5.getIdentificacion(), 7);
        ventaProductos2.put(limpieza5.getIdentificacion(), 2);
        miTienda.venderProductos(ventaProductos2);
        
        System.out.println("\nVenta n°3 exitosas\n");
        ventaProductos3.put(envasado3.getIdentificacion(), 4);
        ventaProductos3.put(bebida3.getIdentificacion(), 1);
        ventaProductos3.put(limpieza3.getIdentificacion(), 10);
        
       miTienda.venderProductos(ventaProductos3);
        
        System.out.println("---------------------------------------------------------------");
        //operacion obtener comestibles con menor descuento
        System.out.println("\nOperacion obtener comestibles con menor descuento");
        miTienda.obtenerComestiblesConMenorDescuento(15);
        
        //operacion listar productos con utilidades inferiores
        System.out.println("\nOperacion listar productos con utilidades inferiores");
        miTienda.listarProductosConUtilidadesInferiores(50);

	}

}
