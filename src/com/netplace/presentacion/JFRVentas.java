/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netplace.presentacion;

import com.netplace.dao.Conexion;
import com.netplace.entidad.Product;
import com.netplace.entidad.Sale;
import com.netplace.entidad.User;
import com.netplace.negocio.SaleLN;
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
public class JFRVentas extends javax.swing.JInternalFrame {

    /**
     * Creates new form JFRVentas
     */
    SaleLN saleLN;
    DefaultTableModel model = null;
    ColorTableEntry colorEntry;
    Object[] data;

    public JFRVentas() {
        initComponents();
        saleLN = new SaleLN();
        getSale(saleLN.getSales());
        this.jTable1.setDefaultRenderer(Object.class, new ColorTableEntry());
        calcs();
        ajust();
    }

    public void getSale(List<Sale> lSale) {
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
        model.addColumn("Precio de Producto");
        model.addColumn("Nombre de Usuario");
        model.addColumn("Fecha");
        model.addColumn("SubTotal");

        jTable1.removeAll();

        for (Sale sale : lSale) {
            data[0] = sale.getIdSale();
            data[1] = sale.getProduct().getName();
            data[2] = sale.getProduct().getDescription().trim();
            data[3] = sale.getQuantity();
            data[4] = sale.getProduct().getSalePrice();
            data[5] = sale.getUser().getUsername();
            data[6] = sale.getDateTime();
            data[7] = sale.getSubtotal();
            model.addRow(data);
        }
    }

    public void search(String text) {
        List<Sale> lSale = new ArrayList<Sale>();
        List<Sale> sales = saleLN.getSales();
        for (Sale sale : sales) {

            if (sale.getProduct().getName().toLowerCase().contains(text.toLowerCase())
                    || (sale.getProduct().getDescription().toLowerCase().contains(text.toLowerCase()))
                    || (sale.getUser().getUsername().toLowerCase().contains(text.toLowerCase()))
                    || (sale.getDateTime().equals(text))) {
                Sale s = new Sale();
                Product p = new Product();
                User u = new User();
                s.setIdSale(sale.getIdSale());
                p.setName(sale.getProduct().getName());
                p.setDescription(sale.getProduct().getDescription());
                s.setQuantity(sale.getQuantity());
                p.setSalePrice(sale.getProduct().getSalePrice());
                s.setProduct(p);
                u.setUsername(sale.getUser().getUsername());
                s.setUser(u);
                s.setDateTime(sale.getDateTime());
                s.setSubtotal(sale.getSubtotal());
                lSale.add(s);
            }
        }
        getSale(lSale);
        ajust();
    }

    public void searchText(String text) {
        try {
            getSale(saleLN.getSales());
            if (!text.isEmpty()) {
                search(text);
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
        this.txtTot.setText("" + total);
        int i = this.jTable1.getRowCount();
        this.txtCount.setText("" + i);
    }

    public void ajust() {
        TableColumnModel column = this.jTable1.getColumnModel();
        column.getColumn(0).setPreferredWidth(10);
        column.getColumn(1).setPreferredWidth(150);
        column.getColumn(2).setPreferredWidth(200);
        column.getColumn(5).setPreferredWidth(150);
        column.getColumn(6).setPreferredWidth(130);
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
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txtCount = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTot = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);

        jDesktopPane1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel1.setText("Buscar");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/netplace/recursos/agregar-compra- venta.png"))); // NOI18N
        jButton1.setToolTipText("Nuevo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/netplace/recursos/imprimir.png"))); // NOI18N
        jButton2.setToolTipText("Imprimir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel2.setText("Numero de Ventas");

        txtCount.setEditable(false);
        txtCount.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        txtCount.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setText("Total");

        txtTot.setEditable(false);
        txtTot.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        txtTot.setOpaque(false);

        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtBuscar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jDateChooser1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtCount, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtTot, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCount, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(28, 28, 28)
                        .addComponent(txtTot, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtBuscar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtTot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
            JFRAgregarVenta insertSale = new JFRAgregarVenta();
            OpenInternalFrame.charge(insertSale, desktop);
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            searchText(this.txtBuscar.getText());
            ajust();
            this.jDateChooser1.setCalendar(null);
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        try {
            if (!((JTextField) this.jDateChooser1.getDateEditor().getUiComponent()).getText().isEmpty()) {
                Date date = this.jDateChooser1.getDate();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                searchText(sdf.format(date));
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
                p.put("fecha", sdf.format(date));
                report = JasperCompileManager.compileReport(new File("").getAbsolutePath() + "/src/com/netplace/recursos/Venta.jrxml");
                print = JasperFillManager.fillReport(report, p, cn);
                JasperViewer view = new JasperViewer(print, false);
                view.setTitle("VENTAS");
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
                report = JasperCompileManager.compileReport(new File("").getAbsolutePath() + "/src/com/netplace/recursos/Sales.jrxml");
                print = JasperFillManager.fillReport(report, p, cn);
                JasperViewer view = new JasperViewer(print, false);
                view.setTitle("VENTAS");
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
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCount;
    private javax.swing.JTextField txtTot;
    // End of variables declaration//GEN-END:variables
}
