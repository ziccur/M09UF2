public class Organitzador {

    public static void main(String[] args) {
        Esdeveniment esdeveniment = new Esdeveniment(5);
        Assistent[] asistenes = new Assistent[10];

        for (int i = 0; i < asistenes.length; i++) {
            asistenes[i] = new Assistent("Asistent-" + i, esdeveniment);
            asistenes[i].start();
        }

    }
}

