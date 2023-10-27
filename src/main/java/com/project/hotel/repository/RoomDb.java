package com.project.hotel.repository;

import com.project.hotel.domain.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class RoomDb implements RoomRepository {
    private final HashMap<Integer, Room> hotelRoom = new HashMap<>();

    public RoomDb() {
        save(new Room(0, new HashSet<>(), "single_one", 100, 100));
        save(new Room(1, new HashSet<>(), "single_two", 100, 100));
        save(new Room(2, new HashSet<>(), "double_one", 200, 200));
        save(new Room(3, new HashSet<>(), "double_two", 200, 200));
        save(new Room(4, new HashSet<>(), "standard", 200, 200));
        save(new Room(5, new HashSet<>(), "primiuem", 200, 200));
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
            if (!room.isReserved(localDate)) {
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
        for (Room room : hotelRoom.values()) {
            if (room.getName().contains(roomName)) {
                findRoom.add(room);
            }
        }
        return findRoom;
    }
    //숙박비가 크거나 같은면 출력

    @Override
    public List<Room> findRoomMoney(int userMoney) {
        List<Room> findRoomMoney = new ArrayList<>();
        for (Room room : hotelRoom.values()) {
            if (room.getFee() <= userMoney) {
                findRoomMoney.add(room);
            }
        }
        return findRoomMoney;
    }
    @Override
    public void save(Room room) {
        hotelRoom.put(room.getId(), room);
    }
}
