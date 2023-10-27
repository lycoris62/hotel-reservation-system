package com.project.hotel.repository;

import com.project.hotel.domain.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository {
    /*
    호텔 요청시
    그 날짜가 없는 전체 룸 리스트를 반환
     */
    List<Room> findRoomList(LocalDate localDate);
    /*
    룸 이름으로 검색
     */
    List<Room> findRoom(String roomName);

    List<Room> findRoomMoney(int roomfee);

    void save(Room room);

}