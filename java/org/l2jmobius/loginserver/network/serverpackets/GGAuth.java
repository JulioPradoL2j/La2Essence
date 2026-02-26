package org.l2jmobius.loginserver.network.serverpackets;

import org.l2jmobius.commons.network.WritableBuffer;
import org.l2jmobius.loginserver.network.LoginClient;

public class GGAuth extends LoginServerPacket {
   private final int _response;

   public GGAuth(int response) {
      this._response = response;
   }

   @Override
   protected void writeImpl(LoginClient client, WritableBuffer buffer) {
      buffer.writeByte(11);
      buffer.writeInt(this._response);
      buffer.writeInt(0);
      buffer.writeInt(0);
      buffer.writeInt(0);
      buffer.writeInt(0);
   }
}
