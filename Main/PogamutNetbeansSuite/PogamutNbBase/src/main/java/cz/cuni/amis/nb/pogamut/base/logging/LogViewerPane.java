package cz.cuni.amis.nb.pogamut.base.logging;
/*
 * LogViewer.java
 *
 * Created on 20. b�ezen 2007, 17:55
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 * Panel with log viewer (table showing log records).
 * @author ik, kero
 */
public class LogViewerPane extends javax.swing.JPanel implements Serializable {

    public class LogLevelColorRenderer extends DefaultTableCellRenderer {

        private Map<Level, Color> levelColors = new HashMap<Level, Color>();

        public LogLevelColorRenderer() {
            levelColors.put(Level.INFO, new Color(0, 159, 0));
            levelColors.put(Level.SEVERE, Color.RED);
            levelColors.put(Level.WARNING, Color.MAGENTA);
            levelColors.put(Level.CONFIG, Color.BLUE);
            levelColors.put(Level.FINE, Color.DARK_GRAY);
            levelColors.put(Level.FINER, Color.LIGHT_GRAY);
            levelColors.put(Level.FINEST, Color.LIGHT_GRAY);
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object level,
                boolean isSelected, boolean hasFocus,
                int row, int column) {
            LogTableModel.LogLevelObject obj = (LogTableModel.LogLevelObject) level;

            if (levelColors.containsKey(obj.level)) {
                setForeground(levelColors.get(obj.level));
            } else {
                setForeground(Color.BLACK);
            }
            this.setFont(Font.getFont(Font.SANS_SERIF));

            return super.getTableCellRendererComponent(table, obj.msg, isSelected, hasFocus, row, column);
        }
    }

    /** Creates new form LogViewer */
    public LogViewerPane() {
        initComponents();

        //jSplitPane1.setDividerLocation(jPanel1.getPreferredSize().height);

        // init column widths
        TableColumnModel model = jTable1.getColumnModel();
        for (int i = 0; i < 2; i++) {
            model.getColumn(i).setPreferredWidth(120);
            model.getColumn(i).setMaxWidth(120);
        }
        model.getColumn(2).setPreferredWidth(400);
        model.getColumn(2).setMaxWidth(4000);

        DefaultTableCellRenderer cellRenderer = new LogLevelColorRenderer();
        model.getColumn(0).setCellRenderer(cellRenderer);
        model.getColumn(1).setCellRenderer(cellRenderer);
        model.getColumn(2).setCellRenderer(cellRenderer);

        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (jTable1.getSelectedRow() >= 0) {
                    String str = jTable1.getModel().getValueAt(
                            jTable1.getSelectedRow(),
                            2).toString();

                    logDetail.setText(str);
                } // otherwise let the text of outdated log record on the screen
            }
        });
    }

    /**
     * @return Table model of main table showing log reords.
     */
    public LogTableModel getLogTableModel() {
        return (LogTableModel) jTable1.getModel();
    }

    public void setLogNode(LogNode node) {
        getLogTableModel().removeAllDataSources();
        getLogTableModel().addDataSource(
                node.getLogRecordsSource());
        jToggleButton1.setSelected(getLogTableModel().isFreezed());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        logDetail = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 =
        new JTable() {
            // This table displays a tool tip text based on the string
            // representation of the cell value
            public Component prepareRenderer(TableCellRenderer renderer,
                int rowIndex, int vColIndex) {
                if (rowIndex < getModel().getRowCount()) {
                    Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
                    if (c instanceof JComponent) {
                        JComponent jc = (JComponent)c;
                        jc.setToolTipText(getValueAt(rowIndex, vColIndex).toString());
                    }
                    return c;
                } else
                return null;
            }
        };
        ;

        setLayout(new java.awt.BorderLayout());

        jSplitPane1.setDividerLocation(300);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Log record detail"));
        jPanel1.setPreferredSize(new java.awt.Dimension(84, 100));
        jPanel1.setLayout(new java.awt.BorderLayout());

        logDetail.setColumns(20);
        logDetail.setEditable(false);
        logDetail.setLineWrap(true);
        logDetail.setRows(5);
        jScrollPane3.setViewportView(logDetail);

        jPanel1.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.Y_AXIS));

        jToggleButton1.setText("Freeze");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jToggleButton1);

        jButton1.setText("Clear all");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jPanel1.add(jPanel2, java.awt.BorderLayout.EAST);

        jPanel3.add(jPanel1, java.awt.BorderLayout.CENTER);

        jSplitPane1.setRightComponent(jPanel3);

        jTable1.setModel(new LogTableModel());
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jScrollPane1.setViewportView(jTable1);

        jSplitPane1.setLeftComponent(jScrollPane1);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        ((LogTableModel) jTable1.getModel()).setFreeze(jToggleButton1.isSelected());
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        for (LogRecordsSource lrs : ((LogTableModel) jTable1.getModel()).getDataSources()) {
            lrs.clear();
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTextArea logDetail;
    // End of variables declaration//GEN-END:variables
}
