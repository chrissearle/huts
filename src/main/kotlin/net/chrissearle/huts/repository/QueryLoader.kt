package net.chrissearle.huts.repository

abstract class QueryLoader {

    private val queryMap = mutableMapOf<String, String>()

    fun loadQuery(queryName: String): String? {
        if (!queryMap.containsKey(queryName)) {
            this::class.java.getResourceAsStream("/sql/$queryName")?.bufferedReader()?.readText()?.let { query ->
                queryMap.put(queryName, query)
            }
        }

        return queryMap[queryName]
    }
}
