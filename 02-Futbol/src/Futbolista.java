import java.util.Random;

public class Futbolista extends Thread {
    private int ngols;
    private int ntirades;

    // Constantes
    public static final int NUM_JUGADORS = 11;
    public static final int NUM_TIRADES = 20;
    public static final float PROBABILITAT = 0.5f;

    public Futbolista(String name) {
        super(name);
        this.ngols = 0;
        this.ntirades = 0;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < NUM_TIRADES; i++) {
            ntirades++;
            if (random.nextFloat() <= PROBABILITAT) {
                ngols++;
            }
        }
    }

    public int getNgols() {
        return ngols;
    }

    public int getNtirades() {
        return ntirades;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Inici dels xuts --------------------");

        // Crear i iniciar els fils
        Thread[] jugadors = new Thread[NUM_JUGADORS];
        String[] nomJugadors = {"Ramos", "Piqué", "Iniesta", "Xavi", "Casillas", "Villa", "Torres", "Busquets", "Alba", "Silva", "Morata"};
        for (int i = 0; i < NUM_JUGADORS; i++) {
            jugadors[i] = new Futbolista(nomJugadors[i]);
            jugadors[i].start();
        }

        // Esperar que tots els fils acabin
        for (Thread jugador : jugadors) {
            jugador.join();
        }

        System.out.println("Fi dels xuts -----------------------");

        // Mostrar estadístiques
        System.out.println("--- Estadístiques ------");
        for (Thread jugador : jugadors) {
            Futbolista f = (Futbolista) jugador;
            System.out.printf("%s -> %d gols \n", f.getName(), f.getNgols());
        }
    }
}
    