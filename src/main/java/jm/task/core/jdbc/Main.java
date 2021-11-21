package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Alex", "Potapov", (byte) 25);
        userService.saveUser("Andrew", "Popov", (byte) 18);
        userService.saveUser("Vladimir", "Vinogradov", (byte) 30);
        userService.saveUser("Petr", "Kochetov", (byte) 42);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
