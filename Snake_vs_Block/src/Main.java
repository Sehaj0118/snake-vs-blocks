import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Main extends Application {
    public static Stage window;
    public static boolean flag= false;
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        window=primaryStage;
        window.setTitle("Snake vs Blocks");
        Main_Scene scene1=new Main_Scene();

        window.setScene(scene1.mainscene);
        window.show();
        if (flag){
            System.out.println("ho gya");
        }

    }
}

