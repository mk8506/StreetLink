package io.sunhacks.project.Models;

import io.sunhacks.project.Helpers.StringArrayConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import lombok.Data;

@Data
public class Review {
  private int id;
  private int zipcode;
  private int safety;
  private int publicEdu;
  private int affordability;
  private String review;
  private String[] traits;
}
