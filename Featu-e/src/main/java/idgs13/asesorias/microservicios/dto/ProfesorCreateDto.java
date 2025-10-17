package idgs13.asesorias.microservicios.dto;

public class ProfesorCreateDto {
    private String nombre;
    private String correo;

    public ProfesorCreateDto() {}

    public ProfesorCreateDto(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
}
