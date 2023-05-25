package tag;

public class ValidTag extends Tag {
    private final boolean valid;

    public ValidTag(int id) {
        super(id);
        this.valid = validateId(id);
    }

    private static boolean validateId(int id) {
        return id >= 1 && id <= 9;
    }
}
