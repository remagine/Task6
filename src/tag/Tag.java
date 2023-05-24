package tag;

public class Tag implements Comparable<Tag> {
    private final int id;
    private int failCnt;

    public Tag(int id) {
        this.id = id;
    }

    public static Tag from(String input) {
        int id = Integer.parseInt(input);
        return new Tag(id);
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

        if (id != tag.id) return false;
        return failCnt == tag.failCnt;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + failCnt;
        return result;
    }
}
