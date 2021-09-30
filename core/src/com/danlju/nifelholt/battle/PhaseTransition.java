package com.danlju.nifelholt.battle;

public class PhaseTransition {

    private BattlePhase fromPhase;

    private BattlePhase toPhase;

    public PhaseTransition(BattlePhase fromPhase, BattlePhase toPhase) {
        this.fromPhase = fromPhase;
        this.toPhase = toPhase;
    }

    public BattlePhase getFromPhase() {
        return fromPhase;
    }

    public BattlePhase getToPhase() {
        return toPhase;
    }

    public boolean match(BattlePhase fromPhase, BattlePhase toPhase) {
        return this.fromPhase == fromPhase && this.toPhase == toPhase;
    }
}
