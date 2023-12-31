package com.project.hotel.domain;

import java.time.LocalDateTime;
import java.util.UUID;

// 4. 예약은 객실, 고객의 이름, 고객의 전화번호,예약 날짜를 가지고 있다. + id(String UUID)도 가진다.
public class Reservation {

	private String id;
	private Room room;
	private Person person;
	private LocalDateTime dateTime;
	private int price;

	public Reservation(Room room, Person person, LocalDateTime dateTime, int price) {
		this.id = UUID.randomUUID().toString().split("-")[0];
		this.room = room;
		this.person = person;
		this.dateTime = dateTime;
		this.price = price;
	}


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

	public int getPrice() {
		return price;
	}
}
