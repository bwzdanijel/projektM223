package projekt.m223.projektM223.service;

import org.springframework.beans.factory.annotation.Autowired;
import projekt.m223.projektM223.model.ReservationModel;
import org.springframework.stereotype.Service;
import projekt.m223.projektM223.repository.ReservationRepository;

import java.util.List;


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

}

