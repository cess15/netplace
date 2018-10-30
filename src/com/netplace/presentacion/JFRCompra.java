/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netplace.presentacion;

import com.netplace.dao.Conexion;
import com.netplace.entidad.Entry;
import com.netplace.entidad.Product;
import com.netplace.entidad.User;
import com.netplace.negocio.EntryLN;
import static com.netplace.presentacion.Main.desktop;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Cess
 */
public class JFRCompra extends javax.swing.JInternalFrame {

    /**
     * Creates new form JFRCompra
     */
    EntryLN entryLN;
    DefaultTableModel model = null;
    ColorTableEntry colorEntry;

    Object[] data;

    public JFRCompra() {
        initComponents();
        entryLN = new EntryLN();
        getEntry(entryLN.getEntries());
        calcs();
        this.jTable1.setDefaultRenderer(Object.class, new ColorTableEntry());
        ajust();
    }

    public void getEntry(List<Entry> lEntry) {
        data = new Object[8];
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }

        };
        jTable1.setModel(model);
        model.addColumn("ID");
        model.addColumn("Nombre del Producto");
        model.addColumn("Descripcion");
        model.addColumn("Cantidad");
        model.addColumn("Precio");
        model.addColumn("Nombre de Usuario");
        model.addColumn("Fecha");
        model.addColumn("SubTotal");
        jTable1.removeAll();

        for (Entry entry : lEntry) {
            data[0] = entry.getIdEntry();
            data[1] = entry.getProduct().getName();
            data[2] = entry.getProduct().getDescription().trim();
            data[3] = entry.getQuantity();
            data[4] = entry.getPrice();
            data[5] = entry.getUser().getUsername();
            data[6] = entry.getDateTime();
            data[7] = entry.getSubTotal();
            model.addRow(data);
        }
    }

    public void search(String text) {
        try {
            getEntry(entryLN.getEntries());
            if (!text.isEmpty()) {
                List<Entry> lEntry = new ArrayList<Entry>();
                List<Entry> entries = entryLN.getEntries();
                for (Entry entry : entries) {

                    if (entry.getProduct().getName().toLowerCase().contains(text.toLowerCase())
                            || (entry.getProduct().getDescription().toLowerCase().contains(text.toLowerCase()))
                            || (entry.getUser().getUsername().toLowerCase().contains(text.toLowerCase()))
                            || (entry.getDateTime().equals(text))) {
                        Entry e = new Entry();
                        Product p = new Product();
                        User u = new User();
                        e.setIdEntry(entry.getIdEntry());
                        p.setName(entry.getProduct().getName());
                        p.setDescription(entry.getProduct().getDescription());
                        e.setProduct(p);
                        e.setQuantity(entry.getQuantity());
                        e.setPrice(entry.getPrice());
                        u.setUsername(entry.getUser().getUsername());
                        e.setUser(u);
                        e.setDateTime(entry.getDateTime());
                        lEntry.add(e);
                    }
                }
                getEntry(lEntry);
                ajust();
            }
            calcs();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en fecha: " + e.getMessage());
        }
    }

    public void calcs() {
        double total = 0.0;
        for (int i = 0; i < this.jTable1.getRowCount(); i++) {
            total = total + (double) this.jTable1.getValueAt(i, 7);

        }
        this.txtTotal.setText("" + total);
        int i = this.jTable1.getRowCount();
        this.txtCountEntry.setText("" + i);
    }

    public void ajust() {
        TableColumnModel column = this.jTable1.getColumnModel();
        column.getColumn(0).setPreferredWidth(10);
        column.getColumn(1).setPreferredWidth(150);
        column.getColumn(2).setPreferredWidth(200);
        column.getColumn(5).setPreferredWidth(150);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtCountEntry = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);

        jDesktopPane1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Buscar");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

        jButton1.setText("Nuevo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Imprimir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Total");

        jLabel3.setText("Numero de Compras Realizadas");

        txtTotal.setEditable(false);

        txtCountEntry.setEditable(false);

        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jTextField1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jDateChooser1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtTotal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtCountEntry, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtCountEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 323, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCountEntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            JFRAgregarCompra insertEntry = new JFRAgregarCompra();
            OpenInternalFrame.charge(insertEntry, desktop);
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            search(this.jTextField1.getText());
            ajust();
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        try {
            if (!((JTextField) this.jDateChooser1.getDateEditor().getUiComponent()).getText().isEmpty()) {
                Date date = this.jDateChooser1.getDate();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                search(sdf.format(date));
                ajust();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (!((JTextField) this.jDateChooser1.getDateEditor().getUiComponent()).getText().isEmpty()) {
            Date date = this.jDateChooser1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Connection cn = new Conexion().createConection();
                Map p = new HashMap();
                JasperReport report;
                JasperPrint print;
                p.put("fechas", sdf.format(date));
                report = JasperCompileManager.compileReport(new File("").getAbsolutePath() + "/src/com/netplace/recursos/Compra.jrxml");
                print = JasperFillManager.fillReport(report, p, cn);
                JasperViewer view = new JasperViewer(print, false);
                view.setTitle("COMPRAS");
                view.setVisible(true);
                cn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        } else {
            try {
                Connection cn = new Conexion().createConection();
                Map p = new HashMap();
                JasperReport report;
                JasperPrint print;
                report = JasperCompileManager.compileReport(new File("").getAbsolutePath() + "/src/com/netplace/recursos/Entry.jrxml");
                print = JasperFillManager.fillReport(report, p, cn);
                JasperViewer view = new JasperViewer(print, false);
                view.setTitle("COMPRAS");
                view.setVisible(true);
                cn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    public com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField txtCountEntry;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
