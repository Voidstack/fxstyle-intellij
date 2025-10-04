package com.enosistudio.fxstyleintellij;

import com.enosistudio.fxstyleintellij.tools.FileWatcher;
import com.enosistudio.fxstyleintellij.tools.FxTooltipDebugCss;
import com.enosistudio.generated.R;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;

public class HelloController {
    @FXML
    private TextField fileLabel;
    @FXML
    private Label welcomeText;

    private Optional<File> selectedFile = Optional.empty();

    @FXML
    public void initialize() throws IOException {
        File file = Paths.get("src/main/resources/" + R.css.mainCss.getResourcePath()).toFile();
        selectedFile = Optional.of(file);
        fileLabel.setText(file.getAbsolutePath());
        onApplyStyle();
        FileWatcher.watchFile(selectedFile.get().toPath(), this::onApplyStyle);
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void onDebugCss(ActionEvent actionEvent) {
        CheckMenuItem item = (CheckMenuItem) actionEvent.getSource();
        boolean enabled = item.isSelected();

        if (enabled) {
            FxTooltipDebugCss.install(MainApplication.getMainScene());
        } else {
            FxTooltipDebugCss.uninstall(MainApplication.getMainScene());
        }
    }

    @FXML
    private void onChooseFile(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();

        // On ne veut pas démarrer dans le répertoire courant, mais dans le répertoire des ressources
        // FUCK LE TARGET
        Path path = Paths.get("src/main/resources/" + R.css._self).toAbsolutePath();

        fileChooser.setInitialDirectory(path.toFile());
        fileChooser.setTitle("Sélectionner un fichier");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers CSS", "*.css"));
        File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        if (file != null) {
            selectedFile = Optional.of(file);
            fileLabel.setText(file.getAbsolutePath());
            onApplyStyle();
            FileWatcher.watchFile(selectedFile.get().toPath(), this::onApplyStyle);
        }
    }

    public void onApplyStyle() {
        selectedFile.ifPresent(file -> {
            System.out.println(LocalDateTime.now() + ", Application du style : " + file.getName());
            Application.setUserAgentStylesheet(file.toURI().toString());
        });
    }
}