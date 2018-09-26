
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class SchuelerBL {

    private ArrayList<Schueler> klasse = new ArrayList<>();

    public void add(Schueler s) {
        klasse.add(s);
    }

    public void save(File f) throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));

        for (Schueler schueler : klasse) {
            bw.write(schueler.getName());
            bw.write(";");
            bw.write(schueler.getBirthday().toString());
            bw.newLine();
            
        }
        bw.flush();
    }

    public void load(File f) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(f));

        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(";");
            try {
                add(new Schueler(parts[0], LocalDate.parse(parts[1])));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
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
