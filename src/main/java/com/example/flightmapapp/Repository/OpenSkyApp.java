package com.example.flightmapapp.Repository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;

public class OpenSkyApp {

    public static void main(String[] args) throws IOException, URISyntaxException {
      OpenSkyApi openSkyApi = new OpenSkyApi();
      openSkyApi.responder();

    }

}
