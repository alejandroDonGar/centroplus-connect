package es.ies.puerto.centroplus.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;
/**
 * Configuración de OpenAPI para documentar la API REST de CentroPlus Connect
 * 
 * @author AlejandroDonate y JavierRey
 * @version 1.0
 */
@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "CentroPlus Connect API",                                                           // El título de la API REST
        version = "1.0.0",                                                                          // La versión de la API REST
        description = "API REST para la gestión de usuarios, actividades, reservas e incidencias",  // La descripción de la API REST
        contact = @Contact(
            name = "Alejandro Donate García",                           // El nombre del contacto
            email = "AlejandroDonGar.proyectoIntramodular@dam.com"      // El correo electrónico del contacto    
        )
    )
)
public class OpenApiConfig {}