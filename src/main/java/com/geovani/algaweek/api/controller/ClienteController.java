package com.geovani.algaweek.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.geovani.algaweek.domain.model.Cliente;
import com.geovani.algaweek.domain.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
		
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/teste")
	public String testar() {
		return "teste";
	}
	
	@GetMapping
	public List<Cliente> listar() {
		
		List<Cliente> resultado = clienteRepository.findAll();
		
		resultado.forEach(n -> System.out.println(n.getNome()));
		
		return resultado;
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> bucar(@PathVariable Long clienteId) {
		return clienteRepository.findById(clienteId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente create(@Valid @RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	

	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> update(@PathVariable Long clienteId, @Valid @RequestBody Cliente cliente) {
		
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId);
		cliente = clienteRepository.save(cliente); 
		
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Cliente> exclude(@PathVariable Long clienteId) {
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		clienteRepository.deleteById(clienteId);
		
		return ResponseEntity.noContent().build();
	}

}


