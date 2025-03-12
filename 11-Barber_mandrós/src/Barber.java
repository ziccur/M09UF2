public class Barber extends Thread {
    private String name;
    private Barberia barberia;

    public Barber(String name, Barberia barberia) {
        this.name = name;
        this.barberia = barberia;
    }

    @Override
    public void run() {
        while (true) {
            Client client = barberia.seguentClient();
            if (client != null) {
                System.out.println("Li toca al client " + client.getName());
                try {
                    Thread.sleep(900 + (int)(Math.random() * 100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Tallant cabell a " + client.getName());
            } else {
                synchronized (barberia.getCondBarber()) {
                    try {
                        System.out.println("Barber " + name + " dormint");
                        barberia.getCondBarber().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}