import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

public class Turrets {
    private int power;
    private int speed;
    private int cost;
    private int durability;
    private String fileName;
    private Image image;
    private ImageView imageView;
    private String barrelFileName;
    private Image barrelImage;
    private ImageView barrelImageView;
    private int range;
    Duration timeInMilli = new Duration(4000);
    static ArrayList<Zombie> rangeList = new ArrayList<>();
    private Image explosion;
    private String explosionPath;
    private ImageView explosionView;

    public Turrets(int power, int speed, int cost, int durability, String fileName, String barrelFileName,String explosionPath,int range) {
        this.power = power;
        this.speed = speed;
        this.cost = cost;
        this.durability = durability;
        this.fileName = fileName;
        image = new Image(fileName, 100, 100, false, true);
        imageView = new ImageView(image);
        this.barrelFileName = barrelFileName;
        barrelImage = new Image(barrelFileName, 100, 100, false, true);
        barrelImageView = new ImageView(barrelImage);
        this.explosionPath = explosionPath;
        explosion = new Image(explosionPath, 50,50,false,true);
        explosionView = new ImageView(explosion);
        this.range = range;
    }

    public void setTurret(int x, int y) {
        double height = image.getHeight();
        double width = image.getWidth();
        imageView.setX(x - (width / 2));
        imageView.setY(y - (height / 2));
        barrelImageView.setX(x - (width / 2));
        barrelImageView.setY(y - (height / 2));
    }

    public void rotate() {
        RotateTransition rotate = new RotateTransition();
        rotate.setDuration(timeInMilli);
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(360);
        rotate.setNode(this.getBarrelImageView());
        rotate.setCycleCount(-1);
        rotate.setAutoReverse(true);
        rotate.play();
    }

    public boolean buyTurret() {
        return Base.getGameCurrency() >= this.cost;
    }

    public boolean isInRange(Zombie enemy) {
        double distance = Math.sqrt(Math.pow(enemy.getImgDisplay().getX() - this.imageView.getX(), 2) + Math.pow(enemy.getImgDisplay().getY() - this.imageView.getY(), 2));
        return distance <= this.range;
    }

    public void rangeManager(Zombie enemy) {
        if (rangeList.contains(enemy) && (!isInRange(enemy))) {
            rangeList.remove(enemy);
        }
        if (!(rangeList.contains(enemy)) && isInRange(enemy)) {
            rangeList.add(enemy);
        }
    }

    public void rangeRotation() {
        if (!(rangeList.isEmpty())) {
            Zombie enemy = rangeList.get(0);
            double slope = (enemy.getImgDisplay().getY() - this.getImageView().getY() / (enemy.getImgDisplay().getX() - this.getImageView().getX()));
            double angleInRad = Math.atan(slope);
            double angleinDegs = angleInRad * (180 / Math.PI);
            RotateTransition rotate = new RotateTransition();
            rotate.setDuration(timeInMilli);
            rotate.setAxis(Rotate.Z_AXIS);
            rotate.setByAngle(angleinDegs);
            rotate.setNode(this.getBarrelImageView());
            rotate.play();
        }
    }

    public void attackEnemy(Zombie z) {
        if (!(rangeList.isEmpty())) {
            explosionView.setX(rangeList.get(0).getImgDisplay().getX());
            explosionView.setY(rangeList.get(0).getImgDisplay().getY());
            FadeTransition fade = new FadeTransition();
            PauseTransition pause = new PauseTransition(new Duration((int)(60000/this.speed)));
            fade.setDuration(new Duration(1000));
            fade.setFromValue(0);
            fade.setToValue(10);
            fade.setCycleCount(1);
            fade.setAutoReverse(true);
            fade.setNode(this.explosionView);
            fade.play();
            pause.play();
            z.decrementHealth();
            if (z.decrementHealth()==0)
                MainMenu.mainPane.getChildren().remove(z.getImgDisplay());
        }
    }

    public Image getImage() {
        return image;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getCost() {
        return cost;
    }

    public int getDurability() {
        return durability;
    }

    public int getPower() {
        return power;
    }

    public int getSpeed() {
        return speed;
    }

    public String getFileName() {
        return fileName;
    }

    public int getRange() {
        return range;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Image getBarrelImage() {
        return barrelImage;
    }

    public ImageView getBarrelImageView() {
        return barrelImageView;
    }

    public String getBarrelFileName() {
        return barrelFileName;
    }

    public void setBarrelFileName(String barrelFileName) {
        this.barrelFileName = barrelFileName;
    }

    public void setBarrelImage(Image barrelImage) {
        this.barrelImage = barrelImage;
    }

    public void setBarrelImageView(ImageView barrelImageView) {
        this.barrelImageView = barrelImageView;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public ArrayList<Zombie> getRangeList() {
        return rangeList;
    }
}
