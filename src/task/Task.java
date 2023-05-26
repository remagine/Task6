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
        Tag doneTag = EmptyTag.EMPTY_TAG;
        switch (command){
            case CREATE:
                Tag minTag = availableTags.pollFirst();
                if(minTag != null){
                    doneTag = minTag;
                }
                break;
            case EXECUTE:
                boolean isValidTag = Tag.validateId(tag);
                if(isValidTag && availableTags.add(tag)){
                    doneTag = tag;
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + command);
        }

        if(EmptyTag.EMPTY_TAG.equals(doneTag)){
            taskService.addFailCount(tag);
        }
    }



}
