package idgs13.asesorias.microservicios.client;

import idgs13.asesorias.microservicios.dto.DivisionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "divisiones-service")
public interface DivisionClient {

    @GetMapping("/api/divisiones/{id}")
    DivisionDto getById(@PathVariable("id") Long id);

    @GetMapping("/api/divisiones")
    List<DivisionDto> getAll();

    // POST para enviar lista de IDs y recibir DTOs
    @PostMapping("/api/divisiones/byid")
    List<DivisionDto> getByIds(@RequestBody List<Long> ids);
}
