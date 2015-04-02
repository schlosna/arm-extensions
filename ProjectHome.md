# Goal #
This project is intended to provide extensions to JDK7's try-with-resources (formerly called Automatic Resource Management (ARM), thus the project name} to support areas without [AutoCloseable](http://download.java.net/jdk7/docs/api/java/lang/AutoCloseable.html) support. This library provides adapter objects that implement [AutoCloseable](http://download.java.net/jdk7/docs/api/java/lang/AutoCloseable.html) to manage the resource lifecycle. This management comes at the cost of an additional proxy object allocation, a tradeoff that is often worth making to avoid resource leaks.

## Updates ##
Oracle's Lance Anderson recently pushed [JDBC 4.1 updates](http://hg.openjdk.java.net/jdk7/tl/jdk/rev/c786a9c927fd) to OpenJDK 7 which includes [AutoCloseable](http://download.java.net/jdk7/docs/api/java/lang/AutoCloseable.html) support for JDBC's [Connection](http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/tip/src/share/classes/java/sql/Connection.java), [ResultSet](http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/tip/src/share/classes/java/sql/ResultSet.java), and [Statement](http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/tip/src/share/classes/java/sql/Statement.java) types. See Joe Darcy's [Project Coin: JDBC 4.1 and try-with-resources](http://blogs.sun.com/darcy/entry/project_coin_jdbc_4_1) post for more details. These changes fortunately make this library redundant; however, I like to think that this prototype prompted the JDBC expert group into adopting the try-with-resources features sooner than later. In any case, the adapter pattern can still be applied to other existing types holding resources such as `java.awt.Graphics` that have not or will not be updated to implement [AutoCloseable](http://download.java.net/jdk7/docs/api/java/lang/AutoCloseable.html).

## [API documentation](http://arm-extensions.googlecode.com/hg/javadoc/index.html) ##

## Example usage: ##
Here's a simple example of wrapping a [javax.sql.DataSource](http://download.java.net/jdk7/docs/api/javax/sql/DataSource.html) to have it provide [AutoCloseable](http://download.java.net/jdk7/docs/api/java/lang/AutoCloseable.html) connections, statements, and result sets:

```
import static com.google.code.arm.sql.AutoCloseables.arm;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.google.code.arm.sql.AutoCloseConnection;
import com.google.code.arm.sql.AutoClosePreparedStatement;
import com.google.code.arm.sql.AutoCloseResultSet;

public class AutoCloseExample {
    public static void main(String[] args) throws Exception {
        DataSource dataSource = getDataSource(); // could be injected
        // "arm" the datasource to get an AutoCloseable instance
        try ( AutoCloseConnection c = arm(dataSource).getConnection();
              AutoClosePreparedStatement stmt = c.prepareStatement("select 1 from dual");
              AutoCloseResultSet rs = stmt.executeQuery() ) {

            while (rs.next()) {
                // Do something useful with the result set
            }
            // Note: the connection's transaction must be explicitly committed
            // or rolled back prior to closing either from Connection.close()
            // or the automatic resource management block construct.
        } catch (SQLException e) {
            e.printStackTrace(); // Includes suppressed exceptions (if any) from closing the resources
        }
        // The result set, prepared statement, and connection are all automatically closed
    }
}
```



### Requirements ###
OpenJDK 7 with full ARM support (currently implemented in http://hg.openjdk.java.net/jdk7/tl/), specifically the following changesets:
  * Changeset [c4d60bcce958](http://hg.openjdk.java.net/jdk7/tl/jdk/rev/c4d60bcce958)
    * [6911258](http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6911258): Project Coin: Add essential API support for Automatic Resource Management (ARM) blocks
    * [6911261](http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6911261): Project Coin: Retrofit Automatic Resource Management (ARM) support onto platform APIs
    * [6962571](http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6962571): Infinite loop in printing out Throwable stack traces with circular references
  * Changeset [425960cef714](http://hg.openjdk.java.net/jdk7/tl/jdk/rev/425960cef714)
    * [6963723](http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6963723): Project Coin: Retrofit more JDK classes for ARM
  * Changeset [13354e1abba7](http://hg.openjdk.java.net/jdk7/tl/langtools/rev/13354e1abba7)
    * [6911256](http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6911256): Project Coin: Support Automatic Resource Management (ARM) blocks in the compiler
    * [6964740](http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6964740): Project Coin: More tests for ARM compiler changes
    * [6965277](http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6965277): Project Coin: Correctness issues in ARM implementation
    * [6967065](http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6967065): add -Xlint warning category for Automatic Resource Management (ARM)

### References ###
[Project Coin: ARM API](http://blogs.sun.com/darcy/entry/project_coin_arm_api)

[Project Coin: Updated ARM Spec](http://blogs.sun.com/darcy/entry/project_coin_updated_arm_spec)

[Project Coin: ARM Implementation](http://blogs.sun.com/darcy/entry/project_coin_arm_implementation)

[Proposal: Automatic Resource Management](http://mail.openjdk.java.net/pipermail/coin-dev/2009-February/000011.html)