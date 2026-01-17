package io.sunhacks.project.Controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.sunhacks.project.Models.Area;
import io.sunhacks.project.Services.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.sunhacks.project.Helpers.RestHelper;
import io.sunhacks.project.Models.Review;
import io.sunhacks.project.Services.ReviewService;
import lombok.extern.slf4j.Slf4j;

  @Slf4j
  @RestController
  @RequestMapping("/areas/{zipcode}/reviews")
  public class ReviewController {
  @Autowired
  private ReviewService reviewService;

  @Autowired
  private RestHelper restHelper;

  @Autowired
  private AreaService areaService;

//  @GetMapping("/hello")
//  public int hello(
//          @PathVariable int zipcode
//  ) {
//      return zipcode;
//  }

  @GetMapping("")
  public Map<String, Object> getReviews(
    @PathVariable int zipcode
  ) {
    Review input = new Review();
    input.setZipcode(zipcode);
    List<Review> output = null;
    try {
      output = reviewService.getList(input);
    } catch (Exception e) {
      return restHelper.serverError(e);
    }
    Map<String, Object> data = new LinkedHashMap<String, Object>();
    data.put("Reviews", output);
    return restHelper.sendJson(data);
  }

  //add a new review
  @PostMapping("/{id}") //where to create id?
  public Map<String, Object> postReview(
    @PathVariable int zipcode,
    @RequestParam("safety") int safety,
    @RequestParam("publicEdu") int publicEdu,
    @RequestParam("affordability") int affordability,
    @RequestParam("review") String review,
    @RequestParam("traits") String[] traits
  ) {
      Review input = new Review();
      input.setZipcode(zipcode);
      input.setSafety(safety);
      input.setPublicEdu(publicEdu);
      input.setAffordability(affordability);
      input.setReview(review);
      input.setTraits(traits);
      Review output = null;

      List<Area> original = null;
      try {
          original = areaService.getList();
      } catch (Exception e) {
          return restHelper.serverError(e);
      }
      log.info(original.toString());

//      for() {
//          int[] avgSafety;
//          int[] avgPublicEdu;
//          int[] avgAffordability;
//      }

      Area newInput = new Area();
      newInput.setSafety(safety); //fix: not change -> update using Math
      newInput.setPublicEdu(publicEdu);
      newInput.setAffordability(affordability);
      newInput.setTraits(traits); //fix: traits not change -> add new
      Area areaOutput = null;
      try {
        output = reviewService.addItem(input);
        areaOutput = areaService.editItem(newInput);
      } catch (Exception e) {
        return restHelper.serverError(e);
      }
      Map<String, Object> data = new LinkedHashMap<String, Object>();
      data.put("reviews", output);
      data.put("areas", areaOutput);
      return restHelper.sendJson(data);
  }

  @DeleteMapping("/{id}")
  public Map<String, Object> deleteReview(
    @PathVariable("id") int id) {
    Review input = new Review();
    input.setId(id);
    try {
      reviewService.deleteItem(input);
    } catch (Exception e) {
      log.debug("");
      return restHelper.serverError(e);
    }
    return restHelper.sendJson();
  }
}