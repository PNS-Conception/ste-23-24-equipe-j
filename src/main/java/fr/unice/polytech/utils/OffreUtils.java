package fr.unice.polytech.utils;

import fr.unice.polytech.offre.CreneauDirector;
import fr.unice.polytech.offre.ExceptionelBuilder;
import fr.unice.polytech.offre.ICreneau;
import fr.unice.polytech.offre.QuotidienBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static fr.unice.polytech.offre.DayEnum.getDayOfTheWeek;

public class OffreUtils {

    private OffreUtils() {
        throw new IllegalStateException("Utility class");
    }
    
    public static LocalDate convertStringToLocaleDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            return LocalDate.parse(input, formatter);
        } catch (DateTimeParseException e) {
            LocalDate currentDate = LocalDate.now();
            return currentDate.with(TemporalAdjusters.next(Objects.requireNonNull(getDayOfTheWeek(input))));

        }
    }
    public static  Date convertStringToDate(String input){
        LocalDate local=convertStringToLocaleDate(input);
        if(local!=null)
            return  new Date(local);
        else return  null;

    }
    public static List<ICreneau> createCreneaus(String typeCreneau, List<List<String>>iCreneauList, CreneauDirector creneauDirector){
        List<ICreneau>creneaus=new ArrayList<>();

        if (typeCreneau.equals("quotidien")) {
            QuotidienBuilder quotidienBuilder = new QuotidienBuilder();
            creneauDirector.change(quotidienBuilder);
            for (List<String> creneau : iCreneauList
            ) {
                creneauDirector.make(creneau);
                creneaus.add(quotidienBuilder.getResult());
            }
        }
        else if (typeCreneau.equals("exceptionnel")) {
            ExceptionelBuilder exceptionelBuilder = new ExceptionelBuilder();
            creneauDirector.change(exceptionelBuilder);
            for (List<String> creneau : iCreneauList
            ) {
                creneauDirector.make(creneau);
                creneaus.add(exceptionelBuilder.getResult());
            }
        }
        return creneaus;
    }


}
