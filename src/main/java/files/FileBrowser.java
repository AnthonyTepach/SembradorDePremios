package files;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.Map;

public class FileBrowser {
    JFileChooser f;
    public FileBrowser(){
       f = new JFileChooser();
    }
    public String saveAs(){
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = f.showSaveDialog(null);
        if (result==JFileChooser.CANCEL_OPTION);
        return f.getSelectedFile().toString();
    }
    public String openFile(Map<String,String> typeFile){
        FileNameExtensionFilter filter = new FileNameExtensionFilter(typeFile.get("Desc"), typeFile.get("Ext"),typeFile.get("Type"));
        f.setFileFilter(filter);
        int result = f.showOpenDialog(null);
        File file = f.getSelectedFile();
        if (result==JFileChooser.CANCEL_OPTION);
        return file.getAbsolutePath();
    }
}
