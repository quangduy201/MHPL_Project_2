/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_2.utils;

import com.example.project_2.GUI.Main;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Duy
 */
public class Excel {
    public static final String EXCEL_EXTENSION = ".xlsx";
    private static Workbook workbook;
    private static Sheet sheet;

    public static List<Object> importExcel(File file, List<List<Object>> columns) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            workbook = new XSSFWorkbook(inputStream);
            sheet = workbook.getSheetAt(0);
            int numColumns = sheet.getRow(0).getPhysicalNumberOfCells();
            if (numColumns != columns.size()) {
                StringBuilder columnNames = new StringBuilder();
                for (List<Object> column : columns)
                    columnNames.append(column.get(0)).append(", ");
                columnNames.setLength(columnNames.length() - 2);
                String message = String.format("Dữ liệu cần phải có %d cột: %s.", columns.size(), columnNames);
                return List.of(List.of(), message);
            }
            CellStyle redStyle = workbook.createCellStyle();
            redStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
            redStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            List<List<Object>> data = new ArrayList<>();
            int numRows = sheet.getLastRowNum();
            boolean ok = true;
            for (int i = 1; i <= numRows; i++) {
                List<Object> row = new ArrayList<>();
                boolean hasInvalidData = false;
                for (int j = 0; j < columns.size(); j++) {
                    Cell cell = sheet.getRow(i).getCell(j);
                    Object value;
                    try {
                        if (cell == null)
                            value = null;
                        else
                            value = getValueFrom(cell, cell.getCellType());
                        value = castValue(value, (Type) columns.get(j).get(1));
                        row.add(value);
                    } catch (Exception e) {
                        if (cell != null)
                            cell.setCellStyle(redStyle);
                        hasInvalidData = true;
                    }
                }
                if (hasInvalidData)
                    ok = false;
                else
                    data.add(row);
            }
            inputStream.close();
            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
            if (ok)
                return List.of(data, "Nhập dữ liệu thành công.");
            else
                return List.of(data, "Những dữ liệu không hợp lệ đã được bỏ qua.\nVui lòng mở file để xem những dữ liệu không hợp lệ được đánh dấu màu đỏ.");
        } catch (Exception e) {
            return List.of(List.of(), "Đã xảy ra lỗi khi đọc file Excel. Vui lòng đóng file hoặc kiểm tra định dạng file.");
        }
    }

    public static File openFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn file để lấy dữ liệu");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XLSX files", "xlsx");
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setCurrentDirectory(Objects.requireNonNull(Resource.getPathFromResource("")).toFile());

        int userChoice = fileChooser.showOpenDialog(Main.getFrames()[0]);
        if (userChoice != JFileChooser.APPROVE_OPTION) {
            return null;
        }
        File file = fileChooser.getSelectedFile();
        if (!file.getAbsolutePath().toLowerCase().endsWith(EXCEL_EXTENSION)) {
            file = new File(file.getAbsolutePath() + EXCEL_EXTENSION);
        }
        if (!file.exists()) {
            JOptionPane.showMessageDialog(Main.getFrames()[0], "File không tồn tại.");
            return null;
        }
        return file;
    }

    public static Object getValueFrom(Cell cell, CellType type) {
        return switch (type) {
            case STRING, BLANK, _NONE, ERROR -> cell.getStringCellValue();
            case NUMERIC -> cell.getNumericCellValue();
            case BOOLEAN -> cell.getBooleanCellValue();
            case FORMULA -> getValueFrom(cell, cell.getCachedFormulaResultType());
        };
    }

	public static Object castValue(Object value, Type type) throws ClassCastException {
        return switch (type) {
            case STRING -> {
                if (value == null)
                    yield "";
                yield (String) value;
            }
            case NUMERIC -> {
                if (value == null)
                    yield 0;
                yield Double.parseDouble((String) value);
            }
            case BOOLEAN -> {
                if (value == null)
                    yield false;
                yield Boolean.parseBoolean((String) value);
            }
        };
    }

	public enum Type {
        STRING, NUMERIC, BOOLEAN
    }
}
