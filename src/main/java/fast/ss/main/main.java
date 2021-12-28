package fast.ss.main;

import fast.ss.util.NumberUtils;
import fast.ss.util.WebhookUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class main {


    public static void main(String[] args) throws AWTException, IOException {

        try {
            File cfgfile = new File(".screenshots/cfg.txt");
            if (!cfgfile.createNewFile()) {

            }

            Scanner cfg = new Scanner(new File(".screenshots/cfg.txt"));

            String hookval = cfg.next();
            String name = cfg.next();
            String r = cfg.next();
            String g = cfg.next();
            String b = cfg.next();

            System.out.println("WEBHOOK: " + hookval);

            WebhookUtil webhook = new WebhookUtil(hookval);
            webhook.setContent("Fast Screenshotter");
            webhook.setAvatarUrl("https://your.awesome/image.png");
            webhook.setUsername(name);


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

            webhook.addEmbed(new WebhookUtil.EmbedObject().setTitle("Image: " + file.getName()).setColor(new Color(Integer.parseInt(r), Integer.parseInt(g), Integer.parseInt(b))).setImage(""));
            webhook.execute();


        } catch (IOException e) {
            System.out.println("Err.");
            e.printStackTrace();
        }

    }
}