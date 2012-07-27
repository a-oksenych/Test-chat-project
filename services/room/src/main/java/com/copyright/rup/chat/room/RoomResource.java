package com.copyright.rup.chat.room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.copyright.rup.chat.common.Room;

/**
 * @author Oleksandr Dekhtyar
 * 
 */
@Controller
public class RoomResource {

    @Autowired
    private RoomService roomService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Room createRoom(HttpServletRequest request, HttpServletResponse response,
            @RequestBody String serializedRoom) {
        try {
            Room room = objectMapper.readValue(serializedRoom, Room.class);
            return roomService.createRoom(room);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Room getRoom(@PathVariable int id) {
        return roomService.getRoom(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Room updateRoom(HttpServletRequest request, HttpServletResponse response,
            @RequestBody String serializedRoom) {
        try {
            Room room = objectMapper.readValue(serializedRoom, Room.class);
            return roomService.updateRoom(room);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteRoom(@PathVariable int id) {
        roomService.deleteRoom(id);
    }
}
