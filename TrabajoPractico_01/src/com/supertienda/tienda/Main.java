package com.supertienda.tienda;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.supertienda.producto.ProductoEnvasado;
import com.supertienda.producto.ProductoLimpieza;
import com.supertienda.producto.ProductoLimpieza.TipoAplicacion;
import com.supertienda.producto.Producto;
import com.supertienda.producto.ProductoBebida;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String,Producto> productos = new HashMap<>(); 
		Map<String,Integer> ventaProductos = new HashMap<>();
		
		// Crear instancia de la tienda
        Tienda miTienda = new Tienda("Mi Tienda", 500, 10000.0);
       
    		LocalDate fechaVencimiento = LocalDate.of(2023, 8, 31);
        ProductoEnvasado producto1 = new ProductoEnvasado("AB112", "leche", 100, 1.0f, 1.0f, true,0f, "Plástico", false, fechaVencimiento, 100);
        ProductoLimpieza limpieza = new ProductoLimpieza("AC112", "Bebida 1", 50, 3.0f, 1.5f, true, 0f, TipoAplicacion.COCINA);

        // Realizar compra de productos
        miTienda.comprarProducto(producto1);
        miTienda.comprarProducto(limpieza);
        
        //agrego los descuetnos
        producto1.setPorcentajeDescuento(21);
        limpieza.setPorcentajeDescuento(24);
        
        //agrego al diccionario
        ventaProductos.put(producto1.getIdentificacion(),4);
        ventaProductos.put(limpieza.getIdentificacion(), 10);
        miTienda.venderProductos(ventaProductos);

        //funcion 1 adicional
        //miTienda.obtenerComestiblesConMenorDescuento(25);
		
	}

}
