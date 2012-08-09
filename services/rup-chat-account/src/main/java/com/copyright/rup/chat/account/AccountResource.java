package com.copyright.rup.chat.account;

import com.copyright.rup.chat.common.Account;

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
public class AccountResource {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Account createAccount(HttpServletRequest request, HttpServletResponse response,
            @RequestBody String serializedAccount) {
        try {
            Account account = objectMapper.readValue(serializedAccount, Account.class);
            response.setStatus(HttpServletResponse.SC_CREATED);
            return accountService.createAccount(account);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Account getAccount(HttpServletRequest request, HttpServletResponse response, @PathVariable int id) {
        Account account = accountService.getAccount(id);
        if (account == null) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
        return account;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Account updateAccount(HttpServletRequest request, HttpServletResponse response,
            @RequestBody String serializedAccount) {
        try {
            Account account = objectMapper.readValue(serializedAccount, Account.class);
            return accountService.updateAccount(account);
        } catch (AccountException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteAccount(HttpServletRequest request, HttpServletResponse response, @PathVariable int id) {
        accountService.deleteAccount(id);
    }
}
