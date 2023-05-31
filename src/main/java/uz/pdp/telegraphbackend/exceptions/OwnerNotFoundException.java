package uz.pdp.telegraphbackend.exceptions;

public class OwnerNotFoundException  extends RuntimeException{
    public OwnerNotFoundException(String message) {
        super(message);
    }
}
