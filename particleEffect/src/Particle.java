import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Paint;

public class Particle {
    private double x;
    private double y;

    private Point2D velocity;

    private double radius;
    private double life;
    private double decay;

    private Paint colour;
    private BlendMode blendmode;

    public Particle(double x, double y, Point2D velocity, double radius,
                    double decay, Paint colour, BlendMode blendmode) {
        this.x = x;
        this.y = y;
        this.velocity = velocity;
        this.radius = radius;
        this.life = 1.0;
        this.decay = 0.016/decay;
        this.colour = colour;
        this.blendmode = blendmode;
    }

    public void Update(){
        x+= velocity.getX();
        y+= velocity.getY();

        life-=decay;
    }

    public boolean isAlive(){
        return life>0;
    }

    public void render(GraphicsContext g){
        g.setGlobalAlpha(life);
        g.setGlobalBlendMode(blendmode);
        g.setFill(colour);
        g.fillOval(x,y,radius,radius);
    }
}
