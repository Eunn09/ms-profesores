// File: repository/ProfesorDivisionRepository.java
package idgs13.asesorias.microservicios.repository;

import idgs13.asesorias.microservicios.entity.ProfesoresDivision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfesorDivisionRepository extends JpaRepository<ProfesoresDivision, Long> {
    List<ProfesoresDivision> findByProfesorId(Long profesorId);
    List<ProfesoresDivision> findByDivisionId(Long divisionId);
    Optional<ProfesoresDivision> findByProfesorIdAndDivisionId(Long profesorId, Long divisionId);
    void deleteByProfesorIdAndDivisionId(Long profesorId, Long divisionId);
}