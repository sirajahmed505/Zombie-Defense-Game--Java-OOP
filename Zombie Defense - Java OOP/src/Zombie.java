
import javafx.scene.image.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import java.util.Random;
import javafx.scene.shape.Path;
import javafx.animation.*;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import java.util.ArrayList;
import javafx.util.Duration;
public class Zombie {

    Random rd = new Random();
    private int health;
    private int speed;
    private final int damage;
    private ArrayList<Zombie> enemyArray = new ArrayList<Zombie>();
    private int x_cord;
    private int y_cord;
    private String fileName;
    protected Image img;
    private ImageView imgDisplay;

    public Zombie(int health, int speed, int damage, String fileName, int x_coord, int y_cord) {

        this.health = health;
        this.speed = speed;
        this.damage = damage;
        this.fileName = fileName;
        this.img = new Image(fileName);
        this.imgDisplay = new ImageView(img);
        this.x_cord = x_coord;
        this.y_cord = y_cord;

    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    public int getX_cord() {
        return x_cord;
    }

    public int getY_cord() {
        return y_cord;
    }

    public ImageView getImg() {

        return imgDisplay;
    }
    public ImageView getImgDisplay(){
        return imgDisplay;
    }
    public void setImgDisplay(ImageView imgDisplay) {

        this.imgDisplay = imgDisplay;
    }

    public ArrayList<Zombie> generateEnemies() {
        int rand = rd.nextInt(10);
        for (int i = 0; i < (rand + 10); i++) {
            enemyArray.add(i, new zombieOne());
        }
        return enemyArray;
    }
    public int decrementHealth(){
        health -= 20;
        return health;
    }

}

