package com.bagas.updaters;

import com.bagas.dto.GroupDTO;
import com.bagas.entities.Group;

public class GroupUpdater {

    public static void updateGroup(Group source, GroupDTO target) {
        source.setName(target.getName());
    }
}
