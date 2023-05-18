package com.miportfolio.mbdev.Repository;

import com.miportfolio.mbdev.Entity.Project;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RProject extends JpaRepository<Project, Integer>{
    public Optional<Project> findByTitulo(String titulo);
    public boolean existsByTitulo(String descripcion);
}
