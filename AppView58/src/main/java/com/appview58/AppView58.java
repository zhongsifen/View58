package com.appview58;

import com.gluonhq.particle.application.ParticleApplication;
import javafx.scene.Scene;
import static org.controlsfx.control.action.ActionMap.actions;

public class AppView58 extends ParticleApplication {

    public AppView58() {
        super("Gluon Desktop Application");
    }

    @Override
    public void postInit(Scene scene) {
        scene.getStylesheets().add(AppView58.class.getResource("style.css").toExternalForm());

        setTitle("Gluon Desktop Application");

        getParticle().buildMenu("File -> [signin,---, exit]", "Help -> [about]");
        
        getParticle().getToolBarActions().addAll(actions("signin"));
    }
}