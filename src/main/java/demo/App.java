package demo;

import backend.business.config.ConfigService;
import backend.business.config.IConfigService;
import backend.business.security.AuthenticationService;
import backend.business.security.IAuthService;
import backend.model.transaction.IRepoTrans;
import backend.model.transaction.RepositoryTransaction;
import backend.model.transaction.Transaction;
import backend.model.user.RepositoryUser;
import backend.model.user.Usuario;
import dataBase.DBHandler;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Quique
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        RepositoryUser crud = connnexion();
        // crearUsuarios(crud); //crear usuarios para pruebas
        // listarUsuarios(crud); //listar usuarios
        // pruebaAute(crud); // auntenticacion
        //PruebaUpdate(crud); //Update
        PruebaDelete(crud); //eliminacion
        
    }

    public static RepositoryUser connnexion(){
        DBHandler handler = DBHandler.getInstancia();
        Connection c = handler.getConnection();
        RepositoryUser crud = new RepositoryUser(c);
        return crud;
    }
    public static void crearUsuarios(RepositoryUser crud) {
      
        // 1. Agregar 3 usuarios nuevos
        Usuario user = new Usuario("matias89","matias89@example.com", "password123");
        Usuario user1 = new Usuario("luci090","luci90@example.com", "password908");
        Usuario user2 = new Usuario("juana_perez", "juana.perez@example.com", "securePass");
        Usuario user3 = new Usuario("luna_dev", "luna.dev@example.com","securePass123");
        crud.create(user);
        crud.create(user1);
        crud.create(user2);
        crud.create(user3);
    }
    public static void listarUsuarios(RepositoryUser crud){
        List<Usuario> users = crud.readAll();
        for (Usuario user : users) {
            System.out.println(user);
        }
    }
    public static void pruebaAute(RepositoryUser crud) {
        IAuthService iauth = new AuthenticationService(crud);
        long aut = iauth.signin("matias89", "password123");
    }
    public static void PruebaUpdate(RepositoryUser crud){
        System.out.println("hola");
        Usuario u = new Usuario("matias89","matias890@example.com", "password123");
        crud.update(u);
    }
    public static void PruebaDelete(RepositoryUser crud){
        crud.delete("matias89");
    }
    public static void pruebaTransaction()
    {
        IRepoTrans repo2 = new RepositoryTransaction(); // Se accede mediante la interfaz
        
        Transaction trans1 = new Transaction(4000,"Certificado de Alumno regular",true);
        Transaction trans5 = new Transaction(40006,"Certificado de Alumno regular333",true);
        Transaction trans6 = new Transaction(4005,"Certificado de Alumno regular22",true);
        Transaction trans2 = new Transaction(4001,"Constanccia de materia aprobadas55",true);
        Transaction trans3 = new Transaction(4002,"Constanccia de materia desaprobadas77",true);
        Transaction trans8 = new Transaction(7002,"Constanccia de materia desaprobadas88",true);
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
}