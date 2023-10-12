package com.br.setores.controller;

import com.br.setores.entity.Guiche;
import com.br.setores.service.GuicheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guiche")
public class GuicheController {

    @Autowired
    private GuicheService guicheService;

    @PostMapping
    public ResponseEntity<Object> criarGuiche(@RequestBody Guiche guiche){
        guicheService.criarGuiche(guiche);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Guiche>> obterGuiches(){
        List<Guiche> guiches = guicheService.obterGuiches();
        return ResponseEntity.ok().body(guiches);
    }

    @PutMapping("/{id}/{utilizado}")
    public ResponseEntity<Object> updateGuiche(@PathVariable Long id, @PathVariable Boolean utilizado){
        Guiche guiche = guicheService.updateGuiche(id, utilizado);
        return ResponseEntity.ok().body(guiche);
    }

}
