package es.ies.puerto.centroplus.adapters.mapper;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import es.ies.puerto.centroplus.adapters.out.persistencia.reserva.ReservaJpaEntity;
import es.ies.puerto.centroplus.domain.model.Reserva;
/**
 * Clase de prueba para el mapeador de reservas
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
class ReservaMapperTest {
    private ReservaMapper mapper = Mappers.getMapper(ReservaMapper.class);
    /**
     * Prueba para el mapeo de entidad a dominio
     */
    @Test
    void toDomainDebeMapearCorrectamente() {
        ReservaJpaEntity entity = new ReservaJpaEntity();
        entity.setId(1L);
        entity.setIdUsuario(1L);
        Reserva domain = mapper.toDomain(entity);
        assertNotNull(domain);
        assertEquals(entity.getId(), domain.getId());
        assertEquals(entity.getIdUsuario(), domain.getIdUsuario());
    }
    /**
     * Prueba para el mapeo de dominio a entidad
     */
    @Test
    void toEntityDebeMapearCorrectamente() {
        Reserva domain = new Reserva(1L, 1L, 1L, "2024-06-08", "CONFIRMADA");
        ReservaJpaEntity entity = mapper.toEntity(domain);
        assertNotNull(entity);
        assertEquals(domain.getId(), entity.getId());
        assertEquals(domain.getIdUsuario(), entity.getIdUsuario());
    }
}