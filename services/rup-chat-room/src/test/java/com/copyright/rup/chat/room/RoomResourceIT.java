package com.copyright.rup.chat.room;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.copyright.rup.chat.common.Account;
import com.copyright.rup.chat.common.Room;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.PutMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ObjectUtils;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Oleksandr Dekhtyar
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/context/spring-context-test.xml" })
public class RoomResourceIT {

    private WebConversation conversation;
    @Autowired
    private ObjectMapper objectMapper;
    private Room baseRoom;

    @Before
    public void setUp() {
        conversation = new WebConversation();

        baseRoom = new Room();
        baseRoom.setName("Test room");
        baseRoom.setDescription("Test room description");
        List<Account> users = new ArrayList<Account>();
        for (int i = 0; i < 5; i++) {
            users.add(prepareAccount(i + 1));
        }
        baseRoom.setUsers(users);
    }

    private Account prepareAccount(int id) {
        Account account = new Account();
        account.setId(id);
        account.setName("User_" + id);
        account.setPassword("User_" + id);
        account.setEmail("User_" + id + "@example.com");
        return account;
    }

    @Test
    public void testRoom() throws Exception {
        // create room
        WebRequest request = new PostMethodWebRequest(getCreateUrl(), new ByteArrayInputStream(objectMapper
                .writeValueAsString(baseRoom).getBytes()), MediaType.APPLICATION_JSON_VALUE);
        WebResponse response = conversation.getResponse(request);
        assertEquals(HttpURLConnection.HTTP_OK, response.getResponseCode());
        assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
        Room room = objectMapper.readValue(response.getText(), Room.class);
        assertNotNull(room);
        assertTrue(room.getId() != 0);
        assertTrue(compare(baseRoom, room, "id"));

        baseRoom = room;
        baseRoom.setName("New name");

        request = new PutMethodWebRequest(getUpdateUrl(), new ByteArrayInputStream(objectMapper
                .writeValueAsString(baseRoom).getBytes()), MediaType.APPLICATION_JSON_VALUE);
        response = conversation.getResponse(request);
        assertEquals(HttpURLConnection.HTTP_OK, response.getResponseCode());
        assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
        room = objectMapper.readValue(response.getText(), Room.class);
        assertNotNull(room);
        assertEquals(room, baseRoom);

        request = new GetMethodWebRequest(getViewUrl(baseRoom.getId()));
        response = conversation.getResponse(request);
        assertEquals(HttpURLConnection.HTTP_OK, response.getResponseCode());
        assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
        room = objectMapper.readValue(response.getText(), Room.class);
        assertNotNull(room);
        assertEquals(room, baseRoom);

        request = new WebRequest(getDeleteUrl(baseRoom.getId())) {

            @Override
            public String getMethod() {
                return RequestMethod.DELETE.toString();
            }
        };
        response = conversation.getResponse(request);
        assertEquals(HttpURLConnection.HTTP_OK, response.getResponseCode());
    }

    private boolean compare(Object bean1, Object bean2, String... ignored) throws Exception {
        Map<String, ?> properties1 = PropertyUtils.describe(bean1);
        Map<String, ?> properties2 = PropertyUtils.describe(bean2);
        if (ignored != null) {
            for (String key : ignored) {
                properties1.remove(key);
                properties2.remove(key);
            }
        }
        Iterator<String> iter = properties1.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            if (!ObjectUtils.equals(properties1.get(key), properties2.get(key))) {
                return false;
            }
            iter.remove();
            properties2.remove(key);
        }
        return properties2.isEmpty();
    }

    public static String getUrl() {
        String host = System.getProperty("copyrightRoomHost", "localhost");
        String name = System.getProperty("copyrightRoomPath", "room");
        String port = System.getProperty("copyrightRoomPort", "8082");
        return String.format("http://%s:%s/%s", host, port, name);
    }

    public static String getCreateUrl() {
        return getUrl() + "/create";
    }

    public static String getUpdateUrl() {
        return getUrl() + "/update";
    }

    public static String getViewUrl(int id) {
        return getUrl() + "/" + id;
    }

    public static String getDeleteUrl(int id) {
        return getUrl() + "/" + id;
    }

    protected String getJSON(Object obj) throws JsonMappingException, IOException {
        StringWriter writer = new StringWriter();
        objectMapper.writeValue(writer, obj);
        return writer.toString();
    }

}
