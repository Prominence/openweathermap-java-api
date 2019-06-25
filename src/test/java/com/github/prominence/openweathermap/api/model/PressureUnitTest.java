package com.github.prominence.openweathermap.api.model;

import org.junit.Test;

public class PressureUnitTest {

    @Test
    public void whenCreatePressureWithArgs_thenValueIsSet() {
        Pressure pressure = new Pressure(100);
        assert pressure.getValue() == 100;

        assert new Pressure(0).getValue() == 0;
        assert new Pressure(100).getValue() == 100;
        assert new Pressure(55).getValue() == 55;
    }

    @Test
    public void whenCreateTwoIdenticalInstances_thenWheyAreEquals() {
        Pressure one = new Pressure(22);
        Pressure two = new Pressure(22);

        assert one.equals(two);
        assert one.equals(one);
        assert one.hashCode() == two.hashCode();

        one.setSeaLevelValue(333);
        one.setGroundLevelValue(555);

        two.setSeaLevelValue(333);
        two.setGroundLevelValue(555);

        assert one.equals(two);
        assert two.equals(one);
        assert one.hashCode() == two.hashCode();
    }

    @Test
    public void whenCreateTwoDifferentInstances_thenWheyAreNotEquals() {
        Pressure one = new Pressure(5);
        Pressure two = new Pressure(88);

        assert !one.equals(two);
        assert !two.equals(one);
        assert !one.equals(new Object());
        assert one.hashCode() != two.hashCode();

        one = new Pressure(44);
        one.setSeaLevelValue(44);
        two = new Pressure(44);
        two.setGroundLevelValue(22);

        assert !one.equals(two);
        assert !two.equals(one);

        two.setSeaLevelValue(44);

        assert !one.equals(two);
        assert !two.equals(one);
    }

    @Test
    public void whenSetValidValues_thenAllIsFine() {
        Pressure pressure = new Pressure(14);
        pressure.setValue(0);
        pressure.setValue(15);
        pressure.setValue(100);

        pressure.setGroundLevelValue(222);
        assert pressure.getGroundLevelValue() == 222;

        pressure.setSeaLevelValue(4232);
        assert pressure.getSeaLevelValue() == 4232;
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final String pressureString = new Pressure(44).toString();
        assert pressureString != null;
        assert !"".equals(pressureString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreatePressureByConstructorWithInvalidDataNegative_thenThrowAnException() {
        new Pressure(-33);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreatePressureAndSetInvalidDataNegative_thenThrowAnException() {
        Pressure pressure = new Pressure(88);
        pressure.setValue(-89);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetInvalidSeaLevelPressure_thenThrowAnException() {
        Pressure pressure = new Pressure(88);
        pressure.setSeaLevelValue(-89);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetInvalidGroundLevelPressure_thenThrowAnException() {
        Pressure pressure = new Pressure(88);
        pressure.setGroundLevelValue(-223);
    }
}
