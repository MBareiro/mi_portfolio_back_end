package com.miportfolio.mbdev.Interface;

import com.miportfolio.mbdev.Entity.Persona;
import java.util.List;


public interface IPersonaService {
    // Traer una lista de personas
    public List<Persona> getPersona();
    
    // Guardar un objeto de tipo Persona
    public void savePersona(Persona persona);
    
    // Eliminar un objeto pero lo buscamos por Id
    public void deletePersona(Long id);
    
    // Buscar una persona por Id
    public Persona findPersona(Long id);
}