public class Principal {
    public static void main(String[] args) {
        // Comportament 1: Execució intercalada
        System.out.println("Comportament 1:");
        executarIntercalada();

        // Comportament 2: Primer Pepe, després Juan
        System.out.println("\nComportament 2:");
        executarPrimerPepeDespresJuan();

        // Comportament 3: Execució alterna estricta
        System.out.println("\nComportament 3:");
        executarAlternaEstricta();
    }

    public static void executarIntercalada() {
        Fil juan = new Fil("Juan", false);
        Fil pepe = new Fil("Pepe", false);

        System.out.println("Termina thread main");

        juan.start();
        pepe.start();

        try {
            juan.join();
            pepe.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void executarPrimerPepeDespresJuan() {
        Fil pepe = new Fil("Pepe", false);
        Fil juan = new Fil("Juan", false);

        System.out.println("Termina thread main");

        // Donem prioritat a Pepe
        pepe.setPriority(10);
        juan.setPriority(1);

        pepe.start();
        juan.start();
        
        try {
            pepe.join();
            juan.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void executarAlternaEstricta() {
        System.out.println("Termina thread main");

        // Crear instancias de los hilos
        Fil juan = new Fil("Juan", true);
        Fil pepe = new Fil("Pepe",  true);

        // Iniciar los hilos
        juan.start();
        pepe.start();

        // Esperar a que ambos hilos terminen
        try {
            juan.join();
            pepe.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
