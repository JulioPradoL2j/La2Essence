package org.l2jmobius.loginserver.network.gameserverpackets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import org.l2jmobius.commons.database.DatabaseFactory;
import org.l2jmobius.commons.network.base.BaseReadablePacket;
import org.l2jmobius.loginserver.LoginController;

public class RequestTempBan extends BaseReadablePacket {
   private static final Logger LOGGER = Logger.getLogger(RequestTempBan.class.getName());
   private final String _accountName;
   private final String _ip;
   long _banTime;

   public RequestTempBan(byte[] decrypt) {
      super(decrypt);
      this.readByte();
      this._accountName = this.readString();
      this._ip = this.readString();
      this._banTime = this.readLong();
      boolean haveReason = this.readByte() != 0;
      if (haveReason) {
         this.readString();
      }

      this.banUser();
   }

   private void banUser() {
      try (
         Connection con = DatabaseFactory.getConnection();
         PreparedStatement ps = con.prepareStatement("INSERT INTO account_data VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE value=?");
      ) {
         ps.setString(1, this._accountName);
         ps.setString(2, "ban_temp");
         ps.setString(3, Long.toString(this._banTime));
         ps.setString(4, Long.toString(this._banTime));
         ps.execute();
      } catch (SQLException var9) {
         LOGGER.warning(this.getClass().getSimpleName() + ": " + var9.getMessage());
      }

      LoginController.getInstance().addBanForAddress(this._ip, this._banTime);
   }
}
