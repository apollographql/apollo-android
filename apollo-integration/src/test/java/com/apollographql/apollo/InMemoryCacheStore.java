package com.apollographql.apollo;

import com.apollographql.apollo.cache.normalized.CacheStore;
import com.apollographql.apollo.cache.normalized.Record;
import com.apollographql.apollo.cache.normalized.RecordSet;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Nonnull;

public final class InMemoryCacheStore extends CacheStore {

  private RecordSet recordSet;

  public InMemoryCacheStore() {
    this.recordSet = new RecordSet();
  }

  @Nonnull public Record loadRecord(String key) {
    return recordSet.get(key);
  }

  @Nonnull public Set<String> merge(Record apolloRecord) {
    return recordSet.merge(apolloRecord);
  }

  @Nonnull @Override public Set<String> merge(Collection<Record> recordSet) {
    Set<String> changedKeys = new LinkedHashSet<>();
    for (Record record : recordSet) {
      changedKeys.addAll(merge(record));
    }
    return changedKeys;
  }

  @Override public void clearAll() {
    recordSet = new RecordSet();
  }

  public Collection<Record> allRecords() {
    return recordSet.allRecords();
  }
}
