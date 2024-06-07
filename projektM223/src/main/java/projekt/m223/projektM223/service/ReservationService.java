package projekt.m223.projektM223.service;

import projekt.m223.projektM223.model.ReservationModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class ReservationService {

    private final List<ReservationModel> reservations = new CopyOnWriteArrayList<>();

    public ReservationModel saveReservation(ReservationModel reservation) {
        reservations.add(reservation);
        return reservation;
    }

    public ReservationModel findByPublicCode(String publicCode) {
        for (ReservationModel reservation : reservations) {
            if (reservation.getPublicCode().equals(publicCode)) {
                return reservation;
            }
        }
        return null;
    }

    public ReservationModel findByPrivateCode(String privateCode) {
        for (ReservationModel reservation : reservations) {
            if (reservation.getPrivateCode().equals(privateCode)) {
                return reservation;
            }
        }
        return null;
    }

    // Add methods for other functionalities as needed
    // (e.g., update reservation, cancel reservation)
}
