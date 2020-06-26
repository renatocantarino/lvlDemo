package com.cantarino.application.demo.Repositories;

import com.cantarino.application.demo.Models.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CidadeRepository extends JpaRepository<Cidade, Long>
{
    @Query("SELECT c.nome FROM Cidade c INNER JOIN Estado e ON c.estado.id = e.id WHERE e.sigla = UPPER(:siglaEstado) ")
    List<String> getAllByEstado(String siglaEstado);


    List<Cidade> getCidadeByNomeIgnoreCase(String nomeCidade);

}
