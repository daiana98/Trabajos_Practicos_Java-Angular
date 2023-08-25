package com.supertienda.producto;

import java.time.LocalDate;

import com.supertienda.interfaces.Comestible;

public class ProductoBebida extends Producto implements Comestible{
	private boolean contieneAlcohol;
	private float graduacionAlcoholica;
	private boolean importado;
	private LocalDate fechaVencimiento;
	private int calorias;

	
	public ProductoBebida() {
		super();
	}
	
	public ProductoBebida(String identificacion, String descripcion, int stock, float precioPorUnidad,
			float costoPorUnidad, boolean disponibleParaVenta, float porcentajeDescuento, boolean contieneAlcohol, float graduacionAlcoholica, boolean importado, LocalDate fechaVencimiento, int calorias) {
		super(identificacion, descripcion, stock, precioPorUnidad, costoPorUnidad, disponibleParaVenta, porcentajeDescuento);
		this.contieneAlcohol = contieneAlcohol;
		this.graduacionAlcoholica = graduacionAlcoholica;
		this.importado = importado;
		this.fechaVencimiento = fechaVencimiento;
		this.calorias = calorias;
	}

	public boolean isContieneAlcohol() {
		return contieneAlcohol;
	}

	public void setContieneAlcohol(boolean contieneAlcohol) {
		this.contieneAlcohol = contieneAlcohol;
	}

	public float getGraduacionAlcoholica() {
		return graduacionAlcoholica;
	}

	public void setGraduacionAlcoholica(float graduacionAlcoholica) {
		this.graduacionAlcoholica = graduacionAlcoholica;
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
		this.fechaVencimiento = fechaVencimiento;
	}

	@Override
	public int getCalorias() {
		// TODO Auto-generated method stub
		return calorias;
	}

	@Override
	public void setCalorias(int calorias) {
		this.calorias = calorias;
		
	}
	
}
