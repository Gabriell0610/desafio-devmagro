package exceptions;

public class DuplicateEmailException extends Exception{

    public DuplicateEmailException(String mensage) {
        super(mensage);
    }
}
