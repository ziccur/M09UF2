import java.util.Random;

public class Soci extends Thread {

    Compte compte;
    private final float aportacio = 10f;
    private final int esperaMax = 100;
    Random rnd = new Random();
    private final int maxAnys = 10;

    public Soci() {
        this.compte = Compte.getInstance();
    }

    Compte getCompte() {
        return compte;
    }

    @Override
    public void run() {
        for(int i = 0; i < maxAnys; i++) {
            for(int y = 0; y <= 12; y++) {
                try {
                    Thread.sleep(rnd.nextInt(esperaMax)); // Simula el pas del temps
                } catch (InterruptedException e) {
                    System.out.println(getName() + " ha estat interromput.");
                }

                if( y%2 == 0 ){
                    compte.setSaldo(compte.getSaldo() + aportacio);
                } else {
                    compte.setSaldo(compte.getSaldo() - aportacio);
                }

            }
        }
    }

    

    

}
