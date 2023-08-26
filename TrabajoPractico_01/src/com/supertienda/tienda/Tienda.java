package com.supertienda.tienda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.supertienda.producto.ProductoBebida;
import com.supertienda.producto.ProductoEnvasado;
import com.supertienda.producto.ProductoLimpieza;
import com.supertienda.producto.Producto;

public class Tienda {
	public enum TipoProducto{
		ENVASADO, BEBIDA, LIMPIEZA
	}
	
	private String nombre;
	private int maxStock;
	private double saldoCaja;
	private Map<String, List<Producto>> productosEnStock; //falta el new hashmap
	
	
	public Tienda(String nombre, int maxStock, double saldoCaja) {
        this.nombre = nombre;
        this.maxStock = maxStock;
        this.saldoCaja = saldoCaja;
        this.productosEnStock = new HashMap<>();
        this.productosEnStock.put("envasados", new ArrayList<>());
        this.productosEnStock.put("bebidas", new ArrayList<>());
        this.productosEnStock.put("limpieza", new ArrayList<>());
    }
	
	
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getMaxStock() {
		return maxStock;
	}


	public void setMaxStock(int maxStock) {
		this.maxStock = maxStock;
	}


	public double getSaldoCaja() {
		return saldoCaja;
	}


	public void setSaldoCaja(double saldoCaja) {
		this.saldoCaja = saldoCaja;
	}


	public Map<String, List<Producto>> getProductosEnStock() {
		return productosEnStock;
	}


	public void setProductosEnStock(Map<String, List<Producto>> productosEnStock) {
		this.productosEnStock = productosEnStock;
	}


	public void comprarProducto(Producto producto) {
        List<Producto> lista = productosEnStock.get(getTipoProducto(producto));
        int totalProductos = calculaTotalElementos(productosEnStock);
        float costoTotal = producto.getCostoPorUnidad() * producto.getStock();
        if (totalProductos < maxStock && saldoCaja >= costoTotal) {
        	String tipoProducto = getTipoProducto(producto);
        	if(lista.contains(producto)) {
        		for (Producto p : lista) {
	                if (p.getIdentificacion().equals(producto.getIdentificacion())) {
	                    p.setStock(p.getStock() + producto.getStock());
	                    System.out.println("Cantidad actualizada para el producto"+ p.getIdentificacion()+" tenia:  "+ p.getStock()+" stock. Ahora tiene: "+p.getStock()+producto.getStock());
	                    break;
	                }
	            }
        	}else {
        		lista.add(producto);
        	}
            saldoCaja -= costoTotal;
        } else {
            System.out.println("El producto no puede ser agregado a la tienda por saldo insuficiente en la caja.");
        }
    }
	
	public void venderProductos(Map<String, Integer> productosAVender) {
		Map<String, Float> montosPorProductoFinales = new HashMap<String, Float>();
		if(validarVentaCantidad(productosAVender)) {
			productosAVender= validarProductoHabilitado(productosAVender);//chequear!!!!!!!!!
			if(productosAVender.keySet().size()>0) {
				productosAVender = validarDisponibilidadDeProductos(productosAVender);
				//validarDescuento
				List<String> claves = productosAVender.keySet().stream().filter(clave -> true).collect(Collectors.toList());
				for(String clave : claves) {
					float monto = obtenerMontoPorProducto(clave, productosAVender.get(clave));
					if(monto!= 0f) {
						montosPorProductoFinales.put(clave, monto);
					}else {
						productosAVender.remove(clave);
						System.out.println("No se puede realizar la treansacción del producto "+ clave+" , error: no cumple con los requisitos de ganancia");
					}
				}
				actualizarDatosTienda(productosAVender, montosPorProductoFinales);
				imprimirTicket(productosAVender, montosPorProductoFinales);
			}else {
				System.out.println("No se puede realizar la transacción, error:Producto/s no habilitados");
			}
		}else {
			System.out.println("No se puede realizar la transacción, error: cantidad de productos no permitidos");
		}
    }
	
	public void obtenerComestiblesConMenorDescuento(float porcentajeDescuento) {
		List <Producto>comestiblesMenorAporcentajeDesc = new ArrayList<Producto>();
		String tipo;
		for(String clave: this.productosEnStock.keySet()) {
			tipo = clave;
			if(!(tipo.equals("limpieza"))) {
				for(Producto p: this.productosEnStock.get(clave)) {
					if(tipo.equals("envasados")) {
						ProductoEnvasado producto = getProductoEnvasado(p);
						if(!producto.isImportado() && producto.getPorcentajeDescuento()<porcentajeDescuento) {
							comestiblesMenorAporcentajeDesc.add(producto);
						}
					}else {
						ProductoBebida producto = getProductoBebida(p);
						if(!producto.isImportado() && producto.getPorcentajeDescuento()<porcentajeDescuento) {
							comestiblesMenorAporcentajeDesc.add(producto);
						}
					}
				}
			}	
		}
		imprimirListaOrdenada(comestiblesMenorAporcentajeDesc);
	}
	
	public void listarProductosConUtilidadesInferiores(float porcentajeUtilidad){
		List<Producto> productosConUtilidadesInferiores = new ArrayList<>();
		this.productosEnStock.entrySet().stream().forEach(entry -> 
			productosConUtilidadesInferiores.addAll(entry.getValue().stream().filter(producto 
					->producto.getPorcentajeGanancia()<porcentajeUtilidad).collect(Collectors.toList())));
		productosConUtilidadesInferiores.stream().forEach(producto -> 
					System.out.println(producto.getIdentificacion()+ " " + producto.getDescripcion() +" " + producto.getStock()));
		
	}
	
	
	private void imprimirListaOrdenada(List<Producto>comestibles) {
		Collections.sort(comestibles,Comparator.comparingDouble(Producto::getPrecioPorUnidad));
		for(Producto p:comestibles) {
			System.out.println(p.getDescripcion().toUpperCase());
		}
	}
	
