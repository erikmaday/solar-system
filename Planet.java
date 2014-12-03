import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * This class represents a Planet enum.
 *
 * @author Erik Maday
 * @version 1.0
 */
public enum Planet {

    EARTH(Color.SPRINGGREEN, 1, 1, 1),
    MERCURY(Color.SILVER, .24, .1915, .387),
    VENUS(Color.GREEN, .62, .4745, .723),
    MARS(Color.RED, 1.88, .266, 1.52);

    private final int earthRadius = 35;
    private final int earthDistance = 265;
    private final int earthPeriod = 5;

    private Color color;
    private final double period;
    private final double radius;
    private final double distance;
    private final int maxSize = 1200;

    Planet(Color color, double period, double radius, double distance) {
        this.color = color;
        this.period = earthPeriod * period;
        this.radius = earthRadius * radius;
        this.distance = earthDistance * distance;
    }

    /**
     * Creates the circle representing the planet.
     * @return a circle representing the planet.
     */
    public Circle makePlanet() {
        return new Circle(distance + (maxSize / 2),
            distance + (maxSize / 2), radius, color);
    }

    /**
     * Gets the planet's distance from the sun.
     * @return the planet's distance from the sun.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Gets the planet's period of orbit.
     * @return the planet's period of orbit.
     */
    public double getPeriod() {
        return period;
    }
}
