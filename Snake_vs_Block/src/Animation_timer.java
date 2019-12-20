import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class Animation_timer extends Application {
    private Pane canvas = new Pane();
    Random random= new Random();
    //public static int count =0;

    Rectangle r = new Rectangle(460,750);
    private int j=0;
    public Canvas Can= new Canvas(460,750);
    private int snake_size = 40;
    private int snake_posX = 185;
    private int snake_posY = 350;
    private int x_initial = snake_posX;

    public AnimationTimer timer= new AnimationTimer() {
        @Override
        public void handle(long now) {
            onUpdate(snake_posX,snake_posY);
        }

    };
    private GraphicsContext graphics;

    int i=0;

    private  Emitter emitter = new fireEmitter();

    private List<Particle> particles= new ArrayList<Particle>();


    public void Rectangle_Generator(int y, StackPane[] arr){
        int x = 0;
        Color[] rang = new Color[]{Color.AQUA,Color.CHARTREUSE,Color.DARKGOLDENROD,Color.GOLDENROD,Color.LIGHTCORAL};
        String[] number = new String[]{"1","1","1","1","1"};
        for (int i = 0; i <arr.length ; i++) {
            StackPane stackPane = new StackPane();
            stackPane.relocate(x+i*92,y);
            Rectangle rectangle = new Rectangle();
            rectangle.setFill(rang[i]);
            rectangle.setHeight(92);
            rectangle.setWidth(92);
            rectangle.setArcHeight(20);
            rectangle.setArcWidth(20);
            stackPane.getChildren().addAll(rectangle, new Text(number[i]));
            arr[i] = stackPane;
        }
        for (int i = 0; i <arr.length ; i++) {
            canvas.getChildren().add(arr[i]);
        }

    }
    public void Ball_generator(int y,StackPane[] arr){
        int x = 47;
//        String[] number = new String[]{"3","4","5","6","6"};
        for (int i = 0; i < arr.length; i++) {
            StackPane stackPane = new StackPane();
            stackPane.relocate(x+i*92,y);
            Rectangle circle = new Rectangle();
            circle.setFill(Color.YELLOW);
            circle.setHeight(30);
            circle.setWidth(30);
            circle.setArcHeight(180);
            circle.setArcWidth(180);
            stackPane.getChildren().addAll(circle, new Text(Integer.toString(i)));
            arr[i] = stackPane;
        }
        for (int i = 0; i <arr.length ; i++) {
            canvas.getChildren().add(arr[i]);
        }
    }
    public void snake_active(ImageView[] body, int n){
        boolean f = true;
        for (int i = 0; i <body.length ; i++) {
            body[i].setVisible(f);
            if (i == n-1){
                f= false;
            }
        }

    }

    public static void main(String[] args){
        launch(args);

    }
    public void Wall_Geneartor(int y, StackPane[] arr){
        int x = 1;
        for (int i = 0; i < arr.length; i++) {
            StackPane stackPane = new StackPane();
            stackPane.relocate(x+i*92,y);
            Rectangle diwaar = new Rectangle();
            diwaar.setFill(Color.GREY);
            diwaar.setHeight(80);
            diwaar.setWidth(5);
            diwaar.setArcWidth(5);
            diwaar.setArcHeight(5);
            stackPane.getChildren().addAll(diwaar);
            arr[i] = stackPane;
        }
        for (int i = 0; i <arr.length ; i++) {
            canvas.getChildren().add(arr[i]);
        }

    }
    public void move_left(StackPane[] body, Snake snake){
        int f = 0;
        for (int j = 0; j < snake.getSize(); j++) {
            if ( x_initial > 10)
                body[j].relocate((x_initial-46),snake_posY+snake_size*j);
            else
                f =1;
        }
        if (f == 0)
            x_initial = x_initial - 46;

    }
    public void move_right(StackPane[] body, Snake snake){
        int f =0;
        for (int j = 0; j < snake.getSize(); j++) {
            if (x_initial < 400)
                body[j].relocate((x_initial+46),snake_posY+snake_size*j);
            else
                f =1;
        }
        if (f == 0)
            x_initial = x_initial +46;
    }

    public static Scene scene;
    @Override
    public void start(Stage primaryStage) throws Exception {
        scene = new Scene(canvas,460,750,Color.ALICEBLUE);
        canvas.getChildren().add(r);
        Snake snake = new Snake(4);
        //timer.start();
        StackPane score =new StackPane();
        score.setLayoutX(400);
        score.setLayoutY(0);
        score.setPrefSize(70,30);
        Rectangle rect= new Rectangle(70,30);
        rect.setFill(Color.YELLOW);
        score.getChildren().add(rect);
        score.getChildren().add(new Text(Integer.toString(0)));


        StackPane[] blocks1 = new StackPane[5];
        StackPane[] blocks2 = new StackPane[5];
        StackPane[] balls1 = new StackPane[5];
        StackPane[] balls2 = new StackPane[5];
        StackPane[] walls = new StackPane[5];

        int b1 = -15;
        int ba1 = -107;
        int w = -199;
        int ba2 = -291;
        int b2 = -383;

        Rectangle_Generator(b2,blocks2);
        Ball_generator(ba2,balls2);
        Wall_Geneartor(w,walls);
        Ball_generator(ba1,balls1);
        Rectangle_Generator(b1,blocks1);

        int[] Num = new int[]{1,1,1,1,1};
        int[] Num1 = new int[]{1,2,3,4,5};
        Layer Blocks1 = new block_layer(blocks1,b1,0,snake,Num);
        Layer Balls1 = new Ball_layer(balls1,ba1,0,snake,Num1);
        Layer Walls = new Wall_layer(walls,w,0,snake);
        Layer Balls2 = new Ball_layer(balls2,ba2,1,snake,Num1);
        Layer Blocks2 = new block_layer(blocks2,b2,1,snake,Num);

        StackPane[] body= new StackPane[snake.getSize()];
        Circle Head= new Circle(20);
        Head.setFill(Color.YELLOW);
        StackPane Snakehead=  new StackPane();
        Snakehead.relocate(snake_posX,snake_posY);
        Snakehead.getChildren().add(Head);
        canvas.getChildren().add(Snakehead);
        body[0]=Snakehead;

        for (int k = 1; k <snake.getSize() ; k++) {
            Circle b= new Circle(20);
            b.setFill(Color.YELLOW);
            StackPane bd= new StackPane();
            bd.relocate(snake_posX,(snake_posY+snake_size*k));
            bd.getChildren().add(b);
            canvas.getChildren().add(bd);
            body[k]=bd;
        }

//        Image head = new Image("hd.png");
//
//        ImageView sur = new ImageView(head);
//        ImageView[] body = new ImageView[snake.getSize()];
//        body[0] = sur;
//
//
//        sur.setRotate(90);
//        sur.relocate(snake_posX,snake_posY);
//        sur.setFitHeight(snake_size);
//        sur.setFitWidth(snake_size);
//        sur.setSmooth(true);
//        canvas.getChildren().add(sur);
//
//        for (int j = 1; j < snake.getSize(); j++) {
//            ImageView b = new ImageView(new Image("download.png"));
//            b.setSmooth(true);
//            b.setFitWidth(snake_size);
//            b.setFitHeight(snake_size);
//            b.relocate(snake_posX,(snake_posY+snake_size*j));
//            canvas.getChildren().add(b);
//            body[j] = b;
//        }
        snake.setBody(body);
        snake.setActive(4);
        snake.snake_active();
        canvas.getChildren().add(score);

        Timeline timeline =  new Timeline(new KeyFrame(Duration.millis(30), new EventHandler<ActionEvent>() {

            double move_y = 4;
            Layer start = Blocks1;
            boolean k = false;
            int[] array = new int[5];
            int h;
            @Override
            public void handle(ActionEvent event) {
                //1
                Blocks1.setY_coordinate(move_y);
                k = Blocks1.check_Y(-15);
                if (k){
                    for (int i = 0; i <blocks1.length ; i++) {
                        h = random.nextInt(15);
                        if (h == 0){
                            h=1;
                        }
                        array[i] = h;
                        blocks1[i].getChildren().remove(1);
                        blocks1[i].getChildren().add(1,new Text(Integer.toString(h)));
                    }
                    ((block_layer) Blocks1).setNumbers(array);
                    k =false;
                }
//                System.out.println("Blocks1 "+Blocks1.getY_coordinate());

                //2
                Balls1.setY_coordinate(move_y);
                k = Balls1.check_Y(-15);
                ((Ball_layer) Balls1).create_Bomb();
                ((Ball_layer) Balls1).create_Magnet();
                ((Ball_layer) Balls1).create_Sheild();
                if (k){
                    for (int i = 0; i <balls1.length ; i++) {
                        h = random.nextInt(6);
                        if (h == 0){
                            h=1;
                        }
                        array[i] = h;
                        balls1[i].getChildren().remove(1);
                        balls1[i].getChildren().add(1,new Text(Integer.toString(h)));
                        ((Ball_layer) Balls1).setSankhiya(array);
                    }

                    k =false;
                }
//                System.out.println("Balls1 "+Balls1.getY_coordinate());

                //3
                Walls.setY_coordinate(move_y);
                Walls.check_Y(-15);
//                System.out.println("Walls "+ Walls.getY_coordinate());

                //4
                Balls2.setY_coordinate(move_y);
                k =Balls2.check_Y(-15);
                ((Ball_layer) Balls2).create_Bomb();
                ((Ball_layer) Balls2).create_Magnet();
                ((Ball_layer) Balls2).create_Sheild();
                if (k){
                    for (int i = 0; i <balls2.length ; i++) {
                        h = random.nextInt(6);
                        if (h == 0){
                            h=1;
                        }
                        array[i] = h;
                        balls2[i].getChildren().remove(1);
                        balls2[i].getChildren().add(1,new Text(Integer.toString(h)));
                        ((Ball_layer) Balls2).setSankhiya(array);
                    }
                    k =false;
                }
//                System.out.println("Balls2 "+Balls2.getY_coordinate());

                //5
                Blocks2.setY_coordinate(move_y);
                k =Blocks2.check_Y(-15);
                if (k){
                    for (int i = 0; i <blocks2.length ; i++) {
                        h = random.nextInt(15);
                        if (h == 0){
                            h=1;
                        }
                        array[i] = h;
                        blocks2[i].getChildren().remove(1);
                        blocks2[i].getChildren().add(1,new Text(Integer.toString(h)));
                    }
                    ((block_layer) Blocks2).setNumbers(array);
                    k =false;
                }
                if (start == Balls1 || start == Balls2) {
                    if (start.getY_coordinate() == 309) {
                        start.collision(x_initial);

                        if (start == Blocks1) {
                            start = Balls1;
                        } else if (start == Balls1) {
                            start = Walls;
                        } else if (start == Walls) {
                            start = Balls2;
                        } else if (start == Balls2) {
                            start = Blocks2;
                        } else if (start == Blocks2) {
                            start = Blocks1;
                        }
                    }
                }
                else {
                    if (start.getY_coordinate() == 261) {

                        if (start != Walls) {
                            start.collision(x_initial);
                            score.getChildren().remove(1);
                            score.getChildren().add(new Text(Integer.toString(snake.getScore())));
                        }
                        else{
                        }


                        if (start == Blocks1) {
                            start = Balls1;
                        } else if (start == Balls1) {
                            start = Walls;
                        } else if (start == Walls) {
                            start = Balls2;
                        } else if (start == Balls2) {
                            start = Blocks2;
                        } else if (start == Blocks2) {
                            start = Blocks1;
                        }
                    }
                }

            }
        }
        ));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        scene.setOnKeyPressed(event -> {

            if (event.getCode() == KeyCode.A){
                move_left(body,snake);
//                System.out.println(x_initial);
            }
            else if(event.getCode() == KeyCode.D ){
                move_right(body,snake);
//                System.out.println(x_initial);

            }
            else{
                System.out.println("Wrong key");
            }
        });

        scene.setFill(Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Animation Timer");
        primaryStage.show();





    }
    public void onUpdate(int x,int y){

        graphics.setGlobalAlpha(1.0);
        graphics.setGlobalBlendMode(BlendMode.SRC_OVER);
        graphics.setFill(Color.BLACK);
        graphics.fillRect(0,0,460,750);
        if (i<2) {

            particles.addAll(emitter.emit(x, y));
            i++;
        }
        for (Iterator<Particle> it = particles.iterator(); it.hasNext();){
            Particle p= it.next();
            p.Update();
            if(!p.isAlive()){
                it.remove();
                continue;
            }

            p.render(graphics);
        }

    }
}

//Animation_Timer ends




abstract class Layer{


    private StackPane[] arr;
    private int Y_coordinate;
    private static Snake snake;

    public Snake getSnake() {
        return snake;
    }


    public Layer(StackPane[] arr, int y_coordinate,Snake snake) {
        this.arr = arr;
        Y_coordinate = y_coordinate;
        this.snake = snake;
    }

    public int getY_coordinate() {
        return Y_coordinate;
    }
    public StackPane[] getArr() {
        return arr;
    }
    public void setY_to_initial(double y_coordinate){
       Y_coordinate = (int)y_coordinate;
    }
    public void setY_coordinate(double y_coordinate) {
        for (int i = 0; i <arr.length ; i++) {
            arr[i].setLayoutY(arr[i].getLayoutY() + y_coordinate);
        }
        Y_coordinate += y_coordinate;
    }
    public void setVisibilityTrue(int index){
        arr[index].setVisible(true);
    }
    public void setVisiblilityFalse(int index){
        arr[index].setVisible(false);
    }
    public abstract boolean check_Y(int y_ki_initial_value);
    public abstract void collision(int x_initial);

}
class block_layer extends Layer{
    private int[][] pattern = new int[][]{{1,1,1,1,1},{0,1,1,0,0},{1,0,0,0,1},{1,1,1,0,0},{1,1,0,1,1},{0,1,0,0,1},{0,0,0,1,0}};
    int option;
    int pointer = 1;

    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }

    int[] numbers;
    Random random = new Random();
    public block_layer(StackPane[] arr, int y_coordinate, int option,Snake snak,int[] num) {
        super(arr, y_coordinate,snak);
        this.numbers=num;

        this.option = option;
        if (option == 0){
            pointer = 1;
        }
        else{
            pointer = 6;
        }
    }
    public void number_gen(){
        int n;
        int m;
        Text c;
        if (pointer == 0){
            n = random.nextInt(4);
            m = random.nextInt(super.getSnake().getActive()-1);
            if (m == 0){
                m=1;
            }
            super.getArr()[n].getChildren().remove(1);
            super.getArr()[n].getChildren().add(new Text(Integer.toString(m)));


        }
        else{

            for (int i = 0; i <5 ; i++) {

                if (pattern[pointer][i] == 1){
                    m = random.nextInt(15);
                    if (m == 0){
                        m =1;
                    }
                    c = (Text) super.getArr()[i].getChildren().get(1);
                    super.getArr()[i].getChildren().add(new Text(Integer.toString(m)));
                }
            }
        }


    }
    public void collision(int x_initial){
        if (x_initial == 1 || x_initial == 47){
            super.setVisiblilityFalse(0);
            if (pattern[pointer][0] == 1){
                System.out.println(0);
                super.getSnake().setScore(numbers[0]);
                super.getSnake().setActive(super.getSnake().getActive() - numbers[0]);
                super.getSnake().snake_active();
            }
        }
        else if (x_initial == 93 || x_initial == 139){
            super.setVisiblilityFalse(1);
            if (pattern[pointer][1] == 1){
                System.out.println(0);
                super.getSnake().setScore(numbers[1]);
                super.getSnake().setActive(super.getSnake().getActive() - numbers[1]);
                super.getSnake().snake_active();
            }
        }
        else if (x_initial == 185 || x_initial == 231){
            super.setVisiblilityFalse(2);
            if (pattern[pointer][2] == 1){
                System.out.println(0);
                super.getSnake().setScore(numbers[2]);
                super.getSnake().setActive(super.getSnake().getActive() - numbers[2]);
                super.getSnake().snake_active();
            }
        }
        else if (x_initial == 277 || x_initial == 323){
            super.setVisiblilityFalse(3);
            if (pattern[pointer][3] == 1){
                System.out.println(0);
                super.getSnake().setScore(numbers[3]);
                super.getSnake().setActive(super.getSnake().getActive() - numbers[3]);
                super.getSnake().snake_active();
            }
        }
        else {
            super.setVisiblilityFalse(4);
            if (pattern[pointer][4] == 1){
                System.out.println(0);
                super.getSnake().setScore(numbers[4]);
                super.getSnake().setActive(super.getSnake().getActive() - numbers[4]);
                super.getSnake().snake_active();
            }
        }

    }
    @Override
    public boolean check_Y(int y_ki_initial_value){
        if (super.getY_coordinate() > 750){
            for (int i = 0; i <super.getArr().length ; i++) {
                super.getArr()[i].setLayoutY(y_ki_initial_value);
            }
            super.setY_to_initial(y_ki_initial_value);
            for (int i = 0; i < 5; i++) {
               if (pattern[pointer][i] == 0){
                   super.setVisiblilityFalse(i);
               }
               else{
                   super.setVisibilityTrue(i);
               }
            }
            if (option == 0) {
                pointer += 1;
                if (pointer == 7) {
                    pointer = 0;
                }
            }
            else{
                pointer -= 1;
                if (pointer == -1){
                    pointer = 6;
                }
            }
            //number_gen();
            return true;
        }
        return false;
    }
}

