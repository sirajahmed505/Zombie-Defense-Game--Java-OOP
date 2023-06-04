package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Set;

//Intellij wrote all these^^^^ itself i don't know how
public class MainMenu extends Application implements EventHandler<ActionEvent> {
//the implement thingy is what handles button clicks. Can also be wriiten separetly in the code but this is easier.
    Button NGButton=new Button("New Game");
    //Can also put button text separately by typing button.setText("Text here") in the start method.
    Button LGButton=new Button("Load Game");
    Button SetButton=new Button("Settings");
    Button HTPButton=new Button("How To Play");
    Button EButton=new Button("Exit Game");
    Button LBButton=new Button("Leaderboard");
    Button RButton=new Button("Return");
Image img;

    {
        try {
            img = new Image(new FileInputStream("C:\\Users\\pakcomp\\Desktop\\NUST Study Folder\\Semester-2\\OOP\\OOP Project\\src\\Button.png.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }//IntelliJ wouldnt let me use the image until i used this quick fix. No idea what it does.

    public void start(Stage mainmenu) {
        mainmenu.setTitle("Tower Defense game les gooooo");
        Pane layout = new Pane();
        Scene MMScene = new Scene(layout, 800, 600);


        layout.getChildren().add(NGButton);//Idk what this is it just adds the button.
        NGButton.setLayoutX(200); NGButton.setLayoutY(300);// Sets the X and Y co-ordinates of the button.
        NGButton.setPrefSize(85,20);//Sets the preferred size of the button. Anything bigger gets cropped out.
//The three commands above are wriiten for every button. The co-ordinates can be changed as we please.
//There is a space of two lines between the code for each button that I added for better readability.


        layout.getChildren().add(LGButton);
        LGButton.setLayoutX(515); LGButton.setLayoutY(300);
        //LGButton.setGraphic(new ImageView(img)); (Enter image name in the brackets(img in this case))
        LGButton.setPrefSize(85,20);


        layout.getChildren().add(SetButton);
        SetButton.setLayoutX(92);SetButton.setLayoutY(450);
        //SetButton.setGraphic(new ImageView(img)); (Enter image name in the brackets(img in this case))
        SetButton.setPrefSize(85,20);
        SetButton.setOnAction(e->{
            Pane SgsLayout=new Pane();
            Scene SgsScreen=new Scene(SgsLayout,800,600);
            //Add all settings Code here
            SgsLayout.getChildren().add(RButton);
            mainmenu.setScene(SgsScreen);
        });


        layout.getChildren().add(HTPButton);
        HTPButton.setLayoutX(269);HTPButton.setLayoutY(450);
        //HTPButton.setGraphic(new ImageView(img)); (Enter image name in the brackets(img in this case))
        HTPButton.setPrefSize(85,20);
        HTPButton.setOnAction(e->{
            Pane HTPLayout=new Pane();
            Scene HTPScreen=new Scene(HTPLayout,800,600);
            //add all How To Play code here
            HTPLayout.getChildren().add(RButton);
            mainmenu.setScene(HTPScreen);
        });


        layout.getChildren().add(LBButton);
        LBButton.setLayoutX(446);LBButton.setLayoutY(450);
        //LBButton.setGraphic(new ImageView(img)); (Enter image name in the brackets(img in this case))
        LBButton.setPrefSize(85,20);
        LBButton.setOnAction(e->{
            Pane LBLayout = new Pane();
            Scene LBScreen = new Scene(LBLayout, 800, 600);
            //Add all leaderboard code here
            LBLayout.getChildren().add(RButton);
            mainmenu.setScene(LBScreen);
        });


        layout.getChildren().add(EButton);
        EButton.setLayoutX(623);EButton.setLayoutY(450);
        //EButton.setGraphic(new ImageView(img)); (Enter image name in the brackets(img in this case))
        EButton.setPrefSize(85,20);
        EButton.setOnAction(e->mainmenu.close());


        RButton.setLayoutX(675); RButton.setLayoutY(540);
        //RButton.setGraphic(new ImageView(img)); (Enter image name in the brackets(img in this case))
        RButton.setPrefSize(85,20);
        RButton.setOnAction(e->{ //Place this button on each of the scenes(Leaderboard, settings etc to return the screen to the main menu Scene
            mainmenu.setScene(MMScene);
        });
//NOTE: All the co-ordinates for buttons were entered keeping 800x600 window in mind. They will NOT adjust themselves if the window is resized (yet. I can add that if you want)


        mainmenu.setScene(MMScene);//Puts scene onto the window
        mainmenu.show();//shows the window

        //button.setOnAction(this);(calls handle method. Can be used to put all button functions into one method)


    }
    @Override
    public void handle(ActionEvent actionEvent) {
        if (actionEvent.getSource()==LBButton){ //enter button name after ==
            System.out.println("Nani?");
        }

    }
}
