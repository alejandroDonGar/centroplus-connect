package es.ies.puerto.centroplus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Clase principal de la aplicacion Centroplus API
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
@SpringBootApplication
public class CentroplusApiApplicacion {
    /**
     * Metodo principal de la aplicacion Centroplus API
     * @param args Los argumentos de la linea de la aplicacion
     */
    public static void main(String[] args) {
        SpringApplication.run(CentroplusApiApplicacion.class, args);
    }
}