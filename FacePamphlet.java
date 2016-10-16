/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;

import java.awt.event.*;

import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
	
		profileDataBase = new FacePamphletDatabase();
		canvas = new FacePamphletCanvas();
		//initializing interactors
		
		changeStatusField = new JTextField (TEXT_FIELD_SIZE);
		add(changeStatusField, WEST);
		changeStatusField.addActionListener(this);
		changeStatusButton = new JButton ("Change Status");
		add(changeStatusButton, WEST);
		
		changePictureField = new JTextField (TEXT_FIELD_SIZE);
		add(changePictureField, WEST);
		changePictureField.addActionListener(this);
		changePictureButton = new JButton ("Change Picture");
		add(changePictureButton, WEST);
		
		addFriendField = new JTextField (TEXT_FIELD_SIZE);
		add(addFriendField, WEST);
		addFriendField.addActionListener(this);
		addFriendButton = new JButton ("Add Friend");
		add(addFriendButton, WEST);
		
		name = new JLabel ("Name");
		add(name, NORTH);
		nameField = new JTextField (TEXT_FIELD_SIZE);
		add(nameField, NORTH);
		nameField.addActionListener(this);
		
		addButton = new JButton("Add");
		add(addButton, NORTH);
		
		deleteButton = new JButton("Delete");
		add(deleteButton, NORTH);
		
		lookupButton = new JButton ("Lookup");
		add(lookupButton, NORTH);
		
		addActionListeners();
		
		add(canvas);
	
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
	
    	/*temporary implementation used to make sure that buttons and fields are working correctly */

    	/*interactor for change status field---------------------------------------------------*/
    	if (e.getSource() == changeStatusField){
    		if(currentProfile ==null){
    			canvas.showMessage("Please select a profile to change the status of");
    		}
    		else{
    		currentProfile.setStatus(changeStatusField.getText());
    		canvas.displayProfile(currentProfile);
    		canvas.showMessage("Status updated to "+changeStatusField.getText());
    		}
    	}
    	
    	if (e.getSource() == changeStatusButton){
    		if(currentProfile ==null){
    			canvas.showMessage("Please select a profile to change the status of");
    		}
    		else{
    		currentProfile.setStatus(changeStatusField.getText());
    		canvas.displayProfile(currentProfile);
    		canvas.showMessage("Status updated to "+changeStatusField.getText());
    		}
    	}
    	
    	
    	/*interactor for change picture field---------------------------------------------------*/
    	if (e.getSource() == changePictureField){
    		if(currentProfile ==null){
    			canvas.showMessage("Please select a profile to change the image of");
    		}
    		else {
    			GImage image = null;
    			 try {
    			 image = new GImage(changePictureField.getText());
    			 } catch (ErrorException ex) {
    			 // Code that is executed if the filename cannot be opened.
    				 throw new ErrorException(ex);
    			 }
    			 currentProfile.setImage(image);
    			 canvas.displayProfile(currentProfile);
    			 canvas.showMessage("The current profile has been updated to the image specified");
    		}
    	}   
    	
    	if (e.getSource() == changePictureButton){
    		if(currentProfile ==null){
    			canvas.showMessage("Please select a profile to change the image of");
    		}
    		else {
    			GImage image = null;
    			 try {
    			 image = new GImage(changePictureField.getText());
    			 } catch (ErrorException ex) {
    			 // Code that is executed if the filename cannot be opened.
    				 throw new ErrorException(ex);
    			 }
    			 currentProfile.setImage(image);
    			 canvas.displayProfile(currentProfile);
    			 canvas.showMessage("The current profile has been updated to the image specified");
    		}
    	}
    	
    	/*interactor for add friend text field---------------------------------------------------*/
    	if (e.getSource() == addFriendField){
    		if(currentProfile==null){
    			canvas.showMessage("Please select a profile to add a friend to");
    		}
    		
    		else if (profileDataBase.getProfile(addFriendField.getText())==null){
    			canvas.showMessage("There is no profile with this name that exists in the database");
    		}
    		
    		else {
    			FacePamphletProfile friendProfile = profileDataBase.getProfile(addFriendField.getText());
    			if(currentProfile.addFriend(addFriendField.getText())==true){
    				currentProfile.addFriend(addFriendField.getText());
    				friendProfile.addFriend(currentProfile.getName());
    				canvas.displayProfile(currentProfile);
    				canvas.showMessage(currentProfile.getName()+"and "+ friendProfile.getName()+" are now friends.");
    			}
    		}
    	}
    	
    	if (e.getSource() == addFriendButton){
    		if(currentProfile==null){
    			canvas.showMessage("Please select a profile to add a friend to");
    		}
    		
    		else if (profileDataBase.getProfile(addFriendField.getText())==null){
    			canvas.showMessage("There is no profile with this name that exists in the database");
    		}
    		
    		else {
    			FacePamphletProfile friendProfile = profileDataBase.getProfile(addFriendField.getText());
    			if(currentProfile.addFriend(addFriendField.getText())==true){
    				currentProfile.addFriend(addFriendField.getText());
    				friendProfile.addFriend(currentProfile.getName());
    				canvas.displayProfile(currentProfile);
    				canvas.showMessage(currentProfile.getName()+"and "+ friendProfile.getName()+" are now friends.");
    			}
    		}
    	}
    	
    	//--------------------------------------------------------------------------------------------
    	String enteredName = nameField.getText();	
    	
    	/*Add Button is clicked---------------------------------------------------*/
    	if (e.getSource() == nameField){
    		if(profileDataBase.containsProfile(enteredName)){
    			FacePamphletProfile profile = profileDataBase.getProfile(enteredName);
    			canvas.displayProfile(profile);
    			canvas.showMessage("A profile with the name " + enteredName + "already exists.");
    			currentProfile = profile;
    		}
    		else{
    			FacePamphletProfile newProfile = new FacePamphletProfile(enteredName);
    			profileDataBase.addProfile(newProfile);
    			canvas.displayProfile(newProfile);
    			canvas.showMessage("New profile created");
    			currentProfile = newProfile;
    		}		
    	}
    	
    	if (e.getSource() == addButton){
    		if(profileDataBase.containsProfile(enteredName)){
    			FacePamphletProfile profile = profileDataBase.getProfile(enteredName);
    			canvas.displayProfile(profile);
    			canvas.showMessage("A profile with the name " + enteredName + "already exists.");
    			currentProfile = profile;
    		}
    		else{
    			FacePamphletProfile newProfile = new FacePamphletProfile(enteredName);
    			profileDataBase.addProfile(newProfile);
    			canvas.displayProfile(newProfile);
    			canvas.showMessage("New profile created");
    			currentProfile = newProfile;
    		}	
    	}
    	
 
    	/*Delete Button is clicked---------------------------------------------------*/
    	if (e.getSource() == deleteButton){
    		if (profileDataBase.containsProfile(enteredName)==true){
    			canvas.removeAll();
    			profileDataBase.deleteProfile(enteredName);
    			currentProfile = null;
    			canvas.showMessage("Profile of "+enteredName+ " deleted.");
    			
    		}
    		else{
    			canvas.showMessage("A profile with the name "+enteredName+" does not exist.");
    		}
    	}
    	
    	/*Lookup button is clicked---------------------------------------------------*/
    	if (e.getSource() == lookupButton){
    		if(profileDataBase.containsProfile(enteredName)){
    			canvas.removeAll();
    			currentProfile = profileDataBase.getProfile(enteredName);
    			canvas.displayProfile(currentProfile);
    			canvas.showMessage("Displaying "+enteredName);
    		}
    		else {
    			currentProfile =null;
    			canvas.showMessage("A profile with the name " + enteredName +" does not exist.");
    		}

    	}
	}


	/*private instance variables*/ 
	
	// variable for change status textfield
	private JTextField changeStatusField;
	
	//variable for change status button
	private JButton changeStatusButton;
	
	// variable for change status textfield
	private JTextField changePictureField;
		
	//variable for change status button
	private JButton changePictureButton;
		
	// variable for change status textfield
	private JTextField addFriendField;
		
	//variable for change status button
	private JButton addFriendButton;
	
	//variable for name label
	private JLabel name;
	
	//variable for name textfield
	private JTextField nameField;
	
	//variable for add button
	private JButton addButton;
	
	//variable for delete button
	private JButton deleteButton;
	
	//variable for lookup button
	private JButton lookupButton;
	
	//variable for database
	private FacePamphletDatabase profileDataBase;
	
	//variable to store current profile
	private FacePamphletProfile currentProfile = null;

	//variable for FacePamphletCanvas
	private FacePamphletCanvas canvas;
}	
	