package com.apollographql.android.cache.normalized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

public abstract class CacheStore {

  @Nullable public abstract Record loadRecord(String key);

  public Collection<Record> loadRecords(Collection<String> keys) {
    List<Record> records = new ArrayList<>(keys.size());
    for (String key : keys) {
      final Record record = loadRecord(key);
      if (record != null) {
        records.add(record);
      }
    }
    return records;
  }

  public abstract Set<String> merge(Record object);

  public Set<String> merge(Collection<Record> recordSet) {
    Set<String> aggregatedDependentKeys = new HashSet<>();
    for (Record record : recordSet) {
      aggregatedDependentKeys.addAll(merge(record));
    }
    return aggregatedDependentKeys;
  }

}
