/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.laboratorio.clinico;

import Dtos.EditarAnalisisDTO;
import Dtos.EditarClienteDTO;
import Dtos.GuardarAnalisisDTO;
import Dtos.GuardarClienteDTO;
import Negocio.AnalisisNegocio;
import Negocio.ClienteNegocio;
import Negocio.IAnalisisNegocio;
import Negocio.IClienteNegocio;
import Negocio.NegocioException;
import Persistencia.AnalisisDAO;
import Persistencia.ClienteDAO;
import Persistencia.ConexionBD;
import Persistencia.IAnalisisDAO;
import Persistencia.IClienteDAO;
import Persistencia.IConexionBD;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ang3lfco
 */
public class LaboratorioClinico {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        IConexionBD conexion = new ConexionBD();
        
        IClienteDAO cliente = new ClienteDAO(conexion); 
        GuardarClienteDTO guardarCliente = new GuardarClienteDTO("Rene","Figueroa","López", LocalDateTime.of(2002, Month.MARCH, 23,5, 34));
        EditarClienteDTO editarCliente = new EditarClienteDTO(2,"Rene2","Figueroa2","López2", LocalDateTime.of(2002, Month.MARCH, 23, 3, 34));
        
        
        IClienteNegocio clienteNegocio = new ClienteNegocio(cliente);
        
        IAnalisisDAO analisisDAO = new AnalisisDAO(conexion);
        GuardarAnalisisDTO gAnalisis = new GuardarAnalisisDTO(LocalDateTime.of(2025, Month.DECEMBER, 17, 4, 24), 2);
        EditarAnalisisDTO eAnalisis = new EditarAnalisisDTO(3,LocalDateTime.of(2003, Month.SEPTEMBER, 11, 9, 13), 2, false);
        
        IAnalisisNegocio analisisNegocio = new AnalisisNegocio(analisisDAO);
        
        try {
            System.out.println(analisisNegocio.buscarPorId(1).toString());
        } catch (NegocioException ex) {
            Logger.getLogger(LaboratorioClinico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
