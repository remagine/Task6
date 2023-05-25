package task;

import command.Command;
import tag.EmptyTag;
import tag.Tag;
import tag.ValidTag;

public class Task {
    private static final TaskService taskService = TaskService.getTaskService();
    private final Command command;
    private final Tag tag;
    private boolean executeFail;

    public Task(Command command, Tag tag) {
        this.command = command;
        this.tag = tag;
    }

    public Task execute() {
/*        if(tag.equals(EmptyTag.EMPTY_TAG)){
            // 생성실패 카운트 증가
            taskService.addFailCount(tag);
        }*/

        // tag 를 실행한다. = tag를 넣는다.
        // 실행가능 여부를 체크한다.
        ValidTag validTag = Tag.checkValidTag(tag);


        taskService.addToAvailableTags(validTag);

    }
}
