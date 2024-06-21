package projekt.m223.projektM223.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekt.m223.projektM223.model.ReservationModel;
import projekt.m223.projektM223.repository.ReservationRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public ReservationModel findByPublicCode(String publicCode) {
        return reservationRepository.findByPublicCode(publicCode);
    }

    public ReservationModel findByPrivateCode(String privateCode) {
        return reservationRepository.findByPrivateCode(privateCode);
    }

    public ReservationModel saveReservation(ReservationModel reservation) throws Exception {
        validateReservation(reservation);
        return reservationRepository.save(reservation);
    }

    public ReservationModel updateReservationByPrivateCode(String privateCode, ReservationModel reservation) throws Exception {
        ReservationModel existingReservation = reservationRepository.findByPrivateCode(privateCode);
        if (existingReservation == null) {
            throw new Exception("Reservation not found.");
        }
        validateReservation(reservation);
        existingReservation.setDate(reservation.getDate());
        existingReservation.setFrom(reservation.getFrom());
        existingReservation.setTo(reservation.getTo());
        existingReservation.setRoom(reservation.getRoom());
        existingReservation.setComment(reservation.getComment());
        existingReservation.setMemberList(reservation.getMemberList());
        return reservationRepository.save(existingReservation);
    }

    public void deleteReservation(ReservationModel reservation) {
        reservationRepository.delete(reservation);
    }

    private void validateReservation(ReservationModel reservation) throws Exception {
        LocalDate today = LocalDate.now();

        // Convert from java.util.Date to  LocalDate
        Instant instant = reservation.getDate().toInstant();
        LocalDate reservationDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

        if (reservationDate.isBefore(today)) {
            throw new Exception("Reservation date cannot be in the past.");
        }
    }
}
