package com.laurencetrippen.fxutils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SceneManager {

	private static SceneManager sceneManager = null;
	
	public static SceneManager getSceneManager() {
		if (sceneManager == null) {
			sceneManager = new SceneManager();
		}
		return sceneManager;
	}
	
	private Map<String, Scene> sceneMap;
	private Map<String, Object> controllerMap;
	
	private SceneManager() {
		this.sceneMap = new HashMap<>();
		this.controllerMap = new HashMap<>();
	}
	
	public void loadScene(String id, String path, double width, double height) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			@SuppressWarnings("static-access")
			Parent root = fxmlLoader.load(getClass().getResource(path).openStream());
			if (root != null) {
				this.sceneMap.put(id, new Scene(root, width, height));
				this.controllerMap.put(id, fxmlLoader.getController());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Scene getSceneById(String id) {
		return this.sceneMap.get(id);
	}
	
	public Object getControllerById(String id) {
		return this.controllerMap.get(id);
	}
}