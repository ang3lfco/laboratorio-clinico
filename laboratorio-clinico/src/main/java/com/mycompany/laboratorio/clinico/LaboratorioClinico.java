/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.laboratorio.clinico;

import Dtos.ReporteDTO;
import Entidades.ReporteEntidad;
import Negocio.AnalisisNegocio;
import Negocio.CategoriaNegocio;
import Negocio.ClienteNegocio;
import Negocio.EmpleadoNegocio;
import Negocio.IAnalisisNegocio;
import Negocio.ICategoriaNegocio;
import Negocio.IClienteNegocio;
import Negocio.IEmpleadoNegocio;
import Negocio.IMedicionNegocio;
import Negocio.IParametroNegocio;
import Negocio.IPruebaNegocio;
import Negocio.IRegistroNegocio;
import Negocio.IReporteNegocio;
import Negocio.IResultadoNegocio;
import Negocio.MedicionNegocio;
import Negocio.NegocioException;
import Negocio.ParametroNegocio;
import Negocio.PruebaNegocio;
import Negocio.RegistroNegocio;
import Negocio.Reporte;
import Negocio.ReporteNegocio;
import Negocio.ResultadoNegocio;
import Persistencia.AnalisisDAO;
import Persistencia.CategoriaDAO;
import Persistencia.ClienteDAO;
import Persistencia.ConexionBD;
import Persistencia.EmpleadoDAO;
import Persistencia.IAnalisisDAO;
import Persistencia.ICategoriaDAO;
import Persistencia.IClienteDAO;
import Persistencia.IConexionBD;
import Persistencia.IEmpleadoDAO;
import Persistencia.IMedicionDAO;
import Persistencia.IParametroDAO;
import Persistencia.IPruebaDAO;
import Persistencia.IRegistroDAO;
import Persistencia.IReporteDAO;
import Persistencia.IResultadoDAO;
import Persistencia.MedicionDAO;
import Persistencia.ParametroDAO;
import Persistencia.PersistenciaException;
import Persistencia.PruebaDAO;
import Persistencia.RegistroDAO;
import Persistencia.ReporteDAO;
import Persistencia.ResultadoDAO;
import Presentacion.frmIniciarSesion;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author ang3lfco
 */
public class LaboratorioClinico {
    public static void main(String[] args) {
        IConexionBD conexion = new ConexionBD();
        
        IReporteDAO reporte = new ReporteDAO(conexion);      
        IReporteNegocio reporteNegocio = new ReporteNegocio(reporte);
        
        IClienteDAO clienteDAO = new ClienteDAO(conexion);
        IClienteNegocio clienteNegocio = new ClienteNegocio(clienteDAO);
        
        IPruebaDAO pruebaDAO = new PruebaDAO(conexion);
        IPruebaNegocio pruebaNegocio = new PruebaNegocio(pruebaDAO);
        
        ICategoriaDAO categoriaDAO = new CategoriaDAO(conexion);
        ICategoriaNegocio categoriaNegocio = new CategoriaNegocio(categoriaDAO);
        
        IAnalisisDAO analisisDAO = new AnalisisDAO(conexion);
        IAnalisisNegocio analisisNegocio = new AnalisisNegocio(analisisDAO);
        
        IParametroDAO parametroDAO = new ParametroDAO(conexion);
        IParametroNegocio parametroNegocio = new ParametroNegocio(parametroDAO);
        
        IMedicionDAO medicionDAO = new MedicionDAO(conexion);
        IMedicionNegocio medicionNegocio = new MedicionNegocio(medicionDAO);
        
        IRegistroDAO registroDAO = new RegistroDAO(conexion);
        IRegistroNegocio registroNegocio = new RegistroNegocio(registroDAO);
        
        IEmpleadoDAO empleadoDAO = new EmpleadoDAO(conexion);
        IEmpleadoNegocio empleadoNegocio = new EmpleadoNegocio(empleadoDAO);
        
        IResultadoDAO resultadoDAO = new ResultadoDAO(conexion);
        IResultadoNegocio resultadoNegocio = new ResultadoNegocio(resultadoDAO);
        
        frmIniciarSesion iniciar = new frmIniciarSesion(clienteNegocio, pruebaNegocio, categoriaNegocio, analisisNegocio, parametroNegocio, medicionNegocio, registroNegocio,empleadoNegocio, reporteNegocio, resultadoNegocio);
        iniciar.setVisible(true);


//        Reporte reporte = new Reporte();
//        reporte.Reporte("fecha1", "fecha2", new DefaultTableModel());
        
    }
}