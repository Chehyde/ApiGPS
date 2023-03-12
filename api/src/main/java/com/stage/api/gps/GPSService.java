package com.stage.api.gps;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class GPSService {

    @Autowired
    private GPSRepository repository;

    /**
     * @param id l'élément que l'on veut retourner
     * @return l'élément de la base de donnée à l'id choisi
     */
    public Optional<GPS> getGPS(final int id) { return repository.findById(id); }

    /**
     * @return une liste de tous les éléments présents dans la base de donnée
     */
    public Iterable<GPS> getGPSs() {
        return repository.findAll();
    }

    /**
     * Supprime l'émément sélectioné de la base de donnée
     * @param id id de l'élément à supprimer
     */
    public void deleteGPS(final int id) {
        repository.deleteById(id);
    }

    /**
     * Ajoute un élément à la base de donnée
     * @param gps élément ajouté à la base de donnée
     * @return l'élément ajouté
     */
    public GPS saveGPS(GPS gps) {
        return repository.save(gps);
    }
}
