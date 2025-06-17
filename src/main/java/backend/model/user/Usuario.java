package backend.model.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Clase que representa a un usuario en el sistema.
 * Contiene los atributos y métodos necesarios para la autenticación.
 * @author matias
 */
@Entity
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codUser;       // ID único del usuario
    @Column(name="username")
    private String username;    // Nombre de usuario
    @Column(name="email")
    private String email;       // email de usuario
    @Column(name="password")
    private String password;    // Contraseña del usuario
    /**
     * Constructor vacío necesario para el mapeo de datos (Jackson lo requiere).
     */
    public Usuario() {
    }

    /**
     * Constructor parametrizado para inicializar la clase con valores específicos.
     * @param codUser ID único del usuario
     * @param username Nombre de usuario
     * @param password Contraseña del usuario
     */
    public Usuario(String username,String email, String password) {
        this.username = username;
        this.password = password;
        this.email=email;
    }
    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }
    /**
     * Obtiene el ID del usuario.
     * @return ID del usuario
     */
    public long getCodUser() {
        return codUser;
    }

    /**
     * Establece el ID del usuario.
     * @param codUser Nuevo ID para el usuario
     */
    public void setCodUser(long codUser) {
        this.codUser = codUser;
    }

    /**
     * Obtiene el nombre de usuario.
     * @return Nombre de usuario
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     * @param username Nuevo nombre de usuario
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene la contraseña del usuario.
     * @return Contraseña del usuario
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * @param password Nueva contraseña del usuario
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Usuario{" + "codUser=" + codUser + ", username=" + username + ", email=" + email + '}';
    }
    
    /**
     * Método para representar al usuario como una cadena (útil para depuración).
     * @return Representación en texto del usuario
     */
    
}