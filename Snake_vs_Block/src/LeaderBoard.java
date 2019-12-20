import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLOutput;

public class LeaderBoard {
    Main game;
    private Button goback;
    public Scene scene;
    private AnchorPane layout;
    private TableView<Score> table;
    LeaderBoard(){
        game= new Main();
        layout= new AnchorPane();
        goback = new Button();
        TableColumn<Score,String> sNum= new TableColumn<>("Position");
        TableColumn<Score,String> dateCol= new TableColumn<>("Date");
        TableColumn<Score,String> score= new TableColumn<>("Score");

        sNum.setMinWidth(100);
        sNum.setCellValueFactory(new PropertyValueFactory<>("position"));

        dateCol.setMinWidth(100);
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        score.setMinWidth(100);
        score.setCellValueFactory(new PropertyValueFactory<>("points"));

        table=new TableView<>();
        table.setItems(getScore());
        table.getColumns().addAll(sNum,dateCol,score);

        goback.getStyleClass().add("return");
        layout.getStyleClass().add("LeaderBoard");
        layout.getChildren().add(goback);
        layout.getChildren().add(table);
        goback.setPrefSize(84,84);
        table.setLayoutX(80);
        table.setLayoutY(180);
        goback.setAlignment(Pos.TOP_LEFT);
        table.getStyleClass().add("leaderboardTable");

        scene=new Scene(layout,460,750);

        scene.getStylesheets().add(getClass().getResource("button_style.css").toExternalForm());
        goback.setPrefSize(52,52);
        goback.setOnAction(e-> {
            System.out.println("Main game please");
            Main_Scene menu= new Main_Scene();
            game.window.setScene(menu.mainscene);
        });
    }
    public ObservableList<Score> getScore(){
        ObservableList<Score> board= FXCollections.observableArrayList();
        board.add(new Score(1,"23/10/2018",500));
        board.add(new Score(2,"23/10/2018",434));
        board.add(new Score(3,"25/10/2018",398));
        board.add(new Score(4,"26/10/2018",340));
        board.add(new Score(5,"24/10/2018",329));
        board.add(new Score(6,"21/10/2018",313));
        board.add(new Score(7,"29/10/2018",277));
        board.add(new Score(8,"30/10/2018",122));
        board.add(new Score(9,"25/10/2018",90));
        board.add(new Score(10,"26/10/2018",85));

        return board;

    }
}
