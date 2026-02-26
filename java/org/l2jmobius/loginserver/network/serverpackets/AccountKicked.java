package org.l2jmobius.loginserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.loginserver.enums.AccountKickedReason;
import org.l2jmobius.loginserver.network.LoginClient;

public class AccountKicked extends LoginServerPacket {
   private final AccountKickedReason _reason;

   public AccountKicked(AccountKickedReason reason) {
      this._reason = reason;
   }

   @Override
   protected void writeImpl(LoginClient client, WritableBuffer buffer) {
      buffer.writeByte(2);
      buffer.writeInt(this._reason.getCode());
   }
}
