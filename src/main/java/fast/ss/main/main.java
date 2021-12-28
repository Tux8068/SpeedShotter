package fast.ss.main;

import fast.ss.util.NumberUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class main {

    public static void main(String [] args) throws AWTException, IOException {

        try {

            Path path = Paths.get(".screenshots");
            Files.createDirectories(path);
            System.out.println(path.toAbsolutePath().getName(1));

            File file = new File(".screenshots/" + NumberUtils.GetDate() + "-" + NumberUtils.GetRng() + ".png");
            ImageIO.write(new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())), "png", file);

            FileWriter logger = new FileWriter(".screenshots/log.txt");

                logger.write(System.lineSeparator());
                logger.write("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + System.lineSeparator());
                logger.write("File: " + file.getName() + System.lineSeparator() + file.getAbsolutePath() + System.lineSeparator());
                logger.write("Created: " + java.time.LocalTime.now() + System.lineSeparator());
                logger.write(System.lineSeparator() + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                logger.close();







        } catch (IOException e) {
            System.out.println("Err.");
            e.printStackTrace();
        }

    }
}
