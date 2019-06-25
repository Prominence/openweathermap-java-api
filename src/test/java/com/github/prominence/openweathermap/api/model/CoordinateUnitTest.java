package com.github.prominence.openweathermap.api.model;

import org.junit.Test;

public class CoordinateUnitTest {

    @Test
    public void whenCreateCoordinateWithValidValues_thenObjectCreated() {
        new Coordinate(44, 53);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateCoordinateWithInvalidLatitudeBelowMinus90_thenThrowAnException() {
        new Coordinate(-333, 44);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateCoordinateWithInvalidLatitudeAbove90_thenThrowAnException() {
        new Coordinate(223, 44);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateCoordinateWithInvalidLongitudeBelowMinus180_thenThrowAnException() {
        new Coordinate(33, -999);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateCoordinateWithInvalidLongitudeAbove180_thenThrowAnException() {
        new Coordinate(33, 999);
    }

    @Test
    public void whenSetValidCoordinates_thenAllIsFine() {
        final Coordinate coordinate = new Coordinate(0, 0);

        coordinate.setLatitude(-90);
        coordinate.setLatitude(90);
        coordinate.setLatitude(44);

        coordinate.setLongitude(-180);
        coordinate.setLongitude(180);
        coordinate.setLongitude(130);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetInvalidLatitudeBelowMinus90_thenThrowAnException() {
        final Coordinate coordinate = new Coordinate(0, 0);
        coordinate.setLatitude(-91);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetInvalidLatitudeAbove90_thenThrowAnException() {
        final Coordinate coordinate = new Coordinate(0, 0);
        coordinate.setLatitude(92);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetInvalidLongitudeBelowMinus180_thenThrowAnException() {
        final Coordinate coordinate = new Coordinate(0, 0);
        coordinate.setLongitude(-194);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetInvalidLongitudeAbove180_thenThrowAnException() {
        final Coordinate coordinate = new Coordinate(0, 0);
        coordinate.setLongitude(444);
    }

    @Test
    public void whenGetLatitude_thenAllIsFine() {
        final Coordinate coordinate = new Coordinate(0, 0);
        assert coordinate.getLatitude() == 0;

        coordinate.setLatitude(45);

        assert coordinate.getLatitude() == 45;
    }

    @Test
    public void whenGetLongitude_thenAllIsFine() {
        final Coordinate coordinate = new Coordinate(0, 0);
        assert coordinate.getLongitude() == 0;

        coordinate.setLongitude(33);

        assert coordinate.getLongitude() == 33;
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final Coordinate coordinate = new Coordinate(0, 0);
        assert coordinate.toString() != null;
        assert !"".equals(coordinate.toString());
    }

    @Test
    public void RainwhenCallHashCode_thenAllIsFine() {
        final Coordinate first = new Coordinate(22, 66);
        final Coordinate second = new Coordinate(22, 44);

        assert first.hashCode() != second.hashCode();

        second.setLongitude(66);

        assert first.hashCode() == second.hashCode();

        second.setLatitude(89);

        assert first.hashCode() != second.hashCode();

        first.setLatitude(89);

        assert first.hashCode() == second.hashCode();
    }

    @Test
    public void whenCheckEquality_thenAllIsFine() {
        final Coordinate first = new Coordinate(11, 99);
        final Coordinate second = new Coordinate(11, 99);

        assert first.equals(second);
        assert first.equals(first);
        assert !first.equals(new Object());

        first.setLatitude(34);

        assert !first.equals(second);

        second.setLatitude(34);

        assert first.equals(second);

        second.setLongitude(74);

        assert !first.equals(second);

        first.setLongitude(74);

        assert first.equals(second);
    }
}
