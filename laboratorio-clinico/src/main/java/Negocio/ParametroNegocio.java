/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Dtos.GuardarParametroDTO;
import Dtos.ParametroDTO;
import Dtos.ParametroTablaDTO;
import Entidades.ParametroEntidad;
import Persistencia.IParametroDAO;
import Persistencia.PersistenciaException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ReneEzequiel23
 */
public class ParametroNegocio implements IParametroNegocio{
    private IParametroDAO parametroDAO;

    public ParametroNegocio(IParametroDAO parametroDAO) {
        this.parametroDAO = parametroDAO;
    }

    @Override
    public List<ParametroTablaDTO> buscarParametros() throws NegocioException {
        try {
            //this.ReglasDeNegocioParaBuscarAlumnos("Hola");
            List<ParametroEntidad> parametrosEntidadLista = this.parametroDAO.buscarParametros(); //2

            return this.convertirParametroTablaDTO(parametrosEntidadLista);
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    @Override
    public ParametroDTO guardar(GuardarParametroDTO parametro) throws NegocioException {
        try {
             this.validarInformacionGuardarParametro(parametro);

             ParametroEntidad parametroGuardado = this.parametroDAO.guardar(parametro);
             
            return this.convertirParametroDTO(parametroGuardado);
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    @Override
    public ParametroDTO buscarPorId(int id) throws NegocioException {
        try {
            if (id <= 0) {
                throw new NegocioException("El id recibido es incorrecto");
            }
            ParametroEntidad parametroBD = this.parametroDAO.buscarPorId(id);
            ParametroDTO parametroDTO = this.convertirParametroDTO(parametroBD);
            return parametroDTO;
        } catch (PersistenciaException ex) {
            System.out.println(ex.getMessage());
            throw new NegocioException(ex.getMessage());
        }
    }

    private List<ParametroTablaDTO> convertirParametroTablaDTO(List<ParametroEntidad> parametrosEntidadLista) {
        if (parametrosEntidadLista == null) {
            return null;
        }
        
        List<ParametroTablaDTO> parametrosDTO = new ArrayList<>();
        for (ParametroEntidad entidad : parametrosEntidadLista) {
            ParametroTablaDTO dato = new ParametroTablaDTO(
                    entidad.getId(),
                    entidad.getNombre());

            parametrosDTO.add(dato);
        }
        return parametrosDTO;
    }

    private void validarInformacionGuardarParametro(GuardarParametroDTO parametro) throws NegocioException {
        String nombre = parametro.getNombre();
        
        if (nombre == null || nombre.isEmpty() || nombre.length()>50) {
            throw new NegocioException("El nombre no debe estar en blanco y tampoco debe de pasar los 50 caracteres.");
        }
    }

    private ParametroDTO convertirParametroDTO(ParametroEntidad parametro) {
        if (parametro == null) {
            return null;
        }
        
        return new ParametroDTO(
        parametro.getId(),
        parametro.getNombre());
    }
    
    
}
