package es.ies.puerto.centroplus.adapters.in.controlador;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import es.ies.puerto.centroplus.adapters.in.api.reserva.ReservaRequest;
import es.ies.puerto.centroplus.adapters.in.api.reserva.ReservaResponse;
import es.ies.puerto.centroplus.business.ports.ReservaServicePort;
import es.ies.puerto.centroplus.domain.model.Reserva;
/**
 * Controlador para manejar las operaciones de Reservas
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/reservas")
public class ReservaController {
    private final ReservaServicePort reservaServicePort;
    /**
     * Constructor de la clase ReservaController
     * @param reservaServicePort El servicio de Reservas utilizado para acceder a la lógica de negocio
     */
    public ReservaController(ReservaServicePort reservaServicePort) {
        this.reservaServicePort = reservaServicePort;
    }
    /**
     * Método para obtener todas las Reservas
     * @return Una lista de ReservasResponse
     */
    @GetMapping
    public List<ReservaResponse> obtenerReservas() {
        return reservaServicePort.findAll()
                .stream() // Convertir a flujo de Reservas
                .map(reserva -> toResponse(reserva)) // Mapear a una ReservaResponse
                .toList();
    }
    /**
     * Método para obtener una Reserva por su ID
     * @param id El ID de la Reserva a obtener
     * @return La ReservaResponse encontrada, o 404 si no se encuentra
     */
    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponse> obtenerReserva(@PathVariable Long id) {
        return reservaServicePort.findById(id)
                .map(reserva -> ResponseEntity.ok(toResponse(reserva))) // Mapear a una ReservaResponse y devolverla con 200 OK
                .orElse(ResponseEntity.notFound().build()); // Si no se encuentra la Res, devolver 404
    }
    /**
     * Método para crear una Reserva
     * @param request La ReservaRequest con los datos de la Reserva a crear
     * @return La ReservaResponse creada
     */
    @PostMapping
    public ResponseEntity<ReservaResponse> save(@RequestBody ReservaRequest request) {
        Reserva reserva = toDomain(request);
        Reserva reservaGuardada = reservaServicePort.save(reserva);
        return ResponseEntity.ok(toResponse(reservaGuardada));
    }
    /**
     * Método para actualizar una Reserva por su ID
     * @param id El ID de la Reserva a actualizar
     * @param request La ReservaRequest con los nuevos datos
     * @return La ReservaResponse actualizada
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ReservaResponse> update(@PathVariable Long id, @RequestBody ReservaRequest request) {
        Reserva reserva = toDomain(request);
        return reservaServicePort.update(id, reserva)
                .map(reservaActualizada -> ResponseEntity.ok(toResponse(reservaActualizada))) // Mapear a una ReservaResponse y devolverla 
                .orElse(ResponseEntity.notFound().build()); // Si no se encuentra la Res, devolver 404
    }
    /**
     * Método para eliminar una Reserva por su ID
     * @param id El ID de la Reserva a eliminar
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean eliminado = reservaServicePort.delete(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    /**
     * Método para convertir una ReservaRequest a una Reserva
     * @param request La ReservaRequest a convertir
     * @return La Reserva correspondiente
     */
    private Reserva toDomain(ReservaRequest request) {
        return new Reserva(null, request.getIdUsuario(), request.getIdActividad(), request.getFecha(), request.getEstado());
    }
    /**
     * Método para convertir una Reserva a una ReservaResponse
     * @param reserva La Reserva a convertir
     * @return La ReservaResponse correspondiente
     */
    private ReservaResponse toResponse(Reserva reserva) {
        return new ReservaResponse(reserva.getId(), reserva.getIdUsuario(), reserva.getIdActividad(), reserva.getFecha(), reserva.getEstado());
    }
}