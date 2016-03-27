package org.accountkeeper;

import java.io.*;

/**
 * This class represents a simple Account, which consists of a system, a username,
 * and a password.
 */
public class Account implements Serializable {
   private String systemName = "";
   private String username   = "";
   private String password   = "";

   public Account() {
   }

   public String getSystemName() {
      return systemName;
   }

   public void setSystemName(String theSystemName) {
      systemName = theSystemName;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String theUsername) {
      username = theUsername;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String thePassword) {
      password = thePassword;
   }

   public Object clone() {
      Account newAcct = new Account();

      newAcct.setSystemName(getSystemName());
      newAcct.setUsername(getUsername());
      newAcct.setPassword(getPassword());

      return newAcct;
   }
}