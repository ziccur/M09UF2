import java.util.ArrayList;
import java.util.List;

public class Esdeveniment {

    List<Assistent> assistents = new ArrayList<Assistent>();
    int placesMaximes;
    int placesDisponibles;

    public Esdeveniment(int placesMaximes) {
        this.placesMaximes = placesMaximes;
        this.placesDisponibles = placesMaximes;
    }

    public synchronized void ferReserva(Assistent assistent) {
        // Esperar mientras no haya plazas disponibles
        while (placesDisponibles == 0) {
            try {
                System.out.printf("%S no ha pogut fer la reserva. Places disponibles: %d\n", assistent.getName(), placesDisponibles);
                wait(); // El hilo espera hasta que se libere una plaza
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Hacer la reserva
        assistents.add(assistent);
        placesDisponibles--;
        System.out.printf("%S ha fet una reserva. Places disponibles: %d\n", assistent.getName(), placesDisponibles);

        // Notificar a los hilos en espera
        notifyAll();
    }

    public synchronized void cancelaReserva(Assistent assistent) {
        // Esperar si no hay reservas para cancelar
        while (!assistents.contains(assistent)) {
            try {
                System.out.printf("%S no ha pogut cancel·lar una reserva inexistents. Places disponibles: %d\n", assistent.getName(), placesDisponibles);
                wait(); // El hilo espera hasta que tenga una reserva
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Cancelar la reserva
        assistents.remove(assistent);
        placesDisponibles++;
        System.out.printf("%S ha cancelat una reserva. Places disponibles: %d\n", assistent.getName(), placesDisponibles);

        // Notificar a los hilos en espera
        notifyAll();
    }
}