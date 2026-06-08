package es.ies.puerto.centroplus.adapters.mapper;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import es.ies.puerto.centroplus.adapters.out.persistencia.incidencia.IncidenciaJpaEntity;
import es.ies.puerto.centroplus.domain.model.Incidencia;
/**
 * Clase de prueba para el mapeador de incidencias
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
class IncidenciaMapperTest {
    private IncidenciaMapper mapper = Mappers.getMapper(IncidenciaMapper.class);
    /**
     * Prueba para el mapeo de entidad a dominio
     */
    @Test
    void toDomainDebeMapearCorrectamente() {
        IncidenciaJpaEntity entity = new IncidenciaJpaEntity();
        entity.setId(1L);
        entity.setAsunto("Gotera");
        Incidencia domain = mapper.toDomain(entity);
        assertNotNull(domain);
        assertEquals(entity.getId(), domain.getId());
        assertEquals(entity.getAsunto(), domain.getAsunto());
    }
    /**
     * Prueba para el mapeo de dominio a entidad
     */
    @Test
    void toEntityDebeMapearCorrectamente() {
        Incidencia domain = new Incidencia(1L, 1L, "Gotera", "Hay una gotera", "2024-06-08", "ABIERTA");
        IncidenciaJpaEntity entity = mapper.toEntity(domain);
        assertNotNull(entity);
        assertEquals(domain.getId(), entity.getId());
        assertEquals(domain.getAsunto(), entity.getAsunto());
    }
}