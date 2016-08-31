package implementations;

import java.util.List;

import gsonObjects.Status;
import requests.GetStatusList;

public class StatusImpl {
	
	public List<Status> getAllStatuses(){
		return new GetStatusList().getStatusList();
	}
	
	public int getStatusId(List<Status> statusList, String selectedStatus) {
		for (Status s : statusList) {
			if (s.getStatusName().equalsIgnoreCase(selectedStatus)) {
				return s.getStatusId();
			}
		}
		return -1;
	}

}
