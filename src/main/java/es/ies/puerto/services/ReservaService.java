package es.ies.puerto.services;
import java.util.List;
import es.ies.puerto.modelos.Reservas;
import es.ies.puerto.repositories.interfaces.IReservaRepository;
import es.ies.puerto.services.interfaces.IReservaService;
import es.ies.puerto.utils.Validaciones;
/**
 * @author AlejandroDonGar y JavierReyPer
 * @version 1.0.0
 * 
 * Clase ReservaService donde implementamos los metodos de IReservaService
 */
public class ReservaService implements IReservaService{

    IReservaRepository reservaRepository;
    es.ies.puerto.repositories.interfaces.IUsuarioRepository usuarioRepository;
    es.ies.puerto.repositories.interfaces.IActividadRepository actividadRepository;

    public ReservaService() {
        this.reservaRepository = new es.ies.puerto.repositories.ReservaRepository();
        this.usuarioRepository = new es.ies.puerto.repositories.UsuarioRepository();
        this.actividadRepository = new es.ies.puerto.repositories.ActividadRepository();
    }

    public ReservaService(IReservaRepository reservaRepository, es.ies.puerto.repositories.interfaces.IUsuarioRepository usuarioRepository, es.ies.puerto.repositories.interfaces.IActividadRepository actividadRepository) {
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
        this.actividadRepository = actividadRepository;
    }

    @Override
    public List<Reservas> findAll() {
        return reservaRepository.findAll();
    }

    @Override
    public Reservas findByID(Integer id) {
        if(!Validaciones.esIntegerValido(id)) {
            return null;
        }
        return reservaRepository.findByID(id);
    }

    @Override
    public boolean save(Reservas reserva) {
        if(!Validaciones.esReservaValida(reserva)) {
            return false;
        }
        
        // 1. El usuario existe
        if (usuarioRepository.findByID(reserva.getIdUsuario()) == null) {
            return false;
        }
        
        // 2. La actividad existe
        if (actividadRepository.findByID(reserva.getIdActividad()) == null) {
            return false;
        }
        
        // 3. La actividad tiene plazas libres
        if (reservaRepository.numeroDePlazasDisponibles(reserva.getIdActividad()) <= 0) {
            return false;
        }
        
        // 4. El usuario no tiene ya una reserva activa para esa actividad
        List<Reservas> todas = reservaRepository.findAll();
        for (Reservas r : todas) {
            if (r.getIdUsuario().equals(reserva.getIdUsuario()) && 
                r.getIdActividad().equals(reserva.getIdActividad()) && 
                "ACTIVA".equalsIgnoreCase(r.getEstado())) {
                return false;
            }
        }

        // 5. Incrementar plazas ocupadas en la actividad
        if (actividadRepository.reservarPlaza(reserva.getIdUsuario(), reserva.getIdActividad()) == null) {
            return false;
        }

        return reservaRepository.save(reserva);
    }

    @Override
    public boolean update(Reservas reserva) {
        if(!Validaciones.esReservaValida(reserva)) {
            return false;
        }
        return reservaRepository.update(reserva);
    }

    @Override
    public boolean delete(Integer id) {
        if(!Validaciones.esIntegerValido(id)) {
            return false;
        }
        Reservas reserva = reservaRepository.findByID(id);
        if (reserva == null) {
            return false;
        }
        
        // Al borrar/cancelar una reserva, liberamos la plaza en la actividad
        actividadRepository.cancelarPlaza(reserva.getIdActividad());
        
        return reservaRepository.delete(id);
    }
    @Override
    public Integer numeroDePlazasDisponibles(Integer idActividad) {
        if(!Validaciones.esIntegerValido(idActividad)) {
            return -1;
        }
        return reservaRepository.numeroDePlazasDisponibles(idActividad);
    }   
}
