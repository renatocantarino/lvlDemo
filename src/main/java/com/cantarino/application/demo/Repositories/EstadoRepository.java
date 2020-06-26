package com.cantarino.application.demo.Repositories;

import com.cantarino.application.demo.Models.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
    Optional<Estado> getEstadoBySiglaIgnoreCase(String sigla);
}