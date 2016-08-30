package implementations;

import com.github.lgooddatepicker.components.DateTimePicker;

public class DateTimePickerParser {

	public String getDateTimeForDateTimePicker (DateTimePicker dtp){
		
		if(dtp != null){
			String date = dtp.getComponent(0).toString();
			String time = dtp.getComponent(1).toString();	
			String returnDate = date + " " + time + ":00";
			return returnDate;
		}
		return "";
	}
}
