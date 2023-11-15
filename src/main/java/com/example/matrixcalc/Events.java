package com.example.matrixcalc;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Дополнительные функции для программы

public class Events {

    //Функция создания диалогового окна для вывода ошибки

    static void error(String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    //Функция чтения матрицы с csv файла

    static void eddMatrix(Label lable,List<List<Integer>> data){
        try {
            data.clear();
            Stage primaryStage = new Stage();
            File selectedFile;
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            selectedFile = fileChooser.showOpenDialog(primaryStage);
            String csvFile = selectedFile.getPath();
            String line;
            String csvSplitBy = ";";
            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(csvSplitBy);
                    List<Integer> row = new ArrayList<>();
                    for (String value : values) {
                        row.add(Integer.parseInt(value));
                    }
                    data.add(row);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Вывод двумерного массива чисел

            String matrix ="";
            for (List<Integer> row : data) {
                for (int value : row) {
                    matrix+= value + " ";
                }
                matrix+="\n";
            }
            lable.setText(matrix);
        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setHeight(400);
            alert.setContentText("Пожалуйства, загрузите матрицы файлом типа '.csv'. Проверьте, является ли файл матрицей и не сохранён ли он в формате utf-8");
            alert.showAndWait();
        }

    }

    // Вспомогательная функция для получения минора матрицы

    public static List<List<Integer>> getMinorMatrix(List<List<Integer>> matrix, int row, int column) {
        List<List<Integer>> minorMatrix = new ArrayList<>();

        int rows = matrix.size();
        int columns = matrix.get(0).size();

        for (int i = 0; i < rows; i++) {
            if (i != row) {
                List<Integer> minorRow = new ArrayList<>();
                for (int j = 0; j < columns; j++) {
                    if (j != column) {
                        minorRow.add(matrix.get(i).get(j));
                    }
                }
                minorMatrix.add(minorRow);
            }
        }
        return minorMatrix;
    }

    //Функция расчёта определителя на основе минора
    public static int calculateDeterminant(List<List<Integer>> matrix) {
        int rows = matrix.size();
        int columns = matrix.get(0).size();
        if (rows == 1) {
            return matrix.get(0).get(0);
        } else {}

        int determinant = 0;

        // Разложение определителя по первой строке
        for (int j = 0; j < columns; j++) {
            int sign = (j % 2 == 0) ? 1 : -1; // Знак определителя меняется чередованием
            int minorDeterminant = calculateDeterminant(getMinorMatrix(matrix, 0, j));
            determinant += sign * matrix.get(0).get(j) * minorDeterminant;
        }

        return determinant;
    };

    // Вспомогательная функция для вывода матрицы

    public static void printMatrix(List<List<Integer>> matrix,Label label) {
        String matrixdata ="";
        for (List<Integer> row : matrix) {
            for (int value : row) {
                matrixdata+="\t" +value + " ";
            }
            matrixdata+="\n";
        }
        label.setText(matrixdata);
    }
}
