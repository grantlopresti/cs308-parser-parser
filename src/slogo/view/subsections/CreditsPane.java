package slogo.view.subsections;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class CreditsPane {

  public HBox getNode() {
    HBox credits = new HBox();

    Label programCredits = new Label("Slogo - Parser Team 10");
    Label teamCredits = new Label("Created by: Alex Xu, Amjad Syedibrahim, Grant "
        + "LoPresti, and Max Smith");
    Label classCredits = new Label("CS 308 - Spring 2020 - Duvall");

    Region creditsSpacingLeft = new Region();
    HBox.setHgrow(creditsSpacingLeft, Priority.ALWAYS);

    Region creditsSpacingRight = new Region();
    HBox.setHgrow(creditsSpacingRight, Priority.ALWAYS);

    credits.getChildren().addAll(
        programCredits,
        creditsSpacingLeft,
        teamCredits,
        creditsSpacingRight,
        classCredits
    );

    credits.setAlignment(Pos.CENTER);
    credits.setPadding(new Insets(3));

    return credits;
  }
}
