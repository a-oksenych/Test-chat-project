package com.copyright.rup.chat.room;

import com.copyright.rup.chat.common.Room;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Oleksandr Dekhtyar
 * 
 */
@Controller
public class RoomResource {

    @Autowired
    private RoomService roomService;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
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
    @ResponseBody
    public Room getRoom(@PathVariable int id) {
        return roomService.getRoom(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
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
