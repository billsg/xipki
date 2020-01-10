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

package org.xipki.scep.message;

/**
 * Exception indicate error while encoding message.
 *
 * @author Lijun Liao
 */

public class MessageEncodingException extends Exception {

  private static final long serialVersionUID = 1L;

  public MessageEncodingException(String message, Throwable cause) {
    super(message, cause);
  }

  public MessageEncodingException(String message) {
    super(message);
  }

  public MessageEncodingException(Throwable cause) {
    super(cause.getMessage(), cause);
  }

}
