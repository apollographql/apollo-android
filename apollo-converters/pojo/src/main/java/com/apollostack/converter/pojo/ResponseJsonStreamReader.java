package com.apollostack.converter.pojo;

import com.apollostack.api.graphql.Field;
import com.apollostack.api.graphql.ResponseReader;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("WeakerAccess") final class ResponseJsonStreamReader implements ResponseReader {
  private final JsonReader jsonReader;

  ResponseJsonStreamReader(JsonReader jsonReader) {
    this.jsonReader = jsonReader;
  }

  @Override public ResponseReader toBufferedReader() throws IOException {
    Map<String, Object> buffer = toMap(this);
    return new BufferedResponseReader(buffer);
  }

  @Override public void read(ValueHandler handler, Field... fields) throws IOException {
    Map<String, Integer> fieldIndexMap = new HashMap<>();
    Map<String, Field> fieldMap = new HashMap<>();
    int index = 0;
    for (Field field : fields) {
      fieldMap.put(field.responseName(), field);
      fieldIndexMap.put(field.responseName(), index++);
    }

    while (hasNext()) {
      String nextName = nextName();
      Field field = fieldMap.get(nextName);
      if (field != null) {
        int fieldIndex = fieldIndexMap.get(nextName);
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
      } else {
        skipNext();
      }
    }
  }

  @Override public String readString() throws IOException {
    return nextString(false);
  }

  @Override public Integer readInt() throws IOException {
    return nextInt(false);
  }

  @Override public Long readLong() throws IOException {
    return nextLong(false);
  }

  @Override public Double readDouble() throws IOException {
    return nextDouble(false);
  }

  @Override public Boolean readBoolean() throws IOException {
    return nextBoolean(false);
  }

  boolean hasNext() throws IOException {
    return jsonReader.hasNext();
  }

  String nextName() throws IOException {
    return jsonReader.nextName();
  }

  void skipNext() throws IOException {
    jsonReader.skipValue();
  }

  String nextString(boolean optional) throws IOException {
    checkNextValue(optional);
    if (jsonReader.peek() == JsonReader.Token.NULL) {
      jsonReader.skipValue();
      return null;
    } else {
      return jsonReader.nextString();
    }
  }

  Integer nextInt(boolean optional) throws IOException {
    checkNextValue(optional);
    if (jsonReader.peek() == JsonReader.Token.NULL) {
      jsonReader.skipValue();
      return null;
    } else {
      return jsonReader.nextInt();
    }
  }

  Long nextLong(boolean optional) throws IOException {
    checkNextValue(optional);
    if (jsonReader.peek() == JsonReader.Token.NULL) {
      jsonReader.skipValue();
      return null;
    } else {
      return jsonReader.nextLong();
    }
  }

  Double nextDouble(boolean optional) throws IOException {
    checkNextValue(optional);
    if (jsonReader.peek() == JsonReader.Token.NULL) {
      jsonReader.skipValue();
      return null;
    } else {
      return jsonReader.nextDouble();
    }
  }

  Boolean nextBoolean(boolean optional) throws IOException {
    checkNextValue(optional);
    if (jsonReader.peek() == JsonReader.Token.NULL) {
      jsonReader.skipValue();
      return null;
    } else {
      return jsonReader.nextBoolean();
    }
  }

  <T> T nextObject(boolean optional, Field.NestedReader<T> nestedReader) throws IOException {
    checkNextValue(optional);
    if (jsonReader.peek() == JsonReader.Token.NULL) {
      jsonReader.skipValue();
      return null;
    } else {
      jsonReader.beginObject();
      T result = nestedReader.read(this);
      jsonReader.endObject();
      return result;
    }
  }

  <T> List<T> nextList(boolean optional, Field.NestedReader<T> nestedReader) throws IOException {
    checkNextValue(optional);
    if (jsonReader.peek() == JsonReader.Token.NULL) {
      jsonReader.skipValue();
      return null;
    } else {
      List<T> result = new ArrayList<>();
      jsonReader.beginArray();
      while (jsonReader.hasNext()) {
        T item;
        if (isNextObject()) {
          item = nextObject(false, nestedReader);
        } else {
          item = nestedReader.read(this);
        }
        result.add(item);
      }
      jsonReader.endArray();
      return result;
    }
  }

  private String readString(Field field) throws IOException {
    return nextString(field.optional());
  }

  private Integer readInt(Field field) throws IOException {
    return nextInt(field.optional());
  }

  private Long readLong(Field field) throws IOException {
    return nextLong(field.optional());
  }

  private Double readDouble(Field field) throws IOException {
    return nextDouble(field.optional());
  }

  private Boolean readBoolean(Field field) throws IOException {
    return nextBoolean(field.optional());
  }

  @SuppressWarnings("unchecked") private <T> T readObject(Field field) throws IOException {
    return (T) nextObject(field.optional(), field.nestedReader());
  }

  @SuppressWarnings("unchecked") private <T> List<T> readList(Field field) throws IOException {
    return nextList(field.optional(), field.nestedReader());
  }

  private boolean isNextObject() throws IOException {
    return jsonReader.peek() == JsonReader.Token.BEGIN_OBJECT;
  }

  private boolean isNextList() throws IOException {
    return jsonReader.peek() == JsonReader.Token.BEGIN_ARRAY;
  }

  private boolean isNextNull() throws IOException {
    return jsonReader.peek() == JsonReader.Token.NULL;
  }

  private boolean isNextBoolean() throws IOException {
    return jsonReader.peek() == JsonReader.Token.BOOLEAN;
  }

  private boolean isNextNumber() throws IOException {
    return jsonReader.peek() == JsonReader.Token.NUMBER;
  }

  private void checkNextValue(boolean optional) throws IOException {
    if (!optional && jsonReader.peek() == JsonReader.Token.NULL) {
      throw new NullPointerException("corrupted response reader, expected non null value");
    }
  }

  private static Map<String, Object> toMap(ResponseJsonStreamReader streamReader) throws IOException {
    Map<String, Object> result = new HashMap<>();
    while (streamReader.hasNext()) {
      String name = streamReader.nextName();
      if (streamReader.isNextNull()) {
        streamReader.skipNext();
      } else if (streamReader.isNextObject()) {
        result.put(name, readObject(streamReader));
      } else if (streamReader.isNextList()) {
        result.put(name, readList(streamReader));
      } else {
        result.put(name, readScalar(streamReader));
      }
    }
    return result;
  }

  private static Map<String, Object> readObject(final ResponseJsonStreamReader streamReader) throws IOException {
    return streamReader.nextObject(false, new Field.NestedReader<Map<String, Object>>() {
      @Override public Map<String, Object> read(ResponseReader streamReader) throws IOException {
        return toMap((ResponseJsonStreamReader) streamReader);
      }
    });
  }

  private static List<?> readList(final ResponseJsonStreamReader streamReader) throws IOException {
    return streamReader.nextList(false, new Field.NestedReader<Object>() {
      @Override public Object read(ResponseReader reader) throws IOException {
        ResponseJsonStreamReader streamReader = (ResponseJsonStreamReader) reader;
        if (streamReader.isNextObject()) {
          return readObject(streamReader);
        } else if (streamReader.isNextList()) {
          return readList(streamReader);
        } else {
          return readScalar(streamReader);
        }
      }
    });
  }

  private static Object readScalar(ResponseJsonStreamReader streamReader) throws IOException {
    if (streamReader.isNextNull()) {
      streamReader.skipNext();
      return null;
    } else if (streamReader.isNextBoolean()) {
      return streamReader.nextBoolean(false);
    } else if (streamReader.isNextNumber()) {
      return new BigDecimal(streamReader.nextString(false));
    } else {
      return streamReader.nextString(false);
    }
  }
}
