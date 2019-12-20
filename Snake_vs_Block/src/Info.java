import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class Info {
    Main game;
    private Button goback;
    public Scene scene;
    private AnchorPane layout;
    Info(){
        game= new Main();
        layout= new AnchorPane();
        goback = new Button();

        goback.getStyleClass().add("return");

        layout.getStyleClass().add("Information");

        layout.getChildren().add(goback);
        goback.setLayoutX(368);
        goback.setLayoutY(688);

        scene=new Scene(layout,460,750);

        scene.getStylesheets().add(getClass().getResource("button_style.css").toExternalForm());
        goback.setPrefSize(52,52);
        goback.setOnAction(e-> {
            System.out.println("Main game please");
            Main_Scene menu= new Main_Scene();
            game.window.setScene(menu.mainscene);
        });
    }
}
