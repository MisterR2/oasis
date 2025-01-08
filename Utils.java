public class Utils {
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Linux")) {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Erro ao limpar o console.");
        }
    }
}
