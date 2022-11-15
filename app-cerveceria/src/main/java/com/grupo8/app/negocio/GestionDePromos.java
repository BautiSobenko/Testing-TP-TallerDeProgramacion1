package com.grupo8.app.negocio;

import com.grupo8.app.dto.*;
import com.grupo8.app.excepciones.EntidadNoEncontradaException;
import com.grupo8.app.excepciones.MalaSolicitudException;
import com.grupo8.app.modelo.Empresa;
import com.grupo8.app.modelo.Producto;
import com.grupo8.app.modelo.Promociones.PromocionFija;
import com.grupo8.app.modelo.Promociones.PromocionTemporal;
import com.grupo8.app.persistencia.Ipersistencia;
import com.grupo8.app.persistencia.PersistenciaXML;
import com.grupo8.app.wrappers.PromocionesFijasWrapper;
import com.grupo8.app.wrappers.PromocionesTemporalesWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class GestionDePromos {
    private Empresa empresa;

    public GestionDePromos() {
        this.empresa = Empresa.getEmpresa();
    }

    /**
     * Agrega una promocion temporal (por medio de pago) a la lista de promociones de la empresa
     * @param request request con los datos de la promocion a agregar
     * @return DTO de la promocion agregada
     * @throws MalaSolicitudException si no hay dias seleccionados, o el descuento se encuentra fuera del rango 0-100
     */
    public PromoTemporalDTO agregarPromoTemporal(PromoTemporalRequest request) throws MalaSolicitudException {
        if (request.getDiasPromo().size() < 1) {
            throw new MalaSolicitudException("Debe seleccionar al menos un dia");
        }
        if (request.getPorcentajeDescuento() <= 0 || request.getPorcentajeDescuento() > 100) {
            throw new MalaSolicitudException("El porcentaje de descuento debe ser mayor a 0 y menor a 100");
        }

        PromocionTemporal nuevo = new PromocionTemporal(
                request.getNombre(),
                request.getDiasPromo(),
                request.getFormaPago(),
                request.getPorcentajeDescuento(),
                request.isAcumulable()
        );
        this.empresa.getPromocionesTemporales().getPromocionesTemporales().add(nuevo);
        persistirPromosTemporales();

        return PromoTemporalDTO.of(nuevo);
    }

    /**
     * Edita una promocion temporal ya creada en la Empresa
     * @param request request con los datos de la promocion a editar
     * @param id ID de la promocion a editar
     * @return DTO de la promocion editada
     * @throws EntidadNoEncontradaException si la promo temporal no existe
     * @throws MalaSolicitudException si no hay dias seleccionados, o el descuento se encuentra fuera del rango 0-100
     */
    public PromoTemporalDTO editarPromoTemporal(PromoTemporalRequest request, String id) throws EntidadNoEncontradaException, MalaSolicitudException {
        Optional<PromocionTemporal> promo = this.empresa.getPromocionesTemporales().getPromocionesTemporales().stream().filter(p -> p.getIdPromocion().equals(id)).findFirst();
        if (promo.isPresent()) {
            if (request.getDiasPromo().size() < 1) {
                throw new MalaSolicitudException("Debe seleccionar al menos un dia");
            }
            if (request.getPorcentajeDescuento() <= 0 || request.getPorcentajeDescuento() > 100) {
                throw new MalaSolicitudException("El porcentaje de descuento debe ser mayor a 0 y menor a 100");
            }

            PromocionTemporal promoTemporal = (PromocionTemporal) promo.get();
            promoTemporal.setNombre(request.getNombre());
            promoTemporal.setDiasPromo(request.getDiasPromo());
            promoTemporal.setFormaPago(request.getFormaPago());
            promoTemporal.setPorcentajeDescuento(request.getPorcentajeDescuento());
            promoTemporal.setAcumulable(request.isAcumulable());
            persistirPromosTemporales();

            return PromoTemporalDTO.of(promoTemporal);
        } else {
            throw new EntidadNoEncontradaException("No se encontro la promocion temporal");
        }
    }

    /**
     * Reealiza las validaciones antes de acutalizar o agregar una prmocion fija
     * @param request request para agregar o editar una promocion fija
     * @throws MalaSolicitudException si no hay al menos un dia seleccionado, si no se selecciona
     * ningun tipo de promocion, o si seleccionan ambos tipos de promocion
     */
    public void validarUpdatePromoFija(PromoFijaRequest request) throws MalaSolicitudException {

        if (request.getDiasPromo().size() < 1) {
            throw new MalaSolicitudException("Debe seleccionar al menos un dia");
        }
        if (request.getDosPorUno() && request.getDtoPorCantidad()) {
            throw new MalaSolicitudException("No puede seleccionar dos por uno y descuento por cantidad a la vez");
        }
        if (!request.getDosPorUno() && !request.getDtoPorCantidad()) {
            throw new MalaSolicitudException("Debe seleccionar al menos una opcion de descuento");
        }
    }

    /**
     * Agrega una promocion fija a la lista de promociones de la empresa
     * @param request request con los datos de la promocion a agregar
     * @return DTO de la promocion agregada
     * @throws MalaSolicitudException si no existe el producto, o si se propagan excepciones de validacion de validarUpdatePromoFija
     */
    public PromoFijaDTO agregarPromoFija(PromoFijaRequest request) throws MalaSolicitudException {
        Optional<Producto> producto = this.empresa.getProductos().getProductos().stream()
                .filter(p -> p.getId().equals(request.getIdProducto()))
                .findFirst();

        if (!producto.isPresent()) {
            throw new MalaSolicitudException("El producto no existe");
        }

        validarUpdatePromoFija(request);

        PromocionFija promo = new PromocionFija(
                request.getNombre(),
                request.getDiasPromo(),
                producto.get(),
                request.getDosPorUno(),
                request.getDtoPorCantidad(),
                request.getDtoPorCantMin(),
                request.getDtoPorCantPrecioU()
        );

        this.empresa.getPromocionesFijas().getPromocionesFijas().add(promo);
        persistirPromosFijas();

        return PromoFijaDTO.of(promo);
    }

    /**
     * Edita una promocion fija ya creada en la Empresa
     * @param request request con los datos de la promocion a editar
     * @param id ID de la promocion a editar
     * @return DTO de la promocion editada
     * @throws EntidadNoEncontradaException si la promo fija no existe
     * @throws MalaSolicitudException si no existe el producto, o si se propagan excepciones de validacion de validarUpdatePromoFija
     */
    public PromoFijaDTO editarPromoFija(PromoFijaRequest request, String id) throws EntidadNoEncontradaException, MalaSolicitudException {
        Optional<PromocionFija> promo = this.empresa.getPromocionesFijas().getPromocionesFijas().stream().filter(p -> p.getIdPromocion().equals(id)).findFirst();
        if (promo.isPresent()) {
            validarUpdatePromoFija(request);
            PromocionFija promoFija = (PromocionFija) promo.get();
            promoFija.setNombre(request.getNombre());
            promoFija.setDiasPromo(request.getDiasPromo());
            promoFija.setDosPorUno(request.getDosPorUno());
            promoFija.setDtoPorCant(request.getDtoPorCantidad());
            promoFija.setDtoPorCantMin(request.getDtoPorCantMin());
            promoFija.setDtoPorCantPrecioU(request.getDtoPorCantPrecioU());
            persistirPromosFijas();
            return PromoFijaDTO.of(promoFija);
        } else {
            throw new EntidadNoEncontradaException("No se encontro la promocion fija");
        }
    }

    /**
     * Elimina una promocion fija de la lista de promociones de la empresa
     * @param id id de la promo fija a eliminar
     * @return booleano confirmando si se realizo la eliminacion
     * @throws EntidadNoEncontradaException
     */
    public boolean eliminarPromoFija(String id) throws EntidadNoEncontradaException {
        Optional<PromocionFija> promo = this.empresa.getPromocionesFijas().getPromocionesFijas().stream()
                .filter(p -> p.getIdPromocion().equals(id))
                .findFirst();
        if (promo.isPresent()) {
            this.empresa.getPromocionesFijas().getPromocionesFijas().remove(promo.get());
            persistirPromosFijas();
            return true;
        } else {
            throw new EntidadNoEncontradaException("La promocion no existe");
        }
    }

    /**
     * Elimina una promocion temporal de la lista de promociones de la empresa
     * @param id request con los datos de la promocion a agregar
     * @return booleano confirmando si se realizo la eliminacion
     * @throws MalaSolicitudException si no existe el producto
     */
    public boolean eliminarPromoTemporal(String id) throws MalaSolicitudException {
        Optional<PromocionTemporal> promo = this.empresa.getPromocionesTemporales().getPromocionesTemporales().stream()
                .filter(p -> p.getIdPromocion().equals(id))
                .findFirst();
        if (promo.isPresent()) {
            this.empresa.getPromocionesTemporales().getPromocionesTemporales().remove(promo.get());
            persistirPromosTemporales();
            return true;
        } else {
            throw new MalaSolicitudException("La promocion no existe");
        }
    }

    /**
     * Persiste las promociones fijas de la empresa en un archivo XML
     */
    private void persistirPromosFijas() {
        Ipersistencia<PromocionesFijasWrapper> persistencia = new PersistenciaXML();
        try {
            persistencia.abrirOutput("promocionesFijas.xml");
            persistencia.escribir(this.empresa.getPromocionesFijas());
            persistencia.cerrarOutput();
        } catch (Exception e) {
        }
    }


    /**
     * Persiste las promociones temporales de la empresa en un archivo XML
     */
    private void persistirPromosTemporales() {
        Ipersistencia<PromocionesTemporalesWrapper> persistencia = new PersistenciaXML();
        try {
            persistencia.abrirOutput("promocionesTemporales.xml");
            persistencia.escribir(this.empresa.getPromocionesTemporales());
            persistencia.cerrarOutput();
        } catch (Exception e) {
        }
    }

    /**
     * Devuelve una lista de promociones fijas de la empresa
     * @return lista de promociones fijas
     */
    public List<PromoFijaDTO> obtenerPromosFijas() {
        return this.empresa.getPromocionesFijas().getPromocionesFijas().stream()
                .map(PromoFijaDTO::of)
                .collect(Collectors.toList());
    }

    /**
     * Devuelve una lista de promociones temporales de la empresa
     * @return lista de promociones temporales
     */
    public List<PromoTemporalDTO> obtenerPromosTemporales() {
        return this.empresa.getPromocionesTemporales().getPromocionesTemporales().stream()
                .map(PromoTemporalDTO::of)
                .collect(Collectors.toList());
    }

    /**
     * Devuelve una lista de TODAS las promociones de la empresa
     * @return lista de promociones de ambos tipos
     */
    public List<PromocionDTO> obtenerPromos() {
        List<PromocionDTO> promos = new ArrayList<>();
        promos.addAll(obtenerPromosFijas());
        promos.addAll(obtenerPromosTemporales());
        return promos;
    }

    /**
     * elimina una promocion de cualquier tipo
     * @param id id de la promocion a eliminar
     * @throws MalaSolicitudException si no existe la promocion
     */
    public void eliminarPromo(String id) throws MalaSolicitudException {
        try {
            eliminarPromoFija(id);
        } catch (EntidadNoEncontradaException e) {
            eliminarPromoTemporal(id);
        }
    }

    /**
     * Cambia el estado de una promocion fija
     * @param id id de la promo fija a cambiar
     * @return estado final de la promocion
     * @throws EntidadNoEncontradaException si no existe la promocion
     */
    private boolean activarDesactivarPromoFija(String id) throws EntidadNoEncontradaException {
        Optional<PromocionFija> promo = this.empresa.getPromocionesFijas().getPromocionesFijas().stream()
                .filter(p -> p.getIdPromocion().equals(id))
                .findFirst();
        if (promo.isPresent()) {
            promo.get().setActivo(!promo.get().isActivo());
            persistirPromosFijas();
            return promo.get().isActivo();
        } else {
            throw new EntidadNoEncontradaException("La promocion no existe");
        }
    }

    /**
     * Activa o desactiva una promocion temporal
     * @param id id de la promo a cambiar
     * @return el estado final de la promocion
     * @throws EntidadNoEncontradaException si no existe la promocion
     */
    private boolean activarDesactivarPromoTemporal(String id) throws EntidadNoEncontradaException {
        Optional<PromocionTemporal> promo = this.empresa.getPromocionesTemporales().getPromocionesTemporales().stream()
                .filter(p -> p.getIdPromocion().equals(id))
                .findFirst();
        if (promo.isPresent()) {
            promo.get().setActivo(!promo.get().isActivo());
            persistirPromosTemporales();
            return promo.get().isActivo();
        } else {
            throw new EntidadNoEncontradaException("La promocion no existe");
        }
    }

    /**
     * activa o desactiva una promocion cualquiera
     * @param id id de la promo a cambiar
     * @return el estado final de la promocion
     * @throws EntidadNoEncontradaException si no existe la promocion
     */
    public boolean activarDesactivarPromo(String id) throws EntidadNoEncontradaException {
        try {
            return activarDesactivarPromoFija(id);
        } catch (EntidadNoEncontradaException e) {
            return activarDesactivarPromoTemporal(id);
        }
    }
}
