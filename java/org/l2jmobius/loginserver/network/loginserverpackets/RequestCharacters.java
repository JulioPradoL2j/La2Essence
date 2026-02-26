package org.l2jmobius.loginserver.network.loginserverpackets;

import org.l2jmobius.commons.network.base.BaseWritablePacket;

public class RequestCharacters extends BaseWritablePacket {
   public RequestCharacters(String account) {
      this.writeByte(5);
      this.writeString(account);
   }
}
