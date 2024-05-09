package com.bagas.services;

import com.bagas.entities.Computer;
import com.bagas.entities.Location;
import com.bagas.exceptions.ComputerNotFoundException;
import com.bagas.exceptions.LocationNotFoundException;
import com.bagas.repositories.interfaces.ComputerRepository;
import com.bagas.repositories.interfaces.LocationRepository;

import java.util.List;

import static com.bagas.constants.ExceptionMessageConstants.COMPUTER_NOT_FOUND_MESSAGE;
import static com.bagas.constants.ExceptionMessageConstants.LOCATION_NOT_FOUND_MESSAGE;

public class ComputerService {

    private ComputerRepository computerRepository;

    private LocationRepository locationRepository;

    public ComputerService(ComputerRepository computerRepository, LocationRepository locationRepository) {
        this.computerRepository = computerRepository;
        this.locationRepository = locationRepository;
    }

    public List<Computer> getByRoom(Integer room) {
        return computerRepository.findByRoom(room);
    }

    public List<Computer> getAllComputers() {
        return computerRepository.findAll();
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Computer getComputerById(Long id) throws ComputerNotFoundException {
        return computerRepository.findById(id)
                .orElseThrow(() -> new ComputerNotFoundException(COMPUTER_NOT_FOUND_MESSAGE));
    }

    public Location getLocationById(Long id) throws LocationNotFoundException {
        return locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(LOCATION_NOT_FOUND_MESSAGE));
    }

    public void createComputer(Computer computer) {
        computerRepository.save(computer);
    }

    public void createLocation(Location location) {
        locationRepository.save(location);
    }

    public void updateComputer(Computer computer) {
        computerRepository.update(computer);
    }

    public void updateLocation(Location location) {
        locationRepository.update(location);
    }

    public void deleteComputerById(Long id) {
        computerRepository.deleteById(id);
    }

    public void deleteLocationById(Long id) {
        locationRepository.deleteById(id);
    }
}
