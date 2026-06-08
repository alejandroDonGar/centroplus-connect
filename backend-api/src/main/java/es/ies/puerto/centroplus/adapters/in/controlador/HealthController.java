package es.ies.puerto.centroplus.adapters.in.controlador;
import es.ies.puerto.centroplus.adapters.in.api.HealthResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Controlador para manejar la respuesta de salud de la aplicacion
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
@RestController
@RequestMapping("api/v1/health")
public class HealthController {
    /**
     * Metodo para obtener la respuesta de salud de la aplicacion
     * @return La respuesta de salud de la aplicacion
     */
    @GetMapping
    public HealthResponse health() {
        return new HealthResponse("UP", "centroplus-connect API");
    }
}