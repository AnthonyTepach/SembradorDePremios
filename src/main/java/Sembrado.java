import files.FileBrowser;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Sembrado {
    ArrayList<String> allDataSeeding;
    Map<String, String> ext;
    Random r;
    protected Sembrado() {
        allDataSeeding = new ArrayList<>();
        ext= new HashMap<>();
        ext.put("Desc", "Archivo de Texto");
        ext.put("Ext", ".txt");
        ext.put("Type", "TXT");
        r = new Random();
    }

    protected void writeFile() {
        try {
            JOptionPane.showMessageDialog(null,"Selecciona la ruta donde se va a guardar");
            FileWriter fw = new FileWriter(new FileBrowser().saveAs().concat("/sembrado-sucursales-oblatos.txt"), Charset.forName("utf-8"));
            fw.write("FOLIO\tFOLIO_X_SUCURSAL\tUUID\tSUCURSAL\tPREMIO\tCORRECTA\tPREGUNTA\tR1\tR2\tR3\n");
            for (int i = 0; i < this.allDataSeeding.size(); i++) {
                fw.write(String.format("%06d",(i+1))+"\t"+this.allDataSeeding.get(i));
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            JOptionPane.showMessageDialog(null,"Finalizado");
        }
    }

    protected void  readFile() {
        try {
            JOptionPane.showMessageDialog(null,"Selecciona BD de Premios");
            List<String> listTxt = Files.readAllLines(Paths.get(new FileBrowser().openFile(ext)), StandardCharsets.UTF_8);
            JOptionPane.showMessageDialog(null,"Selecciona BD de Preguntas");
            List<String> questionsTxt = Files.readAllLines(Paths.get(new FileBrowser().openFile(ext)), StandardCharsets.UTF_8);
            for(String rowTxt :listTxt) {
               String columsRowTxt[]= rowTxt.split("\t");
               List<String> dataSeeding = new ArrayList<>();
               List<String> randomDataSeeding = new ArrayList<>();
                for(int i =3;i<columsRowTxt.length;i++){
                    int iteratorByAward = Integer.parseInt(columsRowTxt[i].substring(2));
                    for (int j=0;j<iteratorByAward;j++){
                        dataSeeding.add(columsRowTxt[i]);
                    }
                }

                for(int i=0;i<Integer.parseInt(columsRowTxt[2]);i++){
                    int randomNumber = r.nextInt(dataSeeding.size());
                    int randomQuestion = r.nextInt(questionsTxt.size());
                    randomDataSeeding.add(String.format("%06d",(i+1))+"\t"+columsRowTxt[0]+"\t"+columsRowTxt[1]+"\t"+dataSeeding.get(randomNumber).substring(0,1)+"\t"+questionsTxt.get(randomQuestion));
                    dataSeeding.remove(randomNumber);
                }

                for (String all:randomDataSeeding){
                    allDataSeeding.add(all);
                }
            }
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            e.printStackTrace();//imprime el error en consola
        }

    }
}
