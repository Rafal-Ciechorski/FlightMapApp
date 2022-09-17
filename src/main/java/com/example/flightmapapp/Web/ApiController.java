package com.example.flightmapapp.Web;

import com.example.flightmapapp.Repository.OpenSkyApi;
import com.example.flightmapapp.Repository.StateVector;
import com.example.flightmapapp.Repository.States;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Controller
public class ApiController {
    ArrayList<Double> longit = new ArrayList<>();

    @GetMapping("/")
    public String getPinOnMap(Model model) throws IOException {
        OpenSkyApi openSkyApi = new OpenSkyApi();
        openSkyApi.getStates(0,null);
        States states = openSkyApi.getStates(0,null);
        Collection<StateVector> statesCollection = states.getStates();


        StateVector stateVector = new StateVector();
        for (StateVector vector : statesCollection) {
            longit.add(vector.getLongitude());

        }
        model.addAttribute("vector", longit.get(0));
        return "map.html";
    }
}
