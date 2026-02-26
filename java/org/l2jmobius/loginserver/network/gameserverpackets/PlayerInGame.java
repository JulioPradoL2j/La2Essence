package org.l2jmobius.loginserver.network.gameserverpackets;

import org.l2jmobius.commons.network.base.BaseReadablePacket;
import org.l2jmobius.loginserver.GameServerThread;

public class PlayerInGame extends BaseReadablePacket {
   public PlayerInGame(byte[] decrypt, GameServerThread server) {
      super(decrypt);
      this.readByte();
      int size = this.readShort();

      for (int i = 0; i < size; i++) {
         String account = this.readString();
         server.addAccountOnGameServer(account);
      }
   }
}
