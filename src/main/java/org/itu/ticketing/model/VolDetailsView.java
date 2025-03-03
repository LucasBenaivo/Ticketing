package org.itu.ticketing.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VolDetailsView {
    private String volId;
    private String idAvion;
    private String idVilleDepart;
    private String avionId;
    private String idModele;
    private String dateFabrication;
    private String villeDepart;
    private String villeArrivee;
    private String idTypeSiege;
    private String nomTypeSiege;
    private Double prix;

    // Constructeur
    public VolDetailsView() {}

    public VolDetailsView(String volId, String idAvion, String idVilleDepart, String avionId, String idModele,
                          String dateFabrication, String villeDepart, String villeArrivee, String idTypeSiege,
                          String nomTypeSiege, Double prix) {
        this.volId = volId;
        this.idAvion = idAvion;
        this.idVilleDepart = idVilleDepart;
        this.avionId = avionId;
        this.idModele = idModele;
        this.dateFabrication = dateFabrication;
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
        this.idTypeSiege = idTypeSiege;
        this.nomTypeSiege = nomTypeSiege;
        this.prix = prix;
    }

    // Getters et Setters
    public String getVolId() {
        return volId;
    }

    public void setVolId(String volId) {
        this.volId = volId;
    }

    public String getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(String idAvion) {
        this.idAvion = idAvion;
    }

    public String getIdVilleDepart() {
        return idVilleDepart;
    }

    public void setIdVilleDepart(String idVilleDepart) {
        this.idVilleDepart = idVilleDepart;
    }

    public String getAvionId() {
        return avionId;
    }

    public void setAvionId(String avionId) {
        this.avionId = avionId;
    }

    public String getIdModele() {
        return idModele;
    }

    public void setIdModele(String idModele) {
        this.idModele = idModele;
    }

    public String getDateFabrication() {
        return dateFabrication;
    }

    public void setDateFabrication(String dateFabrication) {
        this.dateFabrication = dateFabrication;
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

    public String getIdTypeSiege() {
        return idTypeSiege;
    }

    public void setIdTypeSiege(String idTypeSiege) {
        this.idTypeSiege = idTypeSiege;
    }

    public String getNomTypeSiege() {
        return nomTypeSiege;
    }

    public void setNomTypeSiege(String nomTypeSiege) {
        this.nomTypeSiege = nomTypeSiege;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    // Méthode GET ALL pour récupérer toutes les entrées de la vue
    public static List<VolDetailsView> getAll(Connection connection) throws SQLException {
        String sql = "SELECT vol_id, id_avion, id_ville_depart, avion_id, id_modele, date_fabrication, " +
                "ville_depart, ville_arrivee, id_type_siege, nom_type_siege, prix " +
                "FROM vol_details_v ORDER BY vol_id";
        List<VolDetailsView> volDetailsList = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                VolDetailsView details = new VolDetailsView();
                details.setVolId(rs.getString("vol_id"));
                details.setIdAvion(rs.getString("id_avion"));
                details.setIdVilleDepart(rs.getString("id_ville_depart"));
                details.setAvionId(rs.getString("avion_id"));
                details.setIdModele(rs.getString("id_modele"));
                details.setDateFabrication(rs.getString("date_fabrication"));
                details.setVilleDepart(rs.getString("ville_depart"));
                details.setVilleArrivee(rs.getString("ville_arrivee"));
                details.setIdTypeSiege(rs.getString("id_type_siege"));
                details.setNomTypeSiege(rs.getString("nom_type_siege"));
                details.setPrix(rs.getDouble("prix"));
                volDetailsList.add(details);
            }
        }
        return volDetailsList;
    }
}
