package com.github.prominence.openweathermap.api.model;

import org.junit.Test;

public class SnowUnitTest {

    @Test
    public void whenCreateSnowWithValidArgs_ObjectIsCreated() {
        new Snow(2222.3, 324234.3);
        new Snow(null, -213123.4);
        new Snow(-123123.123, null);
        new Snow(null, null);
    }

    @Test
    public void whenSetValues_thenTheyAreSet() {
        final Snow snow = new Snow(null, null);

        assert snow.getOneHourSnowLevel() == null;
        assert snow.getThreeHourSnowLevel() == null;

        snow.setOneHourSnowLevel(33.3);
        assert snow.getOneHourSnowLevel() == 33.3;

        snow.setThreeHourSnowLevel(55.5);
        assert snow.getThreeHourSnowLevel() == 55.5;
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final Snow snow = new Snow();

        assert snow.toString() != null;
        assert "unknown".equals(snow.toString());

        snow.setThreeHourSnowLevel(33.5);

        assert snow.toString() != null;
        assert !"unknown".equals(snow.toString());

        snow.setOneHourSnowLevel(22.2);

        assert snow.toString() != null;
        assert !"unknown".equals(snow.toString());

        snow.setThreeHourSnowLevel(null);

        assert snow.toString() != null;
        assert !"unknown".equals(snow.toString());
    }

    @Test
    public void RainwhenCallHashCode_thenAllIsFine() {
        final Snow first = new Snow();
        final Snow second = new Snow();

        assert first.hashCode() == second.hashCode();

        second.setThreeHourSnowLevel(11.0);

        assert first.hashCode() != second.hashCode();

        first.setThreeHourSnowLevel(11.0);

        assert first.hashCode() == second.hashCode();

        first.setOneHourSnowLevel(333.2);

        assert first.hashCode() != second.hashCode();

        second.setOneHourSnowLevel(333.2);

        assert first.hashCode() == second.hashCode();
    }

    @Test
    public void whenCheckEquality_thenAllIsFine() {
        final Snow first = new Snow();
        final Snow second = new Snow();

        assert first.equals(second);
        assert first.equals(first);
        assert !first.equals(new Object());

        first.setOneHourSnowLevel(0.34);

        assert !first.equals(second);

        second.setOneHourSnowLevel(0.34);

        assert first.equals(second);

        second.setThreeHourSnowLevel(66.7);

        assert !first.equals(second);

        first.setThreeHourSnowLevel(66.7);

        assert first.equals(second);
    }
}
