package org.l2jmobius.loginserver.network.loginserverpackets;

import org.l2jmobius.commons.network.base.BaseWritablePacket;

public class PlayerAuthResponse extends BaseWritablePacket {
   public PlayerAuthResponse(String account, boolean response) {
      this.writeByte(3);
      this.writeString(account);
      this.writeByte(response ? 1 : 0);
   }
}
