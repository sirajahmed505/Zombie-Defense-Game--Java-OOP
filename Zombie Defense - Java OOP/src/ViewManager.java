import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class ViewManager
{
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    private static AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    public ViewManager()
    {
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setResizable(false);
        createBackground();
        mainStage.setScene(mainScene);
    }
    public static AnchorPane getMainPane()
    {
        return mainPane;
    }
    public Stage getMainStage()
    {
        return mainStage;
    }
    public double getHeight()
    {
        return HEIGHT;
    }
    public double getWidth()
    {
        return WIDTH;
    }
    public Scene getMainScene()
    {
        return mainScene;
    }

    private void createBackground()
    {
        Image backgroundImage = new Image("file:Assets\\Game\\Map.jpg");
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }
}
