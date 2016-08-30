package requests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;

import gsonObjects.Item;
import gsonObjects.Order;
import gsonObjects.OrderItems;
import gsonObjects.Package;
import implementations.Urls;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddOrder {

	OkHttpClient client;

	public AddOrder() {
		client = new OkHttpClient();
	}

	public int sendAddOrderRequest(String orderedBy, String adress, String orderDate, String additionalNotes, int status_id, int user_id) {
		Order order = new Order(0, orderedBy, adress, orderDate, additionalNotes, status_id, user_id);

		String requestJson = new Gson().toJson(order);

		RequestBody body = RequestBody.create(Urls.JSON, requestJson);
		Request request = new Request.Builder().url(Urls.ADD_ORDER).post(body).build();
		try (Response response = client.newCall(request).execute()) {
			String returnResponse = response.body().string();
			if (returnResponse.contains("Order add failed:")) {
				return -1;
			} else {
				return Integer.parseInt(returnResponse);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public boolean addItemsToOrder(int idOrder, DefaultTableModel model, List<Item> itemList, List<Package> packageList) {

		String[][] tableData = getTableData(model);
		List<OrderItems> orderItemList = new ArrayList<>();
		for (int i = 0; i < tableData.length; i++) {
			//TODO: Check dates which you are adding
			OrderItems oi = new OrderItems(0, getItemId(itemList, tableData[i][0]), tableData[i][1], Double.parseDouble(tableData[i][2]), tableData[i][1], parseCheckboxValue(tableData[i][3]), parseCheckboxValue(tableData[i][4]), parseCheckboxValue(tableData[i][5]), getPackageId(packageList, tableData[i][6]), tableData[i][7], tableData[i][9], idOrder, Integer.parseInt(tableData[i][8]));
			orderItemList.add(oi);
		}

		//TODO: Parse to json and send to server

		return false;
	}

	private int parseCheckboxValue(String value) {
		if (value.equalsIgnoreCase("Yes")) {
			return 1;
		} else {
			return 0;
		}
	}

	private int getItemId(List<Item> itemList, String itemName) {

		for (Item i : itemList) {
			if (i.getItemName().equalsIgnoreCase(itemName)) {
				return i.getItemId();
			}
		}
		return -1;
	}

	private int getPackageId(List<Package> packageList, String packageName) {
		for (Package p : packageList) {
			if (p.getPackageName().equalsIgnoreCase(packageName)) {
				return p.getPackageId();
			}
		}
		return -1;
	}

	private String[][] getTableData(DefaultTableModel dtm) {
		int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
		String[][] tableData = new String[nRow][nCol];
		for (int i = 0; i < nRow; i++)
			for (int j = 0; j < nCol; j++)
				tableData[i][j] = dtm.getValueAt(i, j).toString();
		return tableData;
	}
}
