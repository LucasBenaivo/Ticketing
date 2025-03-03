package org.itu.ticketing.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VolTypeSiege {
    private String idVol;
    private String idTypeSiege;
    private Double prix; // Changez BigDecimal en Double

    // Constructeur
    public VolTypeSiege() {}

    public VolTypeSiege(String idVol, String idTypeSiege, Double prix) {
        this.idVol = idVol;
        this.idTypeSiege = idTypeSiege;
        this.prix = prix;
    }

    // Getters et Setters
    public String getIdVol() {
        return idVol;
    }

    public void setIdVol(String idVol) {
        this.idVol = idVol;
    }

    public String getIdTypeSiege() {
        return idTypeSiege;
    }

    public void setIdTypeSiege(String idTypeSiege) {
        this.idTypeSiege = idTypeSiege;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    // Méthode CREATE (insertion d'un enregistrement)
    public static void create(Connection connection, VolTypeSiege volTypeSiege) throws SQLException {
        String sql = "INSERT INTO vol_type_siege (id_vol, id_type_siege, prix) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, volTypeSiege.getIdVol());
            stmt.setString(2, volTypeSiege.getIdTypeSiege());
            stmt.setDouble(3, volTypeSiege.getPrix()); // Utilisez setDouble au lieu de setBigDecimal
            stmt.executeUpdate();
        }
    }

    // Méthode READ (lecture des enregistrements)
    public static List<VolTypeSiege> getAll(Connection connection) throws SQLException {
        String sql = "SELECT id_vol, id_type_siege, prix FROM vol_type_siege";
        List<VolTypeSiege> volTypeSiegeList = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                VolTypeSiege volTypeSiege = new VolTypeSiege();
                volTypeSiege.setIdVol(rs.getString("id_vol"));
                volTypeSiege.setIdTypeSiege(rs.getString("id_type_siege"));
                volTypeSiege.setPrix(rs.getDouble("prix")); // Utilisez getDouble pour récupérer le prix
                volTypeSiegeList.add(volTypeSiege);
            }
        }
        return volTypeSiegeList;
    }

    // Méthode UPDATE (mise à jour d'un enregistrement)
    public static void update(Connection connection, VolTypeSiege volTypeSiege) throws SQLException {
        String sql = "UPDATE vol_type_siege SET prix = ? WHERE id_vol = ? AND id_type_siege = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, volTypeSiege.getPrix()); // Utilisez setDouble pour mettre à jour le prix
            stmt.setString(2, volTypeSiege.getIdVol());
            stmt.setString(3, volTypeSiege.getIdTypeSiege());
            stmt.executeUpdate();
        }
    }

    // Méthode DELETE (suppression d'un enregistrement)
    public static void delete(Connection connection, String idVol, String idTypeSiege) throws SQLException {
        String sql = "DELETE FROM vol_type_siege WHERE id_vol = ? AND id_type_siege = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idVol);
            stmt.setString(2, idTypeSiege);
            stmt.executeUpdate();
        }
    }
}
