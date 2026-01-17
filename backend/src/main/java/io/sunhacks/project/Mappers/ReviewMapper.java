package io.sunhacks.project.Mappers;

import java.util.List;

import io.sunhacks.project.Helpers.StringArrayConverter;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import io.sunhacks.project.Models.Review;

@Mapper
public interface ReviewMapper {
  @Select("<script>" +
            "SELECT * FROM area_review WHERE area_code = #{zipcode}" +
            "</script>"
            )
  @Results({
          @Result(column = "traits", property = "traits",
                  typeHandler = io.sunhacks.project.Helpers.StringArrayConverter.class)
  })
  public List<Review> selectList(Review input);

  @Select("SELECT id, area_code, safety_rating, education_rating, affordability_rating, review, traits " +
          "FROM area_review WHERE area_code = #{zipcode}")
  @Results(id = "ReviewResultMap", value = {
      @Result(property = "traits", column = "traits",
              typeHandler = io.sunhacks.project.Helpers.StringArrayConverter.class)
  })
  public Review selectItem(Review input);

  @Insert("INSERT INTO area_review (id, area_code, safety_rating, education_rating, affordability_rating, review, traits)" +
        "VALUES (#{id}, #{zipcode}, #{safety}, #{publicEdu}, #{affordability}, #{review}, #{traits})")
  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  @Results({
          @Result(column = "traits", property = "traits",
                  typeHandler = io.sunhacks.project.Helpers.StringArrayConverter.class)
  })
  public int insert(Review input);

//   @Update("UPDATE review SET city=#{city} WHERE area_code=#{zipcode}")
//   public Review updateItem(Review input);

  @Delete("DELETE FROM area_review WHERE area_code=#{zipcode}")
  @Results({
          @Result(column = "traits", property = "traits",
                  typeHandler = io.sunhacks.project.Helpers.StringArrayConverter.class)
  })
  public int delete(Review input);
}
