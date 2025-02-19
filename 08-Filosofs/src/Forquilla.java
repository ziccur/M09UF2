public class Forquilla {
    private final int id;
    private volatile boolean enUs;

    public Forquilla(int id) {
        this.id = id;
        this.enUs = false;
    }

    public boolean agafar() {
        if (!enUs) {
            enUs = true;
            return true;
        }
        return false;
    }

    public void deixar() {
        enUs = false;
    }

    public int getId() {
        return id;
    }
}