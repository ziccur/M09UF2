import java.util.LinkedList;
import java.util.Queue;

public class Barberia {
    private Queue<Client> salaEspera;
    private int maxCadires;
    private Object condBarber = new Object();
    private static Barberia instance;

    private Barberia(int maxCadires) {
        this.maxCadires = maxCadires;
        this.salaEspera = new LinkedList<>();
    }

    public static Barberia getInstance(int maxCadires) {
        if (instance == null) {
            instance = new Barberia(maxCadires);
        }
        return instance;
    }

    public synchronized Client seguentClient() {
        return salaEspera.poll();
    }

    public synchronized void entrarClient(Client client) {
        if (salaEspera.size() < maxCadires) {
            salaEspera.add(client);
            System.out.println(client.getName() + " en espera");
            synchronized (condBarber) {
                condBarber.notify();
            }
        } else {
            System.out.println("No queden cadires, client " + client.getName() + " se'n va");
        }
    }

    public Object getCondBarber() {
        return condBarber;
    }

    public void execucio() throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            entrarClient(new Client(i));
            Thread.sleep(500);
        }
        Thread.sleep(10000);
        for (int i = 11; i <= 20; i++) {
            entrarClient(new Client(i));
            Thread.sleep(500);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Barberia barberia = Barberia.getInstance(3);
        Barber barber = new Barber("Pepe", barberia);
        barber.start();
        barberia.execucio();
    }
}