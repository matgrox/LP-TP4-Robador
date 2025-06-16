/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.business.config;

import backend.model.transaction.IRepoTrans;
import backend.model.transaction.RepositoryTransaction;
import backend.model.transaction.Transaction;
import java.util.List;

/**
 *
 * @author micae
 */
public class ConfigService implements IConfigService{
    private ITransTypeService service;
    
    public ConfigService() {
        this.service = new ITransactionTypeService();
    }

    @Override
    public boolean toggleTransactionTypeStatus(long id, boolean status) {
        ITransTypeService service = new ITransactionTypeService();
        List<Transaction> allTransactions = service.getAllTransactionTypes();

        for (Transaction t : allTransactions) {
            if (t.getId() == id) {
                t.setStatus(status); // cambiar el estado
                IRepoTrans repo = new RepositoryTransaction();
                repo.updateTransaction(t); // actualizar en el repositorio
                return true; // Se encontró y se actualizó
            }
        }
        return false; // No se encontró   
    }
    
    
    /**
     * Agrega una nueva transacción.
     */
    public void addNewTransaction(Transaction transaction) {
        service.addTransaction(transaction);
    }
    
    /**
     * Devuelve la transacción según su ID.
     */
    public Transaction getTransactionById(long id) {
        return service.getTransactionById(id);
    }
    
    /**
     * Elimina una transacción por su ID.
     */
    public boolean deleteTransaction(long id) {
        Transaction transaction = service.getTransactionById(id);
        if (transaction != null) {
            service.deleteTransaction(id);
            return true;
        }
        return false;
    }
    
    
    
    
}
