package com.cantarino.application.demo.Resources;

import com.cantarino.application.demo.Dtos.ClienteDto;
import com.cantarino.application.demo.Services.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cliente")
public class ClienteResource {


    @Autowired
    private ClienteService _clienteService;

    @GetMapping
    public ResponseEntity All(){
        return   ResponseEntity.ok(_clienteService.FindAll());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity Save(@RequestBody ClienteDto cliente) {

            try
            {
                return  ResponseEntity.ok(_clienteService.Save(ClienteDto.transform(cliente)));
            }
            catch (RuntimeException runException)
            {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(runException.getMessage());
            }
    }


    @GetMapping(value = "/{nomeCliente}")
    public ResponseEntity GetByNome(@RequestParam String nomeCliente)
    {
        try
        {
            return  ResponseEntity.ok(_clienteService.GetByNomeCompleto(nomeCliente));
        }
        catch (RuntimeException runException)
        {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(runException.getMessage());
        }


    }


    @GetMapping(value = "/{idCliente}")
    public ResponseEntity GetById(@RequestParam Long idCliente)
    {
        try
        {
            return  ResponseEntity.ok(_clienteService.GetById(idCliente));
        }
        catch (RuntimeException runException)
        {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(runException.getMessage());
        }


    }


    @DeleteMapping
    public ResponseEntity Delete(@RequestParam Long idCliente)
    {
        try
        {
            _clienteService.Delete(idCliente);
            return  ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        catch (RuntimeException runException)
        {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(runException.getMessage());
        }


    }

    @PutMapping
    public ResponseEntity Update(@RequestParam Long idCliente , @RequestParam String nomeCliente)
    {
        try
        {
            return  ResponseEntity.ok(_clienteService.Update(idCliente , nomeCliente));
        }
        catch (RuntimeException runException)
        {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(runException.getMessage());
        }
    }
}
