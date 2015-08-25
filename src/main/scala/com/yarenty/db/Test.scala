package com.yarenty.db

import java.sql.DriverManager

import org.jooq._
import org.jooq.impl._
import com.yarenty.db.generated._

/**
 * Created by yarenty on 25/08/2015.
 */
object Test {

  def main(args: Array[String]): Unit = {
    val userName = "root";
    val password = "";
    val url = "jdbc:mysql://localhost:3306/library";

    // Connection is the only JDBC resource that we need
    // PreparedStatement and ResultSet are handled by jOOQ, internally
    try {
      val conn = DriverManager.getConnection(url, userName, password)
      val create = DSL.using(conn, SQLDialect.MYSQL)
      val result = create.select from AUTHOR fetch

      for (r <- result) {
        val id = r.getValue(AUTHOR.ID)
        val firstName = r.getValue(AUTHOR.FIRST_NAME)
        val lastName = r.getValue(AUTHOR.LAST_NAME)

        println("ID: " + id + " first name: " + firstName + " last name: " + lastName)
      }
    }

    // For the sake of this tutorial, let's keep exception handling simple
    catch {
      case ex: Exception => ex.printStackTrace
    }
  }


}
