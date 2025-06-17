package backend.model.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import org.mindrot.jbcrypt.BCrypt;
import java.util.List;
import org.hibernate.exception.ConstraintViolationException;

/**
 * Implementación del repositorio de usuarios usando un archivo JSON como fuente
 * de datos. Se encarga de leer, guardar, actualizar y eliminar usuarios en el
 * archivo "data/user.json".
 *
 * @author Quique
 */
public class RepositoryUser implements IRepoCRUD<Usuario> {

    private EntityManager em;

    public RepositoryUser() {
        // Crear un objeto de fábrica de EntityManager: solo se debe crear 
        // una instancia EntityManagerFactory en una aplicación porque es costosa
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");
        // los objetos de EntityManager administran cada operación CRUD
        this.em = emf.createEntityManager();
    }

    @Override
    public void create(Usuario u) {
        try {
            em.getTransaction().begin();
            // abro transacción
            String password = u.getPassword();
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            u.setPassword(hashedPassword);
            em.persist(u); // guardo en la bd
            em.getTransaction().commit();  // confirmo operación
        } catch (ConstraintViolationException e) {
            System.out.println("Error: El username ya existe en la base de datos.");
            em.getTransaction().rollback();
        } catch (PersistenceException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                System.out.println("Error: Intento de insertar un valor duplicado.");
            }
            em.getTransaction().rollback();
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            em.getTransaction().rollback();
        }

    }

    @Override
    public Usuario read(long id) {
        Usuario usuario = em.find(Usuario.class, id);
        if (usuario != null) {
            System.out.println("Usuario encontrado con iD:" + id + "Resultado: \n" + usuario);
        } else {
            System.out.println("Id no valido, no se encotro conincidencia");
        }
        return usuario;
    }

    @Override
    public Usuario read(String username) {
        if (username == null || username.isEmpty()) {
            System.out.println("Usuario no válido, no se encontró coincidencia.");
            return null;
        }
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.username = :username", Usuario.class);
        query.setParameter("username", username);

        Usuario usuario = null;
        try {
            usuario = query.getSingleResult();
            System.out.println("Usuario encontrado con username: " + username + "\nResultado: \n" + usuario);
        } catch (NoResultException e) {
            System.out.println("No se encontró coincidencia para el username: " + username);
        }

        return usuario;
    }

    @Override
    public Usuario read(String username, String pass) {
        if (username == null || username.isEmpty() || pass == null || pass.isEmpty()) {
            System.out.println("Usuario no válido, no se encontró coincidencia.");
            return null;
        }
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.username = :username", Usuario.class);
        query.setParameter("username", username);
        Usuario usuario = null;
        try {
            usuario = query.getSingleResult();
            if (usuario != null && BCrypt.checkpw(pass, usuario.getPassword())) {
                System.out.println("Usuario encontrado con username: " + username + "\nResultado: \n" + usuario);
                return usuario;
            } else {
                System.out.println("Contraseña incorrecta.");
            }
        } catch (NoResultException e) {
            System.out.println("No se encontró coincidencia para el username: " + username);
        }

        return null;
    }

    @Override
    public List<Usuario> readAll() {
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
        return query.getResultList();
    }

    @Override
    public void update(Usuario u) {
        em.getTransaction().begin();
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.username = :username", Usuario.class);
        query.setParameter("username", u.getUsername());
        try {
            Usuario usuario = query.getSingleResult();
            usuario.setEmail(u.getEmail()); // Actualiza el email
            em.merge(usuario); // Guarda los cambios
            System.out.println("Email actualizado para el usuario: " + u.getUsername());
        } catch (NoResultException e) {
            System.out.println("No se encontró usuario con username: " + u.getUsername());
        }

        em.getTransaction().commit();
    }

    @Override
public void delete(String username) {
    em.getTransaction().begin();

    try {
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.username = :username", Usuario.class);
        query.setParameter("username", username);

        Usuario usuario;
        try {
            usuario = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No se encontró usuario con username: " + username);
            em.getTransaction().rollback(); // Esto está bien, pero podrías dejar que el `catch (Exception e)` lo maneje
            return;
        }

        em.remove(usuario); // Elimina el usuario
        em.getTransaction().commit();
        System.out.println("Usuario eliminado correctamente.");

    } catch (Exception e) {
        System.out.println("Error inesperado: " + e.getMessage());
        em.getTransaction().rollback();
    }
}}


