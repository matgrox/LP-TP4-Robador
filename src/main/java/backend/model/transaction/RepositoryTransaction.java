package backend.model.transaction;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del repositorio de transacciones usando un archivo JSON como fuente de datos.
 * Se encarga de leer, guardar, actualizar y eliminar transacciones en "data/transaction.json".
 * @author Quique
 */
public class RepositoryTransaction implements IRepoTrans {
    private ObjectMapper mapper; // Objeto ObjectMapper para la conversión entre JSON y objetos Java.
    private File file; // Archivo que simula la "base de datos" de transacciones.

    /**
     * Constructor que inicializa el ObjectMapper y define la ruta del archivo JSON.
     */
    public RepositoryTransaction() {
        this.mapper = new ObjectMapper();
        this.file = new File("src/main/java/data/transactionType.json"); // El archivo se encuentra en el directorio "data".
    }

    /**
     * Método auxiliar para leer la lista de transacciones desde el archivo JSON.
     * @return Lista de transacciones. Si el archivo no existe o hay un error, se retorna una lista vacía.
     */
    private List<Transaction> readTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        if (file.exists())
            try {
                transactions = mapper.readValue(file, new TypeReference<List<Transaction>>() {});
            } catch (IOException e) {
                System.err.println("Error al leer el archivo JSON: " + e.getMessage());
            }
        return transactions;
    }

    /**
    * Retorna la lista completa de transacciones leyendo el contenido del archivo JSON.
    * Utiliza el método privado readTransactions() para acceder a los datos.
    * @return Lista de todas las transacciones registradas. 
    */
    @Override
    public List<Transaction> getAllTransactions() {
        return readTransactions(); // Este método sí puede usar el privado porque están en la misma clase
    }
    
    /**
     * Método auxiliar para escribir la lista de transacciones en el archivo JSON.
     * @param transactions Lista de transacciones actualizada.
     */
    private void writeTransactions(List<Transaction> transactions) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, transactions);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo JSON: " + e.getMessage());
        }
    }

    /**
     * Método auxiliar para buscar el índice de una transacción por su ID.
     * @param id Identificador de la transacción.
     * @return Índice en la lista o -1 si no se encuentra.
     */
    private int findTransactionIndexById(List<Transaction> transactions, long id) {
        for (int i = 0; i < transactions.size(); i++)
            if (transactions.get(i).getId() == id)
                return i;
        return -1;
    }

    /** 
     * Busca una transacción por su tipo específico.
     * @param type El tipo a buscar.
     * @return La transaccoón encontrada o null si no existe.
     */
    @Override
    public Transaction findTransaction(String type) {
        List<Transaction> transactions = readTransactions();
        for (Transaction transaction : transactions)
            if (transaction.getType().equals(type))
                return transaction;
        return null;
    }
    
    /**
     * Busca una transacción por su ID.
     * @param id ID de la transacción.
     * @return La transacción encontrada o null si no existe.
     */
    @Override
    public Transaction getTransactionById(long id) {
        List<Transaction> transactions = readTransactions();
        for (Transaction transaction : transactions)
            if (transaction.getId() == id)
                return transaction;
        return null;
    }

    /**
     * Persiste una nueva transacción en el archivo JSON.
     * @param transaction Transacción a guardar.
     */
    @Override
    public void saveTransaction(Transaction transaction) {
        List<Transaction> transactions = readTransactions();
        // Verificar si la transacción ya existe por ID o tipo
        if (getTransactionById(transaction.getId()) != null || findTransaction(transaction.getType()) != null) {
            System.out.println("Error: Transacción con ID " + transaction.getId() + " o tipo '" + transaction.getType() + "' ya existe.");
            return;
        }
        transactions.add(transaction);
        writeTransactions(transactions);
    }

    /**
     * Actualiza una transacción existente en el archivo JSON.
     * @param transaction Transacción con datos actualizados.
     */
    @Override
    public void updateTransaction(Transaction transaction) {
        List<Transaction> transactions = readTransactions();
        int index = findTransactionIndexById(transactions, transaction.getId());
        if (index == -1) {
            System.out.println("Transacción con ID " + transaction.getId() + " no encontrada.");
            return;
        }
        transactions.set(index, transaction); // Actualizar transacción
        writeTransactions(transactions); // Guardar cambios
    }

    /**
     * Elimina una transacción a partir de su identificador.
     * @param id Identificador de la transacción a eliminar.
     */
    @Override
    public void deleteTransaction(long id) {
        List<Transaction> transactions = readTransactions();
        int index = findTransactionIndexById(transactions, id);
        if (index == -1) {
            System.out.println("Transacción con ID " + id + " no encontrada.");
            return;
        }
        transactions.remove(index); // Eliminar transacción
        writeTransactions(transactions); // Guardar cambios
    }
}