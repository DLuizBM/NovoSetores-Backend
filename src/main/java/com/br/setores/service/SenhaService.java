package com.br.setores.service;

import com.br.setores.entity.Fila;
import com.br.setores.entity.Guiche;
import com.br.setores.entity.Senha;
import com.br.setores.enums.SenhaStatus;
import com.br.setores.repository.FilaRepository;
import com.br.setores.repository.GuicheRepository;
import com.br.setores.repository.SenhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class SenhaService {

    @Autowired
    private SenhaRepository senhaRepository;

    @Autowired
    private GuicheRepository guicheRepository;

    @Autowired
    private FilaRepository filaRepository;

    public SenhaService(SenhaRepository senhaRepository){
        this.senhaRepository = senhaRepository;
    }

    public Senha emitirSenha(Long idFila) {
        Optional<Fila> filaOptional = filaRepository.findById(idFila);
        Fila fila = filaOptional.get();
        Senha senha = new Senha();

        if(fila.getContadorSenhas() < fila.getSenhaMaxima()){
            if(fila.getContadorSenhas() == 0) {
                return configurarSenha(senha, configurarFila(fila, fila.getSenhaMinima()));
            }
            return configurarSenha(senha, configurarFila(fila, fila.getContadorSenhas() + 1));
        }else {
            System.out.println("Não é possível emitir mais senhas");
            return null;
        }
    }

    private Fila configurarFila(Fila fila, Long contador) {
        fila.setContadorSenhas(contador);
        filaRepository.save(fila);
        return fila;
    }

    private Senha configurarSenha(Senha senha, Fila fila) {
        senha.setNumeroSenha(fila.getContadorSenhas());
        senha.setFila(fila);
        senha.setSenhaStatus(SenhaStatus.AGUARDANDO_ATENDIMENTO);
        senha.setDataEmissao(new Date());
        senhaRepository.save(senha);
        return senha;
    }

    public Senha chamarSenha(Long idFila, Long idGuiche) {
        Optional<Guiche> guicheOptional = guicheRepository.findById(idGuiche);
        Guiche guiche = guicheOptional.get();
        try {
            Senha senha = senhaRepository.findByFirstDataEmissao(idFila);
            senha.setGuiche(guiche);
            senha.setSenhaStatus(SenhaStatus.EM_ATENDIMENTO);
            senhaRepository.save(senha);
            return senha;
        }catch (NullPointerException e) {
            throw new NullPointerException("Não existem senhas para serem chamadas para essa fila.");
        }
    }

    public Senha ultimaSenhaChamada(Long idFila) {
        return senhaRepository.findByLastCalled(idFila);
    }

    public void finalizarAtendimentoSenha(Long idSenha) {
        Optional<Senha> senhaOptional = senhaRepository.findById(idSenha);
        Senha senha = senhaOptional.get();
        senha.setSenhaStatus(SenhaStatus.ATENDIDA);
        senhaRepository.save(senha);
    }

}
