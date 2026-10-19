package org.example.speedrun;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;

public class HelloController {

    ObservableList<Producto> listaProductos = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Producto, Integer> tcCantidad;
    @FXML
    private TextField tfDescripcion;
    @FXML
    private Label lblTitulo;
    @FXML
    private TextField tfCantidad;
    @FXML
    private TableColumn<Producto, String> tcDescipcion;
    @FXML
    private ColumnConstraints idPanelFormulario;
    @FXML
    private TableView<Producto> tbProducto;
    @FXML
    private Button btnEliminar;
    @FXML
    private ProgressBar pbProgressBar;
    @FXML
    private Button btnAnadir;
    @FXML
    private Slider sldSlider;
    @FXML
    private TextField tfPrecioUnitario;
    @FXML
    private BorderPane base;
    @FXML
    private TableColumn<Producto, Double> tcPrecio;

    @FXML
    public void initialize() {

        tbProducto.setItems(listaProductos);

        tcDescipcion.setCellValueFactory(cellData -> cellData.getValue().descripcionProperty());
        tcCantidad.setCellValueFactory(cellData -> cellData.getValue().cantidadProperty().asObject());
        tcPrecio.setCellValueFactory(cellData -> cellData.getValue().precioProperty().asObject());

        btnEliminar.disableProperty().bind(tbProducto.getSelectionModel().selectedItemProperty().isNull());


        BooleanBinding estaBien = Bindings.createBooleanBinding(() -> {

            Boolean textoMal = (tfDescripcion.getText().trim().isEmpty());

            Boolean numerosBien = true;

            try {

                Integer.parseInt(tfCantidad.getText());
                Double.parseDouble(tfPrecioUnitario.getText());

            } catch (NumberFormatException nfe) {
                numerosBien = false;
            }

            return !textoMal && numerosBien;

        }, tfDescripcion.textProperty(), tfCantidad.textProperty(), tfPrecioUnitario.textProperty()

        );

        btnAnadir.disableProperty().bind(estaBien.not());


        tfCantidad.setOnKeyTyped(event -> {

            try {

                Integer.parseInt(tfCantidad.getText());

            } catch (NumberFormatException nfe) {
                tfCantidad.deletePreviousChar();
            }

        });

        tfPrecioUnitario.setOnKeyTyped(event -> {

            if (!tfPrecioUnitario.getText().matches("[0-9]*[.]?[0-9]*")){
                tfPrecioUnitario.deletePreviousChar();
            }

        });


        base.setOnKeyPressed(event -> {

            if (event.getCode() == KeyCode.ENTER) {
                listaProductos.add(new Producto(tfDescripcion.getText(), Integer.parseInt(tfCantidad.getText()), Double.parseDouble(tfPrecioUnitario.getText())));
                event.consume();

            } else if (event.getCode() == KeyCode.ESCAPE) {
                tfPrecioUnitario.setText("");
                tfCantidad.setText("");
                tfDescripcion.setText("");
                event.consume();
            }

        });


        pbProgressBar.progressProperty().bind(sldSlider.valueProperty().divide(100));


    }

    @FXML
    public void onEliminar(ActionEvent actionEvent) {

        listaProductos.remove(tbProducto.getSelectionModel().getSelectedItem());

    }

    @FXML
    public void onAnadir(ActionEvent actionEvent) {

        listaProductos.add(new Producto(tfDescripcion.getText(), Integer.parseInt(tfCantidad.getText()), Double.parseDouble(tfPrecioUnitario.getText())));

    }
}
