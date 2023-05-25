package task;

import command.Command;
import commandandtag.CommandAndTag;
import tag.Tag;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class TaskService {
    private static final TaskService taskService = new TaskService();
    private static final ConcurrentSkipListSet<Tag> availableTags = new ConcurrentSkipListSet<>();
    private static final ConcurrentHashMap<Tag, Integer> failHistory = new ConcurrentHashMap<>();

    static {
        for(int i = 1; i < 10 ; i++){
            availableTags.add(new Tag(i));
        }
    }

    public static TaskService getTaskService() {
        return taskService;
    }

    public static Task createTask(CommandAndTag commandAndTag) {
        Command command = commandAndTag.getCommand();
        Tag tag;
        switch (command){
            case CREATE:
                tag = availableTags.pollFirst();
                break;
            case EXECUTE:
                tag = commandAndTag.getTag();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + command);
        }

        return new Task(command, tag);
    }

    public ConcurrentSkipListSet<Tag> getAvailableTags() {
        return availableTags;
    }

    public void addFailCount(Tag tag) {
        int count = failHistory.getOrDefault(tag, 0);
        failHistory.put(tag, ++count);
    }

    public boolean addToAvailableTags(Tag tag) {
        if(availableTags.contains(tag)){
            return false;
        }

        availableTags.add(tag);
        return true;
    }
}
