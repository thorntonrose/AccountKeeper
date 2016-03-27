package org.accountkeeper;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import org.javacogs.*;

/**
 * This class is an extension of JTable used for displaying account in a table. It
 * encapsulates the table attributes, such as table column headers, cell renderers,
 * etc. To access the accounts, this class uses an AccountTableModel.
 *
 * @author Thornton Rose
 *
 * @see AccountTableModel
 * @see Account
 */
public class AccountTable extends JTable {
   /**
    * Construct a new instance of this class.
    */
   public AccountTable() {
      // Set cosmetic attributes.

      setBorder(null);
      setShowHorizontalLines(true);
      setShowVerticalLines(true);

      // Add columns to table.

      setAutoCreateColumnsFromModel(false);

      addColumn(new SimpleTableColumn(0, 75, "Account"));
      // addColumn(new SimpleTableColumn(0, 75, "System"));
      // addColumn(new SimpleTableColumn(1, 75, "Username"));
      // addColumn(new SimpleTableColumn(2, 75, "Password"));

      // Set resize mode.

      setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
   }
}
