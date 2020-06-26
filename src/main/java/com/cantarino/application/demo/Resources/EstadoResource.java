package com.cantarino.application.demo.Resources;
import com.cantarino.application.demo.Models.Estado;
import com.cantarino.application.demo.Services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api/estado")
public class EstadoResource {

    @Autowired
    private EstadoService _estadoService;

    @GetMapping
    public ResponseEntity All(){
        return ResponseEntity.ok(_estadoService.Getall());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity save(@RequestBody Estado estado) {
        try
        {
            return  ResponseEntity.ok(_estadoService.Save(estado));
        }
        catch (RuntimeException runException)
        {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(runException.getMessage());
        }
    }
}

