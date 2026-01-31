package io.sunhacks.project.Controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.sunhacks.project.Models.Area;
import io.sunhacks.project.Services.AreaService;
import io.sunhacks.project.Helpers.RestHelper;

@RestController
@RequestMapping("/areas")
public class AreaController {
  @Autowired
  private AreaService areaService;

  @Autowired
  private RestHelper restHelper;

  @GetMapping("/search")
  public Map<String, Object> searchAreas(
    @RequestParam(required = false) String city,
    @RequestParam(required = false) Integer zipcode
  ) {
    if (city == null && zipcode == null) {
        return restHelper.sendError(204, "At least one parameter (city or zipcode) must be provided");
    }
    List<Area> output = null;
    try {
      if (city != null && zipcode != null) {
        output = areaService.findByCityAndZipcode(city, zipcode);
      } else if (city != null) {
        output = areaService.findByCity(city);
      } else {
        output = areaService.findByZipcode(zipcode);
      }
    } catch (Exception e) {
      return restHelper.serverError(e);
    }
    Map<String, Object> data = new LinkedHashMap<String, Object>();
    data.put("areas", output);
    return restHelper.sendJson(data);
  }
}
