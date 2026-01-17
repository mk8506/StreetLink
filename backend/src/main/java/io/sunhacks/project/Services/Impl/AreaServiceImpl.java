package io.sunhacks.project.Services.Impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.sunhacks.project.Mappers.AreaMapper;
import io.sunhacks.project.Models.Area;
import io.sunhacks.project.Services.AreaService;

@Slf4j
@Service
public class AreaServiceImpl implements AreaService {
  @Autowired
  private AreaMapper areaMapper;

  @Override
  public List<Area> getList() throws Exception {
    List<Area> output = null;
    try {
        output = areaMapper.selectList();
        if (output == null) {
            throw new Exception("no data from Mapper");
        }
    } catch (Exception e) {
        throw e;
    }
    return output;
  }

  @Override
  public Area getItem(Area input) throws Exception {
    Area output = null;
    try {
        output = areaMapper.selectItem(input);
        if (output == null) {
          throw new Exception("no data from Mapper");
        }
    } catch (Exception e) {
      throw e;
    }
    return output;
  }

   @Override
   public Area editItem(Area input) throws Exception {
     Area output = null;
     int rows = 0;
     try {
         rows = areaMapper.updateItem(input);
         output = areaMapper.selectItem(input);
         if (output == null) {
           throw new Exception("no data from Mapper");
         } else {
             log.info("update success -> rows : " + rows);
         }
     } catch (Exception e) {
       throw e;
     }
     return output;
   }

  @Override
  public Area addItem(Area input) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'addItem'");
  }

  @Override
  public Area deleteArea(Area input) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deletArea'");
  }
}
