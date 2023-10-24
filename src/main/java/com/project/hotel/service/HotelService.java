package com.project.hotel.service;

import java.util.List;

import com.project.hotel.domain.Person;
import com.project.hotel.domain.Reservation;

public interface HotelService {

	/**
	 * 고객이 예약할 때 사용.
	 *
	 * @param person 고객
	 * @return boolean 예약 실패시 false 반환
	 */
	boolean reserve(Person person);

	/**
	 * 사용자의 예약 정보 조회할 때 사용. 고객이면 고객의 예약 내역, 관리자면 전체 예약 내역을 반환.
	 *
	 * @param person 고객 또는 관리자
	 * @return List<Reservation> 예약 내역
	 */
	List<Reservation> getReservations(Person person);

	/**
	 * 고객이 예약 취소할 때 사용.
	 *
	 * @param person        고객
	 * @param reservationId 예약 번호
	 * @return boolean 예약 취소 실패시 false 반환
	 */
	boolean cancelReservation(Person person, String reservationId);
}
