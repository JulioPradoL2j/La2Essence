package org.l2jmobius.loginserver.network.loginserverpackets;

import org.l2jmobius.commons.network.base.BaseWritablePacket;

public class InitLS extends BaseWritablePacket {
   public InitLS(byte[] publickey) {
      this.writeByte(0);
      this.writeInt(262);
      this.writeInt(publickey.length);
      this.writeBytes(publickey);
   }
}
