package com.geovani.algaweek.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geovani.algaweek.domain.model.Cliente;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByEmail(String email);

}
