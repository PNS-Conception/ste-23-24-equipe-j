package fr.unice.polytech.exceptions;

public class STEException extends Exception {


    private final String errorTitle;

    public STEException(String message) {
        super(message);
        this.errorTitle = "An error has occurred";
    }

    public String getErrorTitle() {
        return this.errorTitle;
    }


}
