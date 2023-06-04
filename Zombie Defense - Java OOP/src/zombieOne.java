

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;

public class zombieOne extends Zombie {

    static int speed=100;
    Random rand = new Random();
    private String fileName1 = "file:Assets\\Game\\Zombies\\Zombie_1r.png";
    private String fileName2 = "file:Assets\\Game\\Zombies\\Zombie_2.png";
    private String fileName3 = "file:Assets\\Game\\Zombies\\Zombie_3.png";
    String[] chooseImg = {fileName1,fileName2,fileName3};
    int choose = rand.nextInt(3);
    String chosen = chooseImg[choose];

    zombieOne(){
        super(100,zombieOne.speed-=2,30,"file:Assets\\Game\\Zombies\\Zombie_1r.png",0,350);
    }
}