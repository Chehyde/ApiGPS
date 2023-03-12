package com.stage.api.gps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
public class GPSRestController {

    @Autowired
    private GPSService service;

    /**
     * Affiche un message à l'utilisateur lui indiquant la distance entre les deux points sélectionnés
     * @param ids les id des points choisis
     * @return un message d'erreur si un id entré n'a pas d'élément dans la bdd ou un message indiquant la distance entre les deux coordonées
     */
    @PostMapping("/distance")
    public String distance(@ModelAttribute("ids") IDs ids){

        GPS gps1, gps2 = new GPS();
        int id1 = ids.getId1();
        int id2 = ids.getId2();

        // Vérification si les deux ids entrés correspondent bien à un élément de la bdd
        try { gps1 = service.getGPS(id1).get(); }
        catch(NoSuchElementException e) { return "Il n'y a pas d'élément numéro " + id1 + " enregistré."; }
        try { gps2 = service.getGPS(id2).get(); }
        catch(NoSuchElementException e){ return "Il n'y a pas d'élément numéro " + id2 + " enregistré."; }

        // Calcul de la distance entre les deux coordonnées
        double d1 = Math.sin(Math.toRadians(gps1.getLatitude())) * Math.sin(Math.toRadians(gps2.getLatitude()));
        double d2 = Math.cos(Math.toRadians(gps1.getLatitude())) * Math.cos(Math.toRadians(gps2.getLatitude())) * Math.cos(Math.toRadians(gps1.getLongitude() - gps2.getLongitude()));
        double distance = Math.acos(d1 + d2) * 6371; // 6371 km correspond au rayon de la terre

        String coords1 = "(" + gps1.getLatitude() + "°, " + gps1.getLongitude() + "°)";
        String coords2 = "(" + gps2.getLatitude() + "°, " + gps2.getLongitude() + "°)";

        return "La distance entre les points de coordonnées " + coords1 + " et " + coords2 + " est " + (distance >= 10 ? "supérieure" : "inférieure") + " à 10 km. (Distance : " + distance + " km)";
    }

}
