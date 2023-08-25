package com.supertienda.producto;

import java.time.LocalDate;

import com.supertienda.interfaces.Comestible;

public class ProductoEnvasado extends Producto implements Comestible{
	
	private String tipo_Envase;
	private boolean importado;
	private LocalDate fechaVencimiento;
	private int calorias;
    
	public ProductoEnvasado() {
		super();
	}
	
	public ProductoEnvasado(String identificacion, String descripcion, int stock, float precioPorUnidad,
			float costoPorUnidad, boolean disponibleParaVenta, float porcentajeDescuento, String tipo_Envase, boolean importado, LocalDate fechaVencimiento, int calorias) {
		super(identificacion, descripcion, stock, precioPorUnidad, costoPorUnidad, disponibleParaVenta, porcentajeDescuento);
		this.tipo_Envase = tipo_Envase;
		this.importado = importado;
		this.fechaVencimiento = fechaVencimiento;
		this.calorias = calorias;
	}

	public String getTipo_Envase() {
		return tipo_Envase;
	}

	public void setTipo_Envase(String tipo_Envase) {
		this.tipo_Envase = tipo_Envase;
	}

	public boolean isImportado() {
		return importado;
	}

	public void setImportado(boolean importado) {
		this.importado = importado;
	}

	@Override
	public LocalDate getFechaVencimiento() {
		// TODO Auto-generated method stub
		return fechaVencimiento;
	}

	@Override
	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		// TODO Auto-generated method stub
		this.fechaVencimiento = fechaVencimiento;
	}

	@Override
	public int getCalorias() {
		// TODO Auto-generated method stub
		return calorias;
	}

	@Override
	public void setCalorias(int calorias) {
		// TODO Auto-generated method stub
		this.calorias = calorias;
	}


	
	
}
