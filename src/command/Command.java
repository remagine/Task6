package command;

public enum Command {
    CREATE("create"), EXECUTE("execute");

    private String commandName;

    Command(String commandName) {
        this.commandName = commandName;
    }

    public static Command from(String input){
        for(Command command : Command.values()){
            if(command.commandName.equals(input)){
                return command;
            }
        }
        throw new IllegalArgumentException("올바른 명령어를 입력하세요.");
    }

}
