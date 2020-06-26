package com.cantarino.application.demo.Repositories;


import com.cantarino.application.demo.Models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente getByNomeCompleto(String nomeCompleto);

    Cliente getById(Long Id);
}
