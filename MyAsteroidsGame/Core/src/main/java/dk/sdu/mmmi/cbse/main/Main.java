package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {

	public static void main(String[] args) {

		int width = 1600;
		int height = 1000;

		Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
		cfg.setTitle("Asteroids");
		cfg.setWindowedMode(width, height);
		cfg.setWindowSizeLimits(width, height, width, height);
		cfg.setResizable(false);

		new Lwjgl3Application(new Game(), cfg);


	}

}
