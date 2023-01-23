package com.geovani.algaweek.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geovani.algaweek.domain.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
