package com.github.prominence.openweathermap.api.model;

import org.junit.Test;

public class RainUnitTest {

    @Test
    public void whenCreateRainWithValidArgs_thenObjectIsCreated() {
        new Rain(2222.3, 324234.3);
        new Rain(null, -213123.4);
        new Rain(-123123.123, null);
        new Rain(null, null);
    }

    @Test
    public void whenSetValues_thenTheyAreSet() {
        final Rain snow = new Rain(null, null);

        assert snow.getOneHourRainLevel() == null;
        assert snow.getThreeHourRainLevel() == null;

        snow.setOneHourRainLevel(33.3);
        assert snow.getOneHourRainLevel() == 33.3;

        snow.setThreeHourRainLevel(55.5);
        assert snow.getThreeHourRainLevel() == 55.5;
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final Rain snow = new Rain();

        assert snow.toString() != null;
        assert "unknown".equals(snow.toString());

        snow.setThreeHourRainLevel(33.5);

        assert snow.toString() != null;
        assert !"unknown".equals(snow.toString());

        snow.setOneHourRainLevel(22.2);

        assert snow.toString() != null;
        assert !"unknown".equals(snow.toString());

        snow.setThreeHourRainLevel(null);

        assert snow.toString() != null;
        assert !"unknown".equals(snow.toString());
    }

    @Test
    public void whenCallHashCode_thenAllIsFine() {
        final Rain first = new Rain();
        final Rain second = new Rain();

        assert first.hashCode() == second.hashCode();

        second.setThreeHourRainLevel(11.0);

        assert first.hashCode() != second.hashCode();

        first.setThreeHourRainLevel(11.0);

        assert first.hashCode() == second.hashCode();

        first.setOneHourRainLevel(333.2);

        assert first.hashCode() != second.hashCode();

        second.setOneHourRainLevel(333.2);

        assert first.hashCode() == second.hashCode();
    }

    @Test
    public void whenCheckEquality_thenAllIsFine() {
        final Rain first = new Rain();
        final Rain second = new Rain();

        assert first.equals(second);
        assert first.equals(first);
        assert !first.equals(new Object());

        first.setOneHourRainLevel(0.34);

        assert !first.equals(second);

        second.setOneHourRainLevel(0.34);

        assert first.equals(second);

        second.setThreeHourRainLevel(66.7);

        assert !first.equals(second);

        first.setThreeHourRainLevel(66.7);

        assert first.equals(second);
    }
}