class Ball_layer extends Layer{
    private int[][] pattern = new int[][]{{1,1,1,1,1},{0,0,0,0,1},{1,1,0,0,1},{1,0,0,0,1},{0,1,1,1,0},{0,0,0,1,0},{0,1,0,0,1}};
    Random random = new Random();

    int option;
    int[] sankhiya;

    public void setSankhiya(int[] sankhiya) {
        this.sankhiya = sankhiya;
    }

    public int[] getSankhiya() {
        return sankhiya;
    }
    public void create_Bomb(){
        if (pointer == 2){
            Circle c = new Circle(20);
            c.setFill(Color.BLUE);

            super.getArr()[2].getChildren().add(c);
            super.getArr()[2].getChildren().add(new Text("B"));
            super.getArr()[2].setVisible(true);


        }
    }
    public void create_Magnet(){
        if (pointer == 3){
            Circle c = new Circle(20);
            c.setFill(Color.MAGENTA);

            super.getArr()[2].getChildren().add(c);
            super.getArr()[2].getChildren().add(new Text("M"));
            super.getArr()[2].setVisible(true);


        }
    }
    public void create_Sheild(){
        if (pointer == 4){
            Circle c = new Circle(20);
            c.setFill(Color.RED);

            super.getArr()[2].getChildren().add(c);
            super.getArr()[2].getChildren().add(new Text("B"));
            super.getArr()[2].setVisible(true);


        }
    }



