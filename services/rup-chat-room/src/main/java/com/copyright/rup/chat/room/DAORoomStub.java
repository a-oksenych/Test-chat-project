package com.copyright.rup.chat.room;

import java.util.HashMap;
import java.util.Map;

import com.copyright.rup.chat.common.Room;
import com.copyright.rup.chat.room.RoomException.Type;

/**
 * @author Oleksandr Dekhtyar
 * 
 */
public class DAORoomStub implements IDAORoom {

    private int idx = 0;
    private Map<Integer, Room> rooms = new HashMap<Integer, Room>();

    @Override
    public Room createRoom(Room room) {
        room.setId(++idx);
        rooms.put(room.getId(), room);
        return room;
    }

    @Override
    public Room getRoom(int id) {
        return rooms.get(id);
    }

    @Override
    public Room updateRoom(Room room) {
        if (!rooms.containsKey(room.getId())) {
            throw new RoomException(Type.NOT_FOUND, "Room not found");
        }
        rooms.put(room.getId(), room);
        return room;
    }

    @Override
    public void deleteRoom(int id) {
        rooms.remove(id);
    }
}
