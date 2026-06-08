package es.ies.puerto.centroplus.adapters.in.controlador;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import es.ies.puerto.centroplus.adapters.in.api.actividad.ActividadRequest;
import es.ies.puerto.centroplus.adapters.in.api.actividad.ActividadResponse;
import es.ies.puerto.centroplus.business.ports.ActividadServicePort;
import es.ies.puerto.centroplus.domain.model.Actividad;
/**
 * Clase para representar un controlador de actividades
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/actividades")
public class ActividadController {
    private final ActividadServicePort actividadServicePort;
    /**
     * Metodo constructor de la clase ActividadController
     * @param actividadServicePort El servicio de actividades
     */
    public ActividadController(ActividadServicePort actividadServicePort) {
        this.actividadServicePort = actividadServicePort;
    }
    /**
     * Metodo para obtener todas las actividades
     * @return La lista de objetos ActividadResponse que representan todas las actividades
     */
    @GetMapping
    public List<ActividadResponse> obtenerActividades() {
        return actividadServicePort.findAll()
                .stream() // Convierte la lista de actividades en un flujo
                .map(actividad -> toResponse(actividad)) // Mapea cada actividad a su objeto ActividadResponse
                .toList(); // Convierte el flujo de objetos ActividadResponse en una lista
    }
    /**
     * Metodo para obtener una actividad por su identificador
     * @param id El identificador de la actividad
     * @return El objeto ActividadResponse que representa la actividad
     */
    @GetMapping("/{id}")
    public ResponseEntity<ActividadResponse> obtenerActividad(@PathVariable Long id) {
        return actividadServicePort.findById(id)
                .map(actividad -> ResponseEntity.ok(toResponse(actividad))) // Si la actividad existe, devuelve una respuesta OK con la actividad
                .orElse(ResponseEntity.notFound().build()); // Si la actividad no existe, devuelve una respuesta 404 Not Found
    }
    /**
     * Metodo para guardar una actividad
     * @param request El objeto ActividadRequest que representa la solicitud de guardar una actividad
     * @return El objeto ActividadResponse que representa la actividad guardada
     */
    @PostMapping
    public ResponseEntity<ActividadResponse> save(@RequestBody ActividadRequest request) {
        Actividad actividad = toDomain(request);
        Actividad actividadGuardada = actividadServicePort.save(actividad);
        return ResponseEntity.ok(toResponse(actividadGuardada));
    }
    /**
     * Metodo para actualizar una actividad
     * @param id El identificador de la actividad
     * @param request El objeto ActividadRequest que representa la solicitud de actualizar una actividad
     * @return El objeto ActividadResponse que representa la actividad actualizada
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ActividadResponse> update(@PathVariable Long id, @RequestBody ActividadRequest request) {
        Actividad actividad = toDomain(request);
        return actividadServicePort.update(id, actividad)
                .map(actividadActualizada -> ResponseEntity.ok(toResponse(actividadActualizada))) // Si la actividad existe, devuelve una respuesta OK con la actividad actualizada
                .orElse(ResponseEntity.notFound().build()); // Si la actividad no existe, devuelve una respuesta 404 Not Found
    }
    /**
     * Metodo para eliminar una actividad por su identificador
     * @param id El identificador de la actividad
     * @return El objeto Void que representa la eliminación exitosa
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean eliminado = actividadServicePort.delete(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    /**
     * Metodo para convertir un objeto ActividadRequest a un objeto Actividad
     * @param request El objeto ActividadRequest que representa la solicitud de guardar una actividad
     * @return El objeto Actividad que representa la actividad
     */
    private Actividad toDomain(ActividadRequest request) {
        return new Actividad(null, request.getNombre(), request.getTipoActividad(), request.getDuracion(), request.getPrecio(), request.getPlazasMaximas(), request.getPlazasOcupadas());
    }
    /**
     * Metodo para convertir un objeto Actividad a un objeto ActividadResponse
     * @param actividad El objeto Actividad que representa la actividad
     * @return El objeto ActividadResponse que representa la actividad
     */
    private ActividadResponse toResponse(Actividad actividad) {
        return new ActividadResponse( actividad.getId(), actividad.getNombre(), actividad.getTipoActividad(), actividad.getDuracion(), actividad.getPrecio(), actividad.getPlazasMaximas(), actividad.getPlazasOcupadas(), actividad.getPlazasDisponibles());
    }
}