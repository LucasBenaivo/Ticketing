package org.itu.ticketing.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    public static Connection getConnectionPostgres() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ticketing", "postgres", "postgres");
            System.out.println("Connexion réussie à PostgreSQL !");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur : Pilote JDBC PostgreSQL introuvable.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion à la base de données PostgreSQL : " + e.getMessage());
        }
        return connection;
    }
}
