package com.br.setores.service;

import com.br.setores.entity.Guiche;
import com.br.setores.enums.GuicheStatus;
import com.br.setores.repository.GuicheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuicheService {

    @Autowired
    private GuicheRepository guicheRepository;

    public GuicheService(GuicheRepository guicheRepository){
        this.guicheRepository = guicheRepository;
    }

    public void criarGuiche(Guiche guiche){
        guicheRepository.save(guiche);
    }

    public List<Guiche> obterGuiches(){
        return guicheRepository.findAll();
    }

    public Guiche updateGuiche(Long id, boolean utilizado) {
        Optional<Guiche> guicheOptional = guicheRepository.findById(id);
        Guiche guiche = guicheOptional.get();
        guiche.setGuicheStatus(utilizado ? GuicheStatus.UTILIZADO : GuicheStatus.NAO_UTILIZADO);
        guicheRepository.save(guiche);
        return guiche;
    }
}
