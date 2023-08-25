package com.supertienda.producto;

import com.supertienda.interfaces.AplicableDescuento;
import com.supertienda.producto.ProductoLimpieza.TipoAplicacion;

public class Producto implements AplicableDescuento{
	private String identificacion;
	private String descripcion;
	private int stock;
	private float precioPorUnidad;
	private float costoPorUnidad;
	private boolean disponibleParaVenta;
	private float porcentajeDescuento;
	
	public Producto() {}

	public Producto(String identificacion, String descripcion, int stock, float precioPorUnidad, float costoPorUnidad,
			boolean disponibleParaVenta, float porcentajeDescuento) {
		this.identificacion = identificacion;
		this.descripcion = descripcion;
		this.stock = stock;
		this.precioPorUnidad = precioPorUnidad;
		this.costoPorUnidad = costoPorUnidad;
		this.disponibleParaVenta = disponibleParaVenta;
		this.porcentajeDescuento = porcentajeDescuento;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public float getPrecioPorUnidad() {
		return precioPorUnidad;
	}

	public void setPrecioPorUnidad(float precioPorUnidad) {
		this.precioPorUnidad = precioPorUnidad;
	}

	public float getCostoPorUnidad() {
		return costoPorUnidad;
	}

	public void setCostoPorUnidad(float costoPorUnidad) {
		this.costoPorUnidad = costoPorUnidad;
	}

	public boolean isDisponibleParaVenta() {
		return disponibleParaVenta;
	}

	public void setDisponibleParaVenta(boolean disponibleParaVenta) {
		this.disponibleParaVenta = disponibleParaVenta;
	}

	@Override
	public void setPorcentajeDescuento(float porcentaje) {
		this.porcentajeDescuento = porcentaje;
	}

	@Override
	public float getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	@Override
	public float getPrecioVentaConDescuento() {
		return precioPorUnidad * (1 - porcentajeDescuento / 100);
	}

	public boolean validarDescuento() {
		boolean status=true;
		if(this instanceof ProductoEnvasado) {
			if(!(this.porcentajeDescuento<=20)) {
				System.out.println("El descuento registrado para el producto "+this.identificacion+" no pudo ser aplicado");
				status =false;
			}
		}else if(this instanceof ProductoLimpieza) {
			if(!(this.porcentajeDescuento<=25)) {
				System.out.println("El descuento registrado para el producto "+this.identificacion+" no pudo ser aplicado");
				status =false;
			}
		}else if(this instanceof ProductoBebida) {
			if(!(this.porcentajeDescuento<=15)) {
				System.out.println("El descuento registrado para el producto "+this.identificacion+" no pudo ser aplicado");
				status =false;
			}
		}
		return status;
	}
	
	public boolean validarGanancia(float precio) {
		boolean status = true;
		float porcentajeGanancia = ((precio * 100)/this.costoPorUnidad) -100;
		if(this instanceof ProductoEnvasado || this instanceof ProductoBebida) {//Comestible
			if(!(porcentajeGanancia<=20)) {
				System.out.println("El porcentaje de ganancia para el producto "+this.identificacion+" supera el 20%");
				status =false;
			}
		}else if(this instanceof ProductoLimpieza) {
			ProductoLimpieza productoL = (ProductoLimpieza) this;
			if(!(porcentajeGanancia<=25)) {
				if(!(productoL.getTipoAplicacion().equals(TipoAplicacion.MULTIUSO) || productoL.getTipoAplicacion().equals(TipoAplicacion.ROPA))) {
					if(!(porcentajeGanancia>=10)) {
						status = false;
						System.out.println("El porcentaje de ganancia para el producto "+this.identificacion+" no alcanza  el 10%");
					}
				}
			}else {
				System.out.println("El porcentaje de ganancia para el producto "+this.identificacion+" supera el 25%");
				status =false;
			}
		}
		return status;
	}
	
	public float aplicarImpuesto(float monto) {
		float montoFinal=monto;
		if(this instanceof ProductoEnvasado) {
			ProductoEnvasado productoE = (ProductoEnvasado) this;
			if(productoE.isImportado()) {
				montoFinal= monto * 1.1f;
			}
		}else if(this instanceof ProductoBebida) {
			ProductoBebida productoB = (ProductoBebida) this;
			if(productoB.isImportado()) {
				montoFinal= monto * 1.1f;
			}
		}
		return montoFinal;
	}
	
	public float getPorcentajeGanancia() {
		float porcentajeGanacia = ((this.precioPorUnidad * 100)/this.costoPorUnidad) -100;
		return porcentajeGanacia;
	}
}
