package com.github.prominence.openweathermap.api.model;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class LocationUnitTest {

    @Test
    public void whenCreateObjectWithValidArgs_thenObjectIsCreated() {
        new Location(33, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateObjectWithoutName_thenThrowAnException() {
        new Location(33, null);
    }

    @Test
    public void whenSetId_thenValueIsSet() {
        final Location location = new Location(33, "test");
        location.setId(55);

        assert location.getId() == 55;
    }

    @Test
    public void whenSetName_thenValueIsSet() {
        final Location location = new Location(33, "test");
        location.setName("city");

        assert "city".equals(location.getName());
    }

    @Test
    public void whenSetCountryCode_thenValueIsSet() {
        final Location location = new Location(33, "test");
        location.setCountryCode("by");

        assert "by".equals(location.getCountryCode());
    }

    @Test
    public void whenSetSunrise_thenValueIsSet() {
        final Location location = new Location(33, "test");
        final LocalDateTime now = LocalDateTime.now();
        location.setSunrise(now);

        assert now.equals(location.getSunrise());
    }

    @Test
    public void whenSetSunset_thenValueIsSet() {
        final Location location = new Location(33, "test");
        final LocalDateTime now = LocalDateTime.now();
        location.setSunset(now);

        assert now.equals(location.getSunset());
    }

    @Test
    public void whenSetZoneOffset_thenValueIsSet() {
        final Location location = new Location(33, "test");
        final ZoneOffset offset = ZoneOffset.UTC;
        location.setZoneOffset(offset);

        assert offset.equals(location.getZoneOffset());
    }

    @Test
    public void whenSetCoordinate_thenValueIsSet() {
        final Location location = new Location(33, "test");
        final Coordinate coordinate = new Coordinate(33.2, 64.2);
        location.setCoordinate(coordinate);

        assert coordinate.equals(location.getCoordinate());
    }

    @Test
    public void whenCallToString_thenAllIsFine() {
        final Location location = new Location(44, "test");

        assert !"".equals(location.toString());

        location.setCoordinate(new Coordinate(33.2, 56.3));

        assert !"".equals(location.toString());

        location.setCountryCode("TN");

        assert !"".equals(location.toString());
    }

    @Test
    public void whenCallHashCode_thenAllIsFine() {
        final Location one = new Location(44, "test");
        final Location two = new Location(44, "test");

        assert one.hashCode() == two.hashCode();

        two.setName("112");

        assert one.hashCode() != two.hashCode();
    }

    @Test
    public void whenCheckEquality_thenAllIsFine() {
        final Location one = new Location(44, "test");
        final Location two = new Location(44, "test");

        assert one.equals(one);
        assert !one.equals(new Object());

        assert one.equals(two);

        two.setId(23);

        assert !one.equals(two);

        one.setId(23);

        assert one.equals(two);

        one.setName("23");

        assert !one.equals(two);

        two.setName("23");

        assert one.equals(two);

        one.setCountryCode("11");

        assert !one.equals(two);

        two.setCountryCode("11");

        assert one.equals(two);

        final LocalDateTime now = LocalDateTime.now();

        one.setSunrise(now);

        assert !one.equals(two);

        two.setSunrise(now);

        assert one.equals(two);

        one.setSunset(now);

        assert !one.equals(two);

        two.setSunset(now);

        assert one.equals(two);

        one.setZoneOffset(ZoneOffset.UTC);

        assert !one.equals(two);

        two.setZoneOffset(ZoneOffset.UTC);

        assert one.equals(two);

        final Coordinate coordinate = new Coordinate(33.5, -22.4);

        one.setCoordinate(coordinate);

        assert !one.equals(two);

        two.setCoordinate(coordinate);

        assert one.equals(two);
    }
}
