package io.sunhacks.project.Services;

import java.util.List;

import io.sunhacks.project.Models.Area;

public interface AreaService {
  public List<Area> getList() throws Exception;
  public List<Area> getItem(Area input) throws Exception;
  public Area addItem(Area input) throws Exception;
  public List<Area> editItem(Area input) throws Exception;
  public Area deleteArea(Area input) throws Exception;
}
