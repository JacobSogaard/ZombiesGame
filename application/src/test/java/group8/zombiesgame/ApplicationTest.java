package group8.zombiesgame;

import group8.common.services.IEntityProcessingService;
import group8.common.services.IGamePluginService;
import group8.common.services.IStandardCollisionService;
import group8.commonenemy.services.IPathFinderService;
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
    
    private static final String ADD_ENEMY_UPDATES_FILE = "/home/jacob/Desktop/ProjectCode/ZombiesGame/application/src/test/add/updatesEnemy.xml";
    private static final String ADD_COLLISION_UPDATES_FILE = "/home/jacob/Desktop/ProjectCode/ZombiesGame/application/src/test/add/updatesCollision.xml";
    private static final String ADD_AI_UPDATES_FILE = "/home/jacob/Desktop/ProjectCode/ZombiesGame/application/src/test/add/updatesAI.xml";
    private static final String REM_ENEMY_UPDATES_FILE = "/home/jacob/Desktop/ProjectCode/ZombiesGame/application/src/test/remove/updatesEnemy.xml";
    private static final String REM_COLLISION_UPDATES_FILE = "/home/jacob/Desktop/ProjectCode/ZombiesGame/application/src/test/remove/updatesCollision.xml";
    private static final String REM_AI_UPDATES_FILE = "/home/jacob/Desktop/ProjectCode/ZombiesGame/application/src/test/remove/updatesAI.xml";
    private static final String UPDATES_FILE = "/home/jacob/Desktop/ProjectCode/ZombiesGame/netbeans_site/updates.xml";

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
//        
//        List<IEntityProcessingService> processors = new CopyOnWriteArrayList<>();
//        List<IGamePluginService> plugins = new CopyOnWriteArrayList<>();
//        List<IStandardCollisionService> collision = new CopyOnWriteArrayList<>();
//        List<IPathFinderService> path = new CopyOnWriteArrayList<>();
//        
//        waitForUpdate(processors, plugins, collision, path);
//        
//        //Add enemy
//        copy(get(ADD_ENEMY_UPDATES_FILE), get(UPDATES_FILE), REPLACE_EXISTING);
//        waitForUpdate(processors, plugins, collision, path);
//        
//        assertEquals("Add enemy plugin", 4, plugins.size());
//        assertEquals("Add enemy processor", 8, processors.size());
//        
//        //Add AI
//        copy(get(ADD_AI_UPDATES_FILE), get(UPDATES_FILE), REPLACE_EXISTING);
//        waitForUpdate(processors, plugins, collision, path);
//        
//        assertEquals("Add AI", 1, path.size());
//        
//        //Add collision
//        copy(get(ADD_COLLISION_UPDATES_FILE), get(UPDATES_FILE), REPLACE_EXISTING);
//        waitForUpdate(processors, plugins, collision, path);
//        
//        assertEquals("Add collision", 1, collision.size());
//        
//        
//        //Remove collision
//        copy(get(REM_COLLISION_UPDATES_FILE), get(UPDATES_FILE), REPLACE_EXISTING);
//        waitForUpdate(processors, plugins, collision, path);
//        
//        assertEquals("No collision", 0, collision.size());
//        
//        //Remove AI
//        copy(get(REM_AI_UPDATES_FILE), get(UPDATES_FILE), REPLACE_EXISTING);
//        waitForUpdate(processors, plugins, collision, path);
//        
//        assertEquals("No AI", 0, path.size());
//        
//        //Remove enemy
//        copy(get(REM_ENEMY_UPDATES_FILE), get(UPDATES_FILE), REPLACE_EXISTING);
//        waitForUpdate(processors, plugins, collision, path);
//        
//        assertEquals("No enemy plugin", 3, plugins.size());
//        assertEquals("No enemy processor", 7, processors.size());
//        
//        
//        
    }
    
    private void waitForUpdate(List<IEntityProcessingService> processors, List<IGamePluginService> plugins, 
            List<IStandardCollisionService> collision, List<IPathFinderService> path) throws InterruptedException {
        
        Thread.sleep(10000);
        
        processors.clear();
        processors.addAll(Lookup.getDefault().lookupAll(IEntityProcessingService.class));
        
        plugins.clear();
        plugins.addAll(Lookup.getDefault().lookupAll(IGamePluginService.class));
        
        collision.clear();
        collision.addAll(Lookup.getDefault().lookupAll(IStandardCollisionService.class));
        
        path.clear();
        path.addAll(Lookup.getDefault().lookupAll(IPathFinderService.class));
    
    }

}
