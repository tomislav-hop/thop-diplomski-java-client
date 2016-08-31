package implementations;

import java.util.List;

import gsonObjects.Package;
import requests.GetPackageList;

public class PackageImpl {

	public List<Package> getPackageList(){
		return new GetPackageList().getPackageList();
	}
	
	public int getPackageId(List<Package> packageList, String packageName) {
		for (Package p : packageList) {
			if (p.getPackageName().equalsIgnoreCase(packageName)) {
				return p.getPackageId();
			}
		}
		return -1;
	}
}
