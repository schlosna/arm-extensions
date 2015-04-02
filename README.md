# arm-extensions
Automatically exported from [code.google.com/p/arm-extensions](https://code.google.com/p/arm-extensions/)

# Goal
This project is intended to provide extensions to JDK7's try-with-resources (formerly called Automatic Resource Management (ARM), thus the project name} to support areas without [http://download.java.net/jdk7/docs/api/java/lang/AutoCloseable.html](AutoCloseable) support. This library provides adapter objects that implement [http://download.java.net/jdk7/docs/api/java/lang/AutoCloseable.html](AutoCloseable) to manage the resource lifecycle. This management comes at the cost of an additional proxy object allocation, a tradeoff that is often worth making to avoid resource leaks.

## Updates 
Oracle's Lance Anderson recently pushed [http://hg.openjdk.java.net/jdk7/tl/jdk/rev/c786a9c927fd](JDBC 4.1 updates) to OpenJDK 7 which includes [http://download.java.net/jdk7/docs/api/java/lang/AutoCloseable.html](AutoCloseable) support for JDBC's [http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/tip/src/share/classes/java/sql/Connection.java](Connection), [http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/tip/src/share/classes/java/sql/ResultSet.java](ResultSet), and [http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/tip/src/share/classes/java/sql/Statement.java](Statement) types. See Joe Darcy's [http://blogs.sun.com/darcy/entry/project_coin_jdbc_4_1](Project Coin: JDBC 4.1 and try-with-resources) post for more details. These changes fortunately make this library redundant; however, I like to think that this prototype prompted the JDBC expert group into adopting the try-with-resources features sooner than later. In any case, the adapter pattern can still be applied to other existing types holding resources such as `java.awt.Graphics` that have not or will not be updated to implement [http://download.java.net/jdk7/docs/api/java/lang/AutoCloseable.html](AutoCloseable).

## [http://arm-extensions.googlecode.com/hg/javadoc/index.html](API documentation)

## Example usage:
Here's a simple example of wrapping a [http://download.java.net/jdk7/docs/api/javax/sql/DataSource.html](javax.sql.DataSource) to have it provide [http://download.java.net/jdk7/docs/api/java/lang/AutoCloseable.html](AutoCloseable) connections, statements, and result sets:

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

### Requirements
OpenJDK 7 with full ARM support (currently implemented in http://hg.openjdk.java.net/jdk7/tl/), specifically the following changesets:
  * Changeset [http://hg.openjdk.java.net/jdk7/tl/jdk/rev/c4d60bcce958](c4d60bcce958)
    *  [http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6911258](6911258): Project Coin: Add essential API support for Automatic Resource Management (ARM) blocks
    *  [http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6911261](6911261): Project Coin: Retrofit Automatic Resource Management (ARM) support onto platform APIs
    *  [http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6962571](6962571): Infinite loop in printing out Throwable stack traces with circular references
  * Changeset [http://hg.openjdk.java.net/jdk7/tl/jdk/rev/425960cef714](425960cef714)
    *  [http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6963723](6963723): Project Coin: Retrofit more JDK classes for ARM
  * Changeset [http://hg.openjdk.java.net/jdk7/tl/langtools/rev/13354e1abba7](13354e1abba7)
    *  [http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6911256](6911256): Project Coin: Support Automatic Resource Management (ARM) blocks in the compiler
    *  [http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6964740](6964740): Project Coin: More tests for ARM compiler changes
    *  [http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6965277](6965277): Project Coin: Correctness issues in ARM implementation
    *  [http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6967065](6967065): add -Xlint warning category for Automatic Resource Management (ARM)

### References
* [http://blogs.sun.com/darcy/entry/project_coin_arm_api](Project Coin: ARM API)
* [http://blogs.sun.com/darcy/entry/project_coin_updated_arm_spec](Project Coin: Updated ARM Spec)
* [http://blogs.sun.com/darcy/entry/project_coin_arm_implementatio](Project Coin: ARM Implementation)
* [http://mail.openjdk.java.net/pipermail/coin-dev/2009-February/000011.html](Proposal: Automatic Resource Management)


