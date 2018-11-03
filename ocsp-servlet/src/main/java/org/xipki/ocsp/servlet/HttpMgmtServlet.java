/*
 *
 * Copyright (c) 2013 - 2018 Lijun Liao
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

package org.xipki.ocsp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xipki.datasource.DataAccessException;
import org.xipki.ocsp.server.impl.OcspServerImpl;
import org.xipki.password.PasswordResolverException;
import org.xipki.util.HttpConstants;
import org.xipki.util.InvalidConfException;
import org.xipki.util.ParamUtil;

/**
 * TODO.
 * @author Lijun Liao
 * @since 3.0.1
 */

@SuppressWarnings("serial")
public class HttpMgmtServlet extends HttpServlet {

  private static final class MyException extends Exception {

    private int status;

    public MyException(int status, String message) {
      super(message);
      this.status = status;
    }

    public int getStatus() {
      return status;
    }

  }

  private static final Logger LOG = LoggerFactory.getLogger(HttpMgmtServlet.class);

  private static final String CT_RESPONSE = "application/json";

  private OcspServerImpl ocspServer;

  public void setOcspServer(OcspServerImpl ocspServer) {
    this.ocspServer = ParamUtil.requireNonNull("ocspServer", ocspServer);;
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      String path = (String) request.getAttribute(HttpConstants.ATTR_XIPKI_PATH);

      if (path == null || path.length() < 2) {
        throw new MyException(HttpServletResponse.SC_NOT_FOUND, "no action is specified");
      }

      String action = path.substring(1);
      if (action == null) {
        throw new MyException(HttpServletResponse.SC_NOT_FOUND,
            "unknown action '" + action + "'");
      }

      switch (action) {
        case "restart": {
            try {
              ocspServer.init(true);
            } catch (InvalidConfException | DataAccessException | PasswordResolverException ex) {
              LOG.warn(action + ": could not restart OCSP server", ex);
              throw new MyException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                  "could not build the CaEntry: " + ex.getMessage());
            }
            break;
        }
        default: {
          throw new MyException(HttpServletResponse.SC_NOT_FOUND,
              "unsupported action " + action);
        }
      }

      response.setContentType(CT_RESPONSE);
      response.setStatus(HttpServletResponse.SC_OK);
      response.setContentLength(0);
    } catch (MyException ex) {
      response.setHeader(HttpConstants.HEADER_XIPKI_ERROR, ex.getMessage());
      response.sendError(ex.getStatus());
    } catch (Throwable th) {
      LOG.error("Throwable thrown, this should not happen!", th);
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    } finally {
      response.flushBuffer();
    }
  } // method service

}
