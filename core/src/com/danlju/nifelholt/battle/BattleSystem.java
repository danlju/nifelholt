package com.danlju.nifelholt.battle;

import com.badlogic.gdx.Gdx;
import com.danlju.nifelholt.BattleLog;
import com.danlju.nifelholt.ecs.ComponentMatcher;
import com.danlju.nifelholt.ecs.Entity;
import com.danlju.nifelholt.ecs.Event;
import com.danlju.nifelholt.ecs.SubSystem;
import com.danlju.nifelholt.entities.DndCharComponent;
import com.danlju.nifelholt.entities.DndStatsComponent;
import com.danlju.nifelholt.rng.Dice;
import com.danlju.nifelholt.stats.Modifiers;

import java.util.List;

/***
 * System for handling turn-based combat
 */
public class BattleSystem extends SubSystem {

    private BattlePhase phase = BattlePhase.INIT;

    private Entity activeEntity;

    List<Entity> turnOrder;

    private long lastRoll = 0;
    private int rollDelayMs = 300;
    private int afterInitDelay = 3000;
    private long initTime = 0;

    public BattleSystem(int rollDelayMs) {
        this.rollDelayMs = rollDelayMs;
    }

    @Override
    public void initialize() {

        super.initialize();
        this.setEntities(
                world().entities(ComponentMatcher.forType(BattleComponent.class))
        );
        Gdx.app.log("BattleSystem", "initialize()");

        initTime = System.currentTimeMillis();
    }

    @Override
    public void updateEntity(Entity entity, float delta) {

        if (phase == BattlePhase.INIT && System.currentTimeMillis() - initTime >= afterInitDelay) {
            phase = BattlePhase.GET_READY;
            Gdx.app.log("BattleSystem", "Get ready!");
            lastRoll = System.currentTimeMillis();

        } else if (phase == BattlePhase.GET_READY) {

            if (!initiativeRollsDone()) {

                if (!hasRolled(entity) && System.currentTimeMillis() - lastRoll >= rollDelayMs) {
                    Gdx.app.log("BattleSystem", entity.getId() + " roll");
                    initiativeRoll(entity);
                    lastRoll = System.currentTimeMillis();
                }
            } else {

                phase = BattlePhase.FIGHT; // TODO
                Gdx.app.log("DEBUG", "Initiative rolls done");
            }
        }
    }

    private boolean hasRolled(Entity entity) {
        return entity.get(BattleComponent.class).initiative != BattleComponent.NOT_ROLLED;
    }

    private boolean initiativeRollsDone() {
        return this.getEntities().stream().noneMatch(
                e -> e.get(BattleComponent.class).initiative == BattleComponent.NOT_ROLLED
        );
    }

    @Override
    public void handleEvent(Event event) {
        if (event.getType() == Event.Type.ENTITY_ADDED) {
            this.setEntities(
                    world().entities(ComponentMatcher.forType(BattleComponent.class))
            );
        }
    }

    public void updateTurnOrder() {
        // TODO
        turnOrder = this.getEntities();
    }

    private boolean entityIsAlive(Entity entity) {
        return false;
    }

    public void initiativeRoll(Entity entity) {

        DndCharComponent cc = entity.get(DndCharComponent.class);
        DndStatsComponent sc = entity.get(DndStatsComponent.class);
        BattleComponent bc = entity.get(BattleComponent.class);

        int initiativeRoll = Dice.d20();
        int modifier = Modifiers.get(sc.get("dex"));

        bc.initiative = initiativeRoll + modifier;

        BattleLog.print(cc.getName() + " rolled initiative " + bc.initiative + " (" +  initiativeRoll + " " + modifier + ")");
    }
}
