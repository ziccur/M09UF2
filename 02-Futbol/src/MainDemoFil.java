public class MainDemoFil {
    public static void main(String[] args) {
        Thread filActual = Thread.currentThread();

        System.out.println("MainDemoFil.main: ");
        System.out.printf("Prioritat -> %d, Nom -> %s%n", filActual.getPriority(), filActual.getName());
        System.out.printf("toString() -> %s%n", filActual.toString());
    }
}
