package com.mycompany.ventacine.service;

import com.mycompany.ventacine.model.Insumo;
import com.mycompany.ventacine.repository.InsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InsumoService {

    @Autowired
    private InsumoRepository insumoRepository;

    public List<Insumo> listarTodos() {
        return insumoRepository.findAll();
    }

    public Optional<Insumo> buscarPorId(Long id) {
        return insumoRepository.findById(id);
    }

    public Insumo guardar(Insumo insumo) {
        return insumoRepository.save(insumo);
    }

    public void eliminar(Long id) {
        insumoRepository.deleteById(id);
    }
}
