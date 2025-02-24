/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Dtos.ClienteDTO;
import Dtos.EmpleadoDTO;
import Entidades.ClienteEntidad;
import Entidades.EmpleadoEntidad;
import Persistencia.IEmpleadoDAO;
import Persistencia.PersistenciaException;

/**
 *
 * @author ReneEzequiel23
 */
public class EmpleadoNegocio implements IEmpleadoNegocio {
    private IEmpleadoDAO empleadoDAO;

    public EmpleadoNegocio(IEmpleadoDAO empleadoDAO) {
        this.empleadoDAO = empleadoDAO;
    }
    
    @Override
    public EmpleadoDTO logIn(String usuario, String pass) throws NegocioException {
        try {
            if (usuario == null || usuario.isEmpty() || usuario.length() > 10) {
                throw new NegocioException("La contraseña no puede estar en blanco y tampoco puede pasar los 10 caracteres");
            }
            if (pass == null || pass.isEmpty() || pass.length() > 10) {
                throw new NegocioException("El usuario no puede estar en blanco y tampoco puede pasar los 10 caracteres");
            }
            EmpleadoEntidad empleadoBD = this.empleadoDAO.logIn(usuario, pass);
            
            EmpleadoDTO empleadoDTO = this.convertirEmpeladoDTO(empleadoBD);
            return empleadoDTO;
        } catch (PersistenciaException ex) {
            System.out.println(ex.getMessage());
            throw new NegocioException(ex.getMessage());
        }
        
    }
    
    private EmpleadoDTO convertirEmpeladoDTO(EmpleadoEntidad empleado) {
        if (empleado == null) {
            return null;
        }
        
        return new EmpleadoDTO(
        empleado.getId(),
        empleado.getUsuario(),
        empleado.getPass(),
        empleado.getTipo());
    }
}
