package com.apollostack.converter.pojo;

import com.apollostack.api.graphql.Field;
import com.apollostack.api.graphql.ResponseReader;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@SuppressWarnings("WeakerAccess") final class BufferedResponseReader implements ResponseReader {
  private final Map<String, Object> buffer;

  BufferedResponseReader(Map<String, Object> buffer) {
    this.buffer = buffer;
  }

  @Override public ResponseReader toBufferedReader() throws IOException {
    return this;
  }

  @Override public void read(ValueHandler handler, Field... fields) throws IOException {
    int fieldIndex = 0;
    for (Field field : fields) {
      switch (field.type()) {
        case Field.TYPE_STRING:
          handler.handle(fieldIndex, readString(field));
          break;
        case Field.TYPE_INT:
          handler.handle(fieldIndex, readInt(field));
          break;
        case Field.TYPE_LONG:
          handler.handle(fieldIndex, readLong(field));
          break;
        case Field.TYPE_DOUBLE:
          handler.handle(fieldIndex, readDouble(field));
          break;
        case Field.TYPE_BOOL:
          handler.handle(fieldIndex, readBoolean(field));
          break;
        case Field.TYPE_OBJECT:
          handler.handle(fieldIndex, readObject(field));
          break;
        case Field.TYPE_LIST:
          handler.handle(fieldIndex, readList(field));
          break;
        default:
          throw new IllegalArgumentException("Unsupported field type");
      }
      fieldIndex++;
    }
  }

  @Override public String readString() throws IOException {
    return readSingleValue();
  }

  @Override public Integer readInt() throws IOException {
    return readSingleValue();
  }

  @Override public Long readLong() throws IOException {
    return readSingleValue();
  }

  @Override public Double readDouble() throws IOException {
    return readSingleValue();
  }

  @Override public Boolean readBoolean() throws IOException {
    return readSingleValue();
  }

  String readString(Field field) throws IOException {
    String value = (String) buffer.get(field.responseName());
    checkValue(value, field.optional());
    if (value == null) {
      return null;
    } else {
      return value;
    }
  }

  Integer readInt(Field field) throws IOException {
    BigDecimal value = (BigDecimal) buffer.get(field.responseName());
    checkValue(value, field.optional());
    if (value == null) {
      return null;
    } else {
      return value.intValue();
    }
  }

  Long readLong(Field field) throws IOException {
    BigDecimal value = (BigDecimal) buffer.get(field.responseName());
    checkValue(value, field.optional());
    if (value == null) {
      return null;
    } else {
      return value.longValue();
    }
  }

  Double readDouble(Field field) throws IOException {
    BigDecimal value = (BigDecimal) buffer.get(field.responseName());
    checkValue(value, field.optional());
    if (value == null) {
      return null;
    } else {
      return value.doubleValue();
    }
  }

  Boolean readBoolean(Field field) throws IOException {
    Boolean value = (Boolean) buffer.get(field.responseName());
    checkValue(value, field.optional());
    if (value == null) {
      return null;
    } else {
      return value;
    }
  }

  @SuppressWarnings("unchecked") <T> T readObject(Field field) throws IOException {
    Map<String, Object> value = (Map<String, Object>) buffer.get(field.responseName());
    checkValue(value, field.optional());
    if (value == null) {
      return null;
    } else {
      return (T) field.nestedReader().read(new BufferedResponseReader(value));
    }
  }

  @SuppressWarnings("unchecked") <T> List<T> readList(Field field) throws IOException {
    List values = (List) buffer.get(field.responseName());
    checkValue(values, field.optional());
    if (values == null) {
      return null;
    } else {
      List<T> result = new ArrayList<>();
      for (Object value : values) {
        if (value instanceof Map) {
          result.add((T) field.nestedReader().read(new BufferedResponseReader((Map<String, Object>) value)));
        } else {
          result.add((T) field.nestedReader().read(new BufferedResponseReader(Collections.singletonMap("", value))));
        }
      }
      return result;
    }
  }

  @SuppressWarnings("unchecked") private <T> T readSingleValue() {
    if (buffer.size() != 1) {
      throw new IllegalStateException("corrupted response reader, expected single value");
    }
    Object value = buffer.get("");
    if (value == null) {
      throw new NullPointerException("corrupted response reader, expected non null value");
    }
    return (T) value;
  }

  private void checkValue(Object value, boolean optional) {
    if (!optional && value == null) {
      throw new NullPointerException("corrupted response reader, expected non null value");
    }
  }
}
