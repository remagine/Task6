import task.Task;
import task.TaskService;
import command.Command;
import commandandtag.CommandAndTag;
import tag.EmptyTag;
import tag.Tag;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        String data = "21\n" +
                "create\n" +
                "create\n" +
                "create\n" +
                "create\n" +
                "execute 11\n" +
                "create\n" +
                "create\n" +
                "create\n" +
                "create\n" +
                "create\n" +
                "create\n" +
                "execute 2\n" +
                "create\n" +
                "execute 2\n" +
                "execute 11\n" +
                "execute 2\n" +
                "execute 5\n" +
                "execute 5\n" +
                "execute 2\n" +
                "execute 5\n" +
                "execute 5";
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        TaskService taskService = TaskService.getTaskService();
        try(InputStream inputStream = new ByteArrayInputStream(bytes);
            InputStream bufferedInputStream = new BufferedInputStream(inputStream, 8192);
            InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(inputStreamReader, 8192);
        ){
            int count = Integer.parseInt(br.readLine());
            while(true){
                String input = br.readLine();
                if(input == null){
                    break;
                }
                String[] inputArray = input.split(" ");
                Command command = Command.from(inputArray[0]);
                Tag tag = EmptyTag.EMPTY_TAG;
                if(inputArray.length == 2){
                    tag = Tag.from(inputArray[1]);
                }
                CommandAndTag commandAndTag = new CommandAndTag(command, tag);

                // task 처리는
                // 처리 대상 생성 > Task 생성
                    // 처리대상이란 무엇인가?
                    // tag이다.
                    // create라면 tag를 받아오고
                    // execute라면 tag를 넣어준다.
                // 처리 대상 실행 > Task 실행
                    // create
                    // execute[tag]
                // execute(emptyTag) = 가장작은 tag
                    // 가장작은 tag가 null이면 실패 > 처리된 태그가 없다.
                // execute(tag) =
                    // 할일 목록에 tag가 있으면 성공
                    // 할일 목록에 tag가 없으면 실패 > 처리된 태그가 없다.
                // 실패 처리 > Fail 처리
                // 3가지로 구조화 할 수 있다고 생각한다.
                // 어떻게 공통으로 처리 할 수 있지?

                // 할일(Task)을 만들자
                Task task = TaskService.createTask(commandAndTag);
                // 할일을 처리하자
                task.execute();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}