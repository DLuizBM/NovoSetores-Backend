package com.br.setores.controller;

import com.br.setores.entity.Fila;
import com.br.setores.service.FilaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fila")
public class FilaController {

    @Autowired
    private FilaService filaService;

    @GetMapping
    public ResponseEntity<List<Fila>> obterFilasAtivas(){
        List<Fila> filas = filaService.obterFilasAtivas();
        return ResponseEntity.ok().body(filas);
    }

    @PostMapping
    public ResponseEntity<Object> criarFila(@RequestBody Fila fila){
        filaService.criarFila(fila);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Fila> atualizarFila(@RequestBody Fila fila) {
        Fila filaAtualizada = filaService.updateFila(fila);
        return ResponseEntity.ok().body(filaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarFila(@PathVariable Long id) {
        filaService.deleteFila(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
