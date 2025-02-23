/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.laboratorio.clinico;

import Negocio.ClienteNegocio;
import Negocio.IClienteNegocio;
import Persistencia.ClienteDAO;
import Persistencia.ConexionBD;
import Persistencia.IClienteDAO;
import Persistencia.IConexionBD;
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
        frmIniciarSesion iniciar = new frmIniciarSesion(clienteNegocio);
        iniciar.setVisible(true);
    }
}