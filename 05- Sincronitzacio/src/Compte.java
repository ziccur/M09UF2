
//! SINGLETON

public class Compte {
    
    float saldo;
    private Compte instance = null;

    private Compte() {
        saldo = 0;
    }

    public Compte getInstance() {
        if(instance == null) {
            instance = new Compte();
        }
        return instance;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getSaldo() {
        return saldo;
    }


}
