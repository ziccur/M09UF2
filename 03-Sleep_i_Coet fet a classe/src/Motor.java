
import java.util.Random;

public class Motor extends Thread {
    
    private int potenciaObjectiu = 0;
    private int potenciaActual = 0;
    Random rand = new Random();

    public Motor(String name){
        super(name);
    }

    public void setPotencia(int p){
        if(p >= 0 && p <= 10){
            potenciaObjectiu = p;
        }
    }

    public int getPotencia(){
        return potenciaActual;
    }

    @Override
    public void run(){
        String estat;

        while(true){
            System.out.printf("");
            while(potenciaActual != potenciaObjectiu){
                estat = "FerRes";
                if(potenciaActual < potenciaObjectiu){
                    potenciaActual++;
                    estat = "Incre.";
                }else{
                    potenciaActual--;
                    estat = "Decre.";
                }
                System.out.printf("%s: %s Objectiu: %d Actual: %d\n", this.getName(), estat, potenciaObjectiu, potenciaActual);
                try {
                    Thread.sleep(1000 + rand.nextInt(1000));
                } catch (InterruptedException e) {
                    System.out.printf("Error configurant la potencia: %s\n", e.getMessage());
                }
            }
            if(potenciaActual == 0 && potenciaObjectiu == 0){
                System.out.printf("%s: Apagat\n", this.getName());
                break;
            }
        }
    }
}
