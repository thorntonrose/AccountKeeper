package org.accountkeeper;

import java.io.*;
import java.util.*;

/**
 * This class represents an account list. It maintains a collection of Account
 * objects and can be loaded from/saved to a file. The default file is
 * "accounts.dat", and the format of the file is a serialized Vector of Account
 * objects.
 *
 * @author Thornton Rose
 *
 * @see Account
 */
public class AccountList {
   private File              dataFile;
   // private File              backupFile;
   private Vector            accounts;
   private AccountComparator acctComparator;

   /**
    * Construct a new account list.
    */
   public AccountList() throws IOException, ClassNotFoundException {
      accounts = new Vector();
      acctComparator = new AccountComparator();

      File acctFile = new File("accounts.dat");
      setFile(acctFile);

      if (acctFile.exists()) {
         load();
      }
   }

   //------------------------------------------------------------------------------------

   /**
    * Get the file.
    */
   public File getFile() {
      return dataFile;
   }

   /**
    * Set the file.
    */
   public void setFile(File theFile) {
      dataFile = theFile;
      // backupFile = new File(dataFile + "~");
   }

   /**
    * Load the accounts from the file.
    */
   public void load()  throws IOException, ClassNotFoundException {
      ObjectInputStream in = new ObjectInputStream(new FileInputStream(getFile()));

      try {
         accounts = (Vector) in.readObject();
      } finally {
         in.close();
      }
   }

   /**
    * Save the accounts to the file.
    */
   public void save() throws IOException {
      // Make backup.

      // dataFile.renameTo(backupFile);

      // Save new file.

      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(getFile()));

      try {
         out.writeObject(accounts);
      } finally {
         out.close();
      }
   }

   //------------------------------------------------------------------------------------

   /**
    * Get the number of journal accounts.
    */
   public int getCount() {
      return accounts.size();
   }

   /**
    * Get the account at the given position.
    */
   public Account get(int row) {
      return (Account) accounts.get(row);
   }

   /**
    * Set the account at the given position, then sort the accounts.
    */
   public void set(int row, Account entry) {
      accounts.setElementAt(entry, row);
      sort();
   }

   /**
    * Add the given account to the account list, then sort the list.
    */
   public void add(Account entry) {
      accounts.addElement(entry);
      sort();
   }

   /**
    * Insert the given account at the given position, the sort the list.
    */
   public void insert(int row, Account entry) {
      accounts.insertElementAt(entry, row);
      sort();
   }

   /**
    * Remove the journal entry at the given position.
    */
   public void remove(int row) {
      accounts.removeElementAt(row);
   }

   /**
    * Sort the journal accounts in default order (work date, start time, end time, meals).
    */
   private void sort() {
      Collections.sort(accounts, acctComparator);
   }

   //------------------------------------------------------------------------------------

   /**
    * This class is the comparator for sorting accounts in default order and is used by
    * sort(). It compares the accounts using the account system name as the key.
    *
    * @author Thornton Rose
    */
   private class AccountComparator implements Comparator {
      /**
       * Construct the comparator.
       */
      public AccountComparator() {
      }

      /**
       * Compare a given account to another.
       *
       * @param entry1 Given account.
       * @param entry2 Account to which to compare.
       *
       * @return Result: negative => entry1 < entry2; 0 => entry1 == entry2;
       *    positive => entry1 > entry2
       */
      public int compare(Object entry1, Object entry2) {
         String key1 = ((Account) entry1).getSystemName();
         String key2 = ((Account) entry2).getSystemName();

         return key1.compareTo(key2);
      }

      /**
       * Determine if this comparator equals the given object.
       *
       * @return True if this equals obj.
       */
      public boolean equals(Object obj) {
         return this.hashCode() == obj.hashCode();
      }
   }
}
