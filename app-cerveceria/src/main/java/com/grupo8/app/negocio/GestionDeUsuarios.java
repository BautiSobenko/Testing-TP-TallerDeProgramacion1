package com.grupo8.app.negocio;

import com.grupo8.app.dto.AddMozoRequest;
import com.grupo8.app.dto.AddOperarioRequest;
import com.grupo8.app.dto.MozoDTO;
import com.grupo8.app.dto.OperarioDTO;
import com.grupo8.app.excepciones.CredencialesInvalidasException;
import com.grupo8.app.excepciones.EntidadNoEncontradaException;
import com.grupo8.app.excepciones.PermisoDenegadoException;
import com.grupo8.app.modelo.Empresa;
import com.grupo8.app.modelo.Mozo;
import com.grupo8.app.modelo.Operario;
import com.grupo8.app.persistencia.Ipersistencia;
import com.grupo8.app.persistencia.PersistenciaXML;
import com.grupo8.app.tipos.EstadoMozo;
import com.grupo8.app.wrappers.MozoWrapper;
import com.grupo8.app.wrappers.OperariosWrapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GestionDeUsuarios {

    private Empresa empresa;

    public GestionDeUsuarios() {
        this.empresa = Empresa.getEmpresa();
    }

    /**
     * loguea un usuario
     * @param username nombre de usuario
     * @param password contraseña
     * @return Operario logueado
     * @throws CredencialesInvalidasException si las credenciales son incorrectas
     */
    public Operario login(String username, String password) throws CredencialesInvalidasException {
        return this.empresa.login(username, password);
    }

    /**
     * Desloguea un usuario
     */
    public void logout() {
        this.empresa.logout();
    }

    /**
     * Agrega un operario a la empresa y lo persiste
     * @param request DTO con los datos del operario a agregar
     * @throws PermisoDenegadoException si el usuario logueado no es administrador
     */
    public void addOperario(AddOperarioRequest request) throws PermisoDenegadoException {
        if (this.empresa.getUsuarioLogueado().getUsername().equals("admin")) {
            this.empresa.getOperarios().getOperarios().add(
                    new Operario(request.getNombreCompleto(), request.getUsername(), request.getPassword()));
            persistirOperarios();
        } else {
            throw new PermisoDenegadoException("No tiene permisos para realizar esta accion");
        }
    }

    /**
     * Agrega un mozo a la empresa y lo persiste
     * @param request DTO con los datos del mozo a agregar
     */
    public MozoDTO addMozo(AddMozoRequest request) {
        Mozo nuevoMozo = new Mozo(request.getNombreCompleto(), request.getFechaNacimiento(), request.getCantidadHijos());
        this.empresa.getMozos().getMozos().add(nuevoMozo);

        persistirMozo();
        return MozoDTO.of(nuevoMozo);
    }

    /**
     * Elimina un mozo de la empresa y lo persiste
     * @param mozo
     */
    public void deleteMozo(MozoDTO mozo) {
        this.empresa.getMozos().getMozos().removeIf(m -> m.getId().equals(mozo.getId()));
        persistirMozo();
    }

    /**
     * Calcula el sueldo total de los mozos teniendo en cuenta la cantidad de hijos
     * @param mozo mozo seleccionado
     * @return sueldo final
     * @throws EntidadNoEncontradaException si el mozo no existe
     */
    public Float calcularSueldoMozo(MozoDTO mozo) throws EntidadNoEncontradaException {
        float base = this.empresa.getSueldoBase();

        Optional<Mozo> mozoEncontrado = this.empresa.getMozos().getMozos().stream()
                .filter(m -> m.getId().equals(mozo.getId()))
                .findFirst();
        if (mozoEncontrado.isPresent()) {
            return base + (mozoEncontrado.get().getCantidadHijos() * 0.05f * base);
        } else {
            throw new EntidadNoEncontradaException("No se encontro el mozo");
        }
    }

    /**
     * Elmina un mozo por el nombre
     * @param nombre nombre del mozo a eliminar
     */
    public void deleteMozoPorNombre(String nombre) {
        this.empresa.getMozos().getMozos().removeIf(m -> m.getNombreCompleto().contains(nombre));
        persistirMozo();
    }

    /**
     * Persiste los mozos de la empresa en el archivo mozos.xml
     */
    private void persistirMozo() {
        Ipersistencia<MozoWrapper> persistencia = new PersistenciaXML();
        try {
            persistencia.abrirOutput("mozos.xml");
            persistencia.escribir(this.empresa.getMozos());
            persistencia.cerrarOutput();
        } catch (Exception e) {
        }
    }

    /**
     * Persiste los operarios de la empresa en el archivo operarios.xml
     */
    private void persistirOperarios() {
        Ipersistencia<OperariosWrapper> persistencia = new PersistenciaXML();
        try {
            persistencia.abrirOutput("operarios.xml");
            persistencia.escribir(this.empresa.getOperarios());
            persistencia.cerrarOutput();
        } catch (Exception e) {
        }
    }

    /**
     * Obtiene los mozos de la empresa
     * @return lista de DTOs de mozos
     */
    public List<MozoDTO> obtenerMozos() {
        return this.empresa.getMozos()
                .getMozos()
                .stream().map(MozoDTO::of)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene los operarios de la empresa
     * @return lista de DTOs de operarios
     */
    public List<OperarioDTO> obtenerOperarios() {
        return this.empresa.getOperarios()
                .getOperarios()
                .stream().map(OperarioDTO::of)
                .collect(Collectors.toList());
    }

    /**
     * Cambia el estado de asistencia de un mozo
     * @param id id del mozo a modificar
     * @param estado estado al que se quiere cambiar
     * @throws EntidadNoEncontradaException si no se encuentra el mozo
     */
    public void tomarAsistencia(String id, EstadoMozo estado) throws EntidadNoEncontradaException {
        Optional<Mozo> mozo = this.empresa.getMozos().getMozos().stream().filter(m -> m.getId().equals(id)).findFirst();

        if (mozo.isPresent()) {
            mozo.get().setEstadoMozo(estado);
        } else {
            throw new EntidadNoEncontradaException("No se encontro el mozo con id " + id);
        }
    }


    /**
     * Elmina un operario
     * @param operarioDTO operario a elminar
     */
    public void deleteOperario(OperarioDTO operarioDTO) {
        this.empresa.getOperarios().getOperarios().removeIf(o -> o.getUsername().equals(operarioDTO.getUsername()));
        persistirOperarios();
    }
}
