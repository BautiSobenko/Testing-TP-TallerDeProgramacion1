package com.grupo8.app.modelo.Promociones;

import com.grupo8.app.modelo.Producto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PromocionFija extends Promocion implements Serializable {
	private Producto producto;
	private Boolean dosPorUno;
	private Boolean dtoPorCant;
	private Integer dtoPorCantMin;
	private Double dtoPorCantPrecioU;

	public PromocionFija(String nombre, List<DayOfWeek> diasPromo, Producto producto, Boolean dosPorUno, Boolean dtoPorCant, Integer dtoPorCantMin, Double dtoPorCantPrecioU) {
		super(nombre, diasPromo);
		this.producto = producto;
		this.dosPorUno = dosPorUno;
		this.dtoPorCant = dtoPorCant;
		this.dtoPorCantMin = dtoPorCantMin;
		this.dtoPorCantPrecioU = dtoPorCantPrecioU;
	}
}
