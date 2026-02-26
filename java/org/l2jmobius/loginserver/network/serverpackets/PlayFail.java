package org.l2jmobius.loginserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.loginserver.enums.PlayFailReason;
import org.l2jmobius.loginserver.network.LoginClient;

public class PlayFail extends LoginServerPacket {
   private final PlayFailReason _reason;

   public PlayFail(PlayFailReason reason) {
      this._reason = reason;
   }

   @Override
   protected void writeImpl(LoginClient client, WritableBuffer buffer) {
      buffer.writeByte(6);
      buffer.writeByte(this._reason.getCode());
   }
}
