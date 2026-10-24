package edu.umg.biblioteca.config;
import java.sql.*;
public final class Conexion {
 private Conexion() {}
 public static Connection abrir() throws SQLException {
  String url=System.getenv().getOrDefault("DB_URL","jdbc:sqlserver://localhost:1433;databaseName=BibliotecaMVC;encrypt=true;trustServerCertificate=true");
  String user=System.getenv("DB_USER"), pass=System.getenv("DB_PASSWORD");
  if(user==null || pass==null) throw new SQLException("Defina DB_USER y DB_PASSWORD");
  return DriverManager.getConnection(url,user,pass);
 }
}
