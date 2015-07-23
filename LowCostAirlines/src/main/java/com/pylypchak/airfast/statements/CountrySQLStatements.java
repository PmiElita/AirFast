package com.pylypchak.airfast.statements;

public class CountrySQLStatements {
       public static final String INSERT_COUNTRY_SQL="INSERT INTO country (name) VALUES (?)";
       public static final String SELECT_ALL_COUTRIES_SQL ="SELECT * FROM country";
       public static final  String SELECT_COUNTRY_BY_ID_SQL = "SELECT * FROM country WHERE id =?";
       public static final String UPDATE_COUNTRY_BYID_SQL = "UPDATE country SET name=? WHERE id=? ";
       
       public static final String[] INSERT_COUNTRY_SQL_ORDER ={"name"};
       public static final  String[] SELECT_COUNTRY_BY_ID_SQL_ORDER ={"id"};
       public static final String[] UPDATE_COUNTRY_BYID_SQL_ORDER = {"name", "id"};
}

