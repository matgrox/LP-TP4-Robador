/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package backend.business.security;

/**
 *
 * @author matias
 */
public interface IAuthService {

    /**
     * Método que permite iniciar sesión mediante nombre de usuario y contraseña
     * @param username Nombre de usuario introducido
     * @param password Contraseña introducida
     * @return ID del usuario si la autenticación es exitosa, -1 en caso contrario
     */
    long signin(String username, String password);
}
