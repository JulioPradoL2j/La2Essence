package org.l2jmobius.loginserver.network.loginserverpackets;

import org.l2jmobius.commons.network.base.BaseWritablePacket;

public class KickPlayer extends BaseWritablePacket {
   public KickPlayer(String account) {
      this.writeByte(4);
      this.writeString(account);
   }
}
