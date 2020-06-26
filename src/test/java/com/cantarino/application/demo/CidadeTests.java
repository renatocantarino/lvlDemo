package com.cantarino.application.demo;

import com.cantarino.application.demo.Models.Cidade;
import com.cantarino.application.demo.Models.Estado;
import com.cantarino.application.demo.Services.CidadeService;
import com.cantarino.application.demo.Services.EstadoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CidadeTests {


    @Autowired
    private CidadeService _cidadeService;


    @Autowired
    private EstadoService _estadoService;

    @Test
    public void DEVE_INSERIR_CIDADE_SUCESSO() throws Exception {

       Estado estado = _estadoService.Save(
                new Estado("DISTRITO FEDERAL" , "DF")
        );

        Cidade cidade = new Cidade("BRASILIA" , estado);

        _cidadeService.Save(cidade);

        List<Cidade> _cidadeRetorno = _cidadeService.getByName(cidade.getNome());

        assertThat(_cidadeRetorno).isNotNull();
        assertThat(_cidadeRetorno.size()).isGreaterThan(0);

    }

    @Test
    public void NAO_DEVE_INSERIR_CIDADE_DUPLICADO_ERRO() throws Exception {

        Estado estado = _estadoService.Save(
                new Estado("DISTRITO FEDERAL" , "DF")
        );

        Cidade cidade = new Cidade("BRASILIA" , estado);

        try {
            _cidadeService.Save(cidade);
            _cidadeService.Save(cidade);
        }
        catch (RuntimeException runException)
        {
            assertThat(runException.getMessage()).isNotNull();
            assertThat(runException.getMessage()).isEqualTo("Cidade Já cadastrada");
        }
    }


    @Test
    public void DEVE_INSERIR_CIDADE_OUTRO_ESTADO_SUCESSO() throws Exception {

        Estado estado = _estadoService.Save(
                new Estado("DISTRITO FEDERAL" , "DF")
        );

        Estado estado2 = _estadoService.Save(
                new Estado("SAO PAULO" , "SP")
        );

        Cidade cidade = new Cidade("BRASILIA" , estado);
        Cidade cidade2 = new Cidade("BRASILIA" , estado2);


            _cidadeService.Save(cidade);
            _cidadeService.Save(cidade2);

            List<String> _cidadeRetorno = _cidadeService.getByState(estado2.getSigla());

            assertThat(_cidadeRetorno).isNotNull();
            assertThat(_cidadeRetorno.size()).isGreaterThan(0);
            assertThat(_cidadeRetorno.get(0)).isEqualTo(cidade2.getNome());
    }


    @Test
    public void DEVE_OBTER_CIDADE_POR_ESTADO_SUCESSO() throws Exception {

        Estado estado = _estadoService.Save(
                new Estado("DISTRITO FEDERAL" , "DF")
        );


        Cidade cidade = new Cidade("BRASILIA" , estado);
        Cidade cidade2 = new Cidade("TAGUATINGA" , estado);


        _cidadeService.Save(cidade);
        _cidadeService.Save(cidade2);

        List<String> _cidadeRetorno = _cidadeService.getByState(estado.getSigla());

        assertThat(_cidadeRetorno).isNotNull();
        assertThat(_cidadeRetorno.size()).isGreaterThan(0);
        assertThat(_cidadeRetorno.size()).isEqualTo(2);
    }
}
