package com.br.setores.service;

import com.br.setores.entity.Fila;
import com.br.setores.repository.FilaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilaService {

    @Autowired
    private FilaRepository filaRepository;

    public FilaService(FilaRepository filaRepository){
        this.filaRepository = filaRepository;
    }

    public void criarFila(Fila fila) {
        fila.setFilaAtiva(true);
        filaRepository.save(fila);
    }

    public List<Fila> obterFilasAtivas() {
        return filaRepository.findByFilaAtiva(true);
    }

    public void deleteFila(Long id) {
        Optional<Fila> filaOptional = filaRepository.findById(id);
        Fila fila = filaOptional.get();
        fila.setFilaAtiva(false);
        filaRepository.save(fila);
    }

    public Fila updateFila(Fila fila) {
        Optional<Fila> filaOptional = filaRepository.findById(fila.getId());
        Fila filaSelecionada = filaOptional.get();
        filaSelecionada.setNome(fila.getNome());
        filaSelecionada.setSenhaMaxima(fila.getSenhaMaxima());
        filaSelecionada.setSenhaMinima(fila.getSenhaMinima());
        filaSelecionada.setTextoInformativo(fila.getTextoInformativo());
        filaRepository.save(filaSelecionada);
        return filaSelecionada;
    }
}
