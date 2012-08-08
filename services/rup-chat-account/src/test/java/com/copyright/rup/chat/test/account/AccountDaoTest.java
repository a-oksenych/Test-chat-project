package com.copyright.rup.chat.test.account;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.copyright.rup.chat.account.DaoAccount;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-context.xml" })
public class AccountDaoTest {
    @Autowired
    private DaoAccount accountDao;

    @Test
    public void simpleTest() {
        // System.out.println();
        System.out.println(new File("changelog.groovy").exists());
        System.out.println("WooF!");
        // GroovyLiquibaseChangeLogParser parser = new GroovyLiquibaseChangeLogParser();
        // System.out.println(parser.supports(
        // "c:\\RUP\\workspaces\\Test-chat-project\\services\\rup-chat-db\\changelog.groovy", null));
        // parser.parse(physicalChangeLogLocation, changeLogParameters, resourceAccessor);
    }
}
