import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Estanc extends Thread {

    private Random random = new Random();
    private Boolean obert = true;

    private ArrayList<Tabac> tabacs;
    private ArrayList<Paper> papers;
    private ArrayList<Llumi> llumins;

    private Lock lock = new ReentrantLock();

    public Estanc() {
        tabacs = new ArrayList<>();
        papers = new ArrayList<>();
        llumins = new ArrayList<>();
    }

    public void nouSubministrament() {
        lock.lock(); // Adquirir el Lock
        try {
            int item = random.nextInt(3) + 1; // Genera del 0 al 2 sumant 1
            switch (item) {
                case 1 -> addTabac();
                case 2 -> addPaper();
                case 3 -> addLlum();
            }
        } finally {
            lock.unlock(); // Liberar el Lock
        }
    }

    public void addTabac() {
        lock.lock(); // Adquirir el Lock
        try {
            tabacs.add(new Tabac());
        } finally {
            lock.unlock(); // Liberar el Lock
        }
    }

    public void addPaper() {
        lock.lock(); // Adquirir el Lock
        try {
            papers.add(new Paper());
        } finally {
            lock.unlock(); // Liberar el Lock
        }
    }

    public void addLlum() {
        lock.lock(); // Adquirir el Lock
        try {
            llumins.add(new Llumi());
        } finally {
            lock.unlock(); // Liberar el Lock
        }
    }

    public Tabac venTabac() {
        lock.lock(); // Adquirir el Lock
        try {
            if (!tabacs.isEmpty()) {
                Tabac tabac = tabacs.get(0);
                tabacs.remove(0);
                return tabac;
            }
            return null;
        } finally {
            lock.unlock(); // Liberar el Lock
        }
    }

    public Paper venPaper() {
        lock.lock(); // Adquirir el Lock
        try {
            if (!papers.isEmpty()) {
                Paper paper = papers.get(0);
                papers.remove(0);
                return paper;
            }
            return null;
        } finally {
            lock.unlock(); // Liberar el Lock
        }
    }

    public Llumi venLlumi() {
        lock.lock(); // Adquirir el Lock
        try {
            if (!llumins.isEmpty()) {
                Llumi llumi = llumins.get(0);
                llumins.remove(0);
                return llumi;
            }
            return null;
        } finally {
            lock.unlock(); // Liberar el Lock
        }
    }

    public void tancarEstanc() {
        obert = false;
    }

    public void run() {
        while (obert) {
            nouSubministrament();
            try {
                Thread.sleep((long) (500 + random.nextInt(1001)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}