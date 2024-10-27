public class Main {
    public static void main(String[] args) {
        CommandHandler handler = new CommandHandler();
        System.out.println("Welcome to the Command Line System! Type 'help' for commands.");
        while (true) {
            String currentPath = System.getProperty("user.dir");
            System.out.print("(Command Line) "+currentPath +"> ");
            if (!handler.executeCommand()) {
                break;
            }
            System.out.print('\n');
        }


    }
}