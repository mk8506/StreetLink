package io.sunhacks.project.Mappers;

import java.util.List;

import io.sunhacks.project.Helpers.StringArrayConverter;
import org.apache.ibatis.annotations.*;

import io.sunhacks.project.Models.Area;

@Mapper
public interface AreaMapper {
  //area -> all zipcodes
  //city -> 4 zipcodes
  //zipcode -> 1 zipcode
  @Select("<script>" +
            "SELECT * FROM area_ratings" +
            "</script>")
  @Results({
          @Result(column = "traits", property = "traits",
                  typeHandler = StringArrayConverter.class)
  })
  public List<Area> selectList();

  @Select("<script>" +
          "SELECT * FROM area_ratings WHERE zipcode = #{zipcode}" +
          "</script>")
  @Results({
          @Result(column = "traits", property = "traits",
                  typeHandler = StringArrayConverter.class)
  })
  public Area selectItem(Area input);

  @Update("<script>" +
            "UPDATE area_ratings SET safety = #{safety}, publicEdu = #{publicEdu}, affordability = #{affordability}" +
            "WHERE  zipcode = #{zipcode}" +
            "</script>")
  @Results({
          @Result(column = "traits", property = "traits",
                  typeHandler = StringArrayConverter.class)
  })
  public int updateItem(Area input);
}
