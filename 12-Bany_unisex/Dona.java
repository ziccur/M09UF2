public class Dona extends Thread {
    private String nom;
    private BanyUnisex bany;
    
    public Dona(String nom, BanyUnisex bany) {
        this.nom = nom;
        this.bany = bany;
    }
    
    @Override
    public void run() {
        System.out.println(nom + " vol entrar al bany");
        bany.entraDona(nom);
        
        // Simula l'ús del bany (entre 2 i 3 segons)
        try {
            Thread.sleep(2000 + (int)(Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        bany.surtDona(nom);
        System.out.println(nom + " ha acabat d'usar el bany");
    }
}
