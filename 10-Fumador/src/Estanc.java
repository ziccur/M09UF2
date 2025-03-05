import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Estanc extends Thread {

  private static Random random = new Random();

  private List<Tabac> tabacs;
  private List<Llumi> llumins;
  private List<Paper> papers;
  private boolean obert = false;

  public Estanc() {
    tabacs = new ArrayList<>();
    llumins = new ArrayList<>();
    papers = new ArrayList<>();
  }

  synchronized public void nouSubministrament() {
    int seleccio = random.nextInt(3);
    switch (seleccio) {
      case 0: afegirTabac(); break;
      case 1: afegirLlumi(); break;
      case 2: afegirPaper(); break;
    }

    notifyAll();
  }

  public void afegirTabac() { 
    System.out.println("Afegint Tabac");
    tabacs.add(new Tabac()); 
  }
  
  public void afegirLlumi() { 
    System.out.println("Afegint Llumi");
    llumins.add(new Llumi()); 
  }
  
  public void afegirPaper() { 
    System.out.println("Afegint Paper");
    papers.add(new Paper()); 
  }

  public Tabac vendreTabac() { 
    if (tabacs.size() > 0) {
      return tabacs.remove(tabacs.size() - 1);
    }
    else return null;
  }

  public Llumi vendreLlumi() { 
    if (llumins.size() > 0) return llumins.remove(llumins.size() - 1);
    else return null;
  }

  public Paper vendrePaper() { 
    if (papers.size() > 0) return papers.remove(papers.size() - 1);
    else return null;
  }

  @Override
  public void run() {

    obert = true;
    System.out.println("Estanc obert");

    while (obert) {
      nouSubministrament();
      
      try {
        Thread.sleep(random.nextInt(1000) + 500);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    System.out.println("Estanc tancat");

  }

  public void tancarEstanc() { obert = false; }

}