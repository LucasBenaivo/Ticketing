package org.itu.ticketing.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Avion {
    private String id;
    private String idModele;
    private Date dateFabrication;

    public Avion() {}

    public Avion(String id, String idModele, Date dateFabrication) {
        this.id = id;
        this.idModele = idModele;
        this.dateFabrication = dateFabrication;
    }

    public Avion(String idModele, Date dateFabrication) {
        this.idModele = idModele;
        this.dateFabrication = dateFabrication;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdModele() {
        return idModele;
    }

    public void setIdModele(String idModele) {
        this.idModele = idModele;
    }

    public Date getDateFabrication() {
        return dateFabrication;
    }

    public void setDateFabrication(Date dateFabrication) {
        this.dateFabrication = dateFabrication;
    }

    public void insert(Connection connection) throws SQLException {
        String sql = "INSERT INTO avion (id_modele, date_fabrication) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, this.idModele);
            stmt.setDate(2, this.dateFabrication);
            stmt.executeUpdate();
        }
    }

    public static List<Avion> getAll(Connection connection) throws SQLException {
        String sql = "SELECT id, id_modele, date_fabrication FROM avion ORDER BY id";
        List<Avion> avions = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Avion avion = new Avion();
                avion.setId(rs.getString("id"));
                avion.setIdModele(rs.getString("id_modele"));
                avion.setDateFabrication(rs.getDate("date_fabrication"));
                avions.add(avion);
            }
        }

        return avions;
    }

    public void update(Connection connection) throws SQLException {
        String sql = "UPDATE avion SET id_modele = ?, date_fabrication = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, this.idModele);
            stmt.setDate(2, this.dateFabrication);
            stmt.setString(3, this.id);
            stmt.executeUpdate();
        }
    }

    public void delete(Connection connection) throws SQLException {
        String sql = "DELETE FROM avion WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, this.id);
            stmt.executeUpdate();
        }
    }
}

