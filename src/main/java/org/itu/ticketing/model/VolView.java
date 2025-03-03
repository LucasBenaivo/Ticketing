package org.itu.ticketing.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VolView {
    private int volId;
    private int idAvion;
    private int idVilleDepart;
    private int avionId;
    private String idModele;
    private String villeDepart;
    private String villeArrivee;
    private String dateDepart;
    private String dateArrivee;

    public VolView() {}

    public VolView(int volId, int idAvion, int idVilleDepart, int avionId, String idModele, String villeDepart, String villeArrivee, String dateDepart, String dateArrivee) {
        this.volId = volId;
        this.idAvion = idAvion;
        this.idVilleDepart = idVilleDepart;
        this.avionId = avionId;
        this.idModele = idModele;
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
    }

    public int getVolId() {
        return volId;
    }

    public void setVolId(int volId) {
        this.volId = volId;
    }

    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    public int getIdVilleDepart() {
        return idVilleDepart;
    }

    public void setIdVilleDepart(int idVilleDepart) {
        this.idVilleDepart = idVilleDepart;
    }

    public int getAvionId() {
        return avionId;
    }

    public void setAvionId(int avionId) {
        this.avionId = avionId;
    }

    public String getIdModele() {
        return idModele;
    }

    public void setIdModele(String idModele) {
        this.idModele = idModele;
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public String getVilleArrivee() {
        return villeArrivee;
    }

    public void setVilleArrivee(String villeArrivee) {
        this.villeArrivee = villeArrivee;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }

    public String getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(String dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public static List<VolView> getAll(Connection connection) throws SQLException {
        String sql = "SELECT vol_id, id_avion, id_ville_depart, avion_id, id_modele, ville_depart, ville_arrivee, date_depart, date_arrivee FROM vol_v ORDER BY vol_id";
        List<VolView> volDetailsList = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                VolView details = new VolView();
                details.setVolId(rs.getInt("vol_id"));
                details.setIdAvion(rs.getInt("id_avion"));
                details.setIdVilleDepart(rs.getInt("id_ville_depart"));
                details.setAvionId(rs.getInt("avion_id"));
                details.setIdModele(rs.getString("id_modele"));
                details.setVilleDepart(rs.getString("ville_depart"));
                details.setVilleArrivee(rs.getString("ville_arrivee"));
                details.setDateDepart(rs.getString("date_depart"));
                details.setDateArrivee(rs.getString("date_arrivee"));
                volDetailsList.add(details);
            }
        }
        return volDetailsList;
    }
}
