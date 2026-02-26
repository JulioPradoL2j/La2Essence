package org.l2jmobius.loginserver.network.gameserverpackets;

import java.util.logging.Logger;
import org.l2jmobius.commons.network.base.BaseReadablePacket;
import org.l2jmobius.loginserver.GameServerThread;

public class PlayerLogout extends BaseReadablePacket {
   protected static final Logger LOGGER = Logger.getLogger(PlayerLogout.class.getName());

   public PlayerLogout(byte[] decrypt, GameServerThread server) {
      super(decrypt);
      this.readByte();
      String account = this.readString();
      server.removeAccountOnGameServer(account);
   }
}
