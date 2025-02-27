public class Barri {

    Estanc estanc;
    Fumador[] fumadors = new Fumador[3];
    boolean fumadorsActius = true;

    public Barri() {
        estanc = new Estanc();
        fumadors[0] = new Fumador(0, estanc);
        fumadors[1] = new Fumador(1, estanc);
        fumadors[2] = new Fumador(2, estanc);

        for (Fumador fumador : fumadors) {
            fumador.start();
        }

        estanc.start();

        while (fumadorsActius) {
            fumadorsActius = false;
            for (Fumador fumador : fumadors) {
            if (fumador.isAlive()) {
                fumadorsActius = true;
                break;
            }
            }
        }

    }
    
}
