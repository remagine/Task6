package task;

import command.Command;
import commandandtag.CommandAndTag;
import tag.Tag;

import java.util.concurrent.ConcurrentSkipListSet;

public class Task {
    private final TaskService taskService = TaskService.getTaskService();
    private final ConcurrentSkipListSet<Tag> availableTags = taskService.getAvailableTags();
    private final Command command;
    private final Tag tag;

    public Task(Command command, Tag tag) {
        this.command = command;
        this.tag = tag;
    }

    public static Task from(CommandAndTag commandAndTag) {
        return new Task(commandAndTag.getCommand(), commandAndTag.getTag());
    }

    public Task execute() {







        if(availableTags.contains(tag)){

        }


    }
}
