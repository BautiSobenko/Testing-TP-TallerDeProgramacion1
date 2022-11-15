package com.grupo8.app.testCajaNegra.escenarios;

import com.grupo8.app.modelo.Empresa;
import com.grupo8.app.modelo.Producto;
import com.grupo8.app.modelo.Promociones.PromocionFija;
import com.grupo8.app.wrappers.PromocionesFijasWrapper;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Escenario2 {

    private PromocionesFijasWrapper promocionesFijasAnterior;

    public void aplicaEscenario2(){

        this.promocionesFijasAnterior = Empresa.getEmpresa().getPromocionesFijas();

        Empresa.getEmpresa().getPromocionesTemporales().setPromocionesTemporales( new HashSet<>() );

        List<DayOfWeek> diasPromo = new ArrayList<>();
        diasPromo.add( DayOfWeek.MONDAY );
        diasPromo.add( DayOfWeek.SUNDAY );
        diasPromo.add( DayOfWeek.TUESDAY );

        Producto producto = new Producto(
                "Pizza",
                20,
                10,
                5
        );

        PromocionFija promocionFija = new PromocionFija(
                "Promo1",
                diasPromo,
                producto,
                true,
                false,
                null,
                null
        );

        Empresa.getEmpresa().getPromocionesFijas().getPromocionesFijas().add(promocionFija);


    }

    public void borraEscenario(){
        Empresa.getEmpresa().setPromocionesFijas(promocionesFijasAnterior);
    }

}
