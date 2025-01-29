
public class Associacio {
    
    int numSocis = 1000;
    Soci[] socis = new Soci[numSocis];

    public Associacio() {
        for (int i = 0; i < numSocis; i++) {
            socis[i] = new Soci();
        }
    }

    void iniciaCompteTempsSocis(){
        for (int i = 0; i < numSocis; i++) {
            socis[i].start();
        }
    }

    void esperaPeriodeSocis(){
        for (int i = 0; i < numSocis; i++) {
            try {
                socis[i].join();
            } catch (InterruptedException e) {
                System.out.println(socis[i].getName() + " ha estat interromput.");
            }
        }
    }

    void mostraBalancComptes(){
        System.out.println("Saldo final: " + socis[0].getCompte().getSaldo());
    }
    

}
