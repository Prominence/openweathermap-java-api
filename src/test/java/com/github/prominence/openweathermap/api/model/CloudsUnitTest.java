package com.github.prominence.openweathermap.api.model;

import org.junit.Test;

public class CloudsUnitTest {

    @Test
    public void whenCreateCloudsWithValidArgs_thenValueIsSet() {
        Clouds clouds = new Clouds((byte) 100);
        assert clouds.getValue() == 100;

        assert new Clouds((byte) 0).getValue() == 0;
        assert new Clouds((byte) 100).getValue() == 100;
        assert new Clouds((byte) 55).getValue() == 55;
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateCloudsByConstructorWithInvalidDataAboveHundred_thenThrowAnException() {
        new Clouds((byte) 110);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateCloudsByConstructorWithInvalidDataNegative_thenThrowAnException() {
        new Clouds((byte) -33);
    }

    @Test
    public void whenSetValidValues_thenAllIsFine() {
        Clouds clouds = new Clouds((byte) 14);
        clouds.setValue((byte) 0);
        clouds.setValue((byte) 15);
        clouds.setValue((byte) 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateCloudsAndSetInvalidDataAboveHundred_thenThrowAnException() {
        Clouds clouds = new Clouds((byte) 12);
        clouds.setValue((byte) 112);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateCloudsAndSetInvalidDataNegative_thenThrowAnException() {
        Clouds clouds = new Clouds((byte) 88);
        clouds.setValue((byte) -89);
    }

    @Test
    public void whenCreateTwoIdenticalInstances_thenWheyAreEquals() {
        Clouds one = new Clouds((byte) 22);
        Clouds two = new Clouds((byte) 22);

        assert one.equals(two);
        assert one.equals(one);
        assert one.hashCode() == two.hashCode();
    }

    @Test
    public void whenCreateTwoDifferentInstances_thenWheyAreNotEquals() {
        Clouds one = new Clouds((byte) 5);
        Clouds two = new Clouds((byte) 88);

        assert !one.equals(two);
        assert !two.equals(one);
        assert !one.equals(new Object());
        assert one.hashCode() != two.hashCode();
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final String cloudsString = new Clouds((byte) 44).toString();
        assert cloudsString != null;
        assert !"".equals(cloudsString);
    }
}
