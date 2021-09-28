package com.danlju.nifelholt.rendering;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.danlju.nifelholt.entities.CharClass;

import java.util.HashMap;
import java.util.Map;

// TODO: this is crap
public class TextureHandler {

    public Texture fighterTexture = new Texture("fighter.png");
    public Texture rogueTexture = new Texture("rogue.png");
    public Texture sorcererTexture = new Texture("sorcerer.png");
    public Texture rangerTexture = new Texture("ranger.png");
    public Texture orcTexture = new Texture("orc.png");
    public Texture d20BlankTexture = new Texture("d20_blank.png");

    public Map<CharClass, Texture> charClassTextureMap = new HashMap<>();

    public TextureHandler() {

        charClassTextureMap.put(CharClass.FIGHTER, fighterTexture);
        charClassTextureMap.put(CharClass.ROGUE, rogueTexture);
        charClassTextureMap.put(CharClass.RANGER, rangerTexture);
        charClassTextureMap.put(CharClass.SORCERER, sorcererTexture);
    }

    public TextureRegion getByCharClass(CharClass charClass) {
        if (!charClassTextureMap.containsKey(charClass)) {
            throw new IllegalArgumentException("Texture not found for " + charClass);
        }
        return new TextureRegion(charClassTextureMap.get(charClass));
    }



    public void dispose() {

        fighterTexture.dispose();
        rogueTexture.dispose();
        sorcererTexture.dispose();
        rangerTexture.dispose();
        orcTexture.dispose();
        d20BlankTexture.dispose();
    }

}
