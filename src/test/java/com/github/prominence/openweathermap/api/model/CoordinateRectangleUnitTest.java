package com.github.prominence.openweathermap.api.model;

import org.junit.Test;

public class CoordinateRectangleUnitTest {

    @Test
    public void whenCreateObjectWithValidArgs_thenObjectIsCreated() {
        new CoordinateRectangle(44.5, 22.4, 54.4, 22.2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateObjectWithLatitudeBottomBelowMinus90_thenThrowAnException() {
        new CoordinateRectangle(44.5, -91.2, 54.4, 22.2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateObjectWithLatitudeBottomAbove90_thenThrowAnException() {
        new CoordinateRectangle(44.5, 91.2, 54.4, 22.2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateObjectWithLatitudeTopBelowMinus90_thenThrowAnException() {
        new CoordinateRectangle(44.5, 22.4, 54.4, -92.3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateObjectWithLatitudeTopAbove90_thenThrowAnException() {
        new CoordinateRectangle(44.5, 22.5, 54.4, 94.887);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateObjectWithLongitudeLeftBelowMinus180_thenThrowAnException() {
        new CoordinateRectangle(-944.5, 22.4, 54.4, 22.2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateObjectWithLongitudeLeftAbove180_thenThrowAnException() {
        new CoordinateRectangle(544.5, 22.4, 54.4, 22.2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateObjectWithLongitudeRightBelowMinus180_thenThrowAnException() {
        new CoordinateRectangle(44.5, 22.4, -254.4, 22.2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateObjectWithLongitudeRightAbove180_thenThrowAnException() {
        new CoordinateRectangle(44.5, 22.4, 354.4, 22.2);
    }

    @Test
    public void whenGetAllParameters_thenAllIsFine() {
        final CoordinateRectangle rectangle = new CoordinateRectangle(44.5, 22.4, 54.4, 22.2);
        assert rectangle.getLongitudeLeft() == 44.5;
        assert rectangle.getLatitudeBottom() == 22.4;
        assert rectangle.getLongitudeRight() == 54.4;
        assert rectangle.getLatitudeTop() == 22.2;
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final CoordinateRectangle rectangle = new CoordinateRectangle(44.5, 22.4, 54.4, 22.2);

        assert rectangle.toString() != null;
        assert !"".equals(rectangle.toString());
    }

    @Test
    public void whenCallHashCode_thenAllIsFine() {
        final CoordinateRectangle first = new CoordinateRectangle(44.5, 22.4, 54.4, 22.2);
        final CoordinateRectangle second = new CoordinateRectangle(44.5, 22.4, 54.4, 22.2);

        assert first.hashCode() == second.hashCode();

        final CoordinateRectangle third = new CoordinateRectangle(44.5, 22.4, 54.4, 23.566);

        assert first.hashCode() != third.hashCode();
        assert second.hashCode() != third.hashCode();
    }

    @Test
    public void whenCheckEquality_thenAllIsFine() {
        CoordinateRectangle first = new CoordinateRectangle(44.5, 22.4, 54.4, 22.2);
        CoordinateRectangle second = new CoordinateRectangle(44.5, 22.4, 54.4, 22.2);

        assert first.equals(second);
        assert first.equals(first);
        assert !first.equals(new Object());

        first = new CoordinateRectangle(49.5, 22.4, 54.4, 22.2);

        assert !first.equals(second);

        first = new CoordinateRectangle(44.5, 29.4, 54.4, 22.2);

        assert !first.equals(second);

        first = new CoordinateRectangle(44.5, 22.4, 24.4, 22.2);

        assert !first.equals(second);

        first = new CoordinateRectangle(44.5, 22.4, 54.4, -2.2);

        assert !first.equals(second);
    }
}
