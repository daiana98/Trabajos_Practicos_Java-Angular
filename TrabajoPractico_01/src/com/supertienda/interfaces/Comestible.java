package com.supertienda.interfaces;

import java.time.LocalDate;

public interface Comestible {
	public abstract LocalDate getFechaVencimiento();
	public abstract void setFechaVencimiento(LocalDate fechaVencimiento);
	
	public abstract int getCalorias();
	public abstract void setCalorias(int calorias);
}
