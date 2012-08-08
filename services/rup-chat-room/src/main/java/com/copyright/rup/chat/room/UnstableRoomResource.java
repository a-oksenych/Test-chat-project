package com.copyright.rup.chat.room;

import com.copyright.rup.chat.common.Room;

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
@RequestMapping("/unstable")
public class UnstableRoomResource extends RoomResource {

    static int counter = 0;
    static Thread timer = null;

    @Override
    public Room createRoom(HttpServletRequest request, HttpServletResponse response,
            @RequestBody String serializedRoom) {
        if (isAvailable(response)) {
            return super.createRoom(request, response, serializedRoom);
        }
        return null;
    }

    @Override
    public Room getRoom(HttpServletRequest request, HttpServletResponse response, @PathVariable int id) {
        if (isAvailable(response)) {
            return super.getRoom(request, response, id);
        }
        return null;
    }

    @Override
    public Room updateRoom(HttpServletRequest request, HttpServletResponse response,
            @RequestBody String serializedRoom) {
        if (isAvailable(response)) {
            return super.updateRoom(request, response, serializedRoom);
        }
        return null;
    }

    @Override
    public void deleteRoom(HttpServletRequest request, HttpServletResponse response, @PathVariable int id) {
        if (isAvailable(response)) {
            super.deleteRoom(request, response, id);
        }
    }

    private synchronized boolean isAvailable(HttpServletResponse response) {
        System.out.println("counter: " + counter);
        if (++counter > 10) {
            if (timer == null) {
                timer = new Thread() {

                    @Override
                    public void run() {
                        try {
                            sleep(20000);
                        } catch (Exception e) {
                            // ignored
                        }
                        System.out.println("RESUME");
                        counter = 0;
                        timer = null;
                    }
                };
                timer.start();
            }
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return false;
        }
        return true;
    }
}
