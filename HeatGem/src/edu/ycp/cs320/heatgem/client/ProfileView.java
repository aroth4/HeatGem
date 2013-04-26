package edu.ycp.cs320.heatgem.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.NumberLabel;

import edu.ycp.cs320.heatgem.shared.User;
import edu.ycp.cs320.heatgem.shared.UserProfile;

public class ProfileView extends Composite {
	
	private Image UserFace;
	private NumberLabel<Integer> numberLevelLabel;
	private NumberLabel<Integer> experienceLabel;
	private Label nameProfileLabel;

	private int EXP, Level, wins, losses, highScore, callback;
	private String usr, email, username, password;
	/**
	 * @param args
	 */
	public ProfileView() {
		
		RPC.userService.getUserProfile(username, password, email, Level, EXP, losses, wins, highScore, new AsyncCallback <UserProfile>() {
			@Override
			public void onFailure(Throwable caught) {
				// show error message
				//errorLabel.setText("Could not communicate with server?");
			}

			@Override
			public void onSuccess(UserProfile result) {
				if (result == null) {
					// show error message
					//errorLabel.setText("Unknown username/password");
				} else {
					// TODO: switch to home page
					//errorLabel.setText("Success! Should switch to home page");
					//HomePage view = new HomePage();
					//HeatGem.setView(view);
					//view.activate(); // do any required dynamic initialization
				}
			}
		});
		
		LayoutPanel layoutPanel = new LayoutPanel();
		initWidget(layoutPanel);
		layoutPanel.setSize("568px", "472px");
		
		Button btnNewButton = new Button("New button");
		layoutPanel.add(btnNewButton);
		layoutPanel.setWidgetLeftWidth(btnNewButton, 72.0, Unit.PX, 81.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(btnNewButton, 384.0, Unit.PX, 30.0, Unit.PX);
		
		Button btnNewButton_1 = new Button("New button");
		layoutPanel.add(btnNewButton_1);
		layoutPanel.setWidgetLeftWidth(btnNewButton_1, 402.0, Unit.PX, 81.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(btnNewButton_1, 384.0, Unit.PX, 30.0, Unit.PX);
		
		Label lblProfileView = new Label("Profile View");
		layoutPanel.add(lblProfileView);
		layoutPanel.setWidgetLeftWidth(lblProfileView, 244.0, Unit.PX, 99.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblProfileView, 23.0, Unit.PX, 18.0, Unit.PX);
		
		//Image image = new Image("Defeat.png");
		UserFace = HeatGem.getImage("Defeat.png");
		
		UserFace.setAltText("you");
		layoutPanel.add(UserFace);
		layoutPanel.setWidgetLeftWidth(UserFace, 337.0, Unit.PX, 179.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(UserFace, 81.0, Unit.PX, 197.0, Unit.PX);
		
		Label lblName = new Label("Alice");
		layoutPanel.add(lblName);
		layoutPanel.setWidgetLeftWidth(lblName, 20.0, Unit.PX, 56.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblName, 81.0, Unit.PX, 18.0, Unit.PX);
		
		Label lblLevel = new Label("Level");
		layoutPanel.add(lblLevel);
		layoutPanel.setWidgetLeftWidth(lblLevel, 20.0, Unit.PX, 56.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblLevel, 128.0, Unit.PX, 18.0, Unit.PX);
		
		Label lblExperience = new Label("Experience");
		layoutPanel.add(lblExperience);
		layoutPanel.setWidgetLeftWidth(lblExperience, 17.0, Unit.PX, 70.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblExperience, 181.0, Unit.PX, 18.0, Unit.PX);
		
		Label lblWins = new Label("Wins");
		layoutPanel.add(lblWins);
		layoutPanel.setWidgetLeftWidth(lblWins, 20.0, Unit.PX, 56.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblWins, 236.0, Unit.PX, 18.0, Unit.PX);
		
		Label lblLosses = new Label("Losses");
		layoutPanel.add(lblLosses);
		layoutPanel.setWidgetLeftWidth(lblLosses, 20.0, Unit.PX, 56.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblLosses, 284.0, Unit.PX, 18.0, Unit.PX);
		
		numberLevelLabel = new NumberLabel<Integer>();
		numberLevelLabel.setValue(Level);
		layoutPanel.add(numberLevelLabel);
		layoutPanel.setWidgetLeftWidth(numberLevelLabel, 105.0, Unit.PX, 131.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(numberLevelLabel, 128.0, Unit.PX, 18.0, Unit.PX);
		
		experienceLabel = new NumberLabel<Integer>();
		experienceLabel.setValue(EXP);
		layoutPanel.add(experienceLabel);
		layoutPanel.setWidgetLeftWidth(experienceLabel, 105.0, Unit.PX, 131.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(experienceLabel, 181.0, Unit.PX, 18.0, Unit.PX);
		
		nameProfileLabel = new Label("New label");
		layoutPanel.add(nameProfileLabel);
		layoutPanel.setWidgetLeftWidth(nameProfileLabel, 105.0, Unit.PX, 56.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(nameProfileLabel, 81.0, Unit.PX, 18.0, Unit.PX);
		
		NumberLabel<Integer> winsLabel = new NumberLabel<Integer>();
		winsLabel.setValue(wins);
		layoutPanel.add(winsLabel);
		layoutPanel.setWidgetLeftWidth(winsLabel, 105.0, Unit.PX, 131.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(winsLabel, 236.0, Unit.PX, 18.0, Unit.PX);
		
		NumberLabel<Integer> lossesLabel = new NumberLabel<Integer>();
		lossesLabel.setValue(losses);
		layoutPanel.add(lossesLabel);
		layoutPanel.setWidgetLeftWidth(lossesLabel, 105.0, Unit.PX, 131.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lossesLabel, 284.0, Unit.PX, 18.0, Unit.PX);
		
		
	}
}
