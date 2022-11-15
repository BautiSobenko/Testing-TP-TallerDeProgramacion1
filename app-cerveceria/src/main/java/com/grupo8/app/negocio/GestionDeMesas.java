	package com.grupo8.app.negocio;

import com.grupo8.app.dto.*;
import com.grupo8.app.excepciones.EntidadNoEncontradaException;
import com.grupo8.app.excepciones.EstadoInvalidoException;
import com.grupo8.app.excepciones.MalaSolicitudException;
import com.grupo8.app.excepciones.NumeroMesaInvalidoException;
import com.grupo8.app.modelo.*;
import com.grupo8.app.modelo.Promociones.Promocion;
import com.grupo8.app.modelo.Promociones.PromocionFija;
import com.grupo8.app.modelo.Promociones.PromocionTemporal;
import com.grupo8.app.persistencia.Ipersistencia;
import com.grupo8.app.persistencia.PersistenciaXML;
import com.grupo8.app.tipos.EstadoComanda;
import com.grupo8.app.tipos.EstadoMesa;
import com.grupo8.app.tipos.EstadoMozo;
import com.grupo8.app.wrappers.CierreComandaWrapper;
import com.grupo8.app.wrappers.ComandasWrapper;
import com.grupo8.app.wrappers.MesasWrapper;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class GestionDeMesas {
    public Empresa empresa;

    public GestionDeMesas() {
        this.empresa = Empresa.getEmpresa();
    }

    /**
     * @param request, contiene numero de mesa y sillas
     * @return MesaDTO, DTO con información de la mesa
     * @throws NumeroMesaInvalidoException si el numero de mesa ya existe
     */
    public MesaDTO addMesa(AddMesaRequest request) throws NumeroMesaInvalidoException, MalaSolicitudException {
        Optional<Mesa> potencialDuplicado =
                this.empresa.getMesas()
                        .getMesas()
                        .stream()
                        .filter(mesa -> Objects.equals(mesa.getNroMesa(), request.getNroMesa())).findFirst();

        if (!potencialDuplicado.isPresent()) {
            Mesa mesa = new Mesa(request.getNroMesa(), request.getCantSillas());
            this.empresa.getMesas().getMesas().add(mesa);

            Optional<Mozo> mozo = Empresa.getEmpresa().getMozos().getMozos().stream().filter(m -> m.getId().equals(request.getMozoAsignado().getId())).findFirst();
            if (mozo.isPresent()) {
                mesa.setMozoAsignado(mozo.get());
            } else {
                throw new MalaSolicitudException("No existe el mozo");
            }

            persistir();

            return MesaDTO.of(mesa);
        } else {
            throw new NumeroMesaInvalidoException("Ya existe una mesa con ese numero");
        }
    }


    /**
     * @param request tiene el numero de mesa a editar y la cantidad de sillas
     * @throws EntidadNoEncontradaException si no existe la mesa
     */
    public void editMesa(AddMesaRequest request) throws EntidadNoEncontradaException {
        Optional<Mesa> mesaAEditar = this.empresa.getMesas().getMesas().stream()
                .filter(mesa -> Objects.equals(mesa.getNroMesa(), request.getNroMesa())).findFirst();

        if (mesaAEditar.isPresent()) {
            Optional<Mozo> mozoAsignado = this.empresa.getMozos().getMozos().stream()
                    .filter(mozo -> Objects.equals(mozo.getId(), request.getMozoAsignado().getId())).findFirst();

            mozoAsignado.ifPresent(mozo -> mesaAEditar.get().setMozoAsignado(mozo));

            mesaAEditar.get().setCantSillas(request.getCantSillas());
            persistir();
        } else {
            throw new EntidadNoEncontradaException("No se encontro la mesa");
        }

    }

    /**
     * @param nroMesa, numero de mesa a eliminar
     */
    public boolean deleteMesa(Integer nroMesa) {
        this.empresa.getMesas().getMesas().removeIf(mesa -> nroMesa.equals(mesa.getNroMesa()));
        persistir();
        return true;
    }

    /**
     * Persiste las mesas en el archivo mesas.xml
     */
    private void persistir() {
        Ipersistencia<MesasWrapper> persistencia = new PersistenciaXML();
        try {
            persistencia.abrirOutput("mesas.xml");
            persistencia.escribir(this.empresa.getMesas());
            persistencia.cerrarOutput();
        } catch (Exception e) {
        }
    }

    private void persistirComandas() {
        Ipersistencia<ComandasWrapper> persistencia = new PersistenciaXML();
        try {
            persistencia.abrirOutput("comandas.xml");
            persistencia.escribir(this.empresa.getComandas());
            persistencia.cerrarOutput();
        } catch (Exception e) {
        }
    }

    /**
     * Persiste los cierres de comandas en el archivo cierres.xml
     */
    private void persistirCierreComandas() {
        Ipersistencia<CierreComandaWrapper> persistencia = new PersistenciaXML();
        try {
            persistencia.abrirOutput("cierres.xml");
            persistencia.escribir(this.empresa.getCierreComandas());
            persistencia.cerrarOutput();
        } catch (Exception e) {
        }
    }


    /**
     * Valida que esten las condiciones dadas para iniciar el dia de trabajo
     * Marca todas las mesas como libres
     * Chequea que haya al menos dos promoociones vigentes
     * @throws EstadoInvalidoException Si no se cumplen las condiciones
     */
    public void iniciarTurno() throws EstadoInvalidoException {
        for (Mesa mesa : this.empresa.getMesas().getMesas()) {
            mesa.setEstadoMesa(EstadoMesa.LIBRE);
            mesa.setMozoAsignado(null);
        }
        List<Promocion> promos = new ArrayList<>();
        promos.addAll(this.empresa.getPromocionesFijas().getPromocionesFijas());
        promos.addAll(this.empresa.getPromocionesTemporales().getPromocionesTemporales());

        List<Promocion> promosActivas = promos.stream().filter(promo -> promo.getDiasPromo() != null && promo.getDiasPromo().contains(LocalDate.now().getDayOfWeek()) && promo.isActivo()).collect(Collectors.toList());

        if (promosActivas.size() < 2) {
            throw new EstadoInvalidoException("Debe haber al menos 2 promociones activas para iniciar el turno");
        }
    }

    /**
     * Crea una comanda para una mesa a la cual se le agregaran pedidos
     * @param nroMesa numero de mesa
     * @return
     * @throws EntidadNoEncontradaException si la mesa no existe
     * @throws EstadoInvalidoException si la mesa no esta libre o no tiene mozo asignado
     */
    public ComandaDTO crearComanda(Integer nroMesa) throws EntidadNoEncontradaException, EstadoInvalidoException {
        Optional<Mesa> mesa = this.empresa.getMesas().getMesas().stream()
                .filter(me -> Objects.equals(me.getNroMesa(), nroMesa)).findFirst();

        if (mesa.isPresent() && mesa.get().getEstadoMesa() == EstadoMesa.LIBRE) {
            mesa.get().setEstadoMesa(EstadoMesa.OCUPADA);
            persistir();

            if (mesa.get().getMozoAsignado() != null || mesa.get().getMozoAsignado().getEstadoMozo() != EstadoMozo.ACTIVO) {
                Comanda comanda = new Comanda(mesa.get());
                this.empresa.getComandas().getComandas().add(comanda);
                persistirComandas();
                return ComandaDTO.of(comanda);
            } else if (mesa.get().getMozoAsignado() == null) {
                throw new EntidadNoEncontradaException("No se encontro mozo asignado");
            } else {
                throw new EstadoInvalidoException("El mozo asignado no esta activo");
            }

        } else if (mesa.get().getEstadoMesa() == EstadoMesa.OCUPADA) {
            throw new EstadoInvalidoException("La mesa ya esta ocupada");
        } else {
            throw new EntidadNoEncontradaException("No se encontro la mesa");
        }
    }

    /**
     * Agrega un pedido a una comanda
     * @param pedido pedido a agregar
     * @throws EntidadNoEncontradaException si no existe la comanda
     */
    public void agregarPedidoAComanda(PedidoRequest pedido) throws EntidadNoEncontradaException, EstadoInvalidoException {
        Optional<Comanda> comanda = this.empresa.getComandas().getComandas().stream()
                .filter(c -> Objects.equals(c.getId(), pedido.getIdComanda())).findFirst();

        if (comanda.isPresent()) {
            Optional<Producto> productoAAgregar = this.empresa.getProductos().getProductos().stream()
                    .filter(producto -> Objects.equals(producto.getId(), pedido.getIdProducto())).findFirst();
            if (productoAAgregar.isPresent()) {

                if (pedido.getCantidad() > productoAAgregar.get().getStock()) {
                    throw new EstadoInvalidoException("No hay stock suficiente");
                }

                Optional<Pedido> pedidoExistente = comanda.get().getPedidos().stream()
                        .filter(p -> Objects.equals(p.getProducto().getId(), pedido.getIdProducto())).findFirst();
                if (pedidoExistente.isPresent()) { //los pedidos se agrupan por producto
                    Pedido pedidoAEditar = pedidoExistente.get();
                    pedidoAEditar.setCantidad(pedidoAEditar.getCantidad() + pedido.getCantidad());
                } else { //si no existe el pedido se crea
                    comanda.get().getPedidos().add(new Pedido(productoAAgregar.get(), pedido.getCantidad()));
                }

                productoAAgregar.get().setStock(productoAAgregar.get().getStock() - pedido.getCantidad());
                persistirComandas();
                new GestionDeProductos().persistir();
            } else {
                throw new EntidadNoEncontradaException("No se encontro el producto");
            }
        } else {
            throw new EntidadNoEncontradaException("No se encontro la comanda");
        }
    }

    /**
     * Aplica las promociones fijas vigentes a una comanda
     * @param cierreComanda la comanda a la cual se le aplicaran las promociones
     * @return booleano que indica si se aplico alguna promocion
     */
    public boolean aplicarPromocionesFijas(CierreComanda cierreComanda) {
        boolean aplicoPromo = false;
        List<Promocion> promos = new ArrayList<>();
        promos.addAll(this.empresa.getPromocionesFijas().getPromocionesFijas());
        //promos.addAll(this.empresa.getPromocionesTemporales().getPromocionesTemporales());

        List<Promocion> promosFijas =
                promos
                .stream()
                .filter(promo -> promo.getDiasPromo() != null && promo.getDiasPromo().contains(LocalDate.now().getDayOfWeek()) && promo.isActivo() && promo instanceof PromocionFija)
                .collect(Collectors.toList());


        for (Promocion promo : promosFijas) {
            PromocionFija promoFija = (PromocionFija) promo;
            Optional<Pedido> pedidoDePromo = cierreComanda.getPedidos().stream()
                    .filter(p -> Objects.equals(p.getProducto().getId(), promoFija.getProducto().getId())).findFirst();

            if (pedidoDePromo.isPresent()) {
                Pedido pedido = pedidoDePromo.get();
                if (promoFija.getDosPorUno()) {
                    int pares = Math.floorDiv(pedidoDePromo.get().getCantidad(), 2);
                    int resto = pedidoDePromo.get().getCantidad() % 2;
                    pedido.setSubtotal(pares * pedido.getProducto().getPrecio() + resto * pedido.getProducto().getPrecio());
                    pedido.setEsPromo(true);
                    cierreComanda.getPromociones().add(promoFija);
                    aplicoPromo = true;
                } else { //es una promo por cantidad
                    if (pedido.getCantidad() >= promoFija.getDtoPorCantMin()) {
                        pedido.setSubtotal((float) (pedido.getCantidad() * promoFija.getDtoPorCantPrecioU()));
                        pedido.setEsPromo(true);
                        cierreComanda.getPromociones().add(promoFija);
                        aplicoPromo = true;
                    }
                }
            }
        }

        return aplicoPromo; //Avisa si realmente logro aplicar alguna promo
    }

    /**
     * Aplica las promociones temporales vigentes a una comanda a la hora de cerrarla
     * @param cierreComanda la comanda a la cual se le aplicaran las promociones
     */
    private void sumarTotal(CierreComanda cierreComanda, String medioDePago) {
        List<PromocionTemporal> promosTemporales =
                this.empresa.getPromocionesTemporales()
                        .getPromocionesTemporales()
                        .stream()
                        .filter(promo -> promo.getDiasPromo() != null && promo.getDiasPromo().contains(LocalDate.now().getDayOfWeek()) && promo.isActivo())
                        .collect(Collectors.toList());

        Optional<PromocionTemporal> promoTemporal = promosTemporales.stream()
                .filter(promo -> promo.getFormaPago().equals(medioDePago)).findFirst();


        cierreComanda.setTotal(cierreComanda.getPedidos()
                .stream()
                .reduce(0f, (subtotal, pedido) -> {
                    if (!promoTemporal.isPresent() || (pedido.isEsPromo() && !promoTemporal.get().isAcumulable())) {
                        return subtotal + (pedido.getSubtotal());
                    } else {
                        return  subtotal + (pedido.getSubtotal()) - (pedido.getSubtotal() * promoTemporal.get().getPorcentajeDescuento() / 100);
                    }
                }, Float::sum));
    }

    /**
     * Aplica las promociones temporales vigentes a una comanda a la hora de cerrarla
     * @param idComanda ID de la comanda a la cual se le aplicaran las promociones
     * @param medioDePago el medio de pago con el cual se cerrara la comanda
     */
    public void cerrarComanda(String idComanda, String medioDePago) throws EntidadNoEncontradaException {
        Optional<Comanda> comanda = this.empresa.getComandas().getComandas().stream()
                .filter(c -> Objects.equals(c.getId(), idComanda)).findFirst();

        if (comanda.isPresent()) {
            CierreComanda cierre = new CierreComanda(comanda.get());
            aplicarPromocionesFijas(cierre);
            sumarTotal(cierre, medioDePago);
            cierre.setEstadoPedido(EstadoComanda.CERRADA);
            comanda.get().getMesa().setEstadoMesa(EstadoMesa.LIBRE);

            this.empresa.getCierreComandas().getCierreComandas().add(cierre);
            this.empresa.getComandas().getComandas().remove(comanda.get());

            persistir();
            persistirComandas();
            persistirCierreComandas();
        } else {
            throw new EntidadNoEncontradaException("No se encontro la comanda");
        }
    }

    /**
     * Devuelve el listado de todas las mesas
     * @return List<MesaDTO> listado de mesas
     */
    public List<MesaDTO> obtenerMesas() {
        return this.empresa.getMesas()
                .getMesas()
                .stream()
                .filter(mesa -> mesa.getMozoAsignado() != null)
                .map(MesaDTO::of)
                .collect(Collectors.toList());
    }

    /**
     * Devuelve el listado las mesas libres
     * @return List<MesaDTO> listado de mesas
     */
    public List<MesaDTO> obtenerMesasLibres() {
        return this.empresa.getMesas()
                .getMesas()
                .stream()
                .filter(mesa -> mesa.getMozoAsignado() != null)
                .filter(mesa -> mesa.getEstadoMesa().equals(EstadoMesa.LIBRE))
                .map(MesaDTO::of)
                .collect(Collectors.toList());
    }

    /*
     * Devuelve el listado de las comandas
     * @return List<ComandaDTO> listado de comandas
     */
    public List<ComandaDTO> obtenerComandas() {
        return this.empresa.getComandas()
                .getComandas()
                .stream()
                .map(ComandaDTO::of)
                .collect(Collectors.toList());
    }

    /**
     * Valida si estan dadas las condiciones para cerrar el turno
     * @throws EstadoInvalidoException Si hay comandas abiertas
     */
    public void cerrarTurno() throws EstadoInvalidoException {
        if (empresa.getComandas().getComandas().size() > 0) {
            throw new EstadoInvalidoException("No se puede cerrar el turno, hay comandas abiertas");
        }
        persistirCierreComandas();
    }

    /**
     * Asigna un mozo a la mesa que va a atender
     * @param nroMesa mesa seleccionada
     * @param mozoDTO mozo seleccionado
     * @throws EntidadNoEncontradaException
     */
    public void asignarMozo(Integer nroMesa, MozoDTO mozoDTO) throws EntidadNoEncontradaException {
        Optional<Mozo> mozo = this.empresa.getMozos().getMozos().stream()
                .filter(m -> Objects.equals(m.getId(), mozoDTO.getId())).findFirst();
        if (mozo.isPresent()) {
            Optional<Mesa> mesa = this.empresa.getMesas().getMesas().stream()
                    .filter(m -> Objects.equals(m.getNroMesa(), nroMesa)).findFirst();
            if (mesa.isPresent()) {
                mesa.get().setMozoAsignado(mozo.get());
                persistir();
            } else {
                throw new EntidadNoEncontradaException("No se encontro la mesa");
            }
        } else {
            throw new EntidadNoEncontradaException("No se encontro el mozo");
        }
    }

    /**
     * Devuelve una mesa por su numero
     * @param nroMesa numero de mesa
     * @return DTO de la mesa
     * @throws EntidadNoEncontradaException si no se encuentra la mesa
     */
    public MesaDTO obtenerPorNro(Integer nroMesa) throws EntidadNoEncontradaException{
        Optional <MesaDTO> mesa= this.obtenerMesas().stream().filter(m->m.getNroMesa().equals(nroMesa)).findFirst();
        if(mesa.isPresent())
            return mesa.get();
        else throw new EntidadNoEncontradaException(" no se encontro la mesa");


    }
}
