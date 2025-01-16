import java.util.Scanner;

public class Coet {

    private static Motor[] motors = new Motor[4];
    
    public static void main(String[] args) {
        
        Coet coet = new Coet();
        
        for(int i = 0; i < 4; i++){
            coet.motors[i] = new Motor("Motor " + i);
        }

        // CANVI DE POTENCIES
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int potencia = scanner.nextInt();
            if(((Motor)motors[1]).getPotencia() == 0){
                
                coet.passaAPotencia(potencia);
                coet.arranca();
            }else{
                coet.passaAPotencia(potencia);
            }
        }

    }

    public void passaAPotencia(int p){
        if (p >= 0 && p <= 10){
            System.out.println("Passant a potencia " + p);
            for(int i = 0; i < 4; i++){
                motors[i].setPotencia(p);
            }
        }else{
            System.out.println("La potencia ha d'estar entre 0 i 10");
        }
    }

    public void arranca(){
        for(int i = 0; i < 4; i++){
            motors[i].start();
        }
    }

    public void sleep(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




}
