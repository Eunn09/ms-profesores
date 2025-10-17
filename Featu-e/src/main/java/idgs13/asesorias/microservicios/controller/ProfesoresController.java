package idgs13.asesorias.microservicios.controller;

import idgs13.asesorias.microservicios.dto.ProfesorDto;
import idgs13.asesorias.microservicios.dto.ProfesorDivisionDto;
import idgs13.asesorias.microservicios.dto.ProfesorCreateDto;
import idgs13.asesorias.microservicios.dto.ProfesorUpdateDto;
import idgs13.asesorias.microservicios.service.ProfesorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesoresController {

    private final ProfesorService profesorService;

    public ProfesoresController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @PostMapping
    public ResponseEntity<ProfesorDto> create(@RequestBody ProfesorCreateDto dto) {
        return ResponseEntity.ok(profesorService.createProfesor(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesorDto> update(@PathVariable Long id,
                                              @RequestBody ProfesorUpdateDto dto) {
        return ResponseEntity.ok(profesorService.updateProfesor(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        profesorService.deleteProfesor(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/estatus")
    public ResponseEntity<ProfesorDto> changeStatus(@PathVariable Long id,
                                                    @RequestParam Boolean estatus) {
        if (estatus == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(profesorService.changeStatus(id, estatus));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorDivisionDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(profesorService.getProfesorWithDivisions(id));
    }

    @GetMapping
    public ResponseEntity<List<ProfesorDivisionDto>> getAll() {
        return ResponseEntity.ok(profesorService.getAllWithDivisions());
    }

    @GetMapping("/simple")
    public ResponseEntity<List<ProfesorDto>> getAllSimple() {
        return ResponseEntity.ok(profesorService.getAll());
    }

    @PostMapping("/{profesorId}/divisiones/{divisionId}")
    public ResponseEntity<Void> assignDivision(@PathVariable Long profesorId,
                                               @PathVariable Long divisionId) {
        profesorService.assignDivision(profesorId, divisionId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{profesorId}/divisiones/{divisionId}")
    public ResponseEntity<Void> removeDivision(@PathVariable Long profesorId,
                                               @PathVariable Long divisionId) {
        profesorService.removeDivision(profesorId, divisionId);
        return ResponseEntity.noContent().build();
    }
}
