import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Main extends Application {
   public AnimationTimer timer= new AnimationTimer() {
        @Override
        public void handle(long now) {
            onUpdate();
        }

    };
    private GraphicsContext graphics;

    int i=0;

    private  Emitter emitter = new fireEmitter();

    private List<Particle> particles= new ArrayList<Particle>();

    public static void main(String[] args) {
        launch(args);
    }

    public Parent createContent(){
        Pane root= new Pane();
        root.setPrefSize(600,600);

        Canvas canvas= new Canvas(600,600);
        graphics=canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        return root;

    }

    public void onUpdate(){

        graphics.setGlobalAlpha(1.0);
        graphics.setGlobalBlendMode(BlendMode.SRC_OVER);
        graphics.setFill(Color.BLACK);
        graphics.fillRect(0,0,600,600);
        if (i<2) {

            particles.addAll(emitter.emit(30, 300));
            i++;
        }
        for (Iterator<Particle> it= particles.iterator(); it.hasNext();){
            Particle p= it.next();
            p.Update();
            if(!p.isAlive()){
                it.remove();
                continue;
            }

            p.render(graphics);
        }

    }

    public void start(Stage primarystage){
        primarystage.setScene( new Scene(createContent()));
        primarystage.show();


        timer.start(); //main line of code
    }
}
