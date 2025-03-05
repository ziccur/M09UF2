public class Barri {

  static private Estanc estanc;
  static private Fumador[] fumadors = new Fumador[3];

  public static void main(String[] args) {

    estanc = new Estanc();

    for (int i = 0; i < 3; i++) {
      fumadors[i] = new Fumador(estanc, i);
    }
    
    for (Fumador f: fumadors) {
      f.start();
    }

    estanc.start();

    for (Fumador f: fumadors) {
      try {
        f.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    estanc.tancarEstanc();

  }
}