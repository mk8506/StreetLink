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
  public List<Area> selectItem(Area input);

  @Update("<script>" +
            "UPDATE area_ratings SET safety = #{safety}, publicEdu = #{publicEdu}, affordability = #{affordability}" +
            "WHERE  zipcode = #{zipcode}" +
            "</script>")
  @Results({
          @Result(column = "traits", property = "traits",
                  typeHandler = StringArrayConverter.class)
  })
  public int updateItem(Area input);
    
  @Select("SELECT * FROM area_ratings WHERE city = #{city} AND zipcode = #{zipcode}")
  @Results({
        @Result(property = "zipcode", column = "zipcode"),
        @Result(property = "city", column = "city"),
        @Result(property = "population", column = "population"),
        @Result(property = "safety", column = "safety"),
        @Result(property = "publicEdu", column = "public_edu"),
        @Result(property = "affordability", column = "affordability"),
        @Result(property = "traits", column = "traits", 
                typeHandler = StringArrayConverter.class),
        @Result(property = "description", column = "description")
  })
  List<Area> findByCityAndZipcode(@Param("city") String city, @Param("zipcode") int zipcode);
    
  @Select("SELECT * FROM area_ratings WHERE city = #{city}")
  @Results({
        @Result(property = "zipcode", column = "zipcode"),
        @Result(property = "city", column = "city"),
        @Result(property = "population", column = "population"),
        @Result(property = "safety", column = "safety"),
        @Result(property = "publicEdu", column = "public_edu"),
        @Result(property = "affordability", column = "affordability"),
        @Result(property = "traits", column = "traits", 
                typeHandler = StringArrayConverter.class),
        @Result(property = "description", column = "description")
  })
  List<Area> findByCity(@Param("city") String city);
    
  @Select("SELECT * FROM area_ratings WHERE zipcode = #{zipcode}")
  @Results({
        @Result(property = "zipcode", column = "zipcode"),
        @Result(property = "city", column = "city"),
        @Result(property = "population", column = "population"),
        @Result(property = "safety", column = "safety"),
        @Result(property = "publicEdu", column = "public_edu"),
        @Result(property = "affordability", column = "affordability"),
        @Result(property = "traits", column = "traits", 
                typeHandler = StringArrayConverter.class),
        @Result(property = "description", column = "description")
  })
  List<Area> findByZipcode(@Param("zipcode") int zipcode);
}
