package group8.core.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.openide.modules.ModuleInstall;

/**
 * Class used for setting the basics of the game: size and label.
 * @author group8
 */
public class Installer extends ModuleInstall {    

    private static Game g;

    @Override
    public void restored() {

        g = new Game();

        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "InZombia";
        cfg.width = 800;
        cfg.height = 600;

        new LwjglApplication(g, cfg);
    }
}