    private int pointer = 1;
    public Ball_layer(StackPane[] arr, int y_coordinate, int option, Snake snak,int[] num) {
        super(arr, y_coordinate,snak);
        this.sankhiya=num;
        this.option = option;
        if (option == 0){
            pointer = 1;
        }
        else{

            pointer = 6;
        }
    }


    @Override
    public boolean check_Y(int y_ki_initial_value) {
        if (super.getY_coordinate() > 750){
            for (int i = 0; i <super.getArr().length ; i++) {
                super.getArr()[i].setLayoutY(y_ki_initial_value);
            }
            super.setY_to_initial(y_ki_initial_value);
            for (int i = 0; i < 5; i++) {
                if (pattern[pointer][i] == 0){
                    super.setVisiblilityFalse(i);
                }
                else{
                    super.setVisibilityTrue(i);
                }
            }
            if (option == 0) {
                pointer += 1;
                if (pointer == 7) {
                    pointer = 0;
                }
            }
            else{
                pointer -= 1;
                if (pointer == -1){
                    pointer = 6;
                }
            }
            //number_gen_balls();
            return true;
        }
        return false;
    }

    public void number_gen_balls(){
        int n;
        for (int i = 0; i <super.getArr().length ; i++) {
            n =  random.nextInt(6);
            if (n == 0){
                n =1;
            }
            System.out.println("input value " + n);
            super.getArr()[i].getChildren().remove(1);
            System.out.println("Removed value "+super.getArr()[i].getChildren().get(1));
            super.getArr()[i].getChildren().add(new Text(Integer.toString(n)));


        }



    }

