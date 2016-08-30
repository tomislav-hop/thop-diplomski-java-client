package requests;

import java.io.IOException;

import com.google.gson.Gson;

import gsonObjects.Order;
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

}
