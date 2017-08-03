/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connection;

import com.hp.hpl.jena.db.DBConnection;

/**
 *
 * @author hero
 */
public class koneksi {
    private String DBDriver = "com.mysql.jdbc.Driver";
    private String DBUrl = "jdbc:mysql://127.0.0.1/tiket";
    private String DBUser = "root";
    private String DBPass = "";
    private String DBType = "MySQL";
    private DBConnection con;

    public koneksi() {
        try {
            Class.forName(DBDriver);
            con =  new DBConnection(DBUrl, DBUser, DBPass, DBType);
        } catch (ClassNotFoundException e) {
        }
    }
    
    public DBConnection getConnection() {
        return con;
    }
}
