package person.daizhongde.virtue.hibernate;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;

/**
 * not used yet
 * @author daizd
 *
 */
public class ExtendedMySQLDialect extends MySQLDialect 
{
        public ExtendedMySQLDialect() 
        {
           super();
//           registerFunction( "date_sub_interval", new SQLFunctionTemplate( Hibernate.DATE, "date_sub(?1, INTERVAL ?2 ?3)" ) );
//           registerFunction( "date_add_interval", new SQLFunctionTemplate( Hibernate.DATE, "date_add(?1, INTERVAL ?2 ?3)" ) );
//           registerFunction( "TIMESTAMPDIFF_SECOND", new SQLFunctionTemplate( Hibernate.DATE, "TIMESTAMPDIFF(?1, ?2, ?3 )" ) );
//           registerFunction( "DATE_SUB_INTERVAL_HOUR", new SQLFunctionTemplate( Hibernate.DATE, "DATE_SUB( ?1, INTERVAL ?2 ?3 )" ) );
//           registerFunction( "DATE_FORMAT_DIFF", new SQLFunctionTemplate( Hibernate.STRING, "DATE_FORMAT( DATE_SUB(FROM_UNIXTIME( TIMESTAMPDIFF( SECOND, ?1, ?2 ), '%Y-%m-%d %h:%i:%s'), INTERVAL 2+24*365*1971 HOUR), '%Y-%m-%d %H:%i:%S')" ) );
        }
}