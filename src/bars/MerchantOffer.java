package bars;

import characters.GameObject;
import characters.Hero;
import items.Item;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class MerchantOffer {

    /**
     * Vytvorí BorderPane ktorý v sebe má tovar ktorý obchodník predáva, zobrazuje počet mincí hráča a má tlačítko cancel
     * @param pane hracia plocha
     * @param hero hráčova postava
     * @param offer obchodníková ponuka predmetov
     */
    public MerchantOffer(Pane pane, Hero hero, ArrayList<Item> offer) {
        BorderPane shopPane = new BorderPane();
        shopPane.setStyle("-fx-background-color: rgba(0,0,0,0.8); -fx-background-radius: 10;");
        shopPane.setMinWidth(400);
        shopPane.setMinHeight(300);
        shopPane.setLayoutX(200);
        shopPane.setLayoutY(150);


        HBox header = new HBox(10);
        header.setPadding(new Insets(15));
        header.setAlignment(Pos.CENTER_LEFT);
        header.getChildren().add(hero.getCoinBar());
        Text coins = new Text(Integer.toString(hero.getCoins()));
        coins.setFill(Color.WHITE);
        header.getChildren().add(coins);

        Label text = new Label("Offer:");
        text.setTextFill(Color.WHITE);
        HBox.setHgrow(text, Priority.ALWAYS);
        text.setMaxWidth(Double.MAX_VALUE);
        text.setAlignment(Pos.CENTER);
        header.getChildren().add(text);

        shopPane.setTop(header);


        TilePane grid = new TilePane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPrefTileWidth(80);
        grid.setPrefTileHeight(80);
        grid.setAlignment(Pos.CENTER);

        for (Item item : offer) {
            if (item instanceof GameObject obj) {
                ImageView iv = obj.getImage();

                iv.setOnMouseClicked(e -> {
                    if (hero.getCoins() >= item.getPrice()) {
                        hero.buyItem(item);
                        coins.setText(Integer.toString(hero.getCoins()));
                        offer.remove(item);
                        grid.getChildren().remove(iv);
                    } else {
                        pane.getChildren().remove(shopPane);
                    }
                });

                grid.getChildren().add(iv);
            }
        }

        shopPane.setCenter(grid);


        HBox footer = new HBox();
        footer.setPadding(new Insets(10, 15, 15, 15));
        footer.setAlignment(Pos.CENTER_RIGHT);

        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-background-color: white;");
        cancelButton.setCancelButton(true);
        cancelButton.setOnAction(e -> pane.getChildren().remove(shopPane));

        footer.getChildren().add(cancelButton);
        shopPane.setBottom(footer);

        pane.getChildren().add(shopPane);
    }
}
