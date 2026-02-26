package org.l2jmobius.loginserver.network.loginserverpackets;

import org.l2jmobius.commons.network.base.BaseWritablePacket;

public class ChangePasswordResponse extends BaseWritablePacket {
   public ChangePasswordResponse(String characterName, String msgToSend) {
      this.writeByte(6);
      this.writeString(characterName);
      this.writeString(msgToSend);
   }
}
