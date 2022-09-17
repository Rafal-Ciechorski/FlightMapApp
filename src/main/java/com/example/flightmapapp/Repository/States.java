package com.example.flightmapapp.Repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.Collection;
@JsonDeserialize(using = JsonDecoder.class)
public class States {

@JsonProperty("time")
    private int time;
@JsonProperty("stateVector")
    private Collection<StateVector> flightStates;

    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }

    public Collection<StateVector> getStates() {
        if (flightStates == null || flightStates.isEmpty()) return null;
        return this.flightStates;
    }

    public void setStates(Collection<StateVector> states) {
        this.flightStates = states;
    }
}
