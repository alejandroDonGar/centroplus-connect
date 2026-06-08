package es.ies.puerto.centroplus.adapters.mapper;
import org.mapstruct.Mapper;
import es.ies.puerto.centroplus.adapters.out.persistencia.actividad.ActividadJpaEntity;
import es.ies.puerto.centroplus.domain.model.Actividad;
/**
 * Clase para mapear una actividad en la base de datos a un modelo de dominio
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface ActividadMapper {
    /**
     * Metodo para mapear una actividad en la base de datos a un modelo de dominio
     * @param entity La entidad en la base de datos
     * @return El modelo de dominio
     */
    Actividad toDomain(ActividadJpaEntity entity);
    /**
     * Metodo para mapear un modelo de dominio a una entidad en la base de datos
     * @param actividad El modelo de dominio
     * @return La entidad en la base de datos
     */
    ActividadJpaEntity toEntity(Actividad actividad);
}