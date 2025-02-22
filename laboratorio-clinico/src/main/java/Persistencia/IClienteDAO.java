/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import Dtos.EditarClienteDTO;
import Dtos.GuardarClienteDTO;
import Entidades.ClienteEntidad;
import java.util.List;

/**
 * 
 * @author ReneEzequiel23
 */
public interface IClienteDAO {
    List<ClienteEntidad> buscarClientes() throws PersistenciaException;

    ClienteEntidad guardar(GuardarClienteDTO alumno) throws PersistenciaException;

    ClienteEntidad editar(EditarClienteDTO alumno) throws PersistenciaException;

    ClienteEntidad eliminar(int id) throws PersistenciaException;

    ClienteEntidad buscarPorId(int id) throws PersistenciaException;
}
