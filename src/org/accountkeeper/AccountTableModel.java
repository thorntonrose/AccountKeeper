package org.accountkeeper;

import java.beans.IntrospectionException;
import java.text.*;
import javax.swing.event.*;
import javax.swing.table.*;
import org.javacogs.*;

/**
 * This class is a table model used by JTable to access an AccountList, which
 * is a collection Account objects. It maps the table columns to Account
 * properties and uses BeanProxy to access the actual Account objects.
 *
 * @author Thornton Rose
 *
 * @see AccountTableModel
 * @see Account
 * @see BeanProxy
 */
public class AccountTableModel extends AbstractTableModel {
   private AccountList acctList;
   private String[]    columns;
   private BeanProxy   proxy;

   /**
    * Construct table model: create column-property map and JournalEntry bean proxy.
    */
   public AccountTableModel() throws IntrospectionException {
      columns = new String[] { "systemName", "username", "password" };
      proxy = new BeanProxy(Account.class);
   }

   /**
    * Get underlying account list.
    */
   public AccountList getAccountList() {
      return acctList;
   }

   /**
    * Set underlying account list.
    */
   public void setAccountList(AccountList theAccounts) {
      acctList = theAccounts;
   }

   /**
    * Get the Account at the given index.
    */
   public Account getAccount(int index) {
      return acctList.get(index);
   }

   /**
    * Get the number of rows in the account list.
    */
   public int getRowCount() {
      return acctList.getCount();
   }

   /**
    * Get the number of columns to display.
    */
   public int getColumnCount() {
      return columns.length;
   }

   /**
    * Get the class of the given column.
    */
   public Class getColumnClass(int col) {
      return getValueAt(0, col).getClass();
   }

   /**
    * Get the value at the given row and column. The Account property for the given
    * column is retrieved from the column-property map and the account bean proxy is
    * used to fetch the property value for the entry bean at the given row.
    */
   public Object getValueAt(int row, int col) {
      Account entry;
      Object  value;

      try {
         entry = acctList.get(row);
         value = proxy.getProperty(entry, columns[col]);
      } catch(Exception ex) {
         return ex.getMessage();
      }

      return value;
   }

   /**
    * Set the value at the given row and column.
    */
   public void setValueAt(Object value, int row, int col) {
      Account entry;

      try {
         entry = acctList.get(row);
         proxy.setProperty(entry, columns[col], value);
      } catch(Exception ex) {
         System.out.println(ex);
      }
   }

   /**
    * Add the given entry to the underlying account list and notify listeners that
    * the table data has changed (by calling <code>fireTableDataChanged()</code>).
    */
   public void addRow(Account entry) {
      acctList.add(entry);
      fireTableDataChanged();
   }

   /**
    * Update the entry at the given index in the underlying account list and notify
    * the listeners that the table data has changed (by calling
    * <code>fireTableDataChanged()</code>).
    */
   public void updateRow(int index, Account entry) {
      acctList.set(index, entry);
      fireTableDataChanged();
   }

   /**
    * Remove the entry at the given index from the underlying account list and notify
    * listeners that a row was deleted (by calling <code>fireTableRowsDeleted()</code>).
    */
   public void removeRow(int index) {
      acctList.remove(index);
      fireTableRowsDeleted(index, index);
   }
}
