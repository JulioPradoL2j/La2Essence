package org.l2jmobius.loginserver.network.gameserverpackets;

import org.l2jmobius.commons.network.base.BaseReadablePacket;
import org.l2jmobius.loginserver.GameServerThread;
import org.l2jmobius.loginserver.LoginController;

public class ReplyCharacters extends BaseReadablePacket {
   public ReplyCharacters(byte[] decrypt, GameServerThread server) {
      super(decrypt);
      this.readByte();
      String account = this.readString();
      int chars = this.readByte();
      int charsToDel = this.readByte();
      long[] charsList = new long[charsToDel];

      for (int i = 0; i < charsToDel; i++) {
         charsList[i] = this.readLong();
      }

      LoginController.getInstance().setCharactersOnServer(account, chars, charsList, server.getServerId());
   }
}
