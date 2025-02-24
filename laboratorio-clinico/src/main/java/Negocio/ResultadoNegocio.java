/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Dtos.ResultadoDTO;
import Entidades.ResultadoEntidad;
import Persistencia.IResultadoDAO;
import Persistencia.PersistenciaException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ang3lfco
 */
public class ResultadoNegocio implements IResultadoNegocio {
    private IResultadoDAO resultadoDAO;
    
    public ResultadoNegocio(IResultadoDAO resultadoDAO) {
        this.resultadoDAO = resultadoDAO;
    }
    
    @Override
    public List<ResultadoDTO> buscarResultados() throws NegocioException {
        try {
            List<ResultadoEntidad> resultados = resultadoDAO.buscarResultados();
            return convertirDTO(resultados);
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
    
    @Override
    public ResultadoDTO guardar(ResultadoDTO resultadoDTO) throws NegocioException {
        try {
            ResultadoEntidad resultado = resultadoDAO.guardar(resultadoDTO);
            return new ResultadoDTO(resultado.getId(), resultado.getIdAnalisis(), resultado.getIdParametro(), resultado.getValor());
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
    
    @Override
    public ResultadoDTO buscarId(int id) throws NegocioException {
        try {
            ResultadoEntidad r = resultadoDAO.buscarId(id);
            return new ResultadoDTO(r.getId(), r.getIdAnalisis(), r.getIdParametro(), r.getValor());
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
    
    private List<ResultadoDTO> convertirDTO(List<ResultadoEntidad> resultados) {
        List<ResultadoDTO> listaDTO = new ArrayList<>();
        for (ResultadoEntidad r : resultados) {
            listaDTO.add(new ResultadoDTO(r.getId(), r.getIdAnalisis(), r.getIdParametro(), r.getValor()));
        }
        return listaDTO;
    }
}