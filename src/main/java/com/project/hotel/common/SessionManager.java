package com.project.hotel.common;

import com.project.hotel.domain.Person;
import com.project.hotel.domain.Role;

import java.util.HashMap;

public class SessionManager {
    HashMap<String, Person> userMap = new HashMap<>();

    public SessionManager() {
        Person admin = new Person("admin", "123", "010-0000-0000", "관리자", Role.ADMIN, 50000);
        Person customer1 = new Person("user1", "111", "010-1111-1111", "투숙객", Role.CUSTOMER, 300000);
        Person customer2 = new Person("user2", "222", "010-2222-2222", "장발장", Role.CUSTOMER, 300);
        userMap.put(admin.getId(), admin);
        userMap.put(customer1.getId(), customer1);
        userMap.put(customer2.getId(), customer2);
    }

    public Person getUser(String id, String password) {
        if (userMap.containsKey(id) && userMap.get(id).getPassword().equals(password))
            return userMap.get(id);
        throw new RuntimeException("유저 불러오기 실패");
    }

}
