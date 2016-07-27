/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.PrintController;
import model.Print;

/**
 *
 * @author andras
 */
public class PrintDialog extends javax.swing.JDialog {

    public Mainwindow mainwindow;    
    public PrintController printController;

    /**
     * Creates new form PrintDialog
     */
    public PrintDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        super.setLocationRelativeTo(parent);
        printController = new PrintController(this);
        
    }

    public void setMainwindow(Mainwindow mainwindow) {
        this.mainwindow = mainwindow;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        containerPanel = new javax.swing.JPanel();
        toolPanel = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        printButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        pagePanel = new javax.swing.JPanel();
        tablePanel = new javax.swing.JPanel();
        tableScrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        wordsScrollPane = new javax.swing.JScrollPane();
        wordsTextArea = new javax.swing.JTextArea();

        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        containerPanel.setLayout(new javax.swing.BoxLayout(containerPanel, javax.swing.BoxLayout.PAGE_AXIS));

        toolPanel.setLayout(new javax.swing.BoxLayout(toolPanel, javax.swing.BoxLayout.LINE_AXIS));

        jToolBar1.setRollover(true);

        printButton.setText("Print");
        printButton.setFocusable(false);
        printButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        printButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(printButton);

        closeButton.setText("Close");
        closeButton.setFocusable(false);
        closeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        closeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(closeButton);

        toolPanel.add(jToolBar1);

        containerPanel.add(toolPanel);

        pagePanel.setBackground(new java.awt.Color(255, 255, 255));
        pagePanel.setMaximumSize(new java.awt.Dimension(500, 570));
        pagePanel.setMinimumSize(new java.awt.Dimension(500, 570));
        pagePanel.setPreferredSize(new java.awt.Dimension(500, 570));
        pagePanel.setLayout(new javax.swing.BoxLayout(pagePanel, javax.swing.BoxLayout.LINE_AXIS));

        tablePanel.setBackground(new java.awt.Color(255, 255, 255));
        tablePanel.setLayout(new javax.swing.BoxLayout(tablePanel, javax.swing.BoxLayout.LINE_AXIS));

        tableScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        tableScrollPane.setBorder(null);
        tableScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tableScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        tableScrollPane.setViewport(tableScrollPane.getViewport());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table.setFillsViewportHeight(true);
        table.setGridColor(new java.awt.Color(255, 255, 255));
        table.setMaximumSize(new java.awt.Dimension(400, 64));
        table.setSelectionBackground(new java.awt.Color(255, 255, 255));
        table.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tableScrollPane.setViewportView(table);

        tablePanel.add(tableScrollPane);

        pagePanel.add(tablePanel);

        wordsScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        wordsScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        wordsTextArea.setColumns(20);
        wordsTextArea.setRows(5);
        wordsTextArea.setMinimumSize(new java.awt.Dimension(100, 45));
        wordsScrollPane.setViewportView(wordsTextArea);

        pagePanel.add(wordsScrollPane);

        jScrollPane1.setViewportView(pagePanel);

        containerPanel.add(jScrollPane1);

        getContentPane().add(containerPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        Print print = new Print(mainwindow);
        print.setParent(this);
        print.print();
    }//GEN-LAST:event_printButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        setVisible(false);
    }//GEN-LAST:event_closeButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) { //NOI18N
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrintDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrintDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrintDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrintDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PrintDialog dialog = new PrintDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    public javax.swing.JPanel containerPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    public javax.swing.JPanel pagePanel;
    private javax.swing.JButton printButton;
    public javax.swing.JTable table;
    public javax.swing.JPanel tablePanel;
    public javax.swing.JScrollPane tableScrollPane;
    private javax.swing.JPanel toolPanel;
    private javax.swing.JScrollPane wordsScrollPane;
    public javax.swing.JTextArea wordsTextArea;
    // End of variables declaration//GEN-END:variables
}
