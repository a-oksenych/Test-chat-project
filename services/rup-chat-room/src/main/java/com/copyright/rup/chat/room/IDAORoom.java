package com.copyright.rup.chat.room;

import com.copyright.rup.chat.common.Room;

/**
 * @author Oleksandr Dekhtyar
 * 
 */
public interface IDAORoom {

    public Room createRoom(Room room);

    public Room getRoom(int id);

    public Room updateRoom(Room room);

    public void deleteRoom(int id);
}
