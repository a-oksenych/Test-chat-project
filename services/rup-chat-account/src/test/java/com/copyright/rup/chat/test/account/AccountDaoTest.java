package com.copyright.rup.chat.test.account;

import com.copyright.rup.chat.account.DaoAccount;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-context.xml" })
public class AccountDaoTest {

    @Autowired
    private DaoAccount accountDao;

    @Test
    public void simpleTest() {
        // System.out.println();
        System.out.println(new File("changelog.groovy").exists());
        // GroovyLiquibaseChangeLogParser parser = new GroovyLiquibaseChangeLogParser();
        // System.out.println(parser.supports(
        // "c:\\RUP\\workspaces\\Test-chat-project\\services\\rup-chat-db\\changelog.groovy", null));
        // parser.parse(physicalChangeLogLocation, changeLogParameters, resourceAccessor);
    }

}
