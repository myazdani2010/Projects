package cs520.gefp.model.dao;

import java.util.List;

import cs520.gefp.model.Checkpoint;

public interface CheckpointDao 
{
	Checkpoint getCheckpoint( Long id );
	List<Checkpoint> getCheckpoints();
	Checkpoint saveCheckpoint(Checkpoint checkpoint);
}
