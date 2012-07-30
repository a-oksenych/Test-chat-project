databaseChangeLog {

    props = new java.util.Properties();
    props.load(new FileInputStream("gradle.properties"))

    changeSet(id: '2012-05-30-a', author: 'Glenn Street <gstreet@copyright.com>', runInTransaction: false, runAlways:true, failOnError:false) {
	comment('Create the database role/login')
      // Continue on error because we assume it is OK that the role already exists
	preConditions(onFail:'CONTINUE', onFailMessage: 'Role already exists, continuing') { sqlCheck(expectedResult:'0') 
	{"select count(*) from pg_roles where rolname=\'rup\'"} }
        sql(stripComments: true, splitStatements: false, endDelimiter: ';') {
            "CREATE ROLE " + props.getProperty("dbRUPUsername") + " LOGIN PASSWORD '" + props.getProperty("dbRUPPassword") + "'"
        }
    }
    changeSet(id: '2012-05-30-b', author: 'Glenn Street <gstreet@copyright.com>', runInTransaction: false, runAlways:true, failOnError:false) {
	comment('Create necessary tablespaces')
      // Continue on error because we assume it is OK that the tablespaces already exist
	preConditions(onFail:'CONTINUE', onFailMessage: 'Tablespaces already exist, continuing') { sqlCheck(expectedResult:'0') 
	{"select count(*) from pg_tablespace where spcname=\'rup_data\' or spcname=\'rup_index\'"} }
        sql(stripComments: true, splitStatements: false, endDelimiter: ';') {
            "COMMIT; CREATE TABLESPACE " + props.getProperty("dbTablespaceData") + " OWNER " + props.getProperty("dbRUPUsername") + " LOCATION '" + props.getProperty("dbTablespaceLocationData") + "'"
        }
        sql(stripComments: true, splitStatements: false, endDelimiter: ';') {
            "COMMIT; CREATE TABLESPACE " + props.getProperty("dbTablespaceIndex") + " OWNER "+ props.getProperty("dbRUPUsername") + " LOCATION '" + props.getProperty("dbTablespaceLocationIndex") + "'"
        }
    }
    changeSet(id: '2012-05-30-c', author: 'Glenn Street <gstreet@copyright.com>', runInTransaction: false, runAlways:true, failOnError:false) {
	comment('Now we can create the database itself')
      // Continue on error because we assume it is OK that the database already exists
	preConditions (onFail:'CONTINUE', onFailMessage: 'Database already exists'){ sqlCheck(expectedResult:'0') 
	{"select count(*) from pg_database where datname=\'rup\'"} }
        sql(stripComments: true, splitStatements: false, endDelimiter: ';') {
            "COMMIT; CREATE DATABASE " + props.getProperty("dbRUPDatabase") + " WITH OWNER=" + props.getProperty("dbRUPUsername") + " ENCODING='UTF8' TABLESPACE=" + props.getProperty("dbTablespaceData")
	}
    }
}
