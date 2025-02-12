import java.util.ArrayList;
import java.util.List;

public class Taula {
    private List<Filòsof> filòsofs;
    private List<Forquilla> forquilles;

    public Taula(int numFilòsofs) {
        filòsofs = new ArrayList<>();
        forquilles = new ArrayList<>();

        for (int i = 0; i < numFilòsofs; i++) {
            forquilles.add(new Forquilla(i));
        }

        for (int i = 0; i < numFilòsofs; i++) {
            Forquilla esquerra = forquilles.get(i);
            Forquilla dreta = forquilles.get((i + 1) % numFilòsofs);
            filòsofs.add(new Filòsof("fil" + i, esquerra, dreta));
        }
    }

    public void showTaula() {
        for (Filòsof filòsof : filòsofs) {
            System.out.println(filòsof);
        }
    }

    public void cridarATaula() {
        for (Filòsof filòsof : filòsofs) {
            filòsof.start();
        }
    }
}