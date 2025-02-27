
import java.util.Random;

public class Fumador extends Thread {

    Random  random = new Random();
    
    Estanc estanc;
    Boolean segueixFumant = true;

    int id;
    Tabac tabac;
    Paper paper;
    Llumi llumi;

    int fumades;

    public Fumador(int id, Estanc estanc) {
        this.id = id;
        this.estanc = estanc;
        fumades = 0;
    }

    public void fuma(){
        if(tabac != null && paper != null && llumi != null){
            System.out.println("Fumador " + id + " fumant...");
            fumades++;
            tabac = null;
            paper = null;
            llumi = null;
        }

        try {
            Thread.sleep((long) (500 + random.nextInt(501)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }  
    }

    public void compraTabac(){
        tabac = estanc.venTabac();
    }

    public void compraPaper(){
        paper = estanc.venPaper();
    }

    public void compraLlumi(){
        llumi = estanc.venLlumi();
    }

    public void run(){
        while(segueixFumant){
            if(tabac == null){
                compraTabac();
            }else{
                if(paper == null){
                    compraPaper();
                }else{
                    if(llumi == null){
                        compraLlumi();
                    }
                }
            }
            fuma();
            if(fumades >= 3 ){
                segueixFumant = false;
            }
        }
    }


}
