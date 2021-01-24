package models;

import java.util.Objects;

/**
 * This class represent and element's coordinate
 */
public class Coordinate {

    private int axis_x;
    private int axis_y;

    public Coordinate(int axis_x, int axis_y){
        this.axis_x = axis_x;
        this.axis_y = axis_y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return axis_x == that.axis_x &&
                axis_y == that.axis_y;
    }

    public int getAxis_x() {
        return axis_x;
    }

    public int getAxis_y() {
        return axis_y;
    }

    //
//    @Override
//    public int hashCode() {
//        return Objects.hash(axis_x, axis_y);
//    }
}
