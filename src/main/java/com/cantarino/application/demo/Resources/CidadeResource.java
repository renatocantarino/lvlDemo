package com.cantarino.application.demo.Resources;


import com.cantarino.application.demo.Dtos.CidadeDto;
import com.cantarino.application.demo.Dtos.filters.CityByNameFilter;
import com.cantarino.application.demo.Models.Cidade;
import com.cantarino.application.demo.Models.Estado;
import com.cantarino.application.demo.Services.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cidade")
public class CidadeResource {

    @Autowired
    private CidadeService _cidadeService;

    @GetMapping
    public  ResponseEntity<List<CidadeDto>> All(){
        return ResponseEntity.ok(CidadeDto.Transform(_cidadeService.getAll()));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Cidade save( @RequestParam String sigla,   @RequestBody Cidade cidade) {

        cidade.setEstado(new Estado(sigla));
        return _cidadeService.Save(cidade);
    }

    @GetMapping(value = "/{estado}")
    public ResponseEntity getCityByState (@RequestParam String siglaEstado ){
        return ResponseEntity.ok(_cidadeService.getByState(siglaEstado));
    }

    @PostMapping(value = "/{nome}")
    public ResponseEntity<List<CidadeDto>> getCityByName(@RequestBody CityByNameFilter dto){
        return ResponseEntity.ok(CidadeDto.Transform(_cidadeService.getByName(dto.getNome())));
    }
}
