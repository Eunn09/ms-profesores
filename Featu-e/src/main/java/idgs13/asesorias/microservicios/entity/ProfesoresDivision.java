package idgs13.asesorias.microservicios.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "profesores_division", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"profesor_id", "division_id"}))
public class ProfesoresDivision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "profesor_id", nullable = false)
    private Long profesorId;

    @Column(name = "division_id", nullable = false)
    private Long divisionId;

    @Column(length = 50)
    private String clave; 

    // Constructores
    public ProfesoresDivision() {}

    public ProfesoresDivision(Long profesorId, Long divisionId, String clave) {
        this.profesorId = profesorId;
        this.divisionId = divisionId;
        this.clave = clave;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProfesorId() { return profesorId; }
    public void setProfesorId(Long profesorId) { this.profesorId = profesorId; }

    public Long getDivisionId() { return divisionId; }
    public void setDivisionId(Long divisionId) { this.divisionId = divisionId; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }
}
