package demo;

import backend.business.config.ConfigService;
import backend.business.config.IConfigService;
import backend.business.security.AuthenticationService;
import backend.model.transaction.IRepoTrans;
import backend.model.transaction.RepositoryTransaction;
import backend.model.transaction.Transaction;
import backend.model.user.RepositoryUser;
import backend.model.user.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Quique
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        RepositoryUser r = new RepositoryUser();
        Usuario u =null;
        int menu=0;
        while (menu!=7) {            
            switch (menu()) {
            case 1:
                u = new Usuario("nuevoMatias", "nuevoMail@example.com", "password123");
                r.create(u);
                break;
            case 2:
                pruebaAute();
                break;
            case 3:
                listarUsuarios(r);
                break;
            case 4:
                PruebaUpdate(r);
                break;
            case 5:
                PruebaDelete(r);
                break;
            case 6:
                r.read("nuevoMatias");
                break;
            default:
                menu =7;
                break;
        }
        }
        

    }

    public static EntityManager connnexion() {
        // Crear un objeto de fábrica de EntityManager: solo se debe crear 
        // una instancia EntityManagerFactory en una aplicación porque es costosa
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");
        // los objetos de EntityManager administran cada operación CRUD
        EntityManager em = emf.createEntityManager();
        return em;
    }

    public static void crearUsuarios(EntityManager em) {
        // 1. Usuarios ya en la Base de datos dejo los datos para no olvidarme
        Usuario user = new Usuario("matias89", "matias89@example.com", "password123");
        Usuario user1 = new Usuario("luci090", "luci90@example.com", "password908");
        Usuario user2 = new Usuario("juana_perez", "juana.perez@example.com", "securePass");
        Usuario user3 = new Usuario("luna_dev", "luna.dev@example.com", "securePass123");
        em.getTransaction().begin(); // abro transacción
        Usuario u = new Usuario("matias", "matias@uart.unpa.edu.ar", "matiasPass");
        em.persist(u); // guardo en la bd
        em.getTransaction().commit();  // confirmo operación
        //opcion vieja
    }

    public static void listarUsuarios(RepositoryUser crud) {
        List<Usuario> lr = crud.readAll();
                System.out.println("-------------------------------------------------------------------------------------");
                System.out.println("Lista de usuarios: ");
                for (Usuario usuario : lr) {
                    System.out.println(usuario);
                }
                System.out.println("-------------------------------------------------------------------------------------");
        
    }

    public static void pruebaAute() {
        AuthenticationService iauth = new AuthenticationService();
        long aut = iauth.signin("matias89", "password123");
    }

    public static void PruebaUpdate(RepositoryUser crud) {
        Usuario u = new Usuario("nuevoMatias", "nuevoMail2@example.com", "password123");
                crud.update(u);
    }

    public static void PruebaDelete(RepositoryUser crud) {
        crud.delete("nuevoMatias");
    }

    public static void pruebaTransaction() {
        IRepoTrans repo2 = new RepositoryTransaction(); // Se accede mediante la interfaz

        Transaction trans1 = new Transaction(4000, "Certificado de Alumno regular", true);
        Transaction trans5 = new Transaction(40006, "Certificado de Alumno regular333", true);
        Transaction trans6 = new Transaction(4005, "Certificado de Alumno regular22", true);
        Transaction trans2 = new Transaction(4001, "Constanccia de materia aprobadas55", true);
        Transaction trans3 = new Transaction(4002, "Constanccia de materia desaprobadas77", true);
        Transaction trans8 = new Transaction(7002, "Constanccia de materia desaprobadas88", true);
        //Antes
        /*repo2.saveTransaction(trans1);
        repo2.saveTransaction(trans2);
        repo2.saveTransaction(trans3);
        repo2.saveTransaction(trans5);
        repo2.saveTransaction(trans6);
         */
        //despues
        IConfigService confi = new ConfigService();
        confi.addNewTransaction(trans1);
        confi.addNewTransaction(trans5);
        confi.addNewTransaction(trans6);
        confi.addNewTransaction(trans2);
        confi.addNewTransaction(trans3);

        confi.toggleTransactionTypeStatus(4002, false);
    }

    public static int menu() {
        Scanner scanner = new Scanner(System.in); // Crear el objeto Scanner
        int numero=0;
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("Bienvenido a crud Sstema ");
        System.out.println("(1) para crear usuarios");
        System.out.println("(2) para loguearse");
        System.out.println("(3) para listar usuarios");
        System.out.println("(4) para actualizar email del usuario");
        System.out.println("(5) para eliminar usuario");
        System.out.println("(6) para buscar usuario--solo para practica");
        System.out.println("(7) o cualquier otra tecla para salir");
        System.out.println("-------------------------------------------------------------------------------------");
        try {
            numero = scanner.nextInt(); // Leer opcion
        }
        catch(InputMismatchException e) {
            numero=7;    
            System.out.println("Entrada inválida. Debe ingresar un número entero.");
        }  
            return numero;
    }
}
