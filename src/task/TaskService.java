package task;

import commandandtag.CommandAndTag;
import tag.Tag;

import java.util.concurrent.ConcurrentSkipListSet;

public class TaskService {
    private static final TaskService taskService = new TaskService();
    private static final ConcurrentSkipListSet<Tag> availableTags = new ConcurrentSkipListSet<>();

    static {
        for(int i = 1; i < 10 ; i++){
            availableTags.add(new Tag(i));
        }
    }

    public static TaskService getTaskService() {
        return taskService;
    }

    public static Task createTask(CommandAndTag commandAndTag) {
        return Task.from(commandAndTag);
    }


    public static void execute(CommandAndTag commandAndTag) {


    }

    public ConcurrentSkipListSet<Tag> getAvailableTags() {
        return tags;
    }
}
