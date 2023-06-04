import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class MainMenu extends Application implements EventHandler<ActionEvent> {
    //the implement  handles button clicks. Can also be written separately in the code but this is easier.
    Button NGButton = new Button();
    //Can also put button text separately by typing button.setText("Text here") in the start method.
    Button HTPButton = new Button();
    Button EButton = new Button();
    Button RButton = new Button();
    Image SBImg, HTPBImg, EBImg, BGImage, TitleImage;

    public static AnchorPane mainPane;
    ArrayList<NormalTurret> normalTurretsList = new ArrayList<>();
    ArrayList<SpeedTurrets> speedTurretsList = new ArrayList<>();
    ArrayList<Bombers> bombersArrayList = new ArrayList<>();
    public static Stage GameStage = new Stage();

    {
        try {
            SBImg = new Image(new FileInputStream("Assets\\Main Menu\\Buttons\\Start_button.png"));
            //start button png location
            HTPBImg = new Image(new FileInputStream("Assets\\Main Menu\\Buttons\\Help_button.png"));
            //how to play button png location
            EBImg = new Image(new FileInputStream("Assets\\Main Menu\\Buttons\\Exit_button.png"));
            //exit game png location
            BGImage = new Image(new FileInputStream("Assets\\Main Menu\\background_pic.jpg"));
            //background png location
            TitleImage = new Image(new FileInputStream("Assets\\Main Menu\\Title.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void start(Stage mainmenu) {
        mainmenu.setTitle("Zombie Defense");
        Pane layout = new Pane();
        Scene MMScene = new Scene(layout, 800, 600);

        BackgroundImage BGI = new BackgroundImage(BGImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background BG = new Background(BGI);
        layout.setBackground(BG);

        ImageView SBIV = new ImageView(SBImg);
        ImageView HTPBIV = new ImageView(HTPBImg);
        ImageView EBIV = new ImageView(EBImg);
        ImageView TIV = new ImageView(TitleImage);

        SBIV.setFitWidth(170);
        SBIV.setFitHeight(70);
        HTPBIV.setFitWidth(170);
        HTPBIV.setFitHeight(70);
        EBIV.setFitWidth(170);
        EBIV.setFitHeight(70);

        TIV.setFitWidth(800);
        TIV.setFitHeight(300);
        TIV.setPreserveRatio(true);
        layout.getChildren().add(TIV);

        layout.getChildren().add(NGButton);
        NGButton.setStyle("-fx-background-color: transparent;");
        NGButton.setLayoutX(310);
        NGButton.setLayoutY(250);// Sets the X and Y co-ordinates of the button.
        NGButton.setPrefSize(100, 35);
        NGButton.setGraphic(SBIV);
        NGButton.setOnAction(e -> {
            try {
                Gamestart(GameStage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

//The three commands above are written for every button. The co-ordinates can be changed as we please.
//There is a space of two lines between the code for each button that I added for better readability.


        layout.getChildren().add(HTPButton);
        HTPButton.setLayoutX(310);
        HTPButton.setLayoutY(310);
        HTPButton.setStyle("-fx-background-color: transparent;");
        HTPButton.setGraphic(HTPBIV);
        HTPButton.setPrefSize(100, 35);
        HTPButton.setOnAction(e -> {
            Pane HTPLayout = new Pane();
            Scene HTPScreen = new Scene(HTPLayout, 800, 600);
            //add all How To Play code here
            HTPLayout.getChildren().add(RButton);
            mainmenu.setScene(HTPScreen);
        });


        layout.getChildren().add(EButton);
        EButton.setLayoutX(310);
        EButton.setLayoutY(370);
        EButton.setStyle("-fx-background-color: transparent;");
        EButton.setGraphic(EBIV);
        EButton.setPrefSize(100, 35);
        EButton.setOnAction(e -> mainmenu.close());


        RButton.setLayoutX(675);
        RButton.setLayoutY(540);
        //RButton.setGraphic(new ImageView(img)); (Enter image name in the brackets(img in this case))
        RButton.setPrefSize(85, 35);
        RButton.setOnAction(e -> { //Place this button on each of the scenes(Leaderboard, settings etc to return the screen to the main menu Scene
            mainmenu.setScene(MMScene);
        });


        mainmenu.setScene(MMScene);//Puts scene onto the window
        mainmenu.show();//shows the window

    }

    @Override
    public void handle(ActionEvent actionEvent) {
    }


    public void Gamestart(Stage primaryStage) throws Exception {
        ViewManager manager = new ViewManager();
        Image green = new Image("file:Assets\\Game\\greenCursor.png");
        Image red = new Image("file:Assets\\Game\\redCursor.png");
        ImageCursor greenCursor = new ImageCursor(green);
        ImageCursor redCursor = new ImageCursor(red);
        Image subMap = new Image("file:Assets\\Game\\subMap.png", manager.getWidth(), manager.getHeight(), true, true);
        ImageView area = new ImageView(subMap);
        area.setFitWidth(manager.getWidth());
        area.setFitHeight(manager.getHeight());

        Base.setGameCurrency(1000);
        try {
            mainPane = ViewManager.getMainPane();
            primaryStage = manager.getMainStage();
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Label Currency = new Label("Currency: " + (int) Base.getGameCurrency());
        Currency.setFont(new Font("Arial", 35));
        Currency.setTextFill(Color.WHITE);
        Currency.setLayoutX(1030);
        Currency.setLayoutY(5);
        mainPane.getChildren().add(Currency);

        Label health=new Label("Health: "+ (int)Base.getBaseHealth());
        health.setFont(new Font("Arial",35));health.setTextFill(Color.WHITE); health.setLayoutX(1030);health.setLayoutY(290);
        mainPane.getChildren().add(health);

        EnemyMovement em = new EnemyMovement();
        em.addZombie();
        em.spawnZombie();

        mainPane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int mouseX = (int) mouseEvent.getX();
                int mouseY = (int) mouseEvent.getY();
                PixelReader pixelReader = area.getImage().getPixelReader();
                try {
                    if ((manager.getWidth() - mouseX) < manager.getWidth() && (manager.getHeight() - mouseY) < manager.getHeight()) {
                        if (!pixelReader.getColor(mouseX, mouseY).equals(Color.BLACK)) {
                            if (!pixelReader.getColor(mouseX + 10, mouseY + 10).equals(Color.BLACK)) {
                                if (!pixelReader.getColor(mouseX - 10, mouseY - 10).equals(Color.BLACK)) {
                                    if (!pixelReader.getColor(mouseX, mouseY + 10).equals(Color.BLACK)) {
                                        if (!pixelReader.getColor(mouseX, mouseY - 10).equals(Color.BLACK)) {
                                            if (!pixelReader.getColor(mouseX + 10, mouseY).equals(Color.BLACK)) {
                                                if (!pixelReader.getColor(mouseX - 10, mouseY).equals(Color.BLACK)) {
                                                    manager.getMainScene().setCursor(greenCursor);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            manager.getMainScene().setCursor(redCursor);
                        }
                    }
                } catch (IndexOutOfBoundsException e) {

                }
            }
        });

        mainPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (manager.getMainScene().getCursor().equals(greenCursor)) {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        int mouseX = ((int) event.getX());
                        int mouseY = ((int) event.getY());
                        NormalTurret t = new NormalTurret();
                        if (t.buyTurret()) {
                            t.setTurret(mouseX, mouseY);
                            mainPane.getChildren().addAll(t.getImageView(), t.getBarrelImageView());
                            normalTurretsList.add(t);
                            Base.setGameCurrency(Base.getGameCurrency() - t.getCost());
                            Currency.setText("Currency: " + Base.getGameCurrency());
                        }

                        if (!(t.getRangeList().isEmpty())) {
                            t.rangeManager(Turrets.rangeList.get(0));
                            t.rangeRotation();
                            t.attackEnemy(Turrets.rangeList.get(0));
                        } else {
                            t.rotate();
                        }
                        for (NormalTurret normalTurret : normalTurretsList) {
                            boolean overlap = t.getImageView().intersects(normalTurret.getImageView().getLayoutBounds());
                            if (overlap) {
                                if (t != normalTurret) {
                                    mainPane.getChildren().removeAll(t.getImageView(), t.getBarrelImageView());
                                    normalTurretsList.remove(t);
                                }
                            }
                        }
                        for (SpeedTurrets speedTurrets : speedTurretsList) {
                            boolean overlap = t.getImageView().intersects(speedTurrets.getImageView().getLayoutBounds());
                            if (overlap) {
                                mainPane.getChildren().removeAll(t.getImageView(), t.getBarrelImageView());
                                normalTurretsList.remove(t);
                            }
                        }
                        for (Bombers bombers : bombersArrayList) {
                            boolean overlap = t.getImageView().intersects(bombers.getImageView().getLayoutBounds());
                            if (overlap) {
                                mainPane.getChildren().removeAll(t.getImageView(), t.getBarrelImageView());
                                normalTurretsList.remove(t);
                            }
                        }

                    } else if (event.getButton() == MouseButton.SECONDARY) {
                        int mouseX = ((int) event.getX());
                        int mouseY = ((int) event.getY());
                        SpeedTurrets t = new SpeedTurrets();
                        if (t.buyTurret()) {
                            t.setTurret(mouseX, mouseY);
                            mainPane.getChildren().addAll(t.getImageView(), t.getBarrelImageView());
                            speedTurretsList.add(t);
                            Base.setGameCurrency(Base.getGameCurrency() - t.getCost());
                            Currency.setText("Currency: "+ Base.getGameCurrency());
                        }
                        if (!(t.getRangeList().isEmpty())) {
                            t.rangeRotation();
                        } else {
                            t.rotate();
                        }
                        for (NormalTurret normalTurret : normalTurretsList) {
                            boolean overlap = t.getImageView().intersects(normalTurret.getImageView().getLayoutBounds());
                            if (overlap) {
                                mainPane.getChildren().removeAll(t.getImageView(), t.getBarrelImageView());
                                normalTurretsList.remove(t);
                            }
                        }
                        for (SpeedTurrets speedTurrets : speedTurretsList) {
                            boolean overlap = t.getImageView().intersects(speedTurrets.getImageView().getLayoutBounds());
                            if (overlap) {
                                if (t != speedTurrets) {
                                    mainPane.getChildren().removeAll(t.getImageView(), t.getBarrelImageView());
                                    normalTurretsList.remove(t);
                                }
                            }
                        }
                        for (Bombers bombers : bombersArrayList) {
                            boolean overlap = t.getImageView().intersects(bombers.getImageView().getLayoutBounds());
                            if (overlap) {
                                mainPane.getChildren().removeAll(t.getImageView(), t.getBarrelImageView());
                                normalTurretsList.remove(t);
                            }
                        }
                    } else if (event.getButton() == MouseButton.MIDDLE) {
                        int mouseX = ((int) event.getX());
                        int mouseY = ((int) event.getY());
                        Bombers t = new Bombers();
                        if (t.buyTurret()) {
                            t.setTurret(mouseX, mouseY);
                            mainPane.getChildren().addAll(t.getImageView(), t.getBarrelImageView());
                            bombersArrayList.add(t);
                            Base.setGameCurrency(Base.getGameCurrency() - t.getCost());
                            Currency.setText("Currency: "+ Base.getGameCurrency());
                        }
                        if (!(t.getRangeList().isEmpty())) {
                            t.rangeRotation();
                        } else {
                            t.rotate();
                        }
                        for (NormalTurret normalTurret : normalTurretsList) {
                            boolean overlap = t.getImageView().intersects(normalTurret.getImageView().getLayoutBounds());
                            if (overlap) {
                                mainPane.getChildren().removeAll(t.getImageView(), t.getBarrelImageView());
                                normalTurretsList.remove(t);
                            }
                        }
                        for (SpeedTurrets speedTurrets : speedTurretsList) {
                            boolean overlap = t.getImageView().intersects(speedTurrets.getImageView().getLayoutBounds());
                            if (overlap) {
                                mainPane.getChildren().removeAll(t.getImageView(), t.getBarrelImageView());
                                normalTurretsList.remove(t);
                            }
                        }
                        for (Bombers bombers : bombersArrayList) {
                            boolean overlap = t.getImageView().intersects(bombers.getImageView().getLayoutBounds());
                            if (overlap) {
                                if (t != bombers) {
                                    mainPane.getChildren().removeAll(t.getImageView(), t.getBarrelImageView());
                                    normalTurretsList.remove(t);
                                }
                            }
                        }
                    }
                }
            }
        });
    }
}
