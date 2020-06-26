package com.cantarino.application.demo;

import com.cantarino.application.demo.Models.Estado;
import com.cantarino.application.demo.Services.EstadoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EstadoTests {

    @Autowired
    private EstadoService _estadoService;

    @Test
    public void DEVE_INSERIR_ESTADO_SUCESSO() throws Exception {

        Estado estado = new Estado("DISTRITO FEDERAL" , "DF");
        _estadoService.Save(estado);

        Optional<Estado> _estadoRetorno = _estadoService.GetBySigla(estado.getSigla());

        assertThat(_estadoRetorno.get()).isNotNull();
        assertThat(_estadoRetorno.get().getSigla()).isEqualTo(estado.getSigla());
    }

    @Test
    public void NAO_DEVE_INSERIR_ESTADO_DUPLICADO_ERRO() throws Exception {

        Estado estado = new Estado("DISTRITO FEDERAL" , "DF");
        Estado estado2 = new Estado("DISTRITO FEDERAL" , "DF");

        try {
            _estadoService.Save(estado);
            _estadoService.Save(estado2);
        }
        catch (RuntimeException runException)
        {
            assertThat(runException.getMessage()).isNotNull();
            assertThat(runException.getMessage()).isEqualTo("Estado já cadastrado");
        }
    }
}
