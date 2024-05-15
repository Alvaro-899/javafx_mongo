package com.empresa.javafx_mongo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.function.Consumer;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        String url="mongodb+srv://lector:lector@cluster0.hbso5zx.mongodb.net/";
        MongoClient conexion=MongoClients.create(url);
        MongoDatabase database=conexion.getDatabase("practica1");
        MongoCollection<Document> collection=database.getCollection("clientes");

        ObservableList<String> resultado = FXCollections.observableArrayList();
        collection.find().forEach((Consumer<? super Document>) document -> resultado.add(document.toJson()));

        // Crear el ListView
        ListView<String> listView = new ListView<>(resultado);

        // Crear una escena y agregar el ListView a un layout
        VBox root = new VBox();
        root.getChildren().add(listView);
        Scene scene = new Scene(root, 400, 300);

        // Configurar y mostrar la ventana
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Resultados de la consulta MongoDB");
        stage.show();
    }
}
