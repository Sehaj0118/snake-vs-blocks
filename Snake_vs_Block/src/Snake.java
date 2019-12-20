import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Snake {
    private final int size=10;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        if(score< active){
            this.score+=score;
        }
        else{
            //game khatam
        }
    }

    private int score;

    public void setBody(StackPane[] body) {
        this.body = body;
        this.score=0;
    }

    private StackPane[] body;

    private int active;

    public Snake(int s){
        this.active = s;
    }
    public int getSize(){
        return size;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        if(active>0){
            this.active=active;
        }
    }

    public void snake_active(){

        if ( active >= 10) {
            for (int i = 0; i < body.length; i++) {
                body[i].setVisible(true);
            }
        }
        else if (active < 10){
            for (int i = 0; i <active ; i++) {
                body[i].setVisible(true);
            }
            for (int i = active; i <body.length ; i++) {
                body[i].setVisible(false);
            }
        }


    }

}
