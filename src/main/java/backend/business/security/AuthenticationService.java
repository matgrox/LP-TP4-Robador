package backend.business.security;

// Clase que representa un usuario e interface que la maneja
import backend.model.user.RepositoryUser;
import backend.model.user.Usuario;


/**
 * Clase para autenticar usuarios basándose en un archivo JSON
 * @author matias
 */
public class AuthenticationService implements IAuthService{
    private RepositoryUser crud;

    public AuthenticationService(RepositoryUser crud) {
        this.crud = crud;
    }
    
    /**
     * Método que permite iniciar sesión mediante nombre de usuario y contraseña
     * @param username Nombre de usuario introducido
     * @param password Contraseña introducida
     * @return ID del usuario si la autenticación es exitosa, -1 en caso contrario
     */
    @Override
    public long signin(String username, String password) {
        long iduser = -1;
            // Verifica si los datos del usuario coinciden con los valores introducidos
            Usuario u = crud.read(username, password);
            if (u!=null) {
                System.out.println("Auntenticacion exitosa Bienvenid@"+u.getUsername());
        }else{
                System.out.println("La auntenticacion fallo usuario o contraseña incorrecta");
            }
            return iduser; // Retorna el ID del usuario
    }
}