
import java.util.ArrayList;

public class Taula {
    
    ArrayList<Filosof> comensals;
    ArrayList<Forquilla> forquilles;

    public Taula(int numComensals) {
        comensals = new ArrayList<Filosof>();
        forquilles = new ArrayList<Forquilla>();

        for (int i = 0; i < numComensals; i++) {
            comensals.add(new Filosof("Filosof " + i, this));
        }
        for (int i = 0; i < numComensals; i++) {
            forquilles.add(new Forquilla("Forquilla " + i, i));
        }
    }

    private int[] getForquillesPerPosicioFilosof(int posicio){
        int[] posicioForquilles = new int[2];
        posicioForquilles[0] = posicio;
        posicioForquilles[1] = (posicio + 1) % comensals.size();
        return posicioForquilles;
    }

    public void showTaula(){
        for (int i = 0; i < comensals.size(); i++) {
            int[] forquillesFilosof = getForquillesPerPosicioFilosof(i);
            System.out.printf("%S: esq: %d, dret: %d\n", comensals.get(i).getName(), forquilles.get(forquillesFilosof[0]).getName(), forquilles.get(forquillesFilosof[1]).getName());
        }
    }

    public void cridarATaula(){
        for (Filosof filosof : comensals) {
            filosof.start();
        }
    }





}
