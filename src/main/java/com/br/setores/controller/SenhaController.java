package com.br.setores.controller;

import com.br.setores.entity.Senha;
import com.br.setores.service.SenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    public ResponseEntity<Senha> chamarSenha(@PathVariable Long idFila, @PathVariable Long idGuiche){
        Senha senha = senhaService.chamarSenha(idFila, idGuiche);
        sendMessage(senha);
        return ResponseEntity.ok().body(senha);
    }

    @GetMapping("/ultima-chamada/{idFila}")
    public ResponseEntity<Senha> ultimaSenhaChamada(@PathVariable Long idFila) {
        Senha senha = senhaService.ultimaSenhaChamada(idFila);
        return ResponseEntity.ok().body(senha);
    }

    @PutMapping("/finalizar-atendimento-senha/{idSenha}")
    public ResponseEntity<Senha> finalizarAtendimentoSenha(@PathVariable Long idSenha) {
        senhaService.finalizarAtendimentoSenha(idSenha);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    
    @MessageMapping("/setores")
    public void sendMessage(Senha senha){
        System.out.println(senha);
        messagingTemplate.convertAndSend("/senha-chamada",  senha);
    }

}
