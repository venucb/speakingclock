package com.speakingclock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.speakingclock.exception.InvalidInputException;
import com.speakingclock.service.ClockService;

@RestController
public class ClockController {

	@Autowired
	private ClockService clockService;

	@GetMapping("/speakingclock/{time}")
	public ResponseEntity<String> speakTime(@PathVariable("time") String time) {
		if (!StringUtils.hasText(time)) {
			throw new InvalidInputException("Please enter time in HH:MM format");
		}
		return new ResponseEntity<>(clockService.speakTime(time), HttpStatus.OK);
	}
}