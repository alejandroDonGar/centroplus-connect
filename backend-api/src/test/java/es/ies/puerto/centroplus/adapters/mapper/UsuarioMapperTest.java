package es.ies.puerto.centroplus.adapters.mapper;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import es.ies.puerto.centroplus.adapters.out.persistencia.usuario.UsuarioJpaEntity;
import es.ies.puerto.centroplus.domain.model.Usuario;
/**
 * Clase de prueba para el mapeador de usuarios
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
class UsuarioMapperTest {
    private UsuarioMapper mapper = Mappers.getMapper(UsuarioMapper.class);
    /**
     * Prueba para el mapeo de entidad a dominio
     */
    @Test
    void toDomainDebeMapearCorrectamente() {
        UsuarioJpaEntity entity = new UsuarioJpaEntity();
        entity.setId(1L);
        entity.setNombre("Juan Pérez");
        Usuario domain = mapper.toDomain(entity);
        assertNotNull(domain);
        assertEquals(entity.getId(), domain.getId());
        assertEquals(entity.getNombre(), domain.getNombre());
    }
    /**
     * Prueba para el mapeo de dominio a entidad
     */
    @Test
    void toEntityDebeMapearCorrectamente() {
        Usuario domain = new Usuario(1L, "Juan Pérez", "12345678A", "juan@gmail.com", "666555444", "USER");
        UsuarioJpaEntity entity = mapper.toEntity(domain);
        assertNotNull(entity);
        assertEquals(domain.getId(), entity.getId());
        assertEquals(domain.getNombre(), entity.getNombre());
    }
}