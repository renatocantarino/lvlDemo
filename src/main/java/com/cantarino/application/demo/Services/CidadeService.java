package com.cantarino.application.demo.Services;

import com.cantarino.application.demo.Models.Cidade;
import com.cantarino.application.demo.Models.Estado;
import com.cantarino.application.demo.Repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository _cidadeRepository;

    @Autowired
    private EstadoService _estadoService;

    @PersistenceContext
    private EntityManager em;

    public Cidade Save(Cidade cidade)
    {
        Validar(cidade);
        Optional<Estado> estado = _estadoService.GetBySigla(cidade.getEstado().getSigla());
        cidade.setEstado(estado.get());
        return _cidadeRepository.saveAndFlush(cidade);
    }

    private void Validar(Cidade cidade) {
        _estadoService.NaoCadastrado(cidade.getEstado());
        CidadeJaCadastrada(cidade);
    }

    public List<String> getByState(String siglaEstado)
    {
        Optional<Estado> estadoOptional = _estadoService.GetBySigla(siglaEstado);
        if(!estadoOptional.isPresent())
            throw new RuntimeException("Estado não cadastrado");

       return _cidadeRepository.getAllByEstado(siglaEstado);
    }

    public List<Cidade> getByName(String nomeCidade)
    {
        return _cidadeRepository.getCidadeByNomeIgnoreCase(nomeCidade);
    }

    public List<Cidade> getAll()
    {
        return _cidadeRepository.findAll();
    }

    public void CidadeJaCadastrada(Cidade cidade)
    {
        int retorno = em.createQuery(
                "SELECT c.nome FROM Cidade c INNER JOIN Estado e ON c.estado.id = e.id WHERE c.nome = UPPER(:cidade) and e.sigla = UPPER(:siglaEstado)")
                .setParameter("cidade", "%" +  cidade.getNome() + "%")
                .setParameter("siglaEstado", cidade.getEstado().getSigla())
                .getFirstResult();

        if (retorno > 0)
            throw new RuntimeException("Cidade Já cadastrada");

    }


}
