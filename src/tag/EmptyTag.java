package tag;



public class EmptyTag extends Tag {
    public static final Tag EMPTY_TAG = new EmptyTag();

    public EmptyTag() {
        super(Integer.MIN_VALUE);
    }
}