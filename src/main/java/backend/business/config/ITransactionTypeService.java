/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.business.config;

import backend.model.transaction.IRepoTrans;
import backend.model.transaction.RepositoryTransaction;
import backend.model.transaction.Transaction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micae
 */
public class ITransactionTypeService implements ITransTypeService{

    
    /**
    * Obtiene todas las transacciones disponibles desde el repositorio.
    * @return Lista de todas las transacciones.
    */
    @Override
    public List<Transaction> getAllTransactionTypes() {
        
        IRepoTrans repo = new RepositoryTransaction();
        return repo.getAllTransactions();
        
    }
    
    /**
    * Obtiene únicamente las transacciones que están habilitadas (status = true).
    * @return Lista de transacciones habilitadas.
    */
    @Override
    public List<Transaction> getEnableTransactions() {
        IRepoTrans repo = new RepositoryTransaction();

        List<Transaction> todas = repo.getAllTransactions();

        List<Transaction> habilitadas = new ArrayList<>();
        for (Transaction t : todas) {
            if (t.getStatus()) {
                habilitadas.add(t);
                }
        }
    return habilitadas;
    }
    
    
    /**
    * Obtiene únicamente las transacciones que están deshabilitadas (status = false).
    * @return Lista de transacciones deshabilitadas.
    */
    @Override
    public List<Transaction> getDisableTransactions() {
        IRepoTrans repo = new RepositoryTransaction();
        List<Transaction> todas = repo.getAllTransactions();

        List<Transaction> deshabilitadas = new ArrayList<>();
        for (Transaction t : todas) {
            if (!t.getStatus()) {
                deshabilitadas.add(t);
            }
        }
        return deshabilitadas;
    }       
    
    //METODOS QUE SE AGREGO
    
    public void addTransaction(Transaction transaction) {
        IRepoTrans repo = new RepositoryTransaction();
        repo.saveTransaction(transaction);
    }
    
    @Override
    public Transaction getTransactionById(long id) {
        IRepoTrans repo = new RepositoryTransaction();
        return repo.getTransactionById(id);
    }
    
    @Override
    public void deleteTransaction(long id) {
        IRepoTrans repo = new RepositoryTransaction();
        repo.deleteTransaction(id);
    }
}
   
