package com.mycompany.ventacine.service;

import com.mycompany.ventacine.model.Entrada;
import com.mycompany.ventacine.repository.EntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntradaService {

    @Autowired
    private EntradaRepository entradaRepository;

    public List<Entrada> listarTodas() {
        return entradaRepository.findAll();
    }

    public Optional<Entrada> buscarPorId(Long id) {
        return entradaRepository.findById(id);
    }

    public Entrada guardar(Entrada entrada) {
        return entradaRepository.save(entrada);
    }

    public void eliminar(Long id) {
        entradaRepository.deleteById(id);
    }
}
