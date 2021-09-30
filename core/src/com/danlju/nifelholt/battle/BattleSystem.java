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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    private List<PhaseTransition> allowedTransitions = new ArrayList<>();

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

        allowedTransitions = Arrays.asList(
                new PhaseTransition(BattlePhase.INIT, BattlePhase.GET_READY),
                new PhaseTransition(BattlePhase.GET_READY, BattlePhase.FIGHT),
                new PhaseTransition(BattlePhase.FIGHT, BattlePhase.AWAITING_ACTION)
        );
    }

    @Override
    public void updateEntity(Entity entity, float delta) {

        if (entity == activeEntity) {

        }

        if (phase == BattlePhase.INIT && System.currentTimeMillis() - initTime >= afterInitDelay) {

            setPhase(BattlePhase.GET_READY);
            Gdx.app.log("BattleSystem", "Get ready!");
            lastRoll = System.currentTimeMillis();

        } else if (phase == BattlePhase.GET_READY) {

            if (!initiativeRollsDone()) {

                if (!hasRolled(entity) && System.currentTimeMillis() - lastRoll >= rollDelayMs) {
                    initiativeRoll(entity);
                    lastRoll = System.currentTimeMillis();
                }
            } else {

                updateTurnOrder();

                Gdx.app.log("BattleSystem", "Initiative rolls done: ");

                Gdx.app.log("BattleSystem", "Turn order: " +
                        turnOrder.stream().map(e -> e.get(DndCharComponent.class).getName()).collect(Collectors.joining(" -> ")));

                setPhase(BattlePhase.FIGHT);
            }
        } else if (phase == BattlePhase.FIGHT) {

        }
    }

    private void setPhase(BattlePhase newPhase) {

        if (allowedTransitions.stream().noneMatch(t -> t.match(this.phase, newPhase))) {
            throw new IllegalStateException("Transition not allowed " + this.phase + " -> " + newPhase);
        }

        this.phase = newPhase;

        Gdx.app.log("DEBUG", "Battle-phase set to " + newPhase);
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

    private boolean entityIsAlive(Entity entity) {
        return entity.get(DndStatsComponent.class).getHitPoints() > 0;
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

    public void updateTurnOrder() {
        turnOrder = getEntities().stream().filter(this::entityIsAlive).sorted(Comparator.comparingInt(this::entityInitiative).reversed()).collect(Collectors.toList());
    }

    private int entityInitiative(Entity e) {
        BattleComponent bc = e.get(BattleComponent.class);
        return bc.initiative;
    }
}
