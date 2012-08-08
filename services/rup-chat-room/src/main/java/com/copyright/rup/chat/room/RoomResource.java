package com.copyright.rup.chat.room;

import com.copyright.rup.chat.common.Room;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    protected RoomService roomService;

    @Autowired
    protected ObjectMapper objectMapper;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Room createRoom(HttpServletRequest request, HttpServletResponse response,
            @RequestBody String serializedRoom) {
        try {
            Room room = objectMapper.readValue(serializedRoom, Room.class);
            response.setStatus(HttpServletResponse.SC_CREATED);
            return roomService.createRoom(room);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Room getRoom(HttpServletRequest request, HttpServletResponse response, @PathVariable int id) {
        Room room = roomService.getRoom(id);
        if (room == null) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
        return room;
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
    public void deleteRoom(HttpServletRequest request, HttpServletResponse response, @PathVariable int id) {
        roomService.deleteRoom(id);
    }
}
