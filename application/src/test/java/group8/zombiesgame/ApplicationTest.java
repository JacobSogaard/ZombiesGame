package group8.zombiesgame;

import group8.common.services.IEntityProcessingService;
import group8.common.services.IGamePluginService;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import junit.framework.Test;
import org.netbeans.junit.NbModuleSuite;
import org.netbeans.junit.NbTestCase;
import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import org.openide.util.Lookup;

public class ApplicationTest extends NbTestCase {
    
    private static final String ADD_ENEMY_UPDATES_FILE = "/home/jacob/Dropbox/SDU/4_Semester/Component-based_Software_Engineering/Uge_18/Uge10Modules/application/src/test/enemy/updates.xml";
    private static final String REM_ENEMY_UPDATES_FILE = "/home/jacob/Dropbox/SDU/4_Semester/Component-based_Software_Engineering/Uge_18/Uge10Modules/application/src/test/remenemy/updates.xml";
    private static final String UPDATES_FILE = "/home/jacob/netbeans_site/updates.xml";

    public static Test suite() {
        return NbModuleSuite.createConfiguration(ApplicationTest.class).
                gui(false).
                failOnMessage(Level.WARNING). // works at least in RELEASE71
                failOnException(Level.INFO).
                enableClasspathModules(false). 
                clusters(".*").
                suite(); // RELEASE71+, else use NbModuleSuite.create(NbModuleSuite.createConfiguration(...))
    }

    public ApplicationTest(String n) {
        super(n);
    }

    public void testApplication() throws InterruptedException, IOException {
        // pass if there are merely no warnings/exceptions
        /* Example of using Jelly Tools (additional test dependencies required) with gui(true):
        new ActionNoBlock("Help|About", null).performMenu();
        new NbDialogOperator("About").closeByButton();
         */
        
        List<IEntityProcessingService> processors = new CopyOnWriteArrayList<>();
        List<IGamePluginService> plugins = new CopyOnWriteArrayList<>();
        waitForUpdate(processors, plugins);
        
        assertEquals("No plugins", 0, plugins.size());
        assertEquals("No processors", 0, processors.size());
        
        //Test load
        copy(get(ADD_ENEMY_UPDATES_FILE), get(UPDATES_FILE), REPLACE_EXISTING);
        waitForUpdate(processors, plugins);
        
        assertEquals("One plugins", 1, plugins.size());
        assertEquals("One processors", 1, processors.size());
        
        copy(get(REM_ENEMY_UPDATES_FILE), get(UPDATES_FILE), REPLACE_EXISTING);
        waitForUpdate(processors, plugins);
        
        assertEquals("No plugins", 0, plugins.size());
        assertEquals("No processors", 0, processors.size());
//        
    }
    
    private void waitForUpdate(List<IEntityProcessingService> processors, List<IGamePluginService> plugins) throws InterruptedException {
        Thread.sleep(10000);
        processors.clear();
        processors.addAll(Lookup.getDefault().lookupAll(IEntityProcessingService.class));
        
        plugins.clear();
        plugins.addAll(Lookup.getDefault().lookupAll(IGamePluginService.class));
    
    }

}
