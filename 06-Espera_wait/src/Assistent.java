import java.util.Random;

public class Assistent extends Thread {

    Esdeveniment esdeveniment;
    Random random = new Random();

    public Assistent(String nom, Esdeveniment esdeveniment) {
        this.setName(nom);
        this.esdeveniment = esdeveniment;
    }

    int numRandom;

    @Override
    public void run(){
        while (true){
            numRandom = random.nextInt(2);
            if(numRandom == 0){
                esdeveniment.ferReserva(this);
            } else {
                esdeveniment.cancelaReserva(this);
            }
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    

}