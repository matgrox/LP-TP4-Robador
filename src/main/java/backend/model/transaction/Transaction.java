package backend.model.transaction;

/**
 * Clase que representa un tipo de transacción en el sistema.
 * Contiene los atributos y métodos necesarios para identificar y describir
 * un tipo de trámite específico.
 * @author Quique
 */
public class Transaction {
    private long id; // ID único del tipo de transacción
    private String type; // Descripción del tipo de transacción
    private boolean status; // Estado del trámite (habilitado o no)

    /**
     * Constructor vacío necesario para el mapeo de datos (por ejemplo, Jackson).
     */
    public Transaction() {
    }

    /**
     * Constructor parametrizado para inicializar la clase con valores específicos.
     * @param id ID único del tipo de transacción.
     * @param type Descripción del tipo de transacción.
     * @param status Estado de la transacción (true = habilitado, false = deshabilitado).
     */
    public Transaction(long id, String type, boolean status) {
        this.id = id;
        this.type = type;
        this.status = status;
    }

    /**
     * Obtiene el ID del tipo de transacción.
     * @return ID del tipo de transacción.
     */
    public long getId() {
        return id;
    }

    /**
     * Establece el ID del tipo de transacción.
     * @param id Nuevo ID para el tipo de transacción.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre o descripción del tipo de transacción.
     * @return Nombre del tipo de transacción.
     */
    public String getType() {
        return type;
    }

    /**
     * Establece el nombre o descripción del tipo de transacción.
     * @param type Nuevo nombre o descripción para el tipo de transacción.
     */
    public void setType(String type) {
        this.type = type;
    }

    
    /**
     * Obtiene el estado de la transacción.
     * @return `true` si la transacción está habilitada, `false` si está deshabilitada.
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Establece el estado de la transacción.
     * @param status `true` para habilitar la transacción, `false` para deshabilitarla.
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Método para representar el objeto Transaction como una cadena de texto.
     * @return Representación en texto del tipo de transacción.
     */
    @Override
    public String toString() {
        return "Transaction{" + "id=" + id + ", type='" + type + "', status=" + (status ? "Habilitado" : "Deshabilitado") + "}";
    }
}