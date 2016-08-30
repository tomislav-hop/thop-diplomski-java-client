package implementations;

import java.util.List;

import gsonObjects.Status;
import requests.GetStatusList;

public class StatusImpl {
	
	public List<Status> getAllStatuses(){
		return new GetStatusList().getStatusList();
	}

}
