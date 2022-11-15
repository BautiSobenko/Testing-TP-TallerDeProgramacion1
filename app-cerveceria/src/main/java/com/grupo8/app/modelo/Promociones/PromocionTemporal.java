package com.grupo8.app.modelo.Promociones;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PromocionTemporal extends Promocion implements Serializable {
	private String formaPago;
	private int porcentajeDescuento;
	private boolean acumulable;


	public PromocionTemporal(String nombre, List<DayOfWeek> diasPromo, String formaPago, int porcentajeDescuento, boolean acumulable) {
		super(nombre, diasPromo);
		this.formaPago = formaPago;
		this.porcentajeDescuento = porcentajeDescuento;
		this.acumulable = acumulable;
	}
}
