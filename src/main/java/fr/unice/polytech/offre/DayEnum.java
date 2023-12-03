package fr.unice.polytech.offre;

import java.time.DayOfWeek;

public enum DayEnum {
    LUNDI,
   MARDI,
    MERCREDI,
    JEUDI,
    VENDREDI,
    ;


    public static DayOfWeek getDayOfTheWeek(String jour) {
        jour = jour.strip().toLowerCase();
        switch (jour) {
            case "lundi":
                return DayOfWeek.MONDAY;
            case "mardi":
                return DayOfWeek.TUESDAY;
            case "mercredi":
                return DayOfWeek.WEDNESDAY;
            case "jeudi":
                return DayOfWeek.THURSDAY;
            case "vendredi":
                return DayOfWeek.FRIDAY;
            default:
                return null;

        }

    }
}


