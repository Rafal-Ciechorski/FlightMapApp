package com.example.flightmapapp.Repository;

import java.util.HashSet;
import java.util.Set;

public class StateVector {

    private Double geoAltitude;
    private Double longitude;
    private Double latitude;
    private Double velocity;
    private Double heading;
    private Double verticalRate;
    private String icao24;
    private String callsign;
    private boolean onGround;
    private Double lastContact;
    private Double lastPositionUpdate;
    private String originCountry;
    private String squawk;
    private boolean spi;
    private Double baroAltitude;
    private PositionSource positionSource;
    //private Integer positionSource;
    private Set<Integer> sensorsId;



    public void addSensorsId(int serial) {
        if (this.sensorsId == null) {
            this.sensorsId = new HashSet<>();
        }
        this.sensorsId.add(serial);
    }

    public Set<Integer> getSensorsId() {
        return sensorsId;
    }

    public void setSensorsId(Set<Integer> sensorsId) {
        this.sensorsId = sensorsId;
    }

    public enum PositionSource {
        ADS_B,
        ASTERIX,
        MLAT,
        FLARM,
        UNKNOWN
    }

    public Double getGeoAltitude() {
        return geoAltitude;
    }

    public void setGeoAltitude(Double geoAltitude) {
        this.geoAltitude = geoAltitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getVelocity() {
        return velocity;
    }

    public void setVelocity(Double velocity) {
        this.velocity = velocity;
    }

    public Double getHeading() {
        return heading;
    }

    public void setHeading(Double heading) {
        this.heading = heading;
    }

    public Double getVerticalRate() {
        return verticalRate;
    }

    public void setVerticalRate(Double verticalRate) {
        this.verticalRate = verticalRate;
    }

    public String getIcao24() {
        return icao24;
    }

    public void setIcao24(String icao24) {
        this.icao24 = icao24;
    }

    public String getCallsign() {
        return callsign;
    }

    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public Double getLastContact() {
        return lastContact;
    }

    public void setLastContact(Double lastContact) {
        this.lastContact = lastContact;
    }

    public Double getLastPositionUpdate() {
        return lastPositionUpdate;
    }

    public void setLastPositionUpdate(Double lastPositionUpdate) {
        this.lastPositionUpdate = lastPositionUpdate;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getSquawk() {
        return squawk;
    }

    public void setSquawk(String squawk) {
        this.squawk = squawk;
    }

    public boolean isSpi() {
        return spi;
    }

    public void setSpi(boolean spi) {
        this.spi = spi;
    }

    public Double getBaroAltitude() {
        return baroAltitude;
    }

    public void setBaroAltitude(Double baroAltitude) {
        this.baroAltitude = baroAltitude;
    }

    public PositionSource getPositionSource() {
        return positionSource;
    }
//    public Integer getPositionSource() {
//        return positionSource;
//    }

    public void setPositionSource(PositionSource positionSource) {
        this.positionSource = positionSource;
    }

//    public void setPositionSource(Integer positionSource) {
//        this.positionSource = positionSource;
//    }

    @Override
    public String toString() {
        return "StateVector{" +
                "geoAltitude=" + geoAltitude +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", velocity=" + velocity +
                ", heading=" + heading +
                ", verticalRate=" + verticalRate +
                ", icao24='" + icao24 + '\'' +
                ", callsign='" + callsign + '\'' +
                ", onGround=" + onGround +
                ", lastContact=" + lastContact +
                ", lastPositionUpdate=" + lastPositionUpdate +
                ", originCountry='" + originCountry + '\'' +
                ", squawk='" + squawk + '\'' +
                ", spi=" + spi +
                ", baroAltitude=" + baroAltitude +
                ", positionSource=" + positionSource +
                '}';
    }
}
