package com.mycompany.ventacine.service;

import com.mycompany.ventacine.model.Cine;
import com.mycompany.ventacine.model.Empleado;
import com.mycompany.ventacine.repository.CineRepository;
import com.mycompany.ventacine.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private CineRepository cineRepository;

    public List<Empleado> listarTodos() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> buscarPorId(Long id) {
        return empleadoRepository.findById(id);
    }

    public Empleado guardar(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Transactional
    public void eliminar(Long id) {
        // Remover el empleado de todos los cines que lo tengan asociado
        List<Cine> cines = cineRepository.findAll();
        for (Cine cine : cines) {
            cine.getEmpleados().removeIf(e -> e.getId().equals(id));
        }
        cineRepository.saveAll(cines);
        empleadoRepository.deleteById(id);
    }
}
