package Calendar.exceptions;


public class EntityNotFoundException extends RuntimeException{
    private String description;
    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "EntityNotFoundException{" +
                "description='" + description + '\'' +
                '}';
    }
}
