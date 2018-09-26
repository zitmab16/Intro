
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import javax.management.ObjectInstance;

public class SchuelerBL {

    private ArrayList<Schueler> klasse = new ArrayList<>();

    public void add(Schueler s) {
        klasse.add(s);
    }

    public void save(File f) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));   
        
        for (Schueler schueler : klasse) {
            oos.writeObject(schueler);
        }
        oos.flush();
    }

    public void load(File f) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
        
        Object s=null;
        
        while((s=ois.readObject()) !=null){
            add((Schueler)s);
        }

    }

    public static void main(String[] args) {
        Schueler s1= new Schueler("Hans",LocalDate.of(2000, Month.MARCH, 2));
        Schueler s2= new Schueler("Sepp",LocalDate.of(2000, Month.APRIL, 26));
        
        SchuelerBL sb= new SchuelerBL();
        sb.add(s1);
        sb.add(s2);
        
        File f = new File("./data.csv");
        try{
            sb.save(f);
            sb.load(f);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
