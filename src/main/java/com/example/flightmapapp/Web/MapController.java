package com.example.flightmapapp.Web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapController {

    @GetMapping("/")
    public String index()
    {
        return "Witaj na stronie";
    }
}
