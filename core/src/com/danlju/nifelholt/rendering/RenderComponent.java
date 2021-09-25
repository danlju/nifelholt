package com.danlju.nifelholt.rendering;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.danlju.nifelholt.ecs.Component;

public class RenderComponent implements Component {

    private TextureRegion currentFrame;

    public RenderComponent(TextureRegion textureRegion) {
        this.currentFrame = textureRegion;
    }

    public TextureRegion currentFrame() {
        return currentFrame;
    }
}
