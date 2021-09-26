package com.danlju.nifelholt.battle;

import com.danlju.nifelholt.ecs.Component;

public class PartyMemberComponent implements Component {

    private String partyId;

    public PartyMemberComponent(String partyId) {
        this.partyId = partyId;
    }
}
