/*
   Copyright 2008 Chris Searle

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
dataSource {
    pooled = false
    driverClassName = "com.mysql.jdbc.Driver"
    username = "huts"
    password = "huts"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'org.hibernate.cache.EhCacheProvider'
}
// environment specific settings
environments {
    development {
//        dataSource {
//            dbCreate = "update" // one of 'create', 'create-drop','update'
//            url = "jdbc:mysql://localhost/huts_dev"
//        }
        dataSource {
            driverClassName = "org.hsqldb.jdbcDriver"
            username = "sa"
            password = ""
            dbCreate = "create-drop"
            url = "jdbc:hsqldb:mem:devDb"
        }
    }
    test {
        dataSource {
            driverClassName = "org.hsqldb.jdbcDriver"
            username = "sa"
            password = ""
            dbCreate = "create-drop"
            url = "jdbc:hsqldb:mem:testDb"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:mysql://localhost/huts_grails"
        }
    }
}
