package com.example.flightmapapp.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.squareup.okhttp.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import okhttp3.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;

public class OpenSkyApi {
    private static final String HOST = "opensky-network.org";
    private static final String API_ROOT = "https://" + HOST + "/api";
    private static final String STATES_URI = API_ROOT + "/states/all";

    private final ObjectMapper mapper = new ObjectMapper();

    private final OkHttpClient okHttpClient = new OkHttpClient();

    public OpenSkyApi()
    {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(States.class, new JsonDecoder());
        mapper.registerModule(simpleModule);
    }


    /** Make the actual HTTP Request and return the parsed response
     * @param baseUri base uri to request
     * @param nvps name value pairs to be sent as query parameters
     * @return parsed states
     * @throws IOException if there was an HTTP error
     */
    private States getResponse(String baseUri, Collection<AbstractMap.Entry<String,String>> nvps) throws IOException {
        HttpUrl parsedUrl = HttpUrl.parse(baseUri);
        if (parsedUrl == null) {
            throw new MalformedURLException("Could not parse uri " + baseUri);
        }

        HttpUrl.Builder urlBuilder = parsedUrl.newBuilder();
        for (AbstractMap.Entry<String,String> nvp : nvps) {
            urlBuilder.addQueryParameter(nvp.getKey(), nvp.getValue());
        }
        Request req = new Request.Builder()
                .url(urlBuilder.build())
                .build();

        Response response = okHttpClient.newCall(req).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Could not get OpenSky Vectors, response " + response);
        }

        String contentType = response.header("Content-Type");
        Charset charset = null;
        if (contentType != null) {
            MediaType mediaType = MediaType.parse(contentType);
            if (mediaType != null) {
                charset = mediaType.charset();
            }
        }
        if (charset != null) {
            return mapper.readValue(new InputStreamReader(response.body().byteStream(), charset), States.class);
        } else {
            throw new IOException("Could not read charset in response. Content-Type is " + contentType);
        }
    }

    /**
     * Get states from server and handle errors
     * @throws IOException if there was an HTTP error
     */
    private States getSkyStates(String baseUri, ArrayList<AbstractMap.Entry<String,String>> nvps) throws IOException {
        try {
            return getResponse(baseUri, nvps);
        } catch (MalformedURLException e) {
            // this should not happen
            e.printStackTrace();
            throw new RuntimeException("Programming Error in OpenSky API. Invalid URI. Please report a bug");
        } catch (JsonParseException | JsonMappingException e) {
            // this should not happen
            e.printStackTrace();
            throw new RuntimeException("Programming Error in OpenSky API. Could not parse JSON Data. Please report a bug");
        }
    }

    /**
     * Retrieve state vectors for a given time. If time == 0 the most recent ones are taken.
     * Optional filters might be applied for ICAO24 addresses.
     *
     * @param time Unix time stamp (seconds since epoch).
     * @param icao24 retrieve only state vectors for the given ICAO24 addresses. If {@code null}, no filter will be applied on the ICAO24 address.
     * @return {@link States} if request was successful, {@code null} otherwise or if there's no new data/rate limit reached
     * @throws IOException if there was an HTTP error
     */
    public States getStates(int time, String[] icao24) throws IOException {
        ArrayList<AbstractMap.Entry<String,String>> nvps = new ArrayList<>();
        if (icao24 != null) {
            for (String i : icao24) {
                nvps.add(new AbstractMap.SimpleImmutableEntry<>("icao24", i));
            }
        }
        nvps.add(new AbstractMap.SimpleImmutableEntry<>("time", Integer.toString(time)));
        return getSkyStates(STATES_URI, nvps);
    }


    public void responder() throws URISyntaxException, IOException {
        URI uri = new URI(STATES_URI);
        OkHttpClient okHttpClient1 = new OkHttpClient();
        Request request = new Request.Builder()
                .url(STATES_URI)
                .build();

        Call call = okHttpClient1.newCall(request);
        Response response = call.execute();
        if (!response.isSuccessful()) {
            throw new IOException("Could not get OpenSky Vectors, response " + response);
        }
        System.out.println(response.code());
        System.out.println(response.body().string());
    }

}

