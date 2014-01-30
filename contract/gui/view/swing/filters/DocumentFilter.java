package contract.gui.view.swing.filters;
import java.io.File;
import javax.swing.filechooser.*;

/**
 * DocumentFilter class creates special case FileFilter for
 * File Chooser. Allowed formats are doc, docx, xls, xlsx and pdf.
 * 
 * @author Bartosz Kratochwil (bartosz.kratochwil@hp.com)
 * @version 1.0
 */
public class DocumentFilter extends FileFilter {

    /**
     * This method return true only for specified document files. If 
     * file extension is different, file is not accepted and false is returned.
     */
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        
        //based on file extension returns true or false
        String extension = Utils.getExtension(f);
        if (extension != null) {
            if (extension.equals(Utils.doc) ||
                extension.equals(Utils.docx) ||
                extension.equals(Utils.pdf) ||
                extension.equals(Utils.xls) ||
                extension.equals(Utils.xlsx)) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    /**
     * Returns string description of the custom filter.
     */
    public String getDescription() {
        return "Allowed document files";
    }
}
