package com.apollographql.android.cache.normalized.sql;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.apollographql.android.cache.normalized.CacheStore;
import com.apollographql.android.cache.normalized.Record;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nullable;

import static com.apollographql.android.cache.normalized.sql.ApolloSqlHelper.COLUMN_KEY;
import static com.apollographql.android.cache.normalized.sql.ApolloSqlHelper.COLUMN_RECORD;
import static com.apollographql.android.cache.normalized.sql.ApolloSqlHelper.TABLE_RECORDS;

final class SqlStore extends CacheStore {
  private static final String INSERT_STATEMENT =
      String.format("INSERT INTO %s (%s,%s) VALUES (?,?)",
      TABLE_RECORDS,
      COLUMN_KEY,
      COLUMN_RECORD);
  SQLiteDatabase database;
  private final FieldsAdapter parser;
  private final ApolloSqlHelper dbHelper;
  private final String[] allColumns = {ApolloSqlHelper.COLUMN_ID,
      ApolloSqlHelper.COLUMN_KEY,
      ApolloSqlHelper.COLUMN_RECORD};
  private final SQLiteStatement sqLiteStatement;


  public static SqlStore create(ApolloSqlHelper helper, FieldsAdapter adapter) {
    return new SqlStore(helper, adapter);
  }

  private SqlStore(ApolloSqlHelper dbHelper, FieldsAdapter parser) {
    this.dbHelper = dbHelper;
    database = dbHelper.getWritableDatabase();
    this.parser = parser;
    sqLiteStatement = database.compileStatement(INSERT_STATEMENT);
  }

  @Nullable public Record loadRecord(String key) {
    return selectRecordForKey(key);
  }

  public Set<String> merge(Record apolloRecord) {
    Record oldRecord = selectRecordForKey(apolloRecord.key());
    if (oldRecord == null) {
      createRecord(apolloRecord.key(), parser.toJson(apolloRecord.fields()));
    } else {
      oldRecord.mergeWith(apolloRecord);
      updateRecord(oldRecord.key(), parser.toJson(oldRecord.fields()));
    }
    return new HashSet<>(); //TODO // FIXME: 3/9/17 Return something real
  }

  long createRecord(String key, String fields) {
    sqLiteStatement.bindString(1, key);
    sqLiteStatement.bindString(2, fields);

    long recordId = sqLiteStatement.executeInsert();
    return recordId;
  }

  void updateRecord(String key, String fields) {
    sqLiteStatement.bindString(1, key);
    sqLiteStatement.bindString(2, fields);
    sqLiteStatement.executeUpdateDelete();
  }

  Record selectRecordForKey(String key) {
    Cursor cursor = database.query(TABLE_RECORDS,
        allColumns, ApolloSqlHelper.COLUMN_KEY + " = " + key, null,
        null, null, null);
    cursor.moveToFirst();
    Record selectedRecord = cursorToRecord(cursor);
    cursor.close();
    return selectedRecord;
  }

  void deleteRecord(String key) {
    System.out.println("Record deleted with key: " + key);
    database.delete(ApolloSqlHelper.TABLE_RECORDS, ApolloSqlHelper.COLUMN_ID
        + " = " + key, null);
  }

  Record cursorToRecord(Cursor cursor) {
    String key = cursor.getString(1);
    String jsonOfFields = cursor.getString(2);
    return new Record(key,
        parser.fromJson(jsonOfFields));
  }

  public void close() {
    dbHelper.close();
  }
}
