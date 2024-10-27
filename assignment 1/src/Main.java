public class Main {
    public static void main(String[] args) {
        CommandHandler handler = new CommandHandler();
        System.out.println("Welcome to the Command Line System! Type 'help' for commands.");
        while (true) {

            if (!handler.executeCommand()) {
                break;
            }

        }


    }
}