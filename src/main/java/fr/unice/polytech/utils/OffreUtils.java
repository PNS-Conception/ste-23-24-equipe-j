package fr.unice.polytech.utils;

import fr.unice.polytech.offre.CreneauDirector;
import fr.unice.polytech.offre.ExceptionelBuilder;
import fr.unice.polytech.offre.ICreneau;
import fr.unice.polytech.offre.QuotidienBuilder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class OffreUtils {
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

    public static LocalDate convertStringToLocaleDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate date = LocalDate.parse(input, formatter);
            return date;
        } catch (DateTimeParseException e) {
            LocalDate currentDate = LocalDate.now();
            return currentDate.with(TemporalAdjusters.next(getDayOfTheWeek(input)));

        }
    }
    public static  Date convertStringToDate(String input){
        LocalDate local=convertStringToLocaleDate(input);
        if(local!=null)
            return  new Date(local);
        else return  null;

    }
    public static List<ICreneau> createCreneaus(String arg0, List<List<String>>iCreneauList, CreneauDirector creneauDirector){
        List<ICreneau>creneaus=new ArrayList<>();
        switch (arg0) {
            case "quotidien" -> {
                QuotidienBuilder quotidienBuilder = new QuotidienBuilder();
                creneauDirector.change(quotidienBuilder);
                for (List<String> creneau : iCreneauList
                ) {
                    creneauDirector.make(creneau);
                    creneaus.add(quotidienBuilder.getResult());


                }
            }
            case "exceptionnel" -> {
                ExceptionelBuilder exceptionelBuilder = new ExceptionelBuilder();
                creneauDirector.change(exceptionelBuilder);
                for (List<String> creneau : iCreneauList
                ) {
                    creneauDirector.make(creneau);
                    creneaus.add(exceptionelBuilder.getResult());
                    //

                }

            }
        }
        return creneaus;
    }


}
