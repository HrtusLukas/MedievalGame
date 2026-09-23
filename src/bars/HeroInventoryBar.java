package bars;

import characters.GameObject;
import characters.Hero;
import items.IDropable;
import items.IUsable;
import items.Item;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class HeroInventoryBar {

    /**
     * Vytvorý VBox ktorý v sebe zobrazí všetky predmety ktoré má hráč vo svojom inventári
     * @param pane plocha
     * @param hero hráčová postava
     * @param inventory hráčov inventár
     */
    public HeroInventoryBar(Pane pane, Hero hero, HashMap<String, Item> inventory) {
        VBox popup = new VBox(10);
        popup.setPadding(new Insets(15));
        popup.setStyle("-fx-background-color: rgba(0,0,0,0.8); -fx-background-radius: 10;");
        popup.setAlignment(Pos.CENTER);

        Label text = new Label("Inventory:");
        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-background-color: white;");
        popup.getChildren().add(cancelButton);
        cancelButton.setCancelButton(true);
        cancelButton.setOnAction(e -> {
            pane.getChildren().remove(popup);
        });
        text.setTextFill(Color.WHITE);
        popup.getChildren().add(text);



        for (Item item : inventory.values()) {
            if (item instanceof GameObject obj) {
                ImageView iv = obj.getImage();

                iv.setOnMouseClicked(e -> {
                    if (e.getButton() == MouseButton.PRIMARY) {
                        if (item instanceof IUsable usable) {
                            hero.useItem(item);
                            popup.getChildren().remove(item.getImage());
                        }
                    } else {
                        if (item instanceof IDropable) {
                            popup.getChildren().remove(item.getImage());
                            hero.drop(item, pane);
                        }
                    }
                });

                popup.getChildren().add(iv);
            }
        }

        popup.setMinWidth(400);
        popup.setMinHeight(300);
        popup.setLayoutX(200);
        popup.setLayoutY(150);

        pane.getChildren().add(popup);
    }
}
