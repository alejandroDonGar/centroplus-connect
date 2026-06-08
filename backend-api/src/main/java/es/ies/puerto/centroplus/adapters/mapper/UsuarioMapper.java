package es.ies.puerto.centroplus.adapters.mapper;
import org.mapstruct.Mapper;
import es.ies.puerto.centroplus.domain.model.Usuario;
import es.ies.puerto.centroplus.adapters.out.persistencia.usuario.UsuarioJpaEntity;
/**
 * Interfaz para mapear objetos de la clase Usuario a objetos de la clase UsuarioJpaEntity
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
@Mapper(componentModel = "spring") // El modelo de componente utilizado por MapStruct es Spring Bean para inyectar dependencias
public interface UsuarioMapper {
    /**
     * Metodo para mapear una entidad de usuario en la base de datos a un modelo de dominio
     * @param entity La entidad en la base de datos
     * @return El modelo de dominio correspondiente
     */
    Usuario toDomain(UsuarioJpaEntity entity);
    /**
     * Metodo para mapear un modelo de dominio de usuario a una entidad de usuario en la base de datos
     * @param domain El modelo de dominio de usuario
     * @return La entidad en la base de datos correspondiente
     */
    UsuarioJpaEntity toEntity(Usuario domain);
}