    @Override
    public void collision(int x_initial) {
        if ( x_initial == 47){
            super.setVisiblilityFalse(0);
            if (pattern[pointer][0] == 1) {
                super.getSnake().setActive(super.getSnake().getActive() + sankhiya[0]);
                super.getSnake().snake_active();
            }
        }
        else if (x_initial == 139){
            super.setVisiblilityFalse(1);
            if (pattern[pointer][1] == 1) {
                super.getSnake().setActive(super.getSnake().getActive() + sankhiya[1]);
                super.getSnake().snake_active();
            }
        }
        else if (x_initial == 231){
            super.setVisiblilityFalse(2);
            if (pattern[pointer][2] == 1) {
                super.getSnake().setActive(super.getSnake().getActive() + sankhiya[2]);
                super.getSnake().snake_active();
            }
        }
        else if (x_initial == 323){
            super.setVisiblilityFalse(3);
            if (pattern[pointer][3] == 1) {
                super.getSnake().setActive(super.getSnake().getActive() + sankhiya[3]);
                super.getSnake().snake_active();
            }
        }
        else {
            super.setVisiblilityFalse(4);
            if (pattern[pointer][4] == 1) {
                super.getSnake().setActive(super.getSnake().getActive() + sankhiya[4]);
                super.getSnake().snake_active();
            }
        }
    }
}

