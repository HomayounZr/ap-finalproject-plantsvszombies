package models;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class represent and element's coordinate
 */
public class Coordinate implements Serializable {

    // x axis
    private int axis_x;
    // y axis
    private int axis_y;

    /**
     * Constructor for the Class
     * @param axis_x Horizontal Point
     * @param axis_y Vertical Point
     */

    public Coordinate(int axis_x, int axis_y){
        this.axis_x = axis_x;
        this.axis_y = axis_y;
    }

    /**
     * Checks the Equallity of 2 Coordinates
     * @param o Object Coordinate Type
     * @return Boolean True Or False
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return axis_x == that.axis_x &&
                axis_y == that.axis_y;
    }

    /**
     * Getter for Horizontal Point
     * @return X's Vector Point
     */

    public int getAxis_x() {
        return axis_x;
    }

    /**
     * Getter fot Vertical Point
     * @return Y's Vector Point
     */

    public int getAxis_y() {
        return axis_y;
    }

    //
//    @Override
//    public int hashCode() {
//        return Objects.hash(axis_x, axis_y);
//    }
}
