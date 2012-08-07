package com.copyright.rup.chat.common;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Oleksandr Dekhtyar
 * 
 */
@JsonAutoDetect(JsonMethod.NONE)
@JsonTypeInfo(use = Id.CLASS)
public class Room implements Serializable {

    private static final long serialVersionUID = 5063347195226440297L;

    @JsonProperty
    private int id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String description;
    @JsonProperty
    private List<Account> users;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Account> getUsers() {
        return users;
    }

    public void setUsers(List<Account> users) {
        this.users = users;
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
            Room other = (Room) obj;

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
