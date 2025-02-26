public class Filosof extends Thread {
    private final int id;
    private final Forquilla esquerra;
    private final Forquilla dreta;
    private long iniciGana;
    private long fiGana;
    private int gana;

    public Filosof(int id, Forquilla esquerra, Forquilla dreta) {
        this.id = id;
        this.esquerra = esquerra;
        this.dreta = dreta;
        this.gana = 0;
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filòsof: fil" + id + " està pensant");
        Thread.sleep((long) (1000 + Math.random() * 1000)); // Temps de pensar entre 1s i 2s
        iniciGana = System.currentTimeMillis(); // Iniciem el comptador de gana
    }

    private void menjar() throws InterruptedException {
        fiGana = System.currentTimeMillis();
        gana = (int) (fiGana - iniciGana) / 1000; // Calculem la gana en segons
        System.out.println("Filòsof: fil" + id + " menja amb gana " + gana);
        Thread.sleep((long) (1000 + Math.random() * 1000)); // Temps de menjar entre 1s i 2s
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();

                // Intentar agafar la forquilla esquerra
                while (!esquerra.agafar()) {
                    System.out.println("Filòsof: fil" + id + " espera la forquilla esquerra " + esquerra.getNum());
                    Thread.sleep((long) (500 + Math.random() * 500)); // Espera entre 0.5s i 1s
                }
                System.out.println("Filòsof: fil" + id + " agafa la forquilla esquerra " + esquerra.getNum());

                // Intentar agafar la forquilla dreta
                while (!dreta.agafar()) {
                    System.out.println("Filòsof: fil" + id + " deixa l'esquerra (" + esquerra.getNum() + ") i espera (dreta ocupada)");
                    esquerra.deixar();
                    Thread.sleep((long) (500 + Math.random() * 500)); // Espera entre 0.5s i 1s
                    while (!esquerra.agafar()) {
                        System.out.println("Filòsof: fil" + id + " espera la forquilla esquerra " + esquerra.getNum());
                        Thread.sleep((long) (500 + Math.random() * 500));
                    }
                }
                System.out.println("Filòsof: fil" + id + " agafa la forquilla dreta " + dreta.getNum());

                menjar();

                // Deixar les forquilles
                dreta.deixar();
                esquerra.deixar();
                System.out.println("Filòsof: fil" + id + " ha acabat de menjar");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}