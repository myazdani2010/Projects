package cs520.gefp.model.dao;

import java.util.List;

import cs520.gefp.model.Cell;
import cs520.gefp.model.Plan;
import cs520.gefp.model.Runway;
import cs520.gefp.model.Stage;

public interface CellDao 
{
	Cell getCell( Long id);
	List<Cell> getCells();
	Cell saveCell( Cell cell);
	Cell getCell(Plan plan, Runway runway, Stage stage);

}
