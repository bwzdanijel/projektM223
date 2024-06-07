package projekt.m223.projektM223.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekt.m223.projektM223.model.GenerateKeys;
import projekt.m223.projektM223.model.Reservation;
import projekt.m223.projektM223.repository.ReservationRepository;

import java.security.KeyPair;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation createReservation(Reservation reservation) {
        KeyPair keyPair = GenerateKeys.generateKeyPair();
        String publicKey = GenerateKeys.encodeKey(keyPair.getPublic().getEncoded());
        String privateKey = GenerateKeys.encodeKey(keyPair.getPrivate().getEncoded());
        reservation.setPublicKey(publicKey);
        reservation.setPrivateKey(privateKey);
        return reservationRepository.save(reservation);
    }

    public Reservation getReservationByPublicKey(String publicKey) {
        return reservationRepository.findByPublicKey(publicKey);
    }

    public Reservation getReservationByPrivateKey(String privateKey) {
        return reservationRepository.findByPrivateKey(privateKey);
    }

    public Reservation updateReservationByPrivateKey(String privateKey, Reservation newReservationData) {
        Reservation reservation = reservationRepository.findByPrivateKey(privateKey);

        return null;
    }
}
