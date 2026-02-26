package org.l2jmobius.loginserver.network.gameserverpackets;

import org.l2jmobius.commons.network.base.BaseReadablePacket;
import org.l2jmobius.loginserver.GameServerThread;
import org.l2jmobius.loginserver.LoginController;
import org.l2jmobius.loginserver.SessionKey;
import org.l2jmobius.loginserver.network.loginserverpackets.PlayerAuthResponse;

public class PlayerAuthRequest extends BaseReadablePacket {
   public PlayerAuthRequest(byte[] decrypt, GameServerThread server) {
      super(decrypt);
      this.readByte();
      String account = this.readString();
      int playKey1 = this.readInt();
      int playKey2 = this.readInt();
      int loginKey1 = this.readInt();
      int loginKey2 = this.readInt();
      SessionKey sessionKey = new SessionKey(loginKey1, loginKey2, playKey1, playKey2);
      SessionKey storedKey = LoginController.getInstance().getKeyForAccount(account);
      if (storedKey != null && storedKey.equals(sessionKey)) {
         LoginController.getInstance().removeAuthedLoginClient(account);
         server.sendPacket(new PlayerAuthResponse(account, true));
      } else {
         server.sendPacket(new PlayerAuthResponse(account, false));
      }
   }
}
