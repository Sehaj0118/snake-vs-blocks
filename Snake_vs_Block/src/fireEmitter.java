import javafx.geometry.Point2D;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class fireEmitter extends Emitter {
    @Override
    public List<Particle> emit(double x, double y) {
        List<Particle> particles = new ArrayList<Particle>();
        Point2D velocity1,velocity2;
        int num_particles= 5;
        for(int i=0;i<15;i++) {
            velocity1 = new Point2D(Math.random()* +2 +0.5, Math.random() * +2);
            velocity2 = new Point2D(Math.random()* +2 +0.5, Math.random() * -2);
            if (i%2==0) {
                velocity1 = new Point2D(Math.random() *-2 -0.5, Math.random() * -2);
                velocity2 = new Point2D(Math.random()* -2 -0.5, Math.random() * 2);
            }

            Particle p1 = new Particle(x, y, velocity1, 5, 0.5, Color.rgb(230,40,45), BlendMode.ADD);
            Particle p2 = new Particle(x, y, velocity2, 5, 0.5, Color.rgb(230,40,45), BlendMode.ADD);
            particles.add(p1);
            particles.add(p2);
        }
        return particles;
    }
}
