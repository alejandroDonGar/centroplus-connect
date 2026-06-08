package es.ies.puerto.centroplus.adapters.mapper;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import es.ies.puerto.centroplus.adapters.out.persistencia.actividad.ActividadJpaEntity;
import es.ies.puerto.centroplus.domain.model.Actividad;
/**
 * Clase de prueba para el mapeador de actividades
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
class ActividadMapperTest {
    private ActividadMapper mapper = Mappers.getMapper(ActividadMapper.class);
    /**
     * Prueba para el mapeo de entidad a dominio
     */
    @Test
    void toDomainDebeMapearCorrectamente() {
        ActividadJpaEntity entity = new ActividadJpaEntity();
        entity.setId(1L);
        entity.setNombre("Yoga");
        entity.setTipoActividad("DEPORTIVA");
        entity.setDuracion(60);
        entity.setPrecio(25.50);
        entity.setPlazasMaximas(15);
        entity.setPlazasOcupadas(8);
        Actividad domain = mapper.toDomain(entity);
        assertNotNull(domain);
        assertEquals(entity.getId(), domain.getId());
        assertEquals(entity.getNombre(), domain.getNombre());
    }
    /**
     * Prueba para el mapeo de dominio a entidad
     */
    @Test
    void toEntityDebeMapearCorrectamente() {
        Actividad domain = new Actividad(1L, "Yoga", "DEPORTIVA", 60, 25.50, 15, 8);
        ActividadJpaEntity entity = mapper.toEntity(domain);
        assertNotNull(entity);
        assertEquals(domain.getId(), entity.getId());
        assertEquals(domain.getNombre(), entity.getNombre());
    }
}