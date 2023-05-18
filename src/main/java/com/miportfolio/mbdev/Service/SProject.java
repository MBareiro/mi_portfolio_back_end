
package com.miportfolio.mbdev.Service;

import com.miportfolio.mbdev.Entity.Project;
import com.miportfolio.mbdev.Repository.RProject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SProject {
    @Autowired
    RProject rproject;
    
    public List<Project> list(){
        return rproject.findAll();
    }
    
    public Optional<Project> getOne(int id){
        return rproject.findById(id);
    }
    
    public Optional<Project> getByTitulo(String titulo){
        return rproject.findByTitulo(titulo);
    }
    
    public void save(Project project){
        rproject.save(project);
    }
    
    public void delete(int id){
        rproject.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rproject.existsById(id);
    }
    
    public boolean existsByTitulo(String titulo){
        return rproject.existsByTitulo(titulo);
    }
}
