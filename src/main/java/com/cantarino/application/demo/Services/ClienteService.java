package com.cantarino.application.demo.Services;

import com.cantarino.application.demo.Models.Cidade;
import com.cantarino.application.demo.Models.Cliente;
import com.cantarino.application.demo.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository _clienteRepository;

    @Autowired
    private CidadeService _cidadeService;

    public Cliente Save(Cliente cliente)
    {
        Validar(cliente);
        return _clienteRepository.saveAndFlush(cliente);
    }

    public List<Cliente> FindAll()
    {
        return _clienteRepository.findAll(Sort.by("nomeCompleto"));
    }

    public Cliente GetByNomeCompleto(String nomeCompleto)
    {
        Cliente cliente = _clienteRepository.getByNomeCompleto(nomeCompleto);

        if(cliente == null)
            throw  new RuntimeException("Cliente não encontrado");

        return cliente;
    }

    public Cliente Update(Long id, String nomecliente)
    {
        Cliente _cliente = _clienteRepository.getById(id);
        if(_cliente == null)
            throw  new RuntimeException("Cliente não encontrado");

        _cliente.setNomeCompleto(nomecliente);

        return _clienteRepository.saveAndFlush(_cliente);
    }

    public Cliente GetById(Long id)
    {
        Cliente cliente = _clienteRepository.getById(id);
        if(cliente == null)
            throw  new RuntimeException("Cliente nao encontrado");

        return cliente;
    }

    public void Delete(Long id)
    {
        Cliente cliente = _clienteRepository.getById(id);
        if(cliente == null)
            throw  new RuntimeException("Cliente nao encontrado");

        _clienteRepository.delete(cliente);
    }

    private Cliente Validar(Cliente cliente)
    {
        final List<Cidade> byName = _cidadeService.getByName(cliente.getCidade().getNome());

        if (byName.size() < 1)
            throw new RuntimeException("Cidade não cadastrada");

        if(cliente.getDataNascimento().isAfter(LocalDate.now()))
            throw new RuntimeException("Data de nascimento maior que hoje");

        Cidade cidade = byName.get(0);
        cliente.setCidade(cidade);
        return cliente;
    }
}
