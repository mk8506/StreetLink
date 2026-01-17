package io.sunhacks.project.Models;

import io.sunhacks.project.Helpers.StringArrayConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import lombok.Data;

import java.util.List;

@Data
public class Area {
  private int zipcode;
  private String city;
  private int population;
  private float safety;
  private float publicEdu;
  private float affordability;
  private String[] traits;
  private String description;
}
