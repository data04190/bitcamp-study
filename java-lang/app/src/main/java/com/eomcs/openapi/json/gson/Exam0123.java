// JSON 문자열 --> 객체 : JSON 문자열을 해석하여 객체를 생성하기
package com.eomcs.openapi.json.gson;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.core.JsonParseException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

public class Exam0123 {
  public static void main(String[] args) {

    // 1) JSON 문자열 준비
    String jsonStr =
        "{\"no\":100,\"name\":\"홍길동\",\"email\":\"hong@test.com\",\"password\":\"1111\",\"photo\":\"hong.gif\",\"tel\":\"010-2222-1111\",\"registeredDate\":\"2023-07-03\"}";

    // 2) JSON 처리 객체 준비

    class GsonDateFormat implements JsonDeserializer<Date> {
      public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
          throws JsonParseException {
        String strValue = json.getAsString();
        return Date.valueOf(strValue);
      }
    }

    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(Date.class, new GsonDateFormat());

    Gson gson = builder.create();

    // 3) JSON 문자열을 가지고 객체 만들기
    Member m = gson.fromJson(jsonStr, Member.class);

    System.out.println(m);
  }
}


