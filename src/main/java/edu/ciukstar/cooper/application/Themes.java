package edu.ciukstar.cooper.application;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author sergiu
 */
@Named
@ApplicationScoped
public class Themes {
    private List<Theme> choises;

    @PostConstruct
    void init() {
        choises = new ArrayList<>();
        choises.add(new Theme(0, "Aristo", "aristo"));
        choises.add(new Theme(1, "Afterdark", "afterdark"));
        choises.add(new Theme(2, "Afternoon", "afternoon"));
        choises.add(new Theme(3, "Afterwork", "afterwork"));
        choises.add(new Theme(4, "Black-Tie", "black-tie"));
        choises.add(new Theme(5, "Blitzer", "blitzer"));
        choises.add(new Theme(6, "Bluesky", "bluesky"));
        choises.add(new Theme(7, "Bootstrap", "bootstrap"));
        choises.add(new Theme(8, "Casablanca", "casablanca"));
        choises.add(new Theme(9, "Cupertino", "cupertino"));
        choises.add(new Theme(10, "Cruze", "cruze"));
        choises.add(new Theme(11, "Dark-Hive", "dark-hive"));
        choises.add(new Theme(12, "Delta", "delta"));
        choises.add(new Theme(13, "Dot-Luv", "dot-luv"));
        choises.add(new Theme(14, "Eggplant", "eggplant"));
        choises.add(new Theme(15, "Excite-Bike", "excite-bike"));
        choises.add(new Theme(16, "Flick", "flick"));
        choises.add(new Theme(17, "Glass-X", "glass-x"));
        choises.add(new Theme(18, "Home", "home"));
        choises.add(new Theme(19, "Hot-Sneaks", "hot-sneaks"));
        choises.add(new Theme(20, "Humanity", "humanity"));
        choises.add(new Theme(21, "Le-Frog", "le-frog"));
        choises.add(new Theme(22, "Midnight", "midnight"));
        choises.add(new Theme(23, "Mint-Choc", "mint-choc"));
        choises.add(new Theme(24, "Overcast", "overcast"));
        choises.add(new Theme(25, "Pepper-Grinder", "pepper-grinder"));
        choises.add(new Theme(26, "Redmond", "redmond"));
        choises.add(new Theme(27, "Rocket", "rocket"));
        choises.add(new Theme(28, "Sam", "sam"));
        choises.add(new Theme(29, "Smoothness", "smoothness"));
        choises.add(new Theme(30, "South-Street", "south-street"));
        choises.add(new Theme(31, "Start", "start"));
        choises.add(new Theme(32, "Sunny", "sunny"));
        choises.add(new Theme(33, "Swanky-Purse", "swanky-purse"));
        choises.add(new Theme(34, "Trontastic", "trontastic"));
        choises.add(new Theme(35, "UI-Darkness", "ui-darkness"));
        choises.add(new Theme(36, "UI-Lightness", "ui-lightness"));
        choises.add(new Theme(37, "Vader", "vader"));
    }
    
    public List<Theme> getChoises() {
        return new ArrayList<>(choises);
    }
    
}
