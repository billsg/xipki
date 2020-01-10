/*
 *
 * Copyright (c) 2013 - 2020 Lijun Liao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.xipki.ca.api;

/**
 * Request type.
 *
 * @author Lijun Liao
 * @since 2.0.0
 */

public enum RequestType {

  CA(1),
  CMP(2),
  SCEP(3),
  REST(4);

  private final int code;

  RequestType(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  public static RequestType getInstance(int code) {
    for (RequestType value : values()) {
      if (code == value.code) {
        return value;
      }
    }

    throw new IllegalArgumentException("invalid RequestType code " + code);
  } // method getInstance

}
