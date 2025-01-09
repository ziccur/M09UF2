import java.util.Random;

public class DormAleatori extends Thread {

    private String name;
    private long creationTime = 1;

    public DormAleatori(String name) {
        super(name);
        this.name = name;
        this.creationTime = System.currentTimeMillis();
    }

    @Override
    public void run() {
        Random rand = new Random();
        for(int i = 0; i < 10; i++){
            int numRandom = rand.nextInt(1000);
            System.err.printf("%s(%d) %dms total  %d \n", name, i,numRandom, System.currentTimeMillis() - creationTime);

            try {
                Thread.sleep(numRandom);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }


    public static void main(String[] args){
        Thread filJoan = new DormAleatori("Joan");
        Thread filPep = new DormAleatori("Pep");

        filJoan.start();
        filPep.start();

        System.out.println("-- Fi de main --");

    }
}
