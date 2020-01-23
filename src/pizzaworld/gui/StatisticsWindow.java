package pizzaworld.gui;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pizzaworld.gui.elements.BottomPanel;
import pizzaworld.gui.elements.TopPanel;
import pizzaworld.logic.Game;

/**
 *
 * @author André Heinen
 */
public class StatisticsWindow extends Stage {
    
    private final Game game;
    private final Stage stage;

    public StatisticsWindow(Game game, Stage stage) {
        this.game = game;
        this.stage = stage;
    }
    
    public Parent showElement() {
        
        GridPane gp = new GridPane();
        ColumnConstraints cc0 = new ColumnConstraints(142.0);
        ColumnConstraints cc1 = new ColumnConstraints(72.0);
        RowConstraints rc0 = new RowConstraints(27.0);
        gp.setMinSize(360.0, 520.0);
        gp.setMaxSize(360.0, 520.0);
        gp.addRow(0, new Text("Verkaufte Pizzen"));
        gp.add(new Text("Gestern"), 1, 1);
        gp.add(new Text("Woche"), 2, 1);
        gp.add(new Text("Gesamt"), 3, 1);
        for (int i = 0; i < game.getProducts().getDishes().size(); i++) {
            gp.add(new Text(game.getProducts().getDishes().get(i).getName()), 0, i + 2);
            gp.add(new Text(String.valueOf(game.getPlayers()[0].getStatistics().getSoldUnitsYesterday(i, game.getDay()))), 1, i + 2);
            gp.add(new Text(String.valueOf(game.getPlayers()[0].getStatistics().getSoldUnitsWeek(i, game.getDay()))), 2, i + 2);
            gp.add(new Text(String.valueOf(game.getPlayers()[0].getStatistics().getSoldUnitsAllTime(i, game.getDay()))), 3, i + 2);
        }
        gp.add(new Text("Gesamt"), 0, gp.getRowCount());
        gp.add(new Text(String.valueOf(game.getPlayers()[0].getStatistics().getSoldUnitsYesterdayTotal(game.getDay()))), 1, gp.getRowCount() - 1);
        gp.add(new Text(String.valueOf(game.getPlayers()[0].getStatistics().getSoldUnitsWeekTotal(game.getDay()))), 2, gp.getRowCount() - 1);
        gp.add(new Text(String.valueOf(game.getPlayers()[0].getStatistics().getSoldUnitsAllTimeTotal(game.getDay()))), 3, gp.getRowCount() - 1);
        gp.getColumnConstraints().addAll(cc0, cc1, cc1, cc1);
        for (int i = 0; i < gp.getRowCount(); i++) {
            gp.getRowConstraints().addAll(rc0);
        }
        gp.setAlignment(Pos.CENTER);
        for (int i = 1; i < gp.getChildren().size(); i++) {
            if (i % 4 != 0) {
                GridPane.setHalignment(gp.getChildren().get(i), HPos.RIGHT);
            }
        }
        
        Button[] buttons = {
            new Button("Umsatz"),
            new Button("Zurück")
        };

        for (Button button : buttons) {
            button.setMinSize(180.0, 80.0);
            button.setMaxSize(180.0, 80.0);
        }

        FlowPane fp = new FlowPane(buttons);
        fp.setMinSize(360.0, 80.0);
        fp.setMaxSize(360.0, 80.0);
        
        buttons[0].setOnAction((ActionEvent) -> {
            
        });

        buttons[1].setOnAction((ActionEvent) -> {
            stage.getScene().setRoot(new MainWindow(game, stage).showElement());
        });

        GridPane gpRoot = new GridPane();
        gpRoot.addRow(0, new TopPanel(game).showElement());
        gpRoot.addRow(1, gp);
        gpRoot.addRow(2, fp);
        gpRoot.addRow(3, new BottomPanel(game).showElement());
        gpRoot.setGridLinesVisible(true);

        return gpRoot;
    }
}