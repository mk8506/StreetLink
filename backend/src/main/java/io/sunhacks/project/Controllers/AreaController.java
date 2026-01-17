package io.sunhacks.project.Controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.sunhacks.project.Models.Area;
import io.sunhacks.project.Services.AreaService;
import io.sunhacks.project.Helpers.RestHelper;

@RestController // Changed from @Controller
public class AreaController {
  @Autowired
  private AreaService areaService;

  @Autowired
  private RestHelper restHelper;

  //area -> all zipcodes
  //city -> 4 zipcodes
  //zipcode -> 1 zipcode
  @GetMapping("/areas")
  public Map<String, Object> getList() {
    List<Area> output = null;
    try {
      output = areaService.getList();
    } catch (Exception e) {
      return restHelper.serverError(e);
    }
    Map<String, Object> data = new LinkedHashMap<String, Object>();
    data.put("areas", output);
    return restHelper.sendJson(data);
  }

  @GetMapping("/areas/{zipcode}")
  public Map<String, Object> zip(
    @PathVariable(required = true) int zipcode
  ) {
    Area input = new Area();
    input.setZipcode(zipcode);
    Area output = null;
    try {
      output = areaService.getItem(input);
    } catch (Exception e) {
      return restHelper.serverError(e);
    }
    Map<String, Object> data = new LinkedHashMap<String, Object>();
    data.put("area", output);
    return restHelper.sendJson(data);
  }
}
