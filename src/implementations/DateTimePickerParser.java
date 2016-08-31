package implementations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.github.lgooddatepicker.components.DateTimePicker;

public class DateTimePickerParser {

	public String getDateTimeForDateTimePicker(DateTimePicker dtp) {

		if (dtp != null) {
			String date = dtp.getComponent(0).toString();
			String time = dtp.getComponent(1).toString();
			String returnDate = date + " " + time + ":00";
			return returnDate;
		}
		return "";
	}

	public LocalDateTime dateStringIntoLocalDateTime(String dateString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
		return dateTime;
	}
}
