package bars;

import characters.Hero;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import quests.Quest;

public class QuestBar {
    private final Hero hero;
    private final Pane pane;
    private Button questBar;

    /**
     * Vytvorí questBar
     * @param hero hráčová postava
     * @param pane hracia plocha
     */
    public QuestBar(Hero hero, Pane pane) {
        this.hero = hero;
        this.pane = pane;
        this.questBar = new Button("Quest Bar");
    }

    /**
     * Vratí Button ktorý reprezentuje questBar
     * @return questBar Button
     */
    public Button getQuestBar() {
        return this.questBar;
    }


    /**
     * Vytvorí VBox ktorý zobrazí zobrazí, všetky aktuálne hráčove úlohy
     */
    public void openQuestBar() {
        VBox popup = new VBox(10);
        popup.setPadding(new Insets(15));
        popup.setStyle("-fx-background-color: rgba(0,0,0,0.8); -fx-background-radius: 10;");
        popup.setAlignment(Pos.CENTER);

        javafx.scene.control.Label text = new Label("Quest Bar:");
        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-background-color: white;");
        popup.getChildren().add(cancelButton);
        cancelButton.setCancelButton(true);
        cancelButton.setOnAction(e -> {
            this.pane.getChildren().remove(popup);
        });
        text.setTextFill(Color.WHITE);
        popup.getChildren().add(text);



        for (Quest quest : this.hero.getQuests().values()) {
            Label tx = new Label(quest.getQuestInfo() + quest.getCurrentState());
            tx.setTextFill(Color.RED);
            popup.getChildren().add(tx);
        }

        popup.setMinWidth(400);
        popup.setMinHeight(300);
        popup.setLayoutX(200);
        popup.setLayoutY(150);

        this.pane.getChildren().add(popup);
    }
}

