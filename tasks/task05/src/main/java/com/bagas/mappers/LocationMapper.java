package com.bagas.mappers;

import com.bagas.entities.Location;

import static com.bagas.util.ParameterChecker.checkParameter;

public class LocationMapper {

    public static Location createLocation(Long id, Integer room, Integer place) {
        return Location.builder()
                .id(checkParameter(id))
                .room(checkParameter(room))
                .place(checkParameter(place))
                .build();
    }
}
