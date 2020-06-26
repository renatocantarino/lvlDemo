package com.cantarino.application.demo;

import com.cantarino.application.demo.Models.Cidade;
import com.cantarino.application.demo.Models.Cliente;
import com.cantarino.application.demo.Models.Enums.Sexo;
import com.cantarino.application.demo.Models.Estado;
import com.cantarino.application.demo.Services.CidadeService;
import com.cantarino.application.demo.Services.ClienteService;
import com.cantarino.application.demo.Services.EstadoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ClienteTest {

    @Autowired
    private ClienteService _clienteService;

    @Autowired
    private EstadoService _estadoService;

    @Autowired
    private CidadeService _cidadeService;


    @Test
    public void DEVE_INSERIR_CLIENTE_SUCESSO() throws Exception {

        Estado estado = _estadoService.Save(new Estado("DISTRITO FEDERAL" , "DF"));
        Cidade cidade = _cidadeService.Save(new Cidade("BRASILIA" , estado));

        Cliente cliente = new Cliente("Renato" ,  Sexo.M , LocalDate.of(1985, 07,29) );
        cliente.setCidade(cidade);

        _clienteService.Save(cliente);

        Cliente clienteRetorno = _clienteService.GetByNomeCompleto(cliente.getNomeCompleto());


        assertThat(clienteRetorno).isNotNull();
        assertThat(clienteRetorno.getNomeCompleto()).isEqualTo(cliente.getNomeCompleto());
        assertThat(clienteRetorno.getDataNascimento()).isEqualTo(cliente.getDataNascimento());
        assertThat(clienteRetorno.getIdade()).isEqualTo(cliente.getIdade());
    }

    @Test
    public void NAO_DEVE_INSERIR_CLIENTE_DATA_NASCIMENTO_MAIOR_HOJE_ERRO() throws Exception {

        Estado estado = _estadoService.Save(new Estado("DISTRITO FEDERAL" , "DF"));
        Cidade cidade = _cidadeService.Save(new Cidade("BRASILIA" , estado));

        Cliente cliente = new Cliente("Renato" ,  Sexo.M , LocalDate.of(2021, 07,29) );
        cliente.setCidade(cidade);

        try {

            _clienteService.Save(cliente);

        }
        catch (RuntimeException runException)
        {
            assertThat(runException.getMessage()).isNotNull();
            assertThat(runException.getMessage()).isEqualTo("Data de nascimento maior que hoje");
        }
    }

    @Test
    public void DEVE_INSERIR_ATUALIZAR_CLIENTE_SUCESSO() throws Exception {

        Estado estado = _estadoService.Save(new Estado("DISTRITO FEDERAL" , "DF"));
        Cidade cidade = _cidadeService.Save(new Cidade("BRASILIA" , estado));

        Cliente cliente = new Cliente("Renato" ,  Sexo.M , LocalDate.of(1985, 07,29) );
        cliente.setCidade(cidade);

       Cliente inserted =  _clienteService.Save(cliente);

        Cliente clienteRetorno = _clienteService.GetByNomeCompleto(inserted.getNomeCompleto());

        clienteRetorno.setNomeCompleto("NOVO NOME");

        _clienteService.Update(clienteRetorno.getId(), clienteRetorno.getNomeCompleto());


        assertThat(clienteRetorno).isNotNull();
        assertThat(clienteRetorno.getNomeCompleto()).isEqualTo("NOVO NOME");
    }

    @Test
    public void DEVE_OBTER_CLIENTE_POR_NOME_SUCESSO() throws Exception {

        Estado estado = _estadoService.Save(new Estado("DISTRITO FEDERAL" , "DF"));
        Cidade cidade = _cidadeService.Save(new Cidade("BRASILIA" , estado));

        Cliente cliente = new Cliente("Renato" ,  Sexo.M , LocalDate.of(1985, 07,29) );
        cliente.setCidade(cidade);

        Cliente inserted =  _clienteService.Save(cliente);

        Cliente clienteRetorno = _clienteService.GetByNomeCompleto(inserted.getNomeCompleto());

        assertThat(clienteRetorno).isNotNull();
        assertThat(clienteRetorno.getNomeCompleto()).isEqualTo( inserted.getNomeCompleto());
    }

    @Test
    public void DEVE_DELETAR_CLIENTE_POR_NOME_SUCESSO() throws Exception {

        Estado estado = _estadoService.Save(new Estado("DISTRITO FEDERAL" , "DF"));
        Cidade cidade = _cidadeService.Save(new Cidade("BRASILIA" , estado));

        Cliente cliente = new Cliente("Renato" ,  Sexo.M , LocalDate.of(1985, 07,29) );
        cliente.setCidade(cidade);

        Cliente inserted =  _clienteService.Save(cliente);

        _clienteService.Delete(inserted.getId());

        Cliente clienteRetorno = _clienteService.GetByNomeCompleto(inserted.getNomeCompleto());

        assertThat(clienteRetorno).isNull();

    }
}
