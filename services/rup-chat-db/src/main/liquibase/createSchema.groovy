databaseChangeLog {

    changeSet(id: '2012-12-11-a', author: 'Glenn Street <gstreet@copyright.com>', runInTransaction: false, runAlways:true, failOnError:false) {
	comment('Create the RUP apps schema')
      // Continue on error because we assume it is OK that the schema already exists
	preConditions(onFail:'CONTINUE', onError:'CONTINUE', onFailMessage: 'Schema already exists, continuing') { sqlCheck(expectedResult:'0') 
	{"select count(*) from pg_namespace where nspname=\'apps\'"} }
        sql(stripComments: true, splitStatements: false, endDelimiter: ';') {
	  "COMMIT; CREATE SCHEMA apps;"
	}
    }
    changeSet(id: '2012-12-11-b', author: 'Glenn Street <gstreet@copyright.com>', runInTransaction: false, runAlways:true, failOnError:false) {
	comment('Create the RUP common schema')
      // Continue on error because we assume it is OK that the schema already exists
	preConditions(onFail:'CONTINUE', onError:'CONTINUE', onFailMessage: 'Schema already exists, continuing') { sqlCheck(expectedResult:'0') 
	{"select count(*) from pg_namespace where nspname=\'common\'"} }
        sql(stripComments: true, splitStatements: false, endDelimiter: ';') {
	  "COMMIT; CREATE SCHEMA common;"
	}
    }
    changeSet(id: '2012-12-11-c', author: 'Glenn Street <gstreet@copyright.com>', runInTransaction: false, runAlways:true, failOnError:false) {
	comment('Create the RUP schemas')
      // Continue on error because we assume it is OK that the schema already exists
	preConditions(onFail:'CONTINUE', onError:'CONTINUE', onFailMessage: 'Schema already exists, continuing') { sqlCheck(expectedResult:'0') 
	{"select count(*) from pg_namespace where nspname=\'domain\'"} }
        sql(stripComments: true, splitStatements: false, endDelimiter: ';') {
	  "COMMIT; CREATE SCHEMA domain;"
	}
    }
}
