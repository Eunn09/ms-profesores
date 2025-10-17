package idgs13.asesorias.microservicios.dto;

public class DivisionRefDto {
    private Long divisionId;
    private String clave;

    public DivisionRefDto() {}

    public Long getDivisionId() { return divisionId; }
    public void setDivisionId(Long divisionId) { this.divisionId = divisionId; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }
}
