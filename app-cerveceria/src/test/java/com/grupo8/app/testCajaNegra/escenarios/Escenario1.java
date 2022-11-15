package com.grupo8.app.testCajaNegra.escenarios;

import com.grupo8.app.modelo.Empresa;
import com.grupo8.app.wrappers.PromocionesFijasWrapper;

import java.util.HashSet;

public class Escenario1 {

    private PromocionesFijasWrapper promocionesFijasAnterior;


    public void aplicaEscenario1(){

        this.promocionesFijasAnterior = Empresa.getEmpresa().getPromocionesFijas();

        Empresa.getEmpresa().getPromocionesTemporales().setPromocionesTemporales( new HashSet<>() );

    }

    public void borraEscenario(){
        Empresa.getEmpresa().setPromocionesFijas(promocionesFijasAnterior);
    }

}
