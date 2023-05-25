package tag;

public class Tag implements Comparable<Tag> {
    private final int id;

    public Tag(int id) {
        this.id = id;
    }

    public static Tag from(String input) {
        int id = Integer.parseInt(input);
        return new Tag(id);
    }

    public static ValidTag checkValidTag(Tag tag){
        return new ValidTag(tag.id);
    }


    @Override
    public int compareTo(Tag o) {
        return Integer.compare(id, o.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        return id == tag.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
