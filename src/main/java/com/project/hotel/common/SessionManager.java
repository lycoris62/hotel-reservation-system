package com.project.hotel.common;

import com.project.hotel.domain.Person;
import com.project.hotel.domain.Role;

import java.util.HashMap;

public class SessionManager {
    HashMap<String, Person> userMap = new HashMap<>();

    public SessionManager() {
        save(new Person("admin", "123", "010-0000-0000", "관리자", Role.ADMIN, 50000));
        save(new Person("user1", "111", "010-1111-1111", "투숙객", Role.CUSTOMER, 300000));
        save(new Person("user2", "222", "010-2222-2222", "장발장", Role.CUSTOMER, 300));
    }

    public Person getUser(String id, String password) {
        if (userMap.containsKey(id) && userMap.get(id).getPassword().equals(password))
            return userMap.get(id);
        throw new RuntimeException("유저 불러오기 실패");
    }

    public void save(Person user) {
        userMap.put(user.getId(), user);
    }

}