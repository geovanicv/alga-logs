package com.geovani.algaweek.api.service;

import com.geovani.algaweek.api.exception.NegocioException;
import com.geovani.algaweek.domain.model.Cliente;
import com.geovani.algaweek.domain.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CatalogoClienteService {

    public CatalogoClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente) {

        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> clienteExistente.getEmail().equals(cliente.getEmail()));

        if(emailEmUso) {
            throw new NegocioException("Email já existe!");
        }

        return clienteRepository.save(cliente);
    }

    public void excluir(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}
