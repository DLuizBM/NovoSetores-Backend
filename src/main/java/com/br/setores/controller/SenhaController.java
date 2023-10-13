package com.br.setores.controller;

import com.br.setores.entity.Senha;
import com.br.setores.service.SenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/senha")
public class SenhaController {

    @Autowired
    private SenhaService senhaService;

    private final SimpMessagingTemplate messagingTemplate;

    public SenhaController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping("/emitir/{idFila}")
    public ResponseEntity<Senha> emitirSenha(@PathVariable Long idFila){
        Senha senha = senhaService.emitirSenha(idFila);
        return ResponseEntity.ok().body(senha);
    }

    @PostMapping("/chamar/{idFila}/{idGuiche}")
    public void chamarSenha(@PathVariable Long idFila, @PathVariable Long idGuiche){
        Senha senha = senhaService.chamarSenha(idFila, idGuiche);
        messagingTemplate.convertAndSend("/setores/painel", senha);
    }

}
