package org.l2jmobius.loginserver.network.gameserverpackets;

import java.util.logging.Logger;
import org.l2jmobius.commons.network.base.BaseReadablePacket;
import org.l2jmobius.loginserver.GameServerThread;
import org.l2jmobius.loginserver.LoginController;

public class ChangeAccessLevel extends BaseReadablePacket {
   protected static final Logger LOGGER = Logger.getLogger(ChangeAccessLevel.class.getName());

   public ChangeAccessLevel(byte[] decrypt, GameServerThread server) {
      super(decrypt);
      this.readByte();
      int level = this.readInt();
      String account = this.readString();
      LoginController.getInstance().setAccountAccessLevel(account, level);
      LOGGER.info("Changed " + account + " access level to " + level);
   }
}
