CD services\rup-chat-room
SET GRADLE_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n
START "ROOM" gradle jettyRun
