import files.FileBrowser;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    Map<String, String> ext;
    Permutacion permutacion;
    public Main(){
        permutacion= new Permutacion();
        ext= new HashMap<>();
        ext.put("Desc", "Archivo de Texto");
        ext.put("Ext", ".txt");
        ext.put("Type", "TXT");
    }
    public void readFile(){
        try {
            List<String> listTxt = Files.readAllLines(Paths.get(new FileBrowser().openFile(ext)), StandardCharsets.UTF_8);
            for(String rowTxt :listTxt) {
               String columsRowTxt[]= rowTxt.split("\t");

                List<String> randomDataSeeding = new ArrayList<>();
                String elementos[]={columsRowTxt[2],columsRowTxt[3],columsRowTxt[4]};

              permutacion.Perm2(elementos, "", 3, elementos.length,columsRowTxt[4]+"\t"+columsRowTxt[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void writeFile() {
        try {
            FileWriter fw = new FileWriter(new FileBrowser().saveAs().concat("/revolver_preguntas.txt"), Charset.forName("utf-8"));
            fw.write("CORRECTA\tPREGUNTA\tR1\tR2\tR3\n");
            for (int i = 0; i < permutacion.dataSeeding.size(); i++) {
                fw.write(permutacion.dataSeeding.get(i));
                fw.write("\n");
            }
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            JOptionPane.showMessageDialog(null,"Finalizado");
        }
    }
    public static void main(String[] args) {
        Main m=new Main();
        m.readFile();
         m.writeFile();
    }
}
