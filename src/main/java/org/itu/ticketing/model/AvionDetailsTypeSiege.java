package org.itu.ticketing.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AvionDetailsTypeSiege {
    private String idTypeSiege;
    private String idAvion;
    private int nombrePlace;

    public AvionDetailsTypeSiege() {}

    public AvionDetailsTypeSiege(String idTypeSiege, String idAvion, int nombrePlace) {
        this.idTypeSiege = idTypeSiege;
        this.idAvion = idAvion;
        this.nombrePlace = nombrePlace;
    }

    public String getIdTypeSiege() {
        return idTypeSiege;
    }

    public void setIdTypeSiege(String idTypeSiege) {
        this.idTypeSiege = idTypeSiege;
    }

    public String getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(String idAvion) {
        this.idAvion = idAvion;
    }

    public int getNombrePlace() {
        return nombrePlace;
    }

    public void setNombrePlace(int nombrePlace) {
        this.nombrePlace = nombrePlace;
    }

    public void insert(Connection connection) throws SQLException {
        String sql = "INSERT INTO avion_details_type_siege (id_type_siege, id_avion, nombre_place) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, this.idTypeSiege);
            stmt.setString(2, this.idAvion);
            stmt.setInt(3, this.nombrePlace);
            stmt.executeUpdate();
        }
    }

    public void update(Connection connection) throws SQLException {
        String sql = "UPDATE avion_details_type_siege SET nombre_place = ? WHERE id_type_siege = ? AND id_avion = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, this.nombrePlace);
            stmt.setString(2, this.idTypeSiege);
            stmt.setString(3, this.idAvion);
            stmt.executeUpdate();
        }
    }

    public void delete(Connection connection) throws SQLException {
        String sql = "DELETE FROM avion_details_type_siege WHERE id_type_siege = ? AND id_avion = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, this.idTypeSiege);
            stmt.setString(2, this.idAvion);
            stmt.executeUpdate();
        }
    }

    public static List<AvionDetailsTypeSiege> getAll(Connection connection) throws SQLException {
        String sql = "SELECT id_type_siege, id_avion, nombre_place FROM avion_details_type_siege ORDER BY id_type_siege, id_avion";
        List<AvionDetailsTypeSiege> detailsList = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                AvionDetailsTypeSiege details = new AvionDetailsTypeSiege();
                details.setIdTypeSiege(rs.getString("id_type_siege"));
                details.setIdAvion(rs.getString("id_avion"));
                details.setNombrePlace(rs.getInt("nombre_place"));
                detailsList.add(details);
            }
        }
        return detailsList;
    }
}
