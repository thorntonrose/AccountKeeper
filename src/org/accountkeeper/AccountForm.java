package org.accountkeeper;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

/**
 * This class is a UI form for creating/editing a given Account. It was created
 * with JBuilder, and code that was generated by JBuilder is marked with [JBuilder].
 *
 * @author Thornton Rose
 *
 * @see Account
 */
public class AccountForm extends JDialog {
   public static final int RESULT_CANCEL = 0;
   public static final int RESULT_SAVE   = 1;

   private int     result = RESULT_CANCEL;
   private Account acct;

   /*
    * UI components [JBuilder]
    */
   private BorderLayout borderLayout1 = new BorderLayout();
   private CardLayout cardLayout2 = new CardLayout();
   private BorderLayout borderLayout3 = new BorderLayout();
   private JTextField systemField = new JTextField();
   private JTextField usernameField = new JTextField();
   private JTextField passwordField = new JTextField();
   private JPanel contentPanel = new JPanel();
   private JPanel fieldsPanel = new JPanel();
   private JLabel jLabel1 = new JLabel();
   private JLabel jLabel2 = new JLabel();
   private JLabel jLabel3 = new JLabel();
   private JPanel controlPanel = new JPanel();
   private JPanel buttonGridPanel = new JPanel();
   private JPanel buttonPanel = new JPanel();
   private JButton saveButton = new JButton();
   private JButton cancelButton = new JButton();
   private CardLayout cardLayout1 = new CardLayout();
   private GridLayout gridLayout1 = new GridLayout();

   /**
    * Construct a new form with no parent.
    */
   public AccountForm() {
      this((Frame) null);
   }

   /**
    * Construct a new form with the given parent.
    */
   public AccountForm(Frame parent) {
      super(parent);
      jbInit();
      pack();
      addListeners();
   }

   /*
    * Initialize UI components. [JBuilder]
    */
   private void jbInit() {
      this.setTitle("Account");
      this.setSize(new Dimension(257, 123));
      this.getContentPane().setLayout(borderLayout1);
      this.setModal(true);
      gridLayout1.setColumns(2);
      gridLayout1.setHgap(2);
      buttonGridPanel.setMaximumSize(new Dimension(110, 23));
      this.getContentPane().add(contentPanel, BorderLayout.CENTER);
      this.getContentPane().add(controlPanel, BorderLayout.SOUTH);

      cardLayout1.setHgap(2);
      cardLayout1.setVgap(2);

      contentPanel.setLayout(cardLayout1);
      contentPanel.add(fieldsPanel, "card1");

      fieldsPanel.setMaximumSize(new Dimension(252, 85));
      fieldsPanel.setMinimumSize(new Dimension(252, 85));
      fieldsPanel.setPreferredSize(new Dimension(252, 85));
      fieldsPanel.setLayout(null);

      fieldsPanel.add(jLabel3, null);
      fieldsPanel.add(passwordField, null);
      fieldsPanel.add(jLabel1, null);
      fieldsPanel.add(systemField, null);
      fieldsPanel.add(usernameField, null);
      fieldsPanel.add(jLabel2, null);

      jLabel1.setText("System:");
      jLabel1.setBounds(new Rectangle(2, 4, 71, 17));

      systemField.setBounds(new Rectangle(76, 2, 174, 21));

      jLabel2.setBounds(new Rectangle(2, 30, 71, 17));
      jLabel2.setText("User ID:");

      usernameField.setBounds(new Rectangle(76, 28, 174, 21));

      jLabel3.setBounds(new Rectangle(2, 56, 71, 17));
      jLabel3.setText("Password:");

      passwordField.setBounds(new Rectangle(76, 54, 174, 21));

      cardLayout2.setHgap(4);
      cardLayout2.setVgap(4);

      controlPanel.setLayout(cardLayout2);
      controlPanel.setMaximumSize(new Dimension(10, 30));
      controlPanel.setMinimumSize(new Dimension(10, 30));
      controlPanel.setPreferredSize(new Dimension(10, 30));
      controlPanel.add(buttonPanel, "card1");

      buttonPanel.setLayout(borderLayout3);
      buttonPanel.setMinimumSize(new Dimension(150, 23));
      buttonPanel.setPreferredSize(new Dimension(150, 23));
      buttonPanel.add(buttonGridPanel, BorderLayout.WEST);


      buttonGridPanel.setLayout(gridLayout1);
      buttonGridPanel.setMinimumSize(new Dimension(110, 23));
      buttonGridPanel.setPreferredSize(new Dimension(110, 23));

      buttonGridPanel.add(saveButton, null);
      buttonGridPanel.add(cancelButton, null);

      saveButton.setMargin(new Insets(0, 0, 0, 0));
      saveButton.setText("OK");
      // saveButton.setToolTipText("OK");

      cancelButton.setMargin(new Insets(0, 0, 0, 0));
      cancelButton.setText("Cancel");
      // cancelButton.setToolTipText("Cancel");
   }

   /**
    * Show the form, wait for the user to create/edit the given account, and return
    * the result to the caller.
    */
   public int show(Account theAccount) {
      setLocationRelativeTo(getParent());

      acct = theAccount;

      systemField.setText(acct.getSystemName());
      usernameField.setText(acct.getUsername());
      passwordField.setText(acct.getPassword());

      result = RESULT_CANCEL;

      show();

      return result;
   }

   /**
    * Add listeners to form components.
    */
   private void addListeners() {
      // Add listener to Save button.

      saveButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent event) {
            save();
         }
      });

      // Add listener to Cancel button.

      cancelButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent event) {
            cancel();
         }
      });
   }

   /**
    * Save the contents of the form fields to the account object and set the return
    * result to RESULT_SAVE.
    */
   private void save() {
      // Save fields to account object.

      acct.setSystemName(systemField.getText());
      acct.setUsername(usernameField.getText());
      acct.setPassword(passwordField.getText());

      // Set result to SAVE and close window.

      result = RESULT_SAVE;
      hide();
   }

   /**
    * Set the return result to RESULT_CANCEL and hide the form.
    */
   private void cancel() {
      result = RESULT_CANCEL;
      hide();
   }

   //------------------------------------------------------------------------------------

   public static void main(String[] args) {
      Account acct = new Account();
      AccountForm form = new AccountForm(null);

      form.show(acct);

      System.exit(0);
   }
}
