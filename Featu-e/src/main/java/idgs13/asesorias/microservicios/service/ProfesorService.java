// File: service/ProfesorService.java
package idgs13.asesorias.microservicios.service;

import idgs13.asesorias.microservicios.dto.*;

import java.util.List;

public interface ProfesorService {
    ProfesorDto createProfesor(ProfesorCreateDto dto);
    ProfesorDto updateProfesor(Long id, ProfesorUpdateDto dto);
    void deleteProfesor(Long id);
    ProfesorDto changeStatus(Long id, Boolean estatus);
    
    ProfesorDto getById(Long id);
    List<ProfesorDto> getAll();
    
    ProfesorDivisionDto getProfesorWithDivisions(Long id);
    List<ProfesorDivisionDto> getAllWithDivisions();
    
    void assignDivision(Long profesorId, Long divisionId);
    void removeDivision(Long profesorId, Long divisionId);
}