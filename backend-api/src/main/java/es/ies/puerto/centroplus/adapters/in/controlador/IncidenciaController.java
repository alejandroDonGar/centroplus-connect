package es.ies.puerto.centroplus.adapters.in.controlador;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import es.ies.puerto.centroplus.adapters.in.api.incidencia.IncidenciaRequest;
import es.ies.puerto.centroplus.adapters.in.api.incidencia.IncidenciaResponse;
import es.ies.puerto.centroplus.business.ports.IncidenciaServicePort;
import es.ies.puerto.centroplus.domain.model.Incidencia;
import io.swagger.v3.oas.annotations.tags.Tag;
/**
 * Controlador REST para la gestión de incidencias.
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
@Tag(name = "Incidencias", description = "Gestión de incidencias")
@RestController
@RequestMapping("/api/v1/incidencias")
public class IncidenciaController {
    private final IncidenciaServicePort incidenciaServicePort;
    /**
     * Constructor para la clase IncidenciaController
     * @param incidenciaServicePort El servicio IncidenciaServicePort para la interacción con la lógica de negocio
     */
    public IncidenciaController(IncidenciaServicePort incidenciaServicePort) {
        this.incidenciaServicePort = incidenciaServicePort;
    }
    /**
     * Obtiene todas las incidencias
     * @return List<IncidenciaResponse> con todas las incidencias
     */
    @GetMapping
    public List<IncidenciaResponse> obtenerIncidencias() {
        return incidenciaServicePort.findAll()
                .stream() // Convierte la lista de Incidencias a un flujo
                .map(incidencia -> toResponse(incidencia)) // Mapea cada Incidencia a su IncidenciaResponse correspondiente
                .toList(); // Convierte el flujo de IncidenciaResponse a una lista de IncidenciaResponse
    }
    /**
     * Obtiene una incidencia por su ID
     * @param id El ID de la incidencia a obtener
     * @return ResponseEntity con estado 200 (OK) y la respuesta IncidenciaResponse correspondiente si se encontró, o 404 (Not Found) si no se encontró
     */
    @GetMapping("/{id}")
    public ResponseEntity<IncidenciaResponse> obtenerIncidencia(@PathVariable Long id) {
        return incidenciaServicePort.findById(id)
                .map(incidencia -> ResponseEntity.ok(toResponse(incidencia))) // Retorna la respuesta IncidenciaResponse correspondiente
                .orElse(ResponseEntity.notFound().build()); // Retorna 404 (Not Found) si no se encontró la incidencia
    }
    /**
     * Crea una nueva incidencia
     * @param request La solicitud IncidenciaRequest con crear
     * @return ResponseEntity con estado 201 (Created) y la respuesta IncidenciaResponse correspondiente si se creó correctamente, o 400 (Bad Request) si hay un error de validación
     */
    @PostMapping
    public ResponseEntity<IncidenciaResponse> save(@RequestBody IncidenciaRequest request) {
        Incidencia incidencia = toDomain(request);
        Incidencia incidenciaGuardada = incidenciaServicePort.save(incidencia);
        return ResponseEntity.ok(toResponse(incidenciaGuardada));
    }
    /**
     * Actualiza una incidencia por su ID
     * @param id El ID de la incidencia a actualizar
     * @param request La solicitud IncidenciaRequest con actualizar
     * @return ResponseEntity con estado 200 (OK) y la respuesta IncidenciaResponse correspondiente si se actualizó correctamente, o 404 (Not Found) si no se encontró
     */
    @PatchMapping("/{id}")
    public ResponseEntity<IncidenciaResponse> update(@PathVariable Long id, @RequestBody IncidenciaRequest request) {
        Incidencia incidencia = toDomain(request);
        return incidenciaServicePort.update(id, incidencia)
                .map(incidenciaActualizada -> ResponseEntity.ok(toResponse(incidenciaActualizada))) // Retorna la respuesta IncidenciaResponse correspondiente
                .orElse(ResponseEntity.notFound().build()); // Retorna 404 (Not Found) si no se encontró la incidencia
    }
    /**
     * Elimina una incidencia por su ID
     * @param id El ID de la incidencia a eliminar
     * @return ResponseEntity con estado 204 (No Content) si se eliminó correctamente, o 404 (Not Found) si no se encontró
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean eliminado = incidenciaServicePort.delete(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    /**
     * Mapea una solicitud IncidenciaRequest a una entidad Incidencia
     * @param request La solicitud IncidenciaRequest a mapear
     * @return La entidad Incidencia correspondiente
     */
    private Incidencia toDomain(IncidenciaRequest request) {
        return new Incidencia( null, request.getIdUsuario(), request.getAsunto(), request.getDescripcion(), request.getFecha(), request.getEstado());
    }
    /**
     * Mapea una entidad Incidencia a una respuesta IncidenciaResponse
     * @param incidencia La entidad Incidencia a mapear
     * @return La respuesta IncidenciaResponse correspondiente
     */
    private IncidenciaResponse toResponse(Incidencia incidencia) {
        return new IncidenciaResponse( incidencia.getId(), incidencia.getIdUsuario(), incidencia.getAsunto(), incidencia.getDescripcion(), incidencia.getFecha(), incidencia.getEstado());
    }
}