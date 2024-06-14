package com.bagas.updaters;

import com.bagas.dto.DescriptionDTO;
import com.bagas.entities.Description;

public class DescriptionUpdater {

    public static Description updateDescription(Description source, DescriptionDTO target) {
        source.setWidth(target.getWidth());
        source.setLength(target.getLength());
        source.setWeight(target.getWeight());

        return source;
    }
}
