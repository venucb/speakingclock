package com.speakingclock.service.impl;

import org.springframework.stereotype.Service;

import com.speakingclock.exception.InvalidInputException;
import com.speakingclock.service.ClockService;

@Service
public class ClockServiceImpl implements ClockService {

	@Override
	public String speakTime(String time) {
		try {
			String hoursString = time.substring(0, 2);
			String minutesString = time.substring(3);

			int hours = Integer.parseInt(hoursString);
			int minutes = Integer.parseInt(minutesString);
			return getTimeInWords(hours, minutes);
		} catch (Exception e) {
			throw new InvalidInputException("Invalid Input. Please enter time in HH:MM (24 Hr) format");
		}
	}

	private String getTimeInWords(int hours, int minutes) {
		String[] tens = { "", " ten", " twenty", " thirty", " forty", " fifty" };
		String[] ones = { "", " one", " two", " three", " four", " five", " six", " seven", " eight", " nine", " ten",
				" eleven", " twelve", " thirteen", " fourteen", " fifteen", " sixteen", " seventeen", " eighteen",
				" nineteen" };

		StringBuffer timeInWords = new StringBuffer("Its");
		StringBuffer hourInWords = new StringBuffer();
		if ((hours / 10) > 1) {
			hourInWords.append(tens[(hours / 10)]).append(ones[(hours % 10)]);
		} else {
			hourInWords.append(ones[hours]);
		}
		if (minutes == 0)
			if (hours == 12) {
				timeInWords.append(" midday");
			} else if (hours == 24) {
				timeInWords.append(" midnight");
			} else {
				timeInWords.append(hourInWords).append(" o'clock");
			}
		else {
			if (hours >= 24) {
				throw new InvalidInputException("Invalid Input. Please enter time in HH:MM (24 Hr) format");
			}
			timeInWords.append(hourInWords.toString()).append(tens[(minutes / 10)]).append(ones[(minutes % 10)]);
		}
		return timeInWords.toString();
	}
}
