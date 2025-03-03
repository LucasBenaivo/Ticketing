package org.itu.ticketing.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AvionView {
    private String id;
    private String idModele;
    private String nomModele;
    private Date dateFabrication;

    public AvionView() {}

    public AvionView(String id, String idModele, String nomModele, Date dateFabrication) {
        this.id = id;
        this.idModele = idModele;
        this.nomModele = nomModele;
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

    public Date getDateFabrication() {
        return dateFabrication;
    }

    public void setDateFabrication(Date dateFabrication) {
        this.dateFabrication = dateFabrication;
    }

    public static List<AvionView> getAll(Connection connection) throws SQLException {
        String sql = "SELECT id, id_modele, nom_modele, date_fabrication FROM avion_v ORDER BY id";
        List<AvionView> avions = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                AvionView avion = new AvionView();
                avion.setId(rs.getString("id"));
                avion.setIdModele(rs.getString("id_modele"));
                avion.setNomModele(rs.getString("nom_modele"));
                avion.setDateFabrication(rs.getDate("date_fabrication"));
                avions.add(avion);
            }
        }
        return avions;
    }
}

