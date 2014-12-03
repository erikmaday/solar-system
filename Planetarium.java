import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.ArcTo;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.animation.PathTransition.OrientationType;

/**
 * This class represents a Planetarium of our
 * solar system.
 *
 * @author Erik Maday
 * @version 1.0
 */
public class Planetarium extends Application {
    //Since viewport is square, this covers both max height and width
    private final int maxSize = 1200;

    @Override public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, maxSize, maxSize, Color.BLACK);

        Circle sun = new Circle(65, Color.YELLOW);
        sun.setCenterX(maxSize / 2);
        sun.setCenterY(maxSize / 2);

        Circle earth = Planet.EARTH.makePlanet();
        Circle mercury = Planet.MERCURY.makePlanet();
        Circle venus = Planet.VENUS.makePlanet();
        Circle mars = Planet.MARS.makePlanet();

        root = orbit(earth, Planet.EARTH, root);
        root = orbit(mercury, Planet.MERCURY, root);
        root = orbit(venus, Planet.VENUS, root);
        root = orbit(mars, Planet.MARS, root);
        root.getChildren().addAll(sun, earth, mercury, venus, mars);

        stage.setScene(scene);
        stage.setTitle("Solar System");
        stage.show();
    }

    private Group orbit(Circle planetCircle, Planet planet, Group root) {
        double center = maxSize / 2;
        double distance = planet.getDistance();
        MoveTo moveTo = new MoveTo(center + distance, center + distance);
        ArcTo arcTo1 = new ArcTo(distance, distance, 0, center - distance,
            center - distance, true, false);
        ArcTo arcTo2 = new ArcTo(distance, distance, 0, center + distance,
            center + distance, true, false);

        Path path = new Path();
        path.getElements().addAll(moveTo, arcTo1, arcTo2);
        path.setStroke(Color.WHITE);
        root.getChildren().add(path);

        PathTransition p = new PathTransition(
            Duration.millis(planet.getPeriod() * 2000),
            path, planetCircle);
        p.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        p.setCycleCount(Timeline.INDEFINITE);
        p.setAutoReverse(false);
        p.setInterpolator(Interpolator.LINEAR);
        p.play();

        return root;
    }
}