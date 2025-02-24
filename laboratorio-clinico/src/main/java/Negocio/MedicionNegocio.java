/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Dtos.GuardarMedicionDTO;
import Dtos.MedicionDTO;
import Entidades.MedicionEntidad;
import Persistencia.ICategoriaDAO;
import Persistencia.IMedicionDAO;
import Persistencia.PersistenciaException;

/**
 *
 * @author ang3lfco
 */
public class MedicionNegocio implements IMedicionNegocio{
    private IMedicionDAO medicionDAO;
    
    public MedicionNegocio(IMedicionDAO medicionDAO){
        this.medicionDAO = medicionDAO;
    }
    
    @Override
    public MedicionDTO guardar(GuardarMedicionDTO medicionDTO) throws NegocioException{
        try{
            MedicionEntidad medicion = medicionDAO.guardar(medicionDTO);
            return new MedicionDTO(medicion.getId(), medicion.getIdPrueba(), medicion.getIdParametro());
        }
        catch(PersistenciaException e){
            throw new NegocioException(e.getMessage());
        }
    }
}
