databaseChangeLog {

    props = new java.util.Properties();
    props.load(new FileInputStream("gradle.properties"))

    changeSet(id: '2012-05-31-drop', author: 'Glenn Street <gstreet@copyright.com>', runInTransaction: false) {
        sql(stripComments: true, splitStatements: false, endDelimiter: ';') {
            "COMMIT; DROP DATABASE " + props.getProperty("dbRUPDatabase")
        }
        sql(stripComments: true, splitStatements: false, endDelimiter: ';') {
            "COMMIT; DROP TABLESPACE " + props.getProperty("dbTablespaceData")
        }
        sql(stripComments: true, splitStatements: false, endDelimiter: ';') {
            "COMMIT; DROP TABLESPACE " + props.getProperty("dbTablespaceIndex")
        }
        sql(stripComments: true, splitStatements: false, endDelimiter: ';') {
            "DROP ROLE " + props.getProperty("dbRUPUsername")
        }
    }
}
