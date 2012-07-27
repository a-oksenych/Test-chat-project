package com.copyright.rup.chat.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.copyright.rup.chat.common.Room;

/**
 * @author Oleksandr Dekhtyar
 * 
 */
@Service
public class RoomService {

    @Autowired
    private IDAORoom daoRoom;

    public Room createRoom(Room room) {
        return daoRoom.createRoom(room);
    }

    public Room getRoom(int id) {
        return daoRoom.getRoom(id);
    }

    public Room updateRoom(Room room) {
        return daoRoom.updateRoom(room);
    }

    public void deleteRoom(int id) {
        daoRoom.deleteRoom(id);
    }
}
