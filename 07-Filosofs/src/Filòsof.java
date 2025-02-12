import java.util.Random;

public class Filòsof extends Thread {
    private String nom;
    private Forquilla esquerra;
    private Forquilla dreta;
    private int gana;
    private Random random;

    public Filòsof(String nom, Forquilla esquerra, Forquilla dreta) {
        this.nom = nom;
        this.esquerra = esquerra;
        this.dreta = dreta;
        this.gana = 0;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            pensar();
            menjar();
        }
    }

    private void pensar() {
        System.out.println(nom + " pensant");
        try {
            Thread.sleep(random.nextInt(1000) + 1000); // Pensar entre 1s i 2s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void menjar() {
        while (true) {
            if (agafarForquilla(esquerra)) {
                if (agafarForquilla(dreta)) {
                    System.out.println(nom + " agafa la forquilla esquerra " + esquerra.getNumero());
                    System.out.println(nom + " agafa la forquilla dreta " + dreta.getNumero());
                    System.out.println(nom + " menja");
                    gana = 0;
                    try {
                        Thread.sleep(random.nextInt(1000) + 1000); // Menjar entre 1s i 2s
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    deixarForquilla(esquerra);
                    deixarForquilla(dreta);
                    System.out.println(nom + " ha acabat de menjar");
                    break;
                } else {
                    deixarForquilla(esquerra);
                    System.out.println(nom + " deixa l'esquerra (" + esquerra.getNumero() + ") i espera (dreta ocupada)");
                    gana++;
                    System.out.println(nom + " gana=" + gana);
                }
            }
            try {
                Thread.sleep(random.nextInt(500) + 500); // Esperar entre 0.5s i 1s
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean agafarForquilla(Forquilla forquilla) {
        synchronized (forquilla) {
            if (!forquilla.isEnUs()) {
                forquilla.setEnUs(true);
                return true;
            }
            return false;
        }
    }

    private void deixarForquilla(Forquilla forquilla) {
        synchronized (forquilla) {
            forquilla.setEnUs(false);
        }
    }

    @Override
    public String toString() {
        return "Comensal: " + nom + " esq:" + esquerra.getNumero() + " dret:" + dreta.getNumero();
    }
}