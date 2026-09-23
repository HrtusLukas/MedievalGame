package bars;

import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class GameOver {

    /**
     * Vytvorí VBox ktory za zobrazi po smrti hráča
     * @param onFinished kod ktorý sa vykony po uplinutí zobrazenia
     * @return vbox s obrazovkou konca hry
     */
    public static VBox getView(Runnable onFinished) {
        Text text = new Text("YOU DIED");
        text.setFont(Font.font("Arial", FontWeight.BOLD, 64));
        text.setFill(Color.RED);

        VBox vbox = new VBox(text);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: black;");
        vbox.setPrefSize(800, 600);

        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(e -> onFinished.run());
        pause.play();

        return vbox;
    }
}
