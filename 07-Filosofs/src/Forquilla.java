
public class Forquilla extends Thread {

    private int numero;
    private boolean enUs;

    public Forquilla(String name, int numero) {
        super(name);
        this.numero = numero;
        this.enUs = false;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isEnUs() {
        return enUs;
    }

    public void setEnUs(boolean enUs) {
        this.enUs = enUs;
    }


    

}


