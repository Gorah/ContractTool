package contract.gui.view.swing.filters;
import java.io.File;
import javax.swing.ImageIcon;

/**
 * Utils is a support class providing file extension 
 * and holding allowed file extension types.
 * 
 * @author Bartosz Kratochwil (bartosz.kratochwil@hp.com)
 * @version 1.0
 */
public class Utils {
    public final static String doc = "doc";
    public final static String docx = "docx";
    public final static String xls = "xls";
    public final static String xlsx = "xlsx";
    public final static String pdf = "pdf";

    /*
     * Get the extension of a file.
     */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
}
