package com.miportfolio.mbdev.Controller;

import com.miportfolio.mbdev.Dto.dtoPersona;
import com.miportfolio.mbdev.Entity.Persona;
import com.miportfolio.mbdev.Service.ImpPersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
@CrossOrigin(origins = {"https://mbdevfrontend.web.app", "http://localhost:4200"})
public class PersonaController {

    @Autowired
    ImpPersonaService personaService;

    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list() {
        List<Persona> list = personaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id) {
        if (!personaService.existById(id)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Persona persona = personaService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPersona dtopersona) {
        if (!personaService.existById(id)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if (personaService.existByNombre(dtopersona.getNombre()) && personaService.getByNombre(dtopersona.getNombre()).get().getId() != id) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtopersona.getNombre())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Persona persona = personaService.getOne(id).get();

        persona.setNombre(dtopersona.getNombre());
        persona.setApellido(dtopersona.getApellido());
        persona.setDescripcion(dtopersona.getDescripcion());
        persona.setImg(dtopersona.getImg());

        personaService.save(persona);

        return new ResponseEntity(HttpStatus.OK);
    }

    /*
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!personaService.existsById(id)){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        personaService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }*/
    /*
    @PostMapping("/crear")
    public String createPersona(@RequestBody Persona persona) {
        personaService.save(persona);
        return "La persona fue creada correctamente";
    }*/
    
}
