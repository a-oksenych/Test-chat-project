package com.copyright.rup.chat.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.copyright.rup.chat.common.Account;

/**
 * @author Oleksandr Dekhtyar
 * 
 */
@Controller
public class AccountResource {

    @Autowired
    private AccountService accountService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Account createAccount(HttpServletRequest request, HttpServletResponse response,
            @RequestBody String serializedAccount) {
        try {
            Account account = objectMapper.readValue(serializedAccount, Account.class);
            return accountService.createAccount(account);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Account getAccount(@PathVariable int id) {
        return accountService.getAccount(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Account updateAccount(HttpServletRequest request, HttpServletResponse response,
            @RequestBody String serializedAccount) {
        try {
            Account account = objectMapper.readValue(serializedAccount, Account.class);
            return accountService.updateAccount(account);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteAccount(@PathVariable int id) {
        accountService.deleteAccount(id);
    }
}
