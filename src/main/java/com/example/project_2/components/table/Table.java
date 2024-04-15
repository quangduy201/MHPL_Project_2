package com.example.project_2.components.table;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import com.example.project_2.components.scroll.ScrollBar;

public class Table extends JTable {
    private int actionColumn = -1;
    private boolean isOtherAction = false;
    private boolean isOnlyUpdate = true;

    public Table() {
        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(40);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TableHeader header = new TableHeader(o + "");
                header.setHorizontalAlignment(JLabel.CENTER);
                return header;
            }
        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean focus, int i, int i1) {
                if (o instanceof ModelAction) {
                    ModelAction data = (ModelAction) o;
                    if (!isOtherAction) {
                        Action cell = new Action(data);
                        
                        if (selected) {
                            cell.setBackground(new Color(239, 244, 255));
                        } else {
                            cell.setBackground(Color.WHITE);
                        }
                        return cell;
                    } else {
                        OtherAction cell = new OtherAction(data, isOnlyUpdate);
                        
                        if (selected) {
                            cell.setBackground(new Color(239, 244, 255));
                        } else {
                            cell.setBackground(Color.WHITE);
                        }
                        
                        return cell;
                    }
                } else {
                    Component com = super.getTableCellRendererComponent(jtable, o, selected, focus, i, i1);
                    setBorder(noFocusBorder);
                    com.setForeground(new Color(102, 102, 102));
                    if (selected) {
                        com.setBackground(new Color(239, 244, 255));
                    } else {
                        com.setBackground(Color.WHITE);
                    }
                    
                    // Căn giữa cell
                    ((JLabel) com).setHorizontalAlignment(SwingConstants.CENTER);
                    ((JLabel) com).setVerticalAlignment(SwingConstants.CENTER);
        
                    return com;
                }
            }
        });
    }

    public Table(DefaultTableModel model) {
        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(40);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TableHeader header = new TableHeader(o + "");
                header.setHorizontalAlignment(JLabel.CENTER);
                return header;
            }
        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean focus, int i, int i1) {
                if (o instanceof ModelAction) {
                    ModelAction data = (ModelAction) o;
                    if (!isOtherAction) {
                        Action cell = new Action(data);
                        
                        if (selected) {
                            cell.setBackground(new Color(239, 244, 255));
                        } else {
                            cell.setBackground(Color.WHITE);
                        }
                        return cell;
                    } else {
                        OtherAction cell = new OtherAction(data, isOnlyUpdate);
                        
                        if (selected) {
                            cell.setBackground(new Color(239, 244, 255));
                        } else {
                            cell.setBackground(Color.WHITE);
                        }
                        
                        return cell;
                    }
                } else {
                    Component com = super.getTableCellRendererComponent(jtable, o, selected, focus, i, i1);
                    setBorder(noFocusBorder);
                    com.setForeground(new Color(102, 102, 102));
                    if (selected) {
                        com.setBackground(new Color(239, 244, 255));
                    } else {
                        com.setBackground(Color.WHITE);
                    }
                    
                    // Căn giữa cell
                    ((JLabel) com).setHorizontalAlignment(SwingConstants.CENTER);
                    ((JLabel) com).setVerticalAlignment(SwingConstants.CENTER);
        
                    return com;
                }
            }
        });
		this.setModel(model);
    }
    
    public boolean getIsOnlyUpdate() {
        return isOnlyUpdate;
    }

    public void setIsOnlyUpdate(boolean isOnlyUpdate) {
        this.isOnlyUpdate = isOnlyUpdate;
    }

    @Override
    public TableCellEditor getCellEditor(int row, int col) {
        if (col == actionColumn) {
            TableCellAction cell = new TableCellAction();
            
            cell.setIsOnlyUpdate(isOnlyUpdate);
            cell.setIsOtherAction(isOtherAction);
            
            return cell;
        } else {
            return super.getCellEditor(row, col);
        }
    }
    
    public void setActionColumn(int column) {
        this.actionColumn = column;
    }
    
    public int getActionColumn() {
        return this.actionColumn;
    }
    
    public void setIsOtherAction(boolean isOtherAction) {
        this.isOtherAction = isOtherAction;
    }
    
    public boolean getIsOtherAction() {
        return this.isOtherAction;
    }

    public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }
    
    public void removeAllRow() {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.setRowCount(0); // Set row count to 0 to remove all rows
    }

    public void fixTable(JScrollPane scroll) {
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setVerticalScrollBar(new ScrollBar());
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        scroll.setBorder(new EmptyBorder(5, 10, 5, 10));
    }
}
