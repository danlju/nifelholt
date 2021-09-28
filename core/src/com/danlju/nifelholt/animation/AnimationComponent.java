package com.danlju.nifelholt.animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.danlju.nifelholt.ecs.Component;

public class AnimationComponent implements Component {

    public TextureRegion currentFrame;

    public AnimationComponent(TextureRegion currentFrame) {
        this.currentFrame = currentFrame;
    }

    public TextureRegion currentFrame() {
        return currentFrame;
    }

}
