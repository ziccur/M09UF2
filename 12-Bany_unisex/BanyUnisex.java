import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class BanyUnisex {
    private static final int BUIT = 0;
    private static final int HOMES = 1;
    private static final int DONES = 2;
    private static final int MAXIM = 3;
    
    private int estat;
    private int personesDins;
    private Semaphore semaforOcupacio;
    private ReentrantLock mutex;
    
    public BanyUnisex() {
        this.estat = BUIT;
        this.personesDins = 0;
        this.semaforOcupacio = new Semaphore(MAXIM, true);
        this.mutex = new ReentrantLock(true);
    }
    
    public void entrarHome(String nom) throws InterruptedException {
        boolean dins = false;
        while (!dins) {
            mutex.lock();
            try {
                if (estat == BUIT || estat == HOMES) {
                    if (semaforOcupacio.tryAcquire()) {
                        if (estat == BUIT) {
                            estat = HOMES;
                        }
                        personesDins++;
                        System.out.println(nom + " ha entrat al bany. Total: " + personesDins);
                        dins = true;
                    }
                }
            } finally {
                mutex.unlock();
            }
            
            if (!dins) {
                Thread.sleep(30);
            }
        }
    }
    
    public void entrarDona(String nom) throws InterruptedException {
        boolean dins = false;
        while (!dins) {
            mutex.lock();
            try {
                if (estat == BUIT || estat == DONES) {
                    if (semaforOcupacio.tryAcquire()) {
                        if (estat == BUIT) {
                            estat = DONES;
                        }
                        personesDins++;
                        System.out.println(nom + " ha entrat al bany. Total: " + personesDins);
                        dins = true;
                    }
                }
            } finally {
                mutex.unlock();
            }
            
            if (!dins) {
                Thread.sleep(30);
            }
        }
    }
    
    public void sortirHome(String nom) {
        mutex.lock();
        try {
            personesDins--;
            semaforOcupacio.release();
            System.out.println(nom + " ha sortit del bany. Queden: " + personesDins);
            if (personesDins == 0) {
                estat = BUIT;
                System.out.println("Bany buit");
            }
        } finally {
            mutex.unlock();
        }
    }
    
    public void sortirDona(String nom) {
        mutex.lock();
        try {
            personesDins--;
            semaforOcupacio.release();
            System.out.println(nom + " ha sortit del bany. Queden: " + personesDins);
            if (personesDins == 0) {
                estat = BUIT;
                System.out.println("Bany buit");
            }
        } finally {
            mutex.unlock();
        }
    }
    
    public static void main(String[] args) {
        BanyUnisex bany = new BanyUnisex();
        
        // Creem i iniciem els threads
        Thread[] threadsHomes = new Thread[5];
        Thread[] threadsDones = new Thread[5];
        
        for (int i = 0; i < 5; i++) {
            final int id = i;
            threadsHomes[i] = new Thread(() -> {
                try {
                    bany.entrarHome("Home" + id);
                    Thread.sleep(200); // Simula temps al bany
                    bany.sortirHome("Home" + id);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            
            threadsDones[i] = new Thread(() -> {
                try {
                    bany.entrarDona("Dona" + id);
                    Thread.sleep(200); // Simula temps al bany
                    bany.sortirDona("Dona" + id);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        
        // Iniciem els threads
        for (int i = 0; i < 5; i++) {
            threadsHomes[i].start();
        }
        
        try {
            Thread.sleep(150); // Petita pausa
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        for (int i = 0; i < 5; i++) {
            threadsDones[i].start();
        }
        
        // Esperem que acabin
        for (int i = 0; i < 5; i++) {
            try {
                threadsHomes[i].join();
                threadsDones[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}