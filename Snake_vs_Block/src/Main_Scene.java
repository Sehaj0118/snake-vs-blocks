import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

class Main_Scene {
    Main game;
    Animation_timer Atimer= new Animation_timer();
    private Button play,leaderBoard,instruction,exit;
    private AnchorPane layout;
    public Scene mainscene;
    public Main_Scene(){
        game= new Main();

        play=new Button();
        play.getStyleClass().add("play");

        leaderBoard=new Button(); //Image
        leaderBoard.getStyleClass().add("leader");

        exit=new Button();
        exit.getStyleClass().add("exit");


        instruction= new Button(); // image
        instruction.getStyleClass().add("instruction");
        instruction.setPrefSize(37,37);
        layout =new AnchorPane();
        layout.getStyleClass().add("scene1");

        layout.getChildren().add(play);
        layout.getChildren().add(leaderBoard);
        layout.getChildren().add(instruction);
        layout.getChildren().add(exit);
        mainscene= new Scene(layout,460,750);

        instruction.setLayoutX(414);
        instruction.setLayoutY(0);

        exit.setLayoutX(290);
        exit.setLayoutY(527);
        exit.setPrefSize(84,84);

        play.setPrefSize(84,84);
        play.setLayoutX(188);
        play.setLayoutY(351);
        play.setOnAction(e->
        {
            try {
                Atimer.start(Main.window);
            }
            catch (Exception f){

            }

            System.out.println("Play");
        });
        exit.setOnAction(e-> game.window.close());

        leaderBoard.setPrefSize(84,84);
        leaderBoard.setLayoutX(74);
        leaderBoard.setLayoutY(527);
        leaderBoard.setOnAction(e-> {
            System.out.println("Leaderboard");
            LeaderBoard lead= new LeaderBoard();
            game.window.setScene(lead.scene);
        });

        instruction.setOnAction(e->{
            System.out.println("Information please");
            Info info= new Info();
            game.window.setScene(info.scene);
        });

        mainscene.getStylesheets().add(getClass().getResource("button_style.css").toExternalForm());
    }
}