package idgs13.asesorias.microservicios.service;

import idgs13.asesorias.microservicios.client.DivisionClient;
import idgs13.asesorias.microservicios.dto.*;
import idgs13.asesorias.microservicios.entity.*;
import idgs13.asesorias.microservicios.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProfesorServiceImpl implements ProfesorService {

    private final ProfesorRepository profesorRepository;
    private final ProfesorDivisionRepository profesorDivisionRepository;
    private final DivisionClient divisionClient;

    public ProfesorServiceImpl(ProfesorRepository profesorRepository,
                               ProfesorDivisionRepository profesorDivisionRepository,
                               DivisionClient divisionClient) {
        this.profesorRepository = profesorRepository;
        this.profesorDivisionRepository = profesorDivisionRepository;
        this.divisionClient = divisionClient;
    }

    @Override
    public ProfesorDto createProfesor(ProfesorCreateDto dto) {
        Profesor profesor = new Profesor();
        profesor.setNombre(dto.getNombre());
        profesor.setCorreo(dto.getCorreo());
        profesor.setEstatus(true);

        Profesor saved = profesorRepository.save(profesor);
        return toDto(saved);
    }

    @Override
    public ProfesorDto updateProfesor(Long id, ProfesorUpdateDto dto) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Profesor no encontrado: " + id));

        if (dto.getNombre() != null) profesor.setNombre(dto.getNombre());
        if (dto.getCorreo() != null) profesor.setCorreo(dto.getCorreo());
        if (dto.getEstatus() != null) profesor.setEstatus(dto.getEstatus());

        Profesor saved = profesorRepository.save(profesor);
        return toDto(saved);
    }

    @Override
    public void deleteProfesor(Long id) {
        profesorDivisionRepository.deleteAll(profesorDivisionRepository.findByProfesorId(id));
        profesorRepository.deleteById(id);
    }

    @Override
    public ProfesorDto changeStatus(Long id, Boolean estatus) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Profesor no encontrado: " + id));

        profesor.setEstatus(estatus);
        Profesor saved = profesorRepository.save(profesor);
        return toDto(saved);
    }

    @Override
    public ProfesorDto getById(Long id) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Profesor no encontrado: " + id));
        return toDto(profesor);
    }

    @Override
    public List<ProfesorDto> getAll() {
        return profesorRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProfesorDivisionDto getProfesorWithDivisions(Long id) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Profesor no encontrado: " + id));

        List<ProfesoresDivision> relaciones = profesorDivisionRepository.findByProfesorId(id);

        String clavePrincipal = null;
        if (!relaciones.isEmpty()) {
            List<Long> divisionIds = relaciones.stream()
                                               .map(ProfesoresDivision::getDivisionId)
                                               .collect(Collectors.toList());
            try {
                List<DivisionDto> divisiones = divisionClient.getByIds(divisionIds);
                if (!divisiones.isEmpty()) {
                    // Tomamos la primera división como principal
                    clavePrincipal = divisiones.get(0).getClave();
                }
            } catch (Exception e) {
                System.err.println("Error al obtener divisiones: " + e.getMessage());
            }
        }

        ProfesorDivisionDto dto = new ProfesorDivisionDto();
        dto.setId(profesor.getId());
        dto.setNombre(profesor.getNombre());
        dto.setCorreo(profesor.getCorreo());
        dto.setEstatus(profesor.getEstatus());
        dto.setClaveDivision(clavePrincipal);

        return dto;
    }

    @Override
    public List<ProfesorDivisionDto> getAllWithDivisions() {
        return profesorRepository.findAll().stream()
                .map(p -> getProfesorWithDivisions(p.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public void assignDivision(Long profesorId, Long divisionId) {
        profesorRepository.findById(profesorId)
                .orElseThrow(() -> new NotFoundException("Profesor no encontrado: " + profesorId));

        boolean exists = profesorDivisionRepository.findByProfesorIdAndDivisionId(profesorId, divisionId).isPresent();
        if (exists) throw new RuntimeException("El profesor ya está asignado a esta división");

        String clave = null;
        try {
            List<DivisionDto> divisiones = divisionClient.getByIds(Collections.singletonList(divisionId));
            if (!divisiones.isEmpty()) {
                clave = divisiones.get(0).getClave();
            }
        } catch (Exception e) {
            System.err.println("Error al obtener división: " + e.getMessage());
        }

        ProfesoresDivision relacion = new ProfesoresDivision();
        relacion.setProfesorId(profesorId);
        relacion.setDivisionId(divisionId);
        relacion.setClave(clave);

        profesorDivisionRepository.save(relacion);
    }

    @Override
    public void removeDivision(Long profesorId, Long divisionId) {
        profesorDivisionRepository.deleteByProfesorIdAndDivisionId(profesorId, divisionId);
    }

    private ProfesorDto toDto(Profesor profesor) {
        ProfesorDto dto = new ProfesorDto();
        dto.setId(profesor.getId());
        dto.setNombre(profesor.getNombre());
        dto.setCorreo(profesor.getCorreo());
        dto.setEstatus(profesor.getEstatus());
        return dto;
    }

    // Excepción personalizada
    public static class NotFoundException extends RuntimeException {
        public NotFoundException(String message) {
            super(message);
        }
    }
}
