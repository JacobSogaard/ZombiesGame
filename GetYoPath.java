
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author kasper
 */
public class GetYoPath {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
	//Locate the working directory
	String wd = System.getProperty("user.dir");

	//Locate the bundles file
	String bundlesPath = wd +"/SilentUpdate/src/main/resources/org/netbeans/modules/autoupdate/silentupdate/resources/Bundle.properties";
        Path path = Paths.get(bundlesPath);
        Charset charset = StandardCharsets.UTF_8;

        String content = new String(Files.readAllBytes(path), charset);
	
        String subContent = content.replaceAll("(:///)[^&]*(/ZombiesGame)", "://"+wd);
        //content = content.replaceAll("bar", ":///");
        Files.write(path, subContent.getBytes(charset));
	System.out.println(subContent);
    }
    
}
