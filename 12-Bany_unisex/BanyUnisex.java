import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class BanyUnisex {
    public static final int BANY_BUIT = 0;
    public static final int BANY_AMB_HOMES = 1;
    public static final int BANY_AMB_DONES = 2;
    public static final int CAPACITAT_MAX = 3;
    
    private int estatActual;
    private int ocupants;
    private Semaphore capacitat;
    private ReentrantLock lockEstat;
    
    public BanyUnisex() {
        this.estatActual = BANY_BUIT;
        this.ocupants = 0;
        this.capacitat = new Semaphore(CAPACITAT_MAX, true);
        this.lockEstat = new ReentrantLock(true);
    }
    
    public void entraHome(String nom) {
        boolean entered = false;
        while (!entered) {
            lockEstat.lock();
            try {
                // Permet entrar si el bany està buit o amb homes
                if (estatActual == BANY_BUIT || estatActual == BANY_AMB_HOMES) {
                    if (capacitat.tryAcquire()) {
                        if (estatActual == BANY_BUIT) {
                            estatActual = BANY_AMB_HOMES;
                        }
                        ocupants++;
                        System.out.println("Home entra al bany. Ocupants: " + ocupants);
                        entered = true;
                    }
                }
            } finally {
                lockEstat.unlock();
            }
            if (!entered) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void entraDona(String nom) {
        boolean entered = false;
        while (!entered) {
            lockEstat.lock();
            try {
                // Permet entrar si el bany està buit o amb dones
                if (estatActual == BANY_BUIT || estatActual == BANY_AMB_DONES) {
                    if (capacitat.tryAcquire()) {
                        if (estatActual == BANY_BUIT) {
                            estatActual = BANY_AMB_DONES;
                        }
                        ocupants++;
                        System.out.println("Dona entra en el bany. Ocupants: " + ocupants);
                        entered = true;
                    }
                }
            } finally {
                lockEstat.unlock();
            }
            if (!entered) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void surtHome(String nom) {
        lockEstat.lock();
        try {
            ocupants--;
            System.out.println("Home surt del bany. Ocupants: " + ocupants);
            capacitat.release();
            if (ocupants == 0) {
                estatActual = BANY_BUIT;
                System.out.println("El bany està buit");
            }
        } finally {
            lockEstat.unlock();
        }
    }
    
    public void surtDona(String nom) {
        lockEstat.lock();
        try {
            ocupants--;
            System.out.println("Dona surt del bany. Ocupants: " + ocupants);
            capacitat.release();
            if (ocupants == 0) {
                estatActual = BANY_BUIT;
                System.out.println("El bany està buit");
            }
        } finally {
            lockEstat.unlock();
        }
    }
    
    public static void main(String[] args) {
        BanyUnisex bany = new BanyUnisex();
        Home[] homes = new Home[5];
        Dona[] dones = new Dona[5];
        
        for (int i = 0; i < 5; i++) {
            homes[i] = new Home("Home-" + i, bany);
            dones[i] = new Dona("Dona-" + i, bany);
        }
        
        // Llança primer els fils d'home
        for (int i = 0; i < 5; i++) {
            homes[i].start();
        }
        
        // Petita pausa per permetre que els homes intentin entrar
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        for (int i = 0; i < 5; i++) {
            dones[i].start();
        }
        
        // Espera que tots els fils acabin
        for (int i = 0; i < 5; i++) {
            try {
                homes[i].join();
                dones[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
