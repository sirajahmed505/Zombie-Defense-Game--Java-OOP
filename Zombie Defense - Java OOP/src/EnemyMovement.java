
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.awt.event.ActionEvent;
import java.util.Random;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.animation.*;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;

import java.util.ArrayList;

import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class EnemyMovement {

    public static int wave = 0;
    public Path cPath; //chosen path from the path choosing code
    public ArrayList<Zombie> enemyList = new ArrayList<Zombie>();
    Random random = new Random();
    final Path p1 = createPath1();
    final Path p2 = createPath2();
    //Path array to randomly choose from an array
    Path[] paths = {p1, p2};
    AnchorPane mainPane = ViewManager.getMainPane();



    //mini constructor
    public EnemyMovement() {
        wave++;
    }

    public static void waveGenerator() {
        EnemyMovement wave = new EnemyMovement();
        wave.addZombie();
        wave.spawnZombie();
    }

    //adds enemy in the ArrayList for each wave depending on wave number
    //Might need to balance to manage difficulty
    public void addZombie() {
        for (int i = 0; i < wave + 5; i++) {
            Turrets.rangeList.add(new zombieOne());
            enemyList.add(new zombieOne());
            int choosePath = random.nextInt(2) + 1;

            if (wave % choosePath == 0) {
                cPath = p1;   //chosen path
            } else {
                cPath = p1;   //chosen path
            }

        }
    }

    //        this thing basically contains all the movement methods.
//        takes Zombie object as parameter and works individually for each object
//        Some issues with mainPane variable? Can't test
//        IntelliJ is giving me weird error on first line "updateEnemy(Zombie z)", wants me to add a semi colon??
    public void updateEnemy(Zombie z) {
        //PauseTransition pause = new PauseTransition(Duration.millis(5000));
        // PauseTransition just creates a pause between the different animations. Use it if you want to. Maybe to separate waves.
        // PathTransition is the main thing. It's constructors need 3 arguments. Time, Geometric Shape - in our case just a line "p1" created by the createPath method - And the "Node" you want to move
        // Adjust the Time argument to get a speed, the higher it is, the slower the movement and vice versa. It's just the time required to complete the animation.
        PathTransition path1 = new PathTransition(Duration.seconds(3000 / z.getSpeed()), cPath, z.getImgDisplay());

        path1.statusProperty() // Just adding a property to path1. The normal enemy.
                .addListener(new ChangeListener<Animation.Status>() {
                    // Listener is just something in programming that listens to changes in values. In this case, the progress of the animation.
                    @Override
                    public void changed(ObservableValue<? extends Animation.Status> observableValue, Animation.Status status, Animation.Status t1)
                    // Observable value is just the value being observed. In our case, the status of animation. Animation.Status status is just the old value of said observable vale and t1 is the new value.
                    {
                        if (t1 == Animation.Status.STOPPED) // If the animation has stopped. Which happens at the end of our path
                        {
                            mainPane.getChildren().remove(z.getImgDisplay());
                            Base.lowhealth(20);
                            Label health=new Label("Health: "+ (int)Base.getBaseHealth());
                            health.setFont(new Font("Arial",35));health.setTextFill(Color.WHITE); health.setLayoutX(1030);health.setLayoutY(290);
                            mainPane.getChildren().addAll(health);
                            health.setText("Health: " + Base.getBaseHealth());
                            // Remove the image from the screen.
                        }
                    }
                });

        // It is just a sequence of how the animations play out.
        path1.play();
//        pause.play();
        mainPane.getChildren().add(z.getImgDisplay());
    }

    // loops over the arraylist created in add wave and uses updateEnemy method on each element(Zombie object) stored in arrayList
    public void spawnZombie() {
        try {
            //int i=1;
            Iterator itr = enemyList.iterator();
            while (itr.hasNext()) {
                Zombie z1 = (Zombie) itr.next();
                updateEnemy(z1);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


// Create path method is given below. Everything explained there.

    public Path createPath1() {
        // Simple method. Path is just a line in simple terms.
        Path path1 = new Path();
        MoveTo start1 = new MoveTo(30, 260);
        // Move To is the spawn point. It just moves the "Node" to that point instantaneously.
        // Then just lines that use the coordinate System. Pretty standard stuff.
        LineTo line1 = new LineTo(30, 260);
        LineTo line2 = new LineTo(250, 280);
        LineTo line3 = new LineTo(640, 380);
        LineTo line4 = new LineTo(640, 420);
        LineTo line5 = new LineTo(900, 460);
        // Use the addAll method to create our path using all the lines.
        path1.getElements().addAll(start1, line1, line2, line3, line4, line5);
        return path1;


    }

    public Path createPath2() {
        // Simple method. Path is just a line in simple terms.
        Path path1 = new Path();
        MoveTo start1 = new MoveTo(0, 500);
        // Move To is the spawn point. It just moves the "Node" to that point instantaneously.
        // Then just lines that use the coordinate System. Pretty standard stuff.
        LineTo line1 = new LineTo(851, 672);
        LineTo line2 = new LineTo(851, 476);
        LineTo line3 = new LineTo(1139, 476);
        LineTo line4 = new LineTo(1139, 613);
        LineTo line5 = new LineTo(1588, 613);
        // Use the addAll method to create our path using all the lines.
        path1.getElements().addAll(start1, line1, line2, line3, line4, line5);
        return path1;

    }


}

//TODO: Please change Zombie class into a concrete class, get rid of the abstract keyword.
//TODO: Please take a look at the updateEnemy method I dont know why it is giving me that wierd error.
//TODO: Also, can scrap the zombieTwo and ZombieThree since they have lost their purpose, but i suggest leave it there for looks.


//TODO: As I see, there are two paths in the maps. Barricades/Cars are uncrossable. So, use the same technique to make a path for: (0,672) -> (851,672) -> (851,476) -> (1139,476) -> (1139,613) -> (1508,613)
//TODO: Apply this to the ArrayList of enemies. So, we don't hardcode the path of each and every enemy. Find out a way to do this.
//TODO: The horrifying code needs to be written just once. Apply it to the whole class. A suggestion would be making the path a property of the enemy. Each enemy will have its own path.
// But whatever makes it work is good.
//TODO: Create a random number which chooses the path the enemy will take, path1 or path2.

