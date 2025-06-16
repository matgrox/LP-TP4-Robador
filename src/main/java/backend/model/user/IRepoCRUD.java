/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.model.user;

import java.util.List;

/**
 *
 * @author matgr
 */
public interface IRepoCRUD<T> {
    void create(T entity);
    T read(long id);
    T read(String username);
    T read(String username, String pass);
    List<T> readAll();
    void update(T entety);
    void delete(String username);
}
