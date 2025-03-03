package org.itu.ticketing.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Vol {
    private String id;
    private String idAvion;
    private String idVilleDepart;
    private String idVilleArrivee;
    private Timestamp dateDepart;
    private Timestamp dateArrivee;

    public Vol() {}

    public Vol(String id, String idAvion, String idVilleDepart, String idVilleArrivee, Timestamp dateDepart, Timestamp dateArrivee) {
        this.id = id;
        this.idAvion = idAvion;
        this.idVilleDepart = idVilleDepart;
        this.idVilleArrivee = idVilleArrivee;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getIdAvion() { return idAvion; }
    public void setIdAvion(String idAvion) { this.idAvion = idAvion; }

    public String getIdVilleDepart() { return idVilleDepart; }
    public void setIdVilleDepart(String idVilleDepart) { this.idVilleDepart = idVilleDepart; }

    public String getIdVilleArrivee() { return idVilleArrivee; }
    public void setIdVilleArrivee(String idVilleArrivee) { this.idVilleArrivee = idVilleArrivee; }

    public Timestamp getDateDepart() { return dateDepart; }
    public void setDateDepart(Timestamp dateDepart) { this.dateDepart = dateDepart; }

    public Timestamp getDateArrivee() { return dateArrivee; }
    public void setDateArrivee(Timestamp dateArrivee) { this.dateArrivee = dateArrivee; }

    public void insert(Connection connection) throws SQLException {
        String sql = "INSERT INTO vol (id_avion, id_ville_depart, id_ville_arrivee, date_depart, date_arrivee) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, this.idAvion);
            stmt.setString(2, this.idVilleDepart);
            stmt.setString(3, this.idVilleArrivee);
            stmt.setTimestamp(4, this.dateDepart);
            stmt.setTimestamp(5, this.dateArrivee);
            stmt.executeUpdate();
        }
    }

    public void update(Connection connection) throws SQLException {
        String sql = "UPDATE vol SET id_avion=?, id_ville_depart=?, id_ville_arrivee=?, date_depart=?, date_arrivee=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, this.idAvion);
            stmt.setString(2, this.idVilleDepart);
            stmt.setString(3, this.idVilleArrivee);
            stmt.setTimestamp(4, this.dateDepart);
            stmt.setTimestamp(5, this.dateArrivee);
            stmt.setString(6, this.id);
            stmt.executeUpdate();
        }
    }

    public void delete(Connection connection) throws SQLException {
        String sql = "DELETE FROM vol WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, this.id);
            stmt.executeUpdate();
        }
    }

    public static List<Vol> getAll(Connection connection) throws SQLException {
        String sql = "SELECT id, id_avion, id_ville_depart, id_ville_arrivee, date_depart, date_arrivee FROM vol ORDER BY id";
        List<Vol> vols = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Vol vol = new Vol();
                vol.setId(rs.getString("id"));
                vol.setIdAvion(rs.getString("id_avion"));
                vol.setIdVilleDepart(rs.getString("id_ville_depart"));
                vol.setIdVilleArrivee(rs.getString("id_ville_arrivee"));
                vol.setDateDepart(rs.getTimestamp("date_depart"));
                vol.setDateArrivee(rs.getTimestamp("date_arrivee"));
                vols.add(vol);
            }
        }
        return vols;
    }
}

