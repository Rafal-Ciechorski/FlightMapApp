package com.example.flightmapapp.Repository;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class JsonDecoder extends StdDeserializer<States> {


    protected JsonDecoder() {
        super(States.class);
    }

    @Override
    public States deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        if (jsonParser.getCurrentToken() != null && jsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
            throw deserializationContext.mappingException(States.class);
        }
        try {
            States res = new States();
            for (jsonParser.nextToken(); jsonParser.getCurrentToken() != null && jsonParser.getCurrentToken() != JsonToken.END_OBJECT; jsonParser.nextToken()) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    if ("time".equalsIgnoreCase(jsonParser.getCurrentName())) {
                        int t = jsonParser.nextIntValue(0);
                        res.setTime(t);
                    } else if ("states".equalsIgnoreCase(jsonParser.getCurrentName())) {
                       jsonParser.nextToken();
                       res.setStates(statesDeserialize(jsonParser));
                    } else {
                        // ignore other fields, but consume value
                        jsonParser.nextToken();
                    }
                } // ignore others
            }
            return res;
        } catch (JsonParseException jpe) {
            throw deserializationContext.mappingException(States.class);
        }
    }

    public Collection<StateVector>  statesDeserialize(JsonParser jsonParser) throws IOException {
        ArrayList<StateVector> result = new ArrayList<>();

        for (JsonToken next = jsonParser.nextToken(); next != null && next != JsonToken.END_ARRAY ; next = jsonParser.nextToken())
        {
            if(next == JsonToken.END_OBJECT)
            {
                break;
            }

            StateVector sVector = new StateVector();
            sVector.setIcao24(jsonParser.getText());
            sVector.setCallsign(jsonParser.getText());
            sVector.setOriginCountry(jsonParser.getText());
            sVector.setLastPositionUpdate(jsonParser.getDoubleValue());
            sVector.setLastContact(jsonParser.getDoubleValue());
            sVector.setLongitude(jsonParser.getDoubleValue());
            sVector.setLatitude(jsonParser.getDoubleValue());
            sVector.setBaroAltitude(jsonParser.getDoubleValue());
            sVector.setOnGround(jsonParser.getBooleanValue());
            sVector.setVelocity(jsonParser.getDoubleValue());
            sVector.setHeading(jsonParser.getDoubleValue());
            sVector.setVerticalRate(jsonParser.getDoubleValue());

            //sVector.setSensorsId(jsonParser.getIntValue());

            next = jsonParser.nextToken();
            if (next == JsonToken.START_ARRAY) {
                for (next = jsonParser.nextToken(); next != null && next != JsonToken.END_ARRAY; next = jsonParser.nextToken()) {
                    sVector.addSensorsId(jsonParser.getIntValue());
                }
            }

            sVector.setGeoAltitude(jsonParser.getDoubleValue());
            sVector.setSquawk(jsonParser.getText());
            sVector.setSpi(jsonParser.getBooleanValue());
            sVector.setPositionSource(jsonParser.getIntValue());

            // consume Category token - not used at the moment
            next=jsonParser.nextToken();
            // consume END_ARRAY
            next=jsonParser.nextToken();
            // consume END_OBJECT
            next=jsonParser.nextToken();

            result.add(sVector);
        }
        return result;
    }
}
