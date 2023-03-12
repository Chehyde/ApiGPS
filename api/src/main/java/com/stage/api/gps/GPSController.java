package com.stage.api.gps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GPSController {

    @Autowired
    private GPSService service;

    @GetMapping("/")
    public String list(Model model){
        model.addAttribute("gps_list", service.getGPSs());
        model.addAttribute("gps", new GPS());
        model.addAttribute("ids", new IDs());
        return "list";
    }

    /**
     * Ajoute une coordonnée gps à la bdd
     * @param gps la coordonée à ajouter
     * @return un objet ModelAndView qui redirige vers la page d'origine
     */
    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute("gps") GPS gps){
        // Vérification si les coordonnées entrées sont dans le bon format (latitude entre -90 et 90 et longitude entre -180 et 180)
        if(gps.getLatitude() >= -90 && gps.getLatitude() <= 90 && gps.getLongitude() >= -180 && gps.getLongitude() <= 180)
            service.saveGPS(gps);
        return new ModelAndView("redirect:/");
    }

    /**
     * Enlève une coordonnée gps à la bdd
     * @param id l'id de la coordonnée à enlever
     * @return un objet ModelAndView qui redirige vers la page d'origine
     */
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id){
        service.deleteGPS(id);
        return new ModelAndView("redirect:/");
    }

}
