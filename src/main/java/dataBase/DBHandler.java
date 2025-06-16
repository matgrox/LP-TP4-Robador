/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matgr
 */
public class DBHandler {

    private Connection connection;
    private static DBHandler instancia;
    private DBHandler() {
        createConnection();
    }

    public static DBHandler getInstancia() {
      
        if(instancia==null){
            instancia = new DBHandler();
            
        }
        return instancia;
    }
    private void createConnection(){
        try {
            FileInputStream fis = new FileInputStream("src/main/java/configuration/config.properties");
            Properties prop = new Properties();
            prop.load(fis);
            connection = DriverManager.getConnection(prop.getProperty("database"), prop);
        } catch (FileNotFoundException ex) {
            System.out.println("Error al abrir el canal del archivo,"+ex);
        } catch (IOException ex) {
            System.out.println("No se pudo leer el archivo,"+ex);
        } catch (SQLException ex) {
            System.out.println("No se pudo conectar a la base de datos,\n"+ex);
        }
    }
    public Connection getConnection() {
        return connection;
    }

}
