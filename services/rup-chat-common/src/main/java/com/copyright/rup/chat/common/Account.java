package com.copyright.rup.chat.common;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Oleksandr Dekhtyar
 * 
 */
@JsonAutoDetect(JsonMethod.NONE)
public class Account {

    @JsonProperty
    private int id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String email;
    @JsonProperty
    private String password;
    @JsonProperty
    private List<Room> rooms;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder(11, 31);

        Map<String, ?> beanMap = new HashMap<>();
        try {
            beanMap = PropertyUtils.describe(this);
        } catch (Exception e) {
            // odekhtyar: ignored, never should be
        }

        Iterator<String> iterator = beanMap.keySet().iterator();
        while (iterator.hasNext()) {
            builder.append(iterator.next());
        }

        return builder.toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || (this.getClass() != obj.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        try {
            Account other = (Account) obj;

            Map<String, ?> beanMap = PropertyUtils.describe(this);
            Map<String, ?> otherBeanMap = PropertyUtils.describe(other);

            EqualsBuilder builder = new EqualsBuilder();

            Iterator<String> iterator = beanMap.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                builder.append(beanMap.get(key), otherBeanMap.get(key));
            }

            return builder.isEquals();
        } catch (Exception e) {
            return false;
        }
    }

}
