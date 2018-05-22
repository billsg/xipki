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

package org.xipki.ca.server.mgmt.shell;

import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.xipki.ca.server.mgmt.api.CaMgmtException;
import org.xipki.console.karaf.CmdFailure;

/**
 * TODO.
 * @author Lijun Liao
 * @since 2.0.0
 */

@Command(scope = "ca", name = "notify-change", description = "notify the change of CA system")
@Service
public class CaSystemNotifyChangeAction extends CaAction {

  @Override
  protected Object execute0() throws Exception {
    String msg = "the change of CA system";
    try {
      caManager.notifyCaChange();
      println("notified " + msg);
      return null;
    } catch (CaMgmtException ex) {
      throw new CmdFailure("could not notify " + msg + ", error: " + ex.getMessage(), ex);
    }
  }

}
