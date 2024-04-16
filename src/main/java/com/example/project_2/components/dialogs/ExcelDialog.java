/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Dialog.java to edit this template
 */
package com.example.project_2.components.dialogs;

import com.example.project_2.components.table.Table;
import com.example.project_2.utils.Excel;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.function.Function;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Duy
 */
public class ExcelDialog extends java.awt.Dialog {
	private List<List<Object>> columns;
	private boolean cancel;
	private DefaultTableModel model;

	/**
	 * Creates new form ExcelDialog
	 */
	public ExcelDialog(java.awt.Frame parent, boolean modal, List<List<Object>> columns, Function<List<String>, Boolean> runWhenConfirm) {
		super(parent, modal);
		this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cancel = true;
                dispose();
            }
        });

		initComponents();
		model = new DefaultTableModel();
		table.setModel(model);

		this.columns = columns;
		for (List<?> column : columns) {
			model.addColumn(column.get(0).toString());
		}
		table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setRowHeight(22);
        table.setCellSelectionEnabled(true);
		model.addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                Object value = model.getValueAt(row, column).toString();
                Excel.Type type = (Excel.Type) columns.get(column).get(1);
                Object castValue;
                try {
                    castValue = Excel.castValue(value, type);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Dữ liệu không đúng định dạng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    castValue = Excel.castValue(null, type);
                    model.setValueAt(castValue, row, column);
//                    table.addRowSelectionInterval(row, row);
                }
            }
        });

        addListenerToButtons(runWhenConfirm);

        this.setSize(1000, 600);
        this.setLocation(
                (parent.getX() + parent.getWidth() - this.getWidth()) / 2,
                (parent.getY() + parent.getHeight() - this.getHeight()) / 2);
	}

	public Table getTable() {
		return table;
	}

	public boolean isCancel() {
		return cancel;
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new com.example.project_2.components.table.Table();
        jPanel1 = new javax.swing.JPanel();
        btnConfirm = new com.example.project_2.components.swing.Button();
        btnCancel = new com.example.project_2.components.swing.Button();
        btnAddRow = new com.example.project_2.components.swing.Button();
        btnRemoveRow = new com.example.project_2.components.swing.Button();
        btnPaste = new com.example.project_2.components.swing.Button();
        btnReadFile = new com.example.project_2.components.swing.Button();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(table);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        btnConfirm.setText("Xác nhận");
        jPanel1.add(btnConfirm);

        btnCancel.setText("Hủy");
        jPanel1.add(btnCancel);

        btnAddRow.setText("Thêm dòng");
        jPanel1.add(btnAddRow);

        btnRemoveRow.setText("Xóa dòng");
        jPanel1.add(btnRemoveRow);

        btnPaste.setText("Dán");
        jPanel1.add(btnPaste);

        btnReadFile.setText("Đọc file Ẽxcel");
        jPanel1.add(btnReadFile);

        add(jPanel1, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

	/**
	 * Closes the dialog
	 */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
		setVisible(false);
		dispose();
    }//GEN-LAST:event_closeDialog


    private void addListenerToButtons(Function<List<String>, Boolean> runWhenConfirm) {
        btnConfirm.addActionListener(e -> {
            cancel = false;
            List<List<String>> data = getData();
            if (data == null)
                return;

            boolean hasError = false;
            for (int i = 0; i < data.size(); i++) {
                try {
                    List<String> row = data.get(i);
                    boolean result = runWhenConfirm.apply(row);
                    if (!result) {
                        throw new RuntimeException();
                    }
                    data.remove(i);
                    model.removeRow(i);
                    i--;
                } catch (Exception exception) {
                    hasError = true;
                    table.setRowSelectionInterval(i, i);
                    String message = String.format("Tìm thấy lỗi ở dòng %d: %s", i + 1, data.get(i));
                    String title = "Lỗi";
                    Object[] options = new Object[]{"Bỏ qua", "Dừng"};
                    int choice = JOptionPane.showOptionDialog(this, message, title,
                            JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
                    if (choice != 0) break;
                }
            }
            if (!hasError)
                dispose();
        });
        btnCancel.addActionListener(e -> {
            cancel = true;
            refresh();
            dispose();
        });
        btnAddRow.addActionListener(e -> {
            addRow();
            table.clearSelection();
            int row = model.getRowCount() - 1;
            table.addRowSelectionInterval(row, row);
        });
        btnRemoveRow.addActionListener(e -> {
            removeRow();
            table.clearSelection();
        });
        btnPaste.addActionListener(e -> pasteFromClipboard());
        btnReadFile.addActionListener(e -> {
            File file = Excel.openFile();
            if (file == null)
                return;
            List<Object> result = Excel.importExcel(file, columns);
            if (((List<?>) result.get(0)).isEmpty()) {
                JOptionPane.showMessageDialog(this, result.get(1), "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(this, result.get(1), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            table.clearSelection();
            List<List<Object>> data = (List<List<Object>>) result.get(0);
            for (List<Object> datum : data) {
                int row = model.getRowCount();
                addRow();
                for (int j = 0; j < data.get(0).size(); j++) {
                    model.setValueAt(datum.get(j), row, j);
                }
            }
        });
    }

    public void addRow() {
        List<Object> emptyData = new ArrayList<>();
        for (List<Object> column : columns) {
            Object datum = Excel.castValue(null, (Excel.Type) column.get(1));
            emptyData.add(datum);
        }
        model.addRow(emptyData.toArray());
    }

    public void removeRow() {
        int[] rows = table.getSelectedRows();
        for (int i = rows.length - 1; i >= 0 ; i--)
            model.removeRow(rows[i]);
    }

    public void pasteFromClipboard() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable transferable = clipboard.getContents(table);

        if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            try {
                String clipboardData = (String) transferable.getTransferData(DataFlavor.stringFlavor);
                String[] data = clipboardData.split("\n");
                table.clearSelection();

                boolean hasError = false;
                for (String datum : data) {
                    int row = model.getRowCount();
                    String[] values = datum.split("\t");
                    model.addRow(values);
                    for (int j = 0; j < values.length; j++) {
                        Object value = model.getValueAt(row, j);
                        Excel.Type type = (Excel.Type) columns.get(j).get(1);
                        Object castValue;
                        try {
                            castValue = Excel.castValue(value, type);
                        } catch (ClassCastException exception) {
                            hasError = true;
                            castValue = Excel.castValue(null, type);
                            model.setValueAt(castValue, row, j);
                            table.addRowSelectionInterval(row, row);
                        }
                    }
                }
                if (hasError) {
                    JOptionPane.showMessageDialog(this, """
                            Những dữ liệu không hợp lệ đã được bỏ qua.
                            Vui lòng mở file để xem những dữ liệu không hợp lệ được đánh dấu màu đỏ.""",
                            "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Đã xảy ra lỗi khi gán dữ liệu.",
                        "Lỗi", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public List<List<String>> getData() {
        if (cancel)
            return null;
        List<List<String>> data = new ArrayList<>();
        for (Vector<?> row : model.getDataVector()) {
            List<String> rowData = new ArrayList<>();
            for (Object value : row)
                rowData.add(value.toString().trim());
            data.add(rowData);
        }
        return data;
    }

    public void refresh() {
        model.setRowCount(0);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.example.project_2.components.swing.Button btnAddRow;
    private com.example.project_2.components.swing.Button btnCancel;
    private com.example.project_2.components.swing.Button btnConfirm;
    private com.example.project_2.components.swing.Button btnPaste;
    private com.example.project_2.components.swing.Button btnReadFile;
    private com.example.project_2.components.swing.Button btnRemoveRow;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.example.project_2.components.table.Table table;
    // End of variables declaration//GEN-END:variables
}