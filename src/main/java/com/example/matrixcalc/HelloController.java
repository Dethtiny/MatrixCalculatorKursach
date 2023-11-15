package com.example.matrixcalc;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class HelloController {
    private static final Logger logger = LogManager.getLogger(HelloController.class.getName());
    List<List<Integer>> data1 = new ArrayList<>();
    List<List<Integer>> data2 = new ArrayList<>();


//Инициализация объектов программы

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button choose1;

    @FXML
    private Button choose2;

    @FXML
    private Button minus;

    @FXML
    private Button multi;

    @FXML
    private Button opred1;

    @FXML
    private Button opred2;

    @FXML
    private Label m1;

    @FXML
    private Label m2;

    @FXML
    private Label m3;

    @FXML
    private Label opred1text;

    @FXML
    private Label opred2text;

    @FXML
    private Button plus;

    //Описание функций кнопок

    @FXML
    void initialize() {

        //Функция сложения матриц

        plus.setOnAction(event -> {
            if (data1.isEmpty() || data2.isEmpty()){
                Events.error("Пожалуйства, загрузите матрицы файлом типа '.csv'");
                logger.error("Не удалсь выполнить операцию сложения");
            }else if (data1.size() != data2.size()||data1.get(0).size() != data2.get(0).size()){
                Events.error("При сложении матрицы должны быть одного размера");
                logger.error("Не удалсь выполнить операцию сложения");
            }else {
                List<List<Integer>> resultMatrix = new ArrayList<>();
                int rows = data1.size();
                int columns = data1.get(0).size();
                for (int i = 0; i < rows; i++) {
                    List<Integer> row = new ArrayList<>();
                    for (int j = 0; j < columns; j++) {
                        row.add(data1.get(i).get(j) + data2.get(i).get(j));
                    }
                    resultMatrix.add(row);
                }
                Events.printMatrix(resultMatrix,m3);
            }
        });

        //Функция вычитания матриц

        minus.setOnAction(event ->{
            if (data1.isEmpty() || data2.isEmpty()){
                Events.error("Пожалуйства, загрузите матрицы файлом типа '.csv'");
                logger.error("Не удалсь выполнить операцию вычитания");
            }else if (data1.size() != data2.size()||data1.get(0).size() != data2.get(0).size()){
                Events.error("При вычитании матрицы должны быть одного размера");
                logger.error("Не удалсь выполнить операцию вычитания");
            }else {
                List<List<Integer>> resultMatrix = new ArrayList<>();
                int rows = data1.size();
                int columns = data1.get(0).size();
                for (int i = 0; i < rows; i++) {
                    List<Integer> row = new ArrayList<>();
                    for (int j = 0; j < columns; j++) {
                        row.add(data1.get(i).get(j)- data2.get(i).get(j));
                    }
                    resultMatrix.add(row);
                }
                Events.printMatrix(resultMatrix,m3);
            }
        });

        //Функция умножения матриц

        multi.setOnAction(event ->{
            if (data1.isEmpty() || data2.isEmpty()){
                Events.error("Пожалуйства, загрузите матрицы файлом типа '.csv'");
                logger.error("Не удалсь выполнить операцию умножения");
            }else {
                int rows1 = data1.size();
                int columns1 = data1.get(0).size();
                int columns2 = data2.get(0).size();

                // Проверка на соответствие размеров матриц для умножения
                if (columns1 != data2.size()) {
                    Events.error("Невозможно умножить матрицы: количество столбцов первой матрицы не равно количеству строк второй матрицы");
                    logger.error("Не удалсь выполнить операцию умножения");
                }
                else {
                    List<List<Integer>> resultMatrix = new ArrayList<>();

                    for (int i = 0; i < rows1; i++) {
                        List<Integer> row = new ArrayList<>();
                        for (int j = 0; j < columns2; j++) {
                            int sum = 0;
                            for (int k = 0; k < columns1; k++) {
                                sum += data1.get(i).get(k) * data2.get(k).get(j);
                            }
                            row.add(sum);
                        }
                        resultMatrix.add(row);
                    }
                    Events.printMatrix(resultMatrix,m3);}

            }
        });

        //Функция ввода первой матрицы из csv файла

        choose1.setOnAction(event ->{
            Events.eddMatrix(m1,data1);
            opred1text.setText("");
            logger.info("Загружена первая матрица");
        });

        //Функция ввода второй матрицы из csv файла

        choose2.setOnAction(event ->{
            Events.eddMatrix(m2,data2);
            opred2text.setText("");
            logger.info("Загружена вторая матрица");
        });

        //Функция расчёта определителя первой матрицы

        opred1.setOnAction(event ->{
            if (data1.isEmpty()){
                Events.error("Пожалуйства, загрузите матрицы файлом типа '.csv'");
                logger.error("Не удалсь найти определитель");
            } else if (data1.size()!=data1.get(0).size()) {
                Events.error("Определитель можно найти только у квадратной матрицы");
                logger.error("Не удалсь найти определитель");
            }else {
                opred1text.setText(String.valueOf(Events.calculateDeterminant(data1)));}
        });

        //Функция расчёта определителя второй матрицы

        opred2.setOnAction(event ->{
            if (data2.isEmpty()){
                Events.error("Пожалуйства, загрузите матрицы файлом типа '.csv'");
                logger.error("Не удалсь найти определитель");
            } else if (data2.size()!=data2.get(0).size()) {
                Events.error("Определитель можно найти только у квадратной матрицы");
                logger.error("Не удалсь найти определитель");
            }else {
                opred2text.setText(String.valueOf(Events.calculateDeterminant(data2)));}
        });
    }

}
