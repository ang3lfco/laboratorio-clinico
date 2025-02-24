/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Dtos.AnalisisTablaDTO;
import Dtos.ReporteDTO;
import Entidades.AnalisisEntidad;
import Entidades.ReporteEntidad;
import Persistencia.IReporteDAO;
import Persistencia.PersistenciaException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ReneEzequiel23
 */
public class ReporteNegocio implements IReporteNegocio{
    private IReporteDAO reporte;

    public ReporteNegocio(IReporteDAO reporte) {
        this.reporte = reporte;
    }
    
    @Override
    public List<ReporteDTO> reporteDatos(Date inicio, Date fin) throws NegocioException {
    try {
            List<ReporteEntidad> reporteLista = this.reporte.crearReporte(inicio, fin);
            System.out.println("ANTES");
            return this.convertirReporteTablaDTO(reporteLista);
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    private List<ReporteDTO> convertirReporteTablaDTO(List<ReporteEntidad> reporteLista) {
    if (reporteLista == null) {
            return null;
        }
        
        List<ReporteDTO> lista = new ArrayList<>();
        
        for (ReporteEntidad entidad : reporteLista) {
            ReporteDTO dato = new ReporteDTO(
                    entidad.getIdCliente(),
                    entidad.getCliente(),
                    entidad.getPrueba(),
                    entidad.getAnalisis(),
                    entidad.getFechaHora());
            
            lista.add(dato);
        }
        
        return lista;
    }
    
}
