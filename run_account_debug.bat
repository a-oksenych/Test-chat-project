CD services\rup-chat-account
SET GRADLE_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n
START "ACCOUNT" gradle jettyRun
