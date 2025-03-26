public class Home extends Thread {
    private String nom;
    private BanyUnisex bany;
    
    public Home(String nom, BanyUnisex bany) {
        this.nom = nom;
        this.bany = bany;
    }
    
    @Override
    public void run() {
        System.out.println(nom + " vol entrar al bany");
        bany.entraHome(nom);
        
        // Simula l'ús del bany (entre 1 i 2 segons)
        try {
            Thread.sleep(1000 + (int)(Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        bany.surtHome(nom);
        System.out.println(nom + " ha acabat d'usar el bany");
    }
}
