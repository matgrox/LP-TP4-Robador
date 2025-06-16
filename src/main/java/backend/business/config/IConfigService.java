/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.business.config;

import backend.model.transaction.Transaction;

/**
 *
 * @author micae
 */
public interface IConfigService {
    /**
     * @param id = identificador
     * @param status = true o false
     * @return 
     */
    public boolean toggleTransactionTypeStatus(long id,boolean status);
    public void addNewTransaction(Transaction transaction);
    public Transaction getTransactionById(long id);
    public boolean deleteTransaction(long id);
}
