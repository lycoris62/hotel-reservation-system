package com.project.hotel.domain;

import java.time.LocalDateTime;

// 4. 예약은 객실, 고객의 이름, 고객의 전화번호,예약 날짜를 가지고 있다. + id(String UUID)도 가진다.
public class Reservation {

	private String id;
	private Room room;
	private Person person;
	private LocalDateTime dateTime;

	public String getId() {
		return id;
	}

	public Room getRoom() {
		return room;
	}

	public Person getPerson() {
		return person;
	}

	public LocalDateTime getDate() {
		return dateTime;
	}
}
