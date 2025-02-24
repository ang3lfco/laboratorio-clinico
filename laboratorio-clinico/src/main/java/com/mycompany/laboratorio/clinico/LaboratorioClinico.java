/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.laboratorio.clinico;

import Negocio.AnalisisNegocio;
import Negocio.CategoriaNegocio;
import Negocio.ClienteNegocio;
import Negocio.IAnalisisNegocio;
import Negocio.ICategoriaNegocio;
import Negocio.IClienteNegocio;
import Negocio.IMedicionNegocio;
import Negocio.IParametroNegocio;
import Negocio.IPruebaNegocio;
import Negocio.IRegistroNegocio;
import Negocio.MedicionNegocio;
import Negocio.ParametroNegocio;
import Negocio.PruebaNegocio;
import Negocio.RegistroNegocio;
import Negocio.Reporte;
import Persistencia.AnalisisDAO;
import Persistencia.CategoriaDAO;
import Persistencia.ClienteDAO;
import Persistencia.ConexionBD;
import Persistencia.IAnalisisDAO;
import Persistencia.ICategoriaDAO;
import Persistencia.IClienteDAO;
import Persistencia.IConexionBD;
import Persistencia.IMedicionDAO;
import Persistencia.IParametroDAO;
import Persistencia.IPruebaDAO;
import Persistencia.IRegistroDAO;
import Persistencia.MedicionDAO;
import Persistencia.ParametroDAO;
import Persistencia.PruebaDAO;
import Persistencia.RegistroDAO;
import Presentacion.frmIniciarSesion;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author ang3lfco
 */
public class LaboratorioClinico {
    public static void main(String[] args) {
        IConexionBD conexion = new ConexionBD();
        
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
        
        frmIniciarSesion iniciar = new frmIniciarSesion(clienteNegocio, pruebaNegocio, categoriaNegocio, analisisNegocio, parametroNegocio, medicionNegocio, registroNegocio);
        iniciar.setVisible(true);
        
        
//        Reporte reporte = new Reporte();
//        reporte.Reporte("fecha1", "fecha2", new DefaultTableModel());
    }
}