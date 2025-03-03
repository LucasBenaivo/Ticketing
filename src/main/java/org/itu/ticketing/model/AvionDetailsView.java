package org.itu.ticketing.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AvionDetailsView {
    private String id;
    private String idModele;
    private String nomModele;
    private String idTypeSiege;
    private String nomTypeSiege;
    private int nombrePlace;
    private String dateFabrication;

    public AvionDetailsView() {}

    public AvionDetailsView(String id, String idModele, String nomModele, String idTypeSiege, String nomTypeSiege, int nombrePlace, String dateFabrication) {
        this.id = id;
        this.idModele = idModele;
        this.nomModele = nomModele;
        this.idTypeSiege = idTypeSiege;
        this.nomTypeSiege = nomTypeSiege;
        this.nombrePlace = nombrePlace;
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

    public String getNomModele() {
        return nomModele;
    }

    public void setNomModele(String nomModele) {
        this.nomModele = nomModele;
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

    public int getNombrePlace() {
        return nombrePlace;
    }

    public void setNombrePlace(int nombrePlace) {
        this.nombrePlace = nombrePlace;
    }

    public String getDateFabrication() {
        return dateFabrication;
    }

    public void setDateFabrication(String dateFabrication) {
        this.dateFabrication = dateFabrication;
    }

    public static List<AvionDetailsView> getAll(Connection connection) throws SQLException {
        String sql = "SELECT id, id_modele, nom_modele, id_type_siege, nom_type_siege, nombre_place, date_fabrication FROM avion_details_v ORDER BY id";
        List<AvionDetailsView> avionDetailsList = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                AvionDetailsView details = new AvionDetailsView();
                details.setId(rs.getString("id"));
                details.setIdModele(rs.getString("id_modele"));
                details.setNomModele(rs.getString("nom_modele"));
                details.setIdTypeSiege(rs.getString("id_type_siege"));
                details.setNomTypeSiege(rs.getString("nom_type_siege"));
                details.setNombrePlace(rs.getInt("nombre_place"));
                details.setDateFabrication(rs.getString("date_fabrication"));
                avionDetailsList.add(details);
            }
        }
        return avionDetailsList;
    }
}

