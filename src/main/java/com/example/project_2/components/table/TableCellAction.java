package com.example.project_2.components.table;

import javax.swing.*;
import java.awt.*;

public class TableCellAction extends DefaultCellEditor {

    private ModelAction data;
    private boolean isOtherAction = false;
    private boolean isOnlyUpdate = false;

    public TableCellAction() {
        super(new JCheckBox());
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int i, int i1) {
        data = (ModelAction) o;
        if (!isOtherAction) {
            Action cell = new Action(data);
            cell.setBackground(new Color(239, 244, 255));
            return cell;
        } else {
            OtherAction cell = new OtherAction(data, isOnlyUpdate);
            cell.setBackground(new Color(239, 244, 255));
            return cell;
        }
    }

    public boolean getIsOnlyUpdate() {
        return isOnlyUpdate;
    }

    public void setIsOnlyUpdate(boolean isOnlyUpdate) {
        this.isOnlyUpdate = isOnlyUpdate;
    }

    public boolean getIsOtherAction() {
        return isOtherAction;
    }

    public void setIsOtherAction(boolean isOtherAction) {
        this.isOtherAction = isOtherAction;
    }

    //  This method to pass data to cell render when focus lose in cell
    @Override
    public Object getCellEditorValue() {
        return data;
    }
}
