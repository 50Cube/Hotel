package com.mycompany.store;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args)
    {
        Admin admin = new Admin("Norbert", "Gierczak", "dis", "", true);
        Manager manager = new Manager("Marcin", "Krasucki", "jd", "", true);
        Client client1 = new Client("Gabriel", "Nowak", "gabor", "", true);
        Client client2 = new Client("Jakub", "Bogdan", "herb", "", false);

        Room room1 = new Room(1, 50, 2);
        Room room2 = new Room(2, 60, 3);

        Sauna sauna = new Sauna(20);

        RoomRepository roomRepository = new RoomRepository();
        UserRepository userRepository = new UserRepository();

        try
        {
            userRepository.addUser(admin);
            userRepository.addUser(manager);
            userRepository.addUser(client1);
            userRepository.addUser(client2);

            roomRepository.addRoom(room1);
            roomRepository.addRoom(room2);
            roomRepository.addRoom(room1);
        }
        catch(Exception e)
        {
            e.getMessage();
        }

        userRepository.activateUser(client2.getLogin());
        System.out.println(client2.getIsActive());

        Rent rent = new Rent(room1,client1,new GregorianCalendar(2019,11,9), new GregorianCalendar(2018,11,15));
    }
    
}
