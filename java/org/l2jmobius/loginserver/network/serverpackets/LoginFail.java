package org.l2jmobius.loginserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.loginserver.enums.LoginFailReason;
import org.l2jmobius.loginserver.network.LoginClient;

public class LoginFail extends LoginServerPacket {
   private final LoginFailReason _reason;

   public LoginFail(LoginFailReason reason) {
      this._reason = reason;
   }

   @Override
   protected void writeImpl(LoginClient client, WritableBuffer buffer) {
      buffer.writeByte(1);
      buffer.writeByte(this._reason.getCode());
   }
}
