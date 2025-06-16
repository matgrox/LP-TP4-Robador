/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package backend.business.config;

import backend.model.transaction.Transaction;
import java.util.List;

/**
 *
 * @author micae
 */
public interface ITransTypeService {
    public List<Transaction> getAllTransactionTypes();
    public List<Transaction> getEnableTransactions();
    public List<Transaction> getDisableTransactions();
    public void addTransaction(Transaction transaction);
    Transaction getTransactionById(long id);
    public void deleteTransaction(long id);
}
