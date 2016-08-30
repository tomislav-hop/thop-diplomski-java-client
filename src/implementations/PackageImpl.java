package implementations;

import java.util.List;

import gsonObjects.Package;
import requests.GetPackageList;

public class PackageImpl {

	public List<Package> getPackageList(){
		return new GetPackageList().getPackageList();
	}
}
