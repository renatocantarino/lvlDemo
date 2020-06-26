package com.cantarino.application.demo.Services;

import com.cantarino.application.demo.Models.Estado;
import com.cantarino.application.demo.Repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository _estadoRepository;

    public Estado Save(Estado estado)
    {
        Validar(estado);
        return _estadoRepository.saveAndFlush(estado);
    }

    public List<Estado> Getall()
    {
        return _estadoRepository.findAll();
    }

    public Optional<Estado> GetBySigla(String sigla)
    {
        return _estadoRepository.getEstadoBySiglaIgnoreCase(sigla);
    }

    private void Validar(Estado estado)
    {
        Optional<Estado> estadoOptional = _estadoRepository.getEstadoBySiglaIgnoreCase(estado.getSigla());
        if(estadoOptional.isPresent())
            throw new RuntimeException("Estado já cadastrado");
    }

    public void  NaoCadastrado(Estado estado)
    {
        Optional<Estado> estadoOptional = _estadoRepository.getEstadoBySiglaIgnoreCase(estado.getSigla());
        if(!estadoOptional.isPresent())
            throw new RuntimeException("Estado não cadastrado");

    }


}