class Wall_layer extends Layer{
    private int[][] pattern = new int[][]{{1,1,1,1,1},{0,0,0,1,1},{1,1,0,1,0},{1,0,1,0,1},{0,1,1,1,0},{1,0,0,0,1},{0,0,0,0,0}};
    int option;
    private int pointer = 1;
    public Wall_layer(StackPane[] arr, int y_coordinate, int option,Snake snak) {
        super(arr, y_coordinate,snak);
        this.option = option;
        if (option == 0){
            pointer = 1;
        }
        else{
            pointer = 6;
        }

    }



    @Override
    public boolean check_Y(int y_ki_initial_value) {
        if (super.getY_coordinate() > 750){
            for (int i = 0; i <super.getArr().length ; i++) {
                super.getArr()[i].setLayoutY(y_ki_initial_value);
            }
            super.setY_to_initial(y_ki_initial_value);
            for (int i = 0; i < 4; i++) {
                if (pattern[pointer][i] == 0){
                    super.setVisiblilityFalse(i);
                }
                else{
                    super.setVisibilityTrue(i);
                }
            }
            if (option == 0) {
                pointer += 1;
                if (pointer == 7) {
                    pointer = 0;
                }
            }
            else{
                pointer -= 1;
                if (pointer == -1){
                    pointer = 6;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void collision(int x_initial) {

    }
    public int[][] getPattern(){
        return pattern;
    }

}