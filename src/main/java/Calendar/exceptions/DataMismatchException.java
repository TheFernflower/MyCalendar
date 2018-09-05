package Calendar.exceptions;


public class DataMismatchException extends RuntimeException{
    private String description;

    public DataMismatchException(){

    }

    public DataMismatchException(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DataMismatchException{" +
                "description='" + description + '\'' +
                '}';
    }
}
