package backend.model.user;

import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del repositorio de usuarios usando un archivo JSON como fuente
 * de datos. Se encarga de leer, guardar, actualizar y eliminar usuarios en el
 * archivo "data/user.json".
 *
 * @author Quique
 */
public class RepositoryUser implements IRepoCRUD<Usuario> {

    private Connection c;

    public RepositoryUser(Connection c) {
        this.c = c;
    }

    @Override
    public void create(Usuario entity) {
        String query = "INSERT INTO usuario (username, email, password) VALUES (?, ?, ?)";
        String password = entity.getPassword();
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        try {
            PreparedStatement psm = c.prepareStatement(query);
            psm.setString(1, entity.getUsername());
            psm.setString(2, entity.getEmail());
            psm.setString(3, hashedPassword);
            psm.executeUpdate();
            psm.close();
        } catch (SQLException ex) {
            System.out.println("Error al insetar, el usuario\n" + ex);
        }

    }

    @Override
    public Usuario read(long id) {
        Usuario u = null;
        String query = "SELECT * FROM usuario WHERE codUser = ?";
        try {
            PreparedStatement psm = c.prepareStatement(query);
            psm.setLong(1, id);
            ResultSet rs = psm.executeQuery();
            u.setCodUser(rs.getLong("codUser"));
            u.setUsername(rs.getString("username"));
            u.setEmail(rs.getString("email"));
            psm.close();
        } catch (SQLException ex) {
            System.out.println("Error al intetar, el usuario");
        }
        return u;
    }
     @Override
    public Usuario read(String username) {
        Usuario u = null;
        String query = "SELECT * FROM usuario WHERE username = ?";
        try {
            PreparedStatement psm = c.prepareStatement(query);
            psm.setString(1, username);
            ResultSet rs = psm.executeQuery();
            u.setCodUser(rs.getLong("codUser"));
            u.setUsername(rs.getString("username"));
            u.setEmail(rs.getString("email"));
            psm.close();
        } catch (SQLException ex) {
            System.out.println("Error al intetar, el usuario");
        }
        return u;
    }
    @Override
    public Usuario read(String username, String pass) {
        Usuario u = new Usuario();
        String query = "SELECT * FROM usuario WHERE username = ?";
        try {
            PreparedStatement psm = c.prepareStatement(query);
            psm.setString(1, username); // Evita SQL Injection
            ResultSet rs = psm.executeQuery();

            if (rs.next()) { // Asegurar que se encontró el usuario
                u.setCodUser(rs.getLong("codUser"));
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
                // Obtener el hash almacenado
                String storedHash = rs.getString("password");

                // Verificar la contraseña ingresada
                if (BCrypt.checkpw(pass, storedHash)) {
                    System.out.println("Password válida.");
                } else {
                    System.out.println("Password incorrecta.");
                    return null; // Usuario inválido si la contraseña no coincide
                }
            } else {
                System.out.println("Usuario no encontrado.");
                return null;
            }

            psm.close();
        } catch (SQLException ex) {
            System.out.println("Error al consultar el usuario: " + ex.getMessage());
        }
        return u;
    }

    @Override
    public List<Usuario> readAll() {
        List<Usuario> usuarios = new ArrayList();
        try {
            String query = "select * from usuario";
            PreparedStatement psmt = c.prepareStatement(query);
            ResultSet rs = psmt.executeQuery();
            System.out.println(rs);
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setCodUser(rs.getLong("codUser"));
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
                usuarios.add(u);
            }
            psmt.close();
        } catch (SQLException ex) {
            System.out.println("Error al coneactar a la base de datos" + ex.getMessage());
        }
        return usuarios;
    }

    @Override
    public void update(Usuario entety) {
        String query = "UPDATE usuario SET email = ? WHERE username = ?";
        try {
            PreparedStatement psm = c.prepareStatement(query);
            psm.setString(1, entety.getEmail());
            psm.setString(2, entety.getUsername());
            psm.executeUpdate();
            psm.close();
        } catch (SQLException ex) {
            System.out.println("Error al conectar la base de datos");
        }
    }

    @Override
    public void delete(String username) {
        String query = "DELETE FROM usuario WHERE username = ?";
         try{
            PreparedStatement psm = c.prepareStatement(query);
            psm.setString(1, username);
            psm.executeUpdate();
            psm.close();
        } catch (SQLException ex) {
            System.out.println("Error al conectar la base de datos");
        }
    }
}
