package com.copyright.rup.chat.room;

import java.util.HashMap;
import java.util.Map;

import com.copyright.rup.chat.common.Room;

/**
 * @author Oleksandr Dekhtyar
 * 
 */
public class DAORoomStub implements IDAORoom {

    private int id = 0;
    private Map<Integer, Room> rooms = new HashMap<Integer, Room>();

    @Override
    public Room createRoom(Room room) {
        room.setId(++id);
        rooms.put(room.getId(), room);
        return room;
    }

    @Override
    public Room getRoom(int id) {
        return rooms.get(id);
    }

    @Override
    public Room updateRoom(Room room) {
        rooms.put(room.getId(), room);
        return room;
    }

    @Override
    public void deleteRoom(int id) {
        rooms.remove(id);
    }
}
