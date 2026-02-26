package org.l2jmobius.loginserver.network.loginserverpackets;

import org.l2jmobius.commons.network.base.BaseWritablePacket;
import org.l2jmobius.loginserver.GameServerTable;

public class AuthResponse extends BaseWritablePacket {
   public AuthResponse(int serverId) {
      this.writeByte(2);
      this.writeByte(serverId);
      this.writeString(GameServerTable.getInstance().getServerNameById(serverId));
   }
}
