package com.company.aptekainfo.data

import android.os.StrictMode
import android.util.Log
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class ConnectionHelper {

    private val ip="192.168.1.40"
    private val database="Cur"
    private val username="volod"
    private val password="123"

    fun databaseConnect(): Connection?{
        val policy=StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        var connection: Connection?=null
        var connectionURL:String?=null
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver")
            connectionURL = "jdbc:jtds:sqlserver://$ip;databaseName=$database;user=$username;password=$password;"
            connection=DriverManager.getConnection(connectionURL)

        }
        catch (ex1:SQLException){
            Log.e("Error 1 ", ex1.message.toString())
        }
        catch (ex2:ClassNotFoundException){
            Log.e("Error 2 ", ex2.message.toString())
        }
        catch (ex3:Exception){
            Log.e("Error 3 ", ex3.message.toString())
        }
return connection
    }

}