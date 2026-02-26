package org.l2jmobius.loginserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.loginserver.network.LoginClient;

public class PIAgreementCheck extends LoginServerPacket {
   private final int _accountId;
   private final int _status;

   public PIAgreementCheck(int accountId, int status) {
      this._accountId = accountId;
      this._status = status;
   }

   @Override
   protected void writeImpl(LoginClient client, WritableBuffer buffer) {
      buffer.writeByte(17);
      buffer.writeInt(this._accountId);
      buffer.writeByte(this._status);
   }
}
