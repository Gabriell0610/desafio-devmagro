package exceptions;

public class InvalidEmailException extends Exception{

    public InvalidEmailException(String mensage) {
        super(mensage);
    }
}
