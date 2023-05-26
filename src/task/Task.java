package task;

import command.Command;
import tag.EmptyTag;
import tag.Tag;
import tag.ValidTag;

import java.util.concurrent.ConcurrentSkipListSet;

public class Task {
    private static final TaskService taskService = TaskService.getTaskService();
    private final Command command;
    private final Tag tag;

    public Task(Command command, Tag tag) {
        this.command = command;
        this.tag = tag;
    }

    public void execute() {
        ConcurrentSkipListSet<Tag> availableTags = taskService.getAvailableTags();
        Tag result = EmptyTag.EMPTY_TAG;
        switch (command){
            case CREATE:
                result = availableTags.pollFirst();
                if(result == null){
                    result = EmptyTag.EMPTY_TAG;
                }
                break;
            case EXECUTE:
                boolean isValidTag = Tag.validateId(tag);
                if(isValidTag && availableTags.add(tag)){
                    result = tag;
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + command);
        }

        if(EmptyTag.EMPTY_TAG.equals(result)){
            taskService.addFailCount(tag);
        }
    }



}
