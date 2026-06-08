package es.ies.puerto.centroplus.adapters.mapper;
import org.mapstruct.Mapper;
import es.ies.puerto.centroplus.adapters.out.persistencia.reserva.ReservaJpaEntity;
import es.ies.puerto.centroplus.domain.model.Reserva;
/**
 * Mapper para la entidad ReservaJpaEntity
 * 
 * @author Ismael Perez
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface ReservaMapper {
    /**
     * Mapea una entidad ReservaJpaEntity a un objeto Reserva
     * @param entity La entidad ReservaJpaEntity a mapear
     * @return El objeto Reserva correspondiente
     */
    Reserva toDomain(ReservaJpaEntity entity);
    /**
     * Mapea un objeto Reserva a una entidad ReservaJpaEntity
     * @param reserva El objeto Reserva a mapear
     * @return La entidad ReservaJpaEntity correspondiente
     */
    ReservaJpaEntity toEntity(Reserva reserva);
}