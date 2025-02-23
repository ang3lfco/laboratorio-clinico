/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Dtos.ClienteDTO;
import Dtos.ClienteTablaDTO;
import Dtos.EditarClienteDTO;
import Dtos.GuardarClienteDTO;
import Entidades.ClienteEntidad;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import Persistencia.IClienteDAO;
import Persistencia.PersistenciaException;
import java.time.LocalDate;

/**
 * 
 * @author ReneEzequiel23
 */
public class ClienteNegocio implements IClienteNegocio {

    private IClienteDAO clienteDAO;

    public ClienteNegocio(IClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    /**
     *
     * @return @throws NegocioException
     */
    @Override
    public List<ClienteTablaDTO> buscarClientes() throws NegocioException {
        try {
            List<ClienteEntidad> clientesEntidadLista = this.clienteDAO.buscarClientes();
            return this.convertirClienteTablaDTO(clientesEntidadLista);
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    /**
     *
     * @param cliente
     * @return
     * @throws NegocioException
     */
    @Override
    public ClienteDTO guardar(GuardarClienteDTO cliente) throws NegocioException {
        try {
            this.validarInformacionGuardarCliente(cliente);
            ClienteEntidad clienteGuardado = this.clienteDAO.guardar(cliente);
            return this.convertirClienteDTO(clienteGuardado);
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    @Override
    public ClienteDTO editar(EditarClienteDTO cliente) throws NegocioException {
        try {
            this.validarCamposEnEditar(cliente);
            this.existeElCliente(cliente);
            ClienteEntidad clienteModificado = this.clienteDAO.editar(cliente);
            System.out.println(clienteModificado);
            return this.convertirClienteDTO(clienteModificado);
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    @Override
    public ClienteDTO eliminar(int id) throws NegocioException {
            try {
            if (id <= 0) {
                throw new NegocioException("El id recibido es incorrecto");
            }
            ClienteEntidad alumnoBD = this.clienteDAO.buscarPorId(id);
            if (alumnoBD == null) {
                throw new NegocioException("No se pudo obtener el cliente con la clave ingresada");
            }
            ClienteEntidad clienteEliminado = this.clienteDAO.eliminar(id);
            System.out.println(clienteEliminado);
            return this.convertirClienteDTO(clienteEliminado);
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    @Override
    public ClienteDTO buscarPorId(int id) throws NegocioException {
        try {
            if (id <= 0) {
                throw new NegocioException("El id recibido es incorrecto");
            }
            ClienteEntidad clienteBD = this.clienteDAO.buscarPorId(id);
            ClienteDTO clienteDTO = this.convertirClienteDTO(clienteBD);
            return clienteDTO;
        } catch (PersistenciaException ex) {
            System.out.println(ex.getMessage());
            throw new NegocioException(ex.getMessage());
        }
    }

    /**
     *
     * @param alumnos
     * @return
     */
    private List<ClienteTablaDTO> convertirClienteTablaDTO(List<ClienteEntidad> entidades) {
        if (entidades == null) {
            return null;
        }
        List<ClienteTablaDTO> clientesDTO = new ArrayList<>();
        for (ClienteEntidad entidad : entidades) {
            ClienteTablaDTO dato = new ClienteTablaDTO(
                    entidad.getId(),
                    entidad.getNombres(), 
                    entidad.getApellidoPaterno(),
                    entidad.getApellidoMaterno(), 
                    entidad.getFechaNacimiento(),
                    entidad.isEstaBorrado());
            clientesDTO.add(dato);
        }
        return clientesDTO;
    }

    private void validarInformacionGuardarCliente(GuardarClienteDTO cliente) throws NegocioException {
        String nombres = cliente.getNombres();
        String apellidoPaterno = cliente.getApellidoPaterno();
        String apellidoMaterno = cliente.getApellidoMaterno();
        LocalDate fechaNacimiento = cliente.getFechaNacimiento();
        
        if (nombres == null || nombres.isEmpty() || nombres.length()>50) {
            throw new NegocioException("El nombre no debe estar en blanco y tampoco debe de pasar los 50 caracteres.");
        }
         if (apellidoPaterno == null || apellidoPaterno.isEmpty() || apellidoPaterno.length() > 50) {
            throw new NegocioException("El apellido paterno no debe estar en blanco y tampoco debe de pasar los 50 caracteres.");
        }
        if (apellidoMaterno == null || apellidoMaterno.isEmpty() || apellidoMaterno.length() > 50) {
            throw new NegocioException("El apellido materno no debe estar en blanco y tampoco debe de pasar los 50 caracteres.");
        }
        if (fechaNacimiento == null) {
            throw new NegocioException("La fecha de nacimiento no debe de estar en blanco");
        }
    }

    private ClienteDTO convertirClienteDTO(ClienteEntidad cliente) {
        if (cliente == null) {
            return null;
        }
        
        return new ClienteDTO(
        cliente.getId(),
        cliente.getNombres(),
        cliente.getApellidoPaterno(),
        cliente.getApellidoMaterno(),
        cliente.getFechaNacimiento(),
        cliente.isEstaBorrado());
    }

    private void validarCamposEnEditar(EditarClienteDTO cliente) throws NegocioException {
        int id = cliente.getId();
        String nombres = cliente.getNombres();
        String apellidoPaterno = cliente.getApellidoPaterno();
        String apellidoMaterno = cliente.getApellidoMaterno();
        LocalDateTime fechaNacimiento = cliente.getFechaNacimiento();
        
        if (id<1) {
            System.out.println("El ID recibido es incorrecto");
        }
        if (nombres == null || nombres.isEmpty() || nombres.length()>50) {
            throw new NegocioException("El nombre no debe estar en blanco y tampoco debe de pasar los 50 caracteres.");
        }
         if (apellidoPaterno == null || apellidoPaterno.isEmpty() || apellidoPaterno.length() > 50) {
            throw new NegocioException("El apellido paterno no debe estar en blanco y tampoco debe de pasar los 50 caracteres.");
        }
        if (apellidoMaterno == null || apellidoMaterno.isEmpty() || apellidoMaterno.length() > 50) {
            throw new NegocioException("El apellido materno no debe estar en blanco y tampoco debe de pasar los 50 caracteres.");
        }
        if (fechaNacimiento == null) {
            System.out.println("La fecha de nacimiento no debe de estar en blanco");
        }
    }

    private void existeElCliente(EditarClienteDTO cliente) throws PersistenciaException, NegocioException {
        ClienteEntidad alumnoBD = this.clienteDAO.buscarPorId(cliente.getId());
        if (alumnoBD == null) {
            throw new NegocioException("No se pudo obtener el cliente con los parametros ingresados");
        }
    }
}
