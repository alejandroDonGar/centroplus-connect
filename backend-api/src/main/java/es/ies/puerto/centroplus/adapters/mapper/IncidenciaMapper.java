package es.ies.puerto.centroplus.adapters.mapper;
import org.mapstruct.Mapper;
import es.ies.puerto.centroplus.adapters.out.persistencia.incidencia.IncidenciaJpaEntity;
import es.ies.puerto.centroplus.domain.model.Incidencia;
/**
 * Mapper para la entidad IncidenciaJpaEntity
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
@Mapper(componentModel = "spring") // El modelo de componente utilizado por MapStruct es Spring Bean para inyectar dependencias
public interface IncidenciaMapper {
    /**
     * Mapea una entidad IncidenciaJpaEntity a un objeto Incidencia
     * @param entity La entidad IncidenciaJpaEntity a mapear
     * @return El objeto Incidencia correspondiente
     */
    Incidencia toDomain(IncidenciaJpaEntity entity);
    /**
     * Mapea un objeto Incidencia a una entidad IncidenciaJpaEntity
     * @param incidencia El objeto Incidencia a mapear
     * @return La entidad IncidenciaJpaEntity correspondiente
     */
    IncidenciaJpaEntity toEntity(Incidencia incidencia);
}