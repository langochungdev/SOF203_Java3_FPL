package com.rs.dao;

import com.rs.entity.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserDAOTest {

    @Test
    void addUser() {
        try {
            List<User> list = UserDAO.getAllUsers();
            System.out.println(list.toString());
            assertNotNull(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}