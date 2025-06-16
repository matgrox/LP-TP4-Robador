package backend.model.transaction;

import java.util.List;

/**
 * Interfaz que define el contrato para el acceso y manipulación de
 * datos de transacciones en el sistema.
 * @author Quique
 */
public interface IRepoTrans {
    /**
     * Busca una transacción por su identificador único.
     * @param type Tipo de transacción a buscar.
     * @return la transacción encontrada o null si no existe.
     */
    Transaction findTransaction(String type);

    /**
     * Busca una transacción por su identificador único.
     * @param id el identificador único de la transacción.
     * @return el objeto Transaction si se encuentra; de lo contrario, retorna null.
     */
    Transaction getTransactionById(long id);

    /**
     * Persiste una nueva transacción en la fuente de datos.
     * @param transaction la transacción a guardar.
     */
    void saveTransaction(Transaction transaction);

    /**
     * Actualiza una transacción existente en la fuente de datos.
     * @param transaction la transacción con datos actualizados.
     */
    void updateTransaction(Transaction transaction);

    /**
     * Elimina una transacción a partir de su identificador.
     * @param id el identificador de la transacción a eliminar.
     */
    void deleteTransaction(long id);
    
    /**
    * Obtiene todas las transacciones almacenadas en el archivo JSON.
    * @return Lista completa de objetos Transaction. Si el archivo no existe o está vacío, se devuelve una lista vacía.
    */
    List<Transaction> getAllTransactions();
}