package task;

import tag.Tag;

import java.util.concurrent.ConcurrentSkipListSet;

public class AvailableTags {
    private static final ConcurrentSkipListSet<Tag> tags = new ConcurrentSkipListSet<>();
    private static final AvailableTags availableTags = new AvailableTags();

    public static AvailableTags getAvailableTags() {
        return availableTags;
    }

    static {
        for(int i = 1; i < 10 ; i++){
            tags.add(new Tag(i));
        }
    }
}
