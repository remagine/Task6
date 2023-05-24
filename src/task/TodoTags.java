package task;

import tag.Tag;

import java.util.HashMap;
import java.util.HashSet;

public class TodoTags {
    private final HashSet<Tag> tags = new HashSet<>();
    private static final TodoTags todoTags = new TodoTags();

    public static TodoTags getTodoTags() {
        return todoTags;
    }
}
