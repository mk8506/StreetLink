package io.sunhacks.project.Services.Impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.sunhacks.project.Mappers.ReviewMapper;
import io.sunhacks.project.Models.Review;
import io.sunhacks.project.Services.ReviewService;

@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService {
  @Autowired
  private ReviewMapper reviewMapper;

  @Override
  public List<Review> getList(Review input) throws Exception {
    List<Review> output = null;
    try {
        output = reviewMapper.selectList(input);
    } catch (Exception e) {
        throw e;
    }
    return output;
  }

//  @Override
//  public Review getItem(Review input) throws Exception {
//    Review output = null;
//    try {
//        output = reviewMapper.selectItem(input);
//        if (output == null) {
//          throw new Exception("getItem() error");
//        }
//    } catch (Exception e) {
//      throw e;
//    }
//    return output;
//  }

  // @Override
  // public Review editItem(Review input) throws Exception {
  //   Review output = null;
  //   try {
  //       output = reviewMapper.updateItem(input);
  //       if (output == null) {
  //         throw new Exception("editItem() error");
  //       }
  //   } catch (Exception e) {
  //     throw e;
  //   }
  //   return output;
  // }

  @Override
  public Review addItem(Review input) throws Exception {
    int rows = 0;

    try {
      rows = reviewMapper.insert(input);
      if (rows == 0) {
          throw new Exception("no data inserted");
      } else {
          log.info("insert review successful -> rows : " + rows);
      }
    } catch (Exception e) {
        throw e;
    }
    return reviewMapper.selectItem(input);
  }

  @Override
  public int deleteItem(Review input) throws Exception {
        int rows = 0;
        Review review = new Review();
        review.setZipcode(input.getZipcode());
        try {
            rows = reviewMapper.delete(input);
            if (rows == 0) {
                throw new Exception("no data deleted");
            } else {
                log.info("delete review successful -> rows : " + rows);
            }
        } catch (Exception e) {
            throw e;
        }
        return rows;
  }
}