	private void actualizarDatosTienda(Map<String, Integer> productosAVender, Map<String, Float> montosPorProducto) {
		String tipo;
		float montoFinal=0f;
		for(String clave : productosAVender.keySet()) {
			tipo=this.getTipoProducto(clave);
			for(Producto p : this.productosEnStock.get(tipo)){
				if(p.getIdentificacion().equals(clave)) {
					p.setStock(p.getStock()-productosAVender.get(clave));
					if(p.getStock()==0) {
						p.setDisponibleParaVenta(false);
					}//vericar producto
					montoFinal= montoFinal + (montosPorProducto.get(clave)*productosAVender.get(clave));
				}
			}
		}
		this.setSaldoCaja(this.getSaldoCaja() + montoFinal);
	}
	
	private void imprimirTicket(Map<String, Integer> productosAVender, Map<String, Float> montosPorProducto) {
		String tipo;
		float montoFinal=0f;
		for(String clave : productosAVender.keySet()) {
			tipo=this.getTipoProducto(clave);
			for(Producto p : this.productosEnStock.get(tipo)){
				if(p.getIdentificacion().equals(clave)) {
					System.out.println(p.getIdentificacion()+" "+ p.getDescripcion()+" "+productosAVender.get(clave)+" x "+p.getPrecioPorUnidad());
					montoFinal= montoFinal + (montosPorProducto.get(clave)*productosAVender.get(clave));
				}
			}
		}
		System.out.println("TOTAL VENTA: "+montoFinal);
	}
	
	private float obtenerMontoPorProducto(String clave, int cantidad) {
		float monto ;
		float descuento;
		float costo;
		float montoPorProductoF=0f;
		String tipo= getTipoProducto(clave);
		for(Producto p : this.productosEnStock.get(tipo)) {
			if(p.getIdentificacion().equals(clave)) {
				 descuento = p.getPorcentajeDescuento();
				 monto=p.getPrecioPorUnidad();
				 costo=p.getCostoPorUnidad();
				 if(p.validarDescuento() && p.getPrecioVentaConDescuento()>costo) {
					 if(p.validarGanancia(p.getPrecioVentaConDescuento())) {
						 montoPorProductoF= p.getPrecioVentaConDescuento();
					 }
				 }else {
					 if(p.validarGanancia(p.getPrecioPorUnidad())) {
						 montoPorProductoF= p.getPrecioPorUnidad();
					 }
				 }
				 if(montoPorProductoF!=0f) {
					 montoPorProductoF=p.aplicarImpuesto(montoPorProductoF);
				 }
			}
		}
		return montoPorProductoF;
	}
	
	private Map<String, Integer> validarDisponibilidadDeProductos(Map<String, Integer> productosAVender){
		boolean estado=false;
		String tipo;
		for(String clave : productosAVender.keySet()) {
			tipo=getTipoProducto(clave);
			for(Producto p : this.productosEnStock.get(tipo)) {
				if(p.getIdentificacion().equals(clave) && productosAVender.get(clave)>p.getStock()) {
					productosAVender.put(clave, p.getStock());
					
					estado=true;
				}
			}
		}
		if (estado) {
			System.out.println("Hay productos con stock disponible menor al solicitado");
		}
		return productosAVender;
	}
	
	private Map<String, Integer> validarProductoHabilitado(Map<String, Integer> productosAVender){
		String tipo;
		for(String clave : productosAVender.keySet()) {
			tipo=getTipoProducto(clave);
			for(Producto p : this.productosEnStock.get(tipo)) {
				if(p.getIdentificacion().equals(clave) && p.isDisponibleParaVenta()==false) {
					productosAVender.remove(clave);
					System.out.println("El producto "+clave+" "+p.getDescripcion()+" no se encuentra disponible");
				}
			}
		}
		return productosAVender;
	}	
	
	private boolean validarVentaCantidad(Map<String, Integer> productosAVender) {//chequeado
		boolean estado= true;
		if(productosAVender.keySet().size()<4) {
			for(String clave : productosAVender.keySet()) {
				if(!(productosAVender.get(clave)<11)) {
					estado=false;
				}
			}
		}else {
			estado=false;
		}
		return estado;
	}
	
		

	private String getTipoProducto(String identificacion){
	    if (identificacion.contains("AB")) {
	        return "envasados";
	    } else if (identificacion.contains("AC")) {
	        return "bebidas";
	    } else if (identificacion.contains("AZ")) {
	        return "limpieza";
	    }
	    return null;
	}


	

	
	public static int calculaTotalElementos(Map<String, List<Producto>> map) {
        int total = 0;
        
        for (List<Producto> list : map.values()) {
            total += list.size();
        }

        return total;
    }
	
	private String getTipoProducto(Producto producto) {
        if (producto instanceof ProductoEnvasado) {
            return "envasados";
        } else if (producto instanceof ProductoBebida) {
            return "bebidas";
        } else if (producto instanceof ProductoLimpieza) {
            return "limpieza";
        }
        return null;
    }
	
	private ProductoEnvasado getProductoEnvasado(Producto producto) {
		return (ProductoEnvasado) producto;
        
    }
	private ProductoBebida getProductoBebida(Producto producto) {
		return (ProductoBebida) producto;
        
    }

}
