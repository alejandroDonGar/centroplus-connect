package es.ies.puerto.centroplus.adapters.in.api;
/**
 * Clase para representar la respuesta de salud de la aplicacion
 * 
 * @author AlejandroDonGar
 * @version 1.0
 */
public class HealthResponse {

    private String status;
    private String application;

    /**
     * Constructor vacio de la clase HealthResponse
     */
    public HealthResponse() {}
    /**
     * Constructor con parametros de la clase HealthResponse
     * @param status El estado de la aplicacion
     * @param application El nombre de la aplicacion
     */
    public HealthResponse(String status, String application) {
        this.status = status;
        this.application = application;
    }
    /**
     * Metodo para obtener el estado de la aplicacion
     * @return El estado de la aplicacion
     */
    public String getStatus() {
        return status;
    }
    /**
     * Metodo para obtener el nombre de la aplicacion
     * @return El nombre de la aplicacion
     */
    public String getApplication() {
        return application;
    }
    /**
     * Metodo para establecer el estado de la aplicacion
     * @param status El estado de la aplicacion
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * Metodo para establecer el nombre de la aplicacion
     * @param application El nombre de la aplicacion
     */
    public void setApplication(String application) {
        this.application = application;
    }
}