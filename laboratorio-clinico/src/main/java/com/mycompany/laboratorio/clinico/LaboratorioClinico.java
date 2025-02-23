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
import Negocio.IPruebaNegocio;
import Negocio.PruebaNegocio;
import Persistencia.AnalisisDAO;
import Persistencia.CategoriaDAO;
import Persistencia.ClienteDAO;
import Persistencia.ConexionBD;
import Persistencia.IAnalisisDAO;
import Persistencia.ICategoriaDAO;
import Persistencia.IClienteDAO;
import Persistencia.IConexionBD;
import Persistencia.IPruebaDAO;
import Persistencia.PruebaDAO;
import Presentacion.frmIniciarSesion;


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
        
        frmIniciarSesion iniciar = new frmIniciarSesion(clienteNegocio, pruebaNegocio, categoriaNegocio, analisisNegocio);
        iniciar.setVisible(true);
    }
}