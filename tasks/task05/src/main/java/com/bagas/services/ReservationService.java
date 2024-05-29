package com.bagas.services;

import com.bagas.entities.Reservation;
import com.bagas.exceptions.ReservationNotFoundException;
import com.bagas.repositories.interfaces.ReservationRepository;

import java.util.List;

import static com.bagas.constants.ExceptionMessageConstants.RESERVATION_NOT_FOUND_MESSAGE;

public class ReservationService {



    private ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    public Reservation getById(Long id) throws ReservationNotFoundException {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(RESERVATION_NOT_FOUND_MESSAGE));
    }

    public void create(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public void update(Reservation reservation) {
        reservationRepository.update(reservation);
    }

    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }
}
