
package com.miportfolio.mbdev.Controller;

import com.miportfolio.mbdev.Dto.dtoProject;
import com.miportfolio.mbdev.Entity.Project;
import com.miportfolio.mbdev.Service.SProject;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"https://mbdevfrontend.web.app/"})
@RequestMapping("/project")
public class CProject {
    
    @Autowired
    SProject sProject;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Project>> list(){
        List<Project> list = sProject.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Project> getById(@PathVariable("id")int id){
        if(!sProject.existsById(id)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        
        Project project = sProject.getOne(id).get();
        return new ResponseEntity(project, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sProject.existsById(id)){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        sProject.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoProject dtoproject){
        if(StringUtils.isBlank(dtoproject.getTitulo())){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if(sProject.existsByTitulo(dtoproject.getTitulo())){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        
        Project project = new Project(
                dtoproject.getTitulo(), dtoproject.getDescripcion()
            );
        sProject.save(project);
        return new ResponseEntity(HttpStatus.OK);
                
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoProject dtoproject){
        if(!sProject.existsById(id)){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if(sProject.existsByTitulo(dtoproject.getTitulo()) && sProject.getByTitulo(dtoproject.getTitulo()).get().getId() != id){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoproject.getTitulo())){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        
        Project project = sProject.getOne(id).get();
        
        project.setTitulo(dtoproject.getTitulo());
        project.setDescripcion(dtoproject.getDescripcion());
        
        sProject.save(project);
        
        return new ResponseEntity(HttpStatus.OK);
    }
}
