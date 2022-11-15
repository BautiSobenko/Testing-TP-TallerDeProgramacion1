package com.grupo8.app.testCajaBlanca.escenarios;

import com.grupo8.app.modelo.Empresa;
import com.grupo8.app.modelo.Promociones.PromocionFija;
import com.grupo8.app.wrappers.PromocionesFijasWrapper;

import java.util.HashSet;
import java.util.Set;

public class Escenario1 {

    Empresa empresa;
    PromocionesFijasWrapper promocionesFijas;

    public Escenario1() {
        empresa = Empresa.getEmpresa();
    }

    public void aplicaEscenario1(){

        /*
        empresa = Empresa.getEmpresa();

        promocionesFijas = empresa.getPromocionesFijas();
        PromocionesFijasWrapper listaPromocionesVacia = new PromocionesFijasWrapper();
        empresa.setPromocionesFijas(listaPromocionesVacia);

         */

        this.promocionesFijas = empresa.getPromocionesFijas();

        Set<PromocionFija> promocionesFijas = new HashSet<>();

        empresa.getPromocionesFijas().setPromocionesFijas(promocionesFijas);

    }

    public void borraEscenario(){
        empresa.setPromocionesFijas(promocionesFijas);
    }

}
