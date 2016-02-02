package cs520.gefp.model.dao;

import java.util.List;

import cs520.gefp.model.Stage;

public interface StageDao {
	Stage getStage( Long id );
	List<Stage> getStages();
	Stage saveStage(Stage stage);
}
