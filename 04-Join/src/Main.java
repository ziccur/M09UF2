import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Treballador extends Thread {
    private final double nouAnualBrut;
    private final int edatIniciTreball;
    private final int edatFiTreball;
    private int edatActual;
    private double cobrat;
    private final Random rnd;

    public Treballador(double nouAnualBrut, int edatIniciTreball, int edatFiTreball, String nom) {
        super(nom);
        this.nouAnualBrut = nouAnualBrut;
        this.edatIniciTreball = edatIniciTreball;
        this.edatFiTreball = edatFiTreball;
        this.edatActual = 0;
        this.cobrat = 0.0;
        this.rnd = new Random();
    }

    public void cobra() {
        this.cobrat += nouAnualBrut / 12.0;
    }

    public void pagaImpostos() {
        this.cobrat -= (nouAnualBrut / 12.0) * 0.24;
    }

    @Override
    public void run() {
        for (int edat = edatIniciTreball; edat <= edatFiTreball; edat++) {
            cobra();
            pagaImpostos();
            edatActual = edat;
            try {
                Thread.sleep(rnd.nextInt(100)); // Simula el pas del temps
            } catch (InterruptedException e) {
                System.out.println(getName() + " ha estat interromput.");
            }
        }
    }

    public double getCobrat() {
        return cobrat;
    }

    public int getEdat() {
        return edatActual;
    }
}

class Administracio {
    private final int numPoblacioActiva = 50;
    private final List<Treballador> poblacioActiva;

    public Administracio() {
        poblacioActiva = new ArrayList<>();
        for (int i = 0; i < numPoblacioActiva; i++) {
            poblacioActiva.add(new Treballador(25000, 20, 65, "Ciutada-" + i));
        }
    }

    public void simula() {
        for (Treballador treballador : poblacioActiva) {
            treballador.start();
        }

        for (Treballador treballador : poblacioActiva) {
            try {
                treballador.join();
            } catch (InterruptedException e) {
                System.out.println("Error esperant " + treballador.getName());
            }
        }

        for (Treballador treballador : poblacioActiva) {
            System.out.printf("%s -> edat: %d / total: %.2f\n", treballador.getName(), treballador.getEdat(), treballador.getCobrat());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Administracio administracio = new Administracio();
        administracio.simula();
    }
}
