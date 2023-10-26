package com.project.hotel.repository;

import com.project.hotel.domain.Person;
import com.project.hotel.domain.Room;
import com.project.hotel.input.request.GetReserveListRequest;
import com.project.hotel.service.Hotel;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class RoomDb implements RoomRepository{
    HashMap<Integer, Room> hotelRoom = new HashMap<>();
    RoomDb(){
        Room room1 = new Room(0, new HashSet<>(),"single_one",100,100);
        Room room2 = new Room(1, new HashSet<>(),"single_two",100,100);
        Room room3 = new Room(2, new HashSet<>(),"double_one",200,200);
        Room room4 = new Room(3, new HashSet<>(),"double_two",200,200);
        Room room5 = new Room(4, new HashSet<>(),"standard",200,200);
        Room room6 = new Room(5, new HashSet<>(),"primiuem",200,200);
        hotelRoom.put(room1.getId(),room1);
        hotelRoom.put(room2.getId(),room2);
        hotelRoom.put(room3.getId(),room3);
        hotelRoom.put(room4.getId(),room4);
        hotelRoom.put(room5.getId(),room5);
        hotelRoom.put(room6.getId(),room6);
    }
    /*
호텔 요청시
그 날짜가 없는 전체 룸 리스트를 반환
 */
    @Override
    public List<Room> findRoomList(LocalDate localDate) {
        List<Room> findRoomList = new ArrayList<>();
        // 1. 반환할 룸 리스트 선언
        // 2. 기존의 방들(hotelRom) 순회(반복문)//
        // 3. 순회 단계마다 입력으로 받은 localDate가 없을 시 룸 리스트에 입력
        for (Room room : hotelRoom.values()) {
            if(!room.isReserved(localDate)){
              findRoomList.add(room);
            }
        }
        return findRoomList;
    }
    /*
 룸 이름으로 검색
  */
    @Override
    public List<Room> findRoom(String roomName) {
        List<Room> findRoom = new ArrayList<>();
        for(Room room : hotelRoom.values()){
            if(room.getName().contains(roomName)){
                findRoom.add(room);
            }
        }
        return findRoom;
    }

    //숙박비가 크거나 같은면 출력
    @Override
    public List<Room> findRoomMoney(int userMoney) {
        List<Room> findRoomMoney = new ArrayList<>();
        for(Room room : hotelRoom.values()){
            if(room.getFee() <= userMoney){
                findRoomMoney.add(room);
            }
        }
        return findRoomMoney;
    }
}
