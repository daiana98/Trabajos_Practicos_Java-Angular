package com.supertienda.producto;

public class ProductoLimpieza extends Producto{
	public enum TipoAplicacion {
        COCINA, PISOS, ROPA, MULTIUSO
    }
	
	private TipoAplicacion tipoAplicacion;

	public ProductoLimpieza() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductoLimpieza(String identificacion, String descripcion, int stock, float precioPorUnidad,
			float costoPorUnidad, boolean disponibleParaVenta, float porcentajeDescuento, TipoAplicacion tipoAplicacion) {
		super(identificacion, descripcion, stock, precioPorUnidad, costoPorUnidad, disponibleParaVenta, porcentajeDescuento);
		this.tipoAplicacion = tipoAplicacion;
	}

	public TipoAplicacion getTipoAplicacion() {
		return tipoAplicacion;
	}

	public void setTipoAplicacion(TipoAplicacion tipoAplicacion) {
		this.tipoAplicacion = tipoAplicacion;
	}
	
	
}
