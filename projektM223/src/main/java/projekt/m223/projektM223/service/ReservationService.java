package projekt.m223.projektM223.service;

import org.springframework.beans.factory.annotation.Autowired;
import projekt.m223.projektM223.model.ReservationModel;
import org.springframework.stereotype.Service;
import projekt.m223.projektM223.repository.ReservationRepository;


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

    public ReservationModel saveReservation(ReservationModel reservation) {
        return reservationRepository.save(reservation);
    }

    public ReservationModel updateReservationByPrivateCode(String privateCode, ReservationModel reservationDetails) {
        ReservationModel reservation = reservationRepository.findByPrivateCode(privateCode);
        if (reservation != null) {
            reservation.setDate(reservationDetails.getDate());
            reservation.setFrom(reservationDetails.getFrom());
            reservation.setTo(reservationDetails.getTo());
            reservation.setRoom(reservationDetails.getRoom());
            reservation.setComment(reservationDetails.getComment());
            reservation.setMemberList(reservationDetails.getMemberList());
            return reservationRepository.save(reservation);
        }
        return null;
    }

    public void deleteReservation(ReservationModel reservation) {
        reservationRepository.delete(reservation);
    }
}

