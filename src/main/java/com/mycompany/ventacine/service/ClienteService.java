package com.mycompany.ventacine.service;

import com.mycompany.ventacine.model.Cliente;
import com.mycompany.ventacine.model.ClienteVIP;
import com.mycompany.ventacine.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio para la gestión de clientes (comunes y VIP).
 *
 * @author USUARIO
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public List<ClienteVIP> listarVIP() {
        return clienteRepository.findAll().stream()
                .filter(c -> c instanceof ClienteVIP)
                .map(c -> (ClienteVIP) c)
                .collect(Collectors.toList());
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }
}
