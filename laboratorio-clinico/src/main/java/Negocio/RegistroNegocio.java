/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Dtos.GuardarRegistroDTO;
import Dtos.MedicionDTO;
import Dtos.RegistroDTO;
import Entidades.RegistroEntidad;
import Persistencia.IRegistroDAO;
import Persistencia.PersistenciaException;

/**
 *
 * @author ang3lfco
 */
public class RegistroNegocio implements IRegistroNegocio{
    private IRegistroDAO registroDAO;
    
    public RegistroNegocio(IRegistroDAO registroDAO){
        this.registroDAO = registroDAO;
    }
    
    @Override
    public RegistroDTO guardar(GuardarRegistroDTO registroDTO) throws NegocioException{
        try{
            RegistroEntidad registro = registroDAO.guardar(registroDTO);
            return new RegistroDTO(registro.getId(), registro.getIdAnalisis(), registro.getIdPrueba());
        }
        catch(PersistenciaException e){
            throw new NegocioException(e.getMessage());
        }
    }
}
