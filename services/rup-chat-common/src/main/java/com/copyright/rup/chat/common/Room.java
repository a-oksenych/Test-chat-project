package com.copyright.rup.chat.common;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * @author Oleksandr Dekhtyar
 * 
 */
public class Room implements Serializable {

    private static final long serialVersionUID = 5063347195226440297L;

    private int id;
    private String name;
    private String description;
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
}
