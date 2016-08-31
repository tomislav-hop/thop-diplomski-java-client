package implementations;

import java.util.List;

import gsonObjects.Item;
import requests.GetItemList;

public class ItemImpl {

	public List<Item> getItemList() {
		return new GetItemList().getItemList();
	}

	public int getItemId(List<Item> itemList, String itemName) {

		for (Item i : itemList) {
			if (i.getItemName().equalsIgnoreCase(itemName)) {
				return i.getItemId();
			}
		}
		return -1;
	}
}
