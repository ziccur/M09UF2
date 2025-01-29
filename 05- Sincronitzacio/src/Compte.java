public class Compte {

    private float saldo;
    private static final Compte instance = new Compte(); // Inicialización estática (segura en multihilo)

    private Compte() {
        this.saldo = 0;
    }

    public static Compte getInstance() {
        return instance;
    }

    public synchronized void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public synchronized float getSaldo() {
        return saldo;
    }

    public synchronized void ingresar(float cantidad) {
        saldo += cantidad;
    }

    public synchronized void retirar(float cantidad) {
        saldo -= cantidad;
    }
}