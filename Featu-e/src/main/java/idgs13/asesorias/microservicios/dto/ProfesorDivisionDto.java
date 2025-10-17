package idgs13.asesorias.microservicios.dto;

public class ProfesorDivisionDto {
    private Long id;
    private String nombre;
    private String correo;
    private Boolean estatus;
    private String claveDivision; // <-- solo la clave principal

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public Boolean getEstatus() { return estatus; }
    public void setEstatus(Boolean estatus) { this.estatus = estatus; }

    public String getClaveDivision() { return claveDivision; }
    public void setClaveDivision(String claveDivision) { this.claveDivision = claveDivision; }
}
