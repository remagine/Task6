package task;

import command.Command;
import commandandtag.CommandAndTag;
import tag.EmptyTag;
import tag.Tag;
import tag.ValidTag;

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
        Tag tag = commandAndTag.getTag();

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

    public void printTaskHistory() {
        int createFailCnt = failHistory.getOrDefault(EmptyTag.EMPTY_TAG, 0);
        failHistory.remove(EmptyTag.EMPTY_TAG);
        System.out.println(createFailCnt);
        System.out.println(availableTags);
        System.out.println(failHistory);
    }
}
