package io.sunhacks.project.Services;

import java.util.List;

import io.sunhacks.project.Models.Review;

public interface ReviewService {
  public List<Review> getList(Review input) throws Exception;
  //public Review getItem(Review input) throws Exception;
  public Review addItem(Review input) throws Exception;
  //public Review editItem(Review input) throws Exception;
  public int deleteItem(Review input) throws Exception;
}
