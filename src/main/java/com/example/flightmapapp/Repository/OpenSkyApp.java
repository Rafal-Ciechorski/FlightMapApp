package com.example.flightmapapp.Repository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class OpenSkyApp {

    public static void main(String[] args) throws IOException, URISyntaxException {
      OpenSkyApi openSkyApi = new OpenSkyApi();

      openSkyApi.getStates(0,null);
      States states = openSkyApi.getStates(0,null);
     //Collection<StateVector> stateCollection = states.getStates();

    }

//    public static void getStateVector(Collection<StateVector> kolekcja, int i) {
//        for (Iterator iter = kolekcja.iterator(); i < 1; ) {
//            System.out.print(i + " ");
//            System.out.println(iter.next());
//            i++;
//            iter.next();
//        }
//
//  }
}
