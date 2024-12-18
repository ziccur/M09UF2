public class Fil extends Thread {
    private String nom;
    private static boolean turnos; // Controla si se usan turnos o no
    private static volatile String turno = "Juan"; // Controla el turno actual

    public Fil(String nom, boolean turnos) {
        this.nom = nom;
        this.turnos = turnos;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            while (turnos && !turno.equals(nom)) {
                Thread.yield(); // Cede el control si no es el turno del hilo
            }

            // Imprime el número actual
            if(i<10){
                System.out.println(nom + " " + i);
            } else {
                System.out.println("Termina el fil " + nom);
            }

            if (turnos) {
                turno = (nom.equals("Juan")) ? "Pepe" : "Juan"; // Cambia el turno
            }
        }

    }
}
