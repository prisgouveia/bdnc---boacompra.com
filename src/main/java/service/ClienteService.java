/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.Dao;
import dao.DaoJpa;
import entidades.Cliente;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author NandaPC
 */
public class ClienteService {
 
    private Dao<Cliente> daoCliente = new DaoJpa<>();

    public boolean salvar(Cliente cliente) {
        System.out.println("DAO CLIENTE" + cliente);
        return daoCliente.salvar(cliente);
    }

    public Cliente logar(String email, String senha) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("email", email);
        try {
            Cliente cliente = daoCliente.consultaSimples("cliente.buscar", parametros);
            if (cliente != null && cliente.getSenha().equals(senha)) {
                System.out.println("DAO Cliente  " + cliente);
                return cliente;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Cliente buscarPorEmail(String email){
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("email", email);
        return daoCliente.consultaSimples("cliente.buscar", parametros);
    } 

}
