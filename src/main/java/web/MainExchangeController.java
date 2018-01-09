package web;

import model.JPYModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import service.JpyService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MainExchangeController {
    @Autowired
    JpyService service;

    @RequestMapping(value = "/getLast", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public JPYModel getLast() {
        return service.getLast();
    }

    @GetMapping(value = "/buy", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> buy(@RequestParam(value = "sum") String sum) {
        JPYModel jpyModel = service.getLast();
        Map<String, String> resultMap = new HashMap<>();

        if ((sum != null) && jpyModel != null) {

            Double currentCourse = jpyModel.getValue();
            Double currDouble = Double.parseDouble(sum);
            resultMap.put("data", Double.toString(currDouble / currentCourse * jpyModel.getNominal()));
            resultMap.put("status", "OK");

        } else {
            resultMap.put("data", "empty");
            resultMap.put("status", "ERROR");
        }

        return resultMap;


    }

    @GetMapping(value = "/sell", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> sell(@RequestParam(value = "sum") String sum) {
        JPYModel jpyModel = service.getLast();
        Map<String, String> resultMap = new HashMap<>();

        if ((sum != null) && jpyModel != null) {
            Double currentCourse = jpyModel.getValue();
            Double currDouble = Double.parseDouble(sum);
            resultMap.put("data", Double.toString(currDouble * currentCourse / jpyModel.getNominal()));
            resultMap.put("status", "OK");

        } else {
            resultMap.put("data", "empty");
            resultMap.put("status", "ERROR");
        }

        return resultMap;
    }

}
