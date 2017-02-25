package org.parabot.core.ui.newui.models;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.parabot.core.Core;
import org.parabot.core.desc.ServerDescription;
import org.parabot.core.settings.Configuration;
import org.parabot.core.ui.newui.BotUI;
import org.parabot.environment.api.utils.StringUtils;

import java.io.IOException;

/**
 * @author Fryslan, JKetelaar
 */
public class ServerItem extends AnchorPane {

    @FXML
    private AnchorPane serverSelectorItemPane;
    @FXML
    private Label      versionLabel;
    @FXML
    private Label      descriptionLabel;
    @FXML
    private Label      authorsLabel;
    @FXML
    private Label      nameLabel;

    private ServerDescription serverDescription;

    public ServerItem() {
    }

    public void setServerDescription(ServerDescription serverDescription) {
        FXMLLoader fxmlLoader = new FXMLLoader(ServerItem.class.getResource("/storage/ui/server/item_control.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.serverDescription = serverDescription;

        this.setName();
        this.setAuthors();
        this.setVersion();
        this.setDescription();
    }

    private void setVersion() {
        this.versionLabel.setText(String.format("V%.2f", serverDescription.getRevision()));
    }

    private void setDescription() {
        this.descriptionLabel.setText(serverDescription.getDescription());
    }

    private void setAuthors() {
        if (serverDescription.getAuthors().length > 0) {
            this.authorsLabel.setText(StringUtils.implode(", ", serverDescription.getAuthors()));
        } else {
            this.authorsLabel.setText(Configuration.BOT_TITLE);
        }
    }

    private void setName() {
        this.nameLabel.setText(serverDescription.getServerName());
    }

    public AnchorPane getServerSelectorItemPane() {
        return serverSelectorItemPane;
    }

    public void setServerSelectorItemPane(AnchorPane serverSelectorItemPane) {
        this.serverSelectorItemPane = serverSelectorItemPane;
    }

    @FXML
    private void selectServer(MouseEvent event) {
        Stage stage = (Stage) serverSelectorItemPane.getScene().getWindow();
        Core.getInjector().getInstance(BotUI.class).switchState(BotUI.ViewState.GAME, stage);
    }
}
