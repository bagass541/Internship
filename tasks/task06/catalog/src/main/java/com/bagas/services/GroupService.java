package com.bagas.services;

import com.bagas.dto.GroupDTO;
import com.bagas.entities.Group;
import com.bagas.exceptions.GroupNotFoundException;
import com.bagas.mappers.GroupMapper;
import com.bagas.repositories.GroupRepository;
import com.bagas.updaters.GroupUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.bagas.constants.ExceptionMessageConstants.GROUP_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepo;

    public List<GroupDTO> getAll() {
        return groupRepo.findAll().stream()
                .map(GroupMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    public GroupDTO create(GroupDTO groupDTO) {
        Group group = groupRepo.save(GroupMapper.INSTANCE.toEntity(groupDTO));
        return GroupMapper.INSTANCE.toDTO(group);
    }

    public GroupDTO update(Long id, GroupDTO groupDTO) {
        Group groupToUpdate = groupRepo.findById(id)
                .orElseThrow(() -> new GroupNotFoundException(GROUP_NOT_FOUND_MESSAGE));

        GroupUpdater.updateGroup(groupToUpdate, groupDTO);

        return GroupMapper.INSTANCE.toDTO(groupRepo.save(groupToUpdate));
    }

    public void deleteById(Long id) {
        groupRepo.deleteById(id);
    }
}
