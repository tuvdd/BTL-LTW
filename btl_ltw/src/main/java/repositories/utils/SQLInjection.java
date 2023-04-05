package repositories.utils;

import repositories.utils.models.InsertFieldObject;
import repositories.utils.models.LogicalClause;
import repositories.utils.models.OrderByObject;

public class SQLInjection {
    public static String PAGINSQL(int page, int count, OrderByObject[] orderByObjArray) {
        var orderBySql = "";
        orderBySql += orderByObjArray[0].FieldName + " " + orderByObjArray[0].type.label;
        for (int i = 1; i < orderByObjArray.length; i++) {
            orderBySql += ", " + orderByObjArray[0].FieldName + " " + orderByObjArray[0].type.label;
        }
        return " ORDER BY " + orderBySql + " LIMIT " + count + " OFFSET " + (page - 1) * count + " ";
    }

    public static String WHERESQL(LogicalClause[] list) {
        var sql = "WHERE ";
        sql += LOGICAL_CLAUSE(list[0]);
        for (int i = 1; i < list.length; i++) {
            sql += ", " + LOGICAL_CLAUSE(list[i]);
        }
        return " " + sql + " ";
    }
    public static String WHERESQL(LogicalClause clause) {
        var sql = "WHERE ";
        sql += LOGICAL_CLAUSE(clause);
        return " " + sql + " ";
    }

    public static String SELECTSQL(String[] fields, String table) {
        var sql = "SELECT ";

        if (fields != null) {
            if (fields.length > 0) {
                if (fields[0] != "") {
                    sql += fields[0];
                    for (int i = 1; i < fields.length; i++) {
                        sql += ", " + fields[i];
                    }
                }
            }
        } else
            sql += "* ";

        sql += "FROM ";
        sql += table + " ";
        return " " + sql + " ";
    }

    public static String INSERTSQL(InsertFieldObject[] insertFieldObjectArray, String table) {
        var sql = "INSERT INTO " + table + " ";
        var fields = "(" + insertFieldObjectArray[0].fieldName;
        var values = "(" + insertFieldObjectArray[0].getValue();
        for (int i = 1; i < insertFieldObjectArray.length; i++) {
            fields += ", " + insertFieldObjectArray[i].fieldName;
            values += ", " + insertFieldObjectArray[0].getValue();
        }
        fields += ")";
        values += ")";
        sql += fields + " " + values;
        return " " + sql + " ";
    }

    public static String BETWEEN(String field, String from, String to) {
        var sql = field + " BETWEEN " + from + " AND " + to;
        return " " + sql + " ";
    }

    private static String LOGICAL_CLAUSE(LogicalClause lc) {
        return lc.obj1 + " " + lc.sign + " " + lc.obj2;
    }
}
