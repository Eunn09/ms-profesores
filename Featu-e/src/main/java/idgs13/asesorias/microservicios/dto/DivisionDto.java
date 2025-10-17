package idgs13.asesorias.microservicios.dto;

public class DivisionDto {
    private Long id;
    private String nombre;
    private String clave;

    public DivisionDto() {}

    public DivisionDto(Long id, String nombre, String clave) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }
}
