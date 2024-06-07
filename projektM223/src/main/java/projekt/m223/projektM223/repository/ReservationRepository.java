package projekt.m223.projektM223.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import projekt.m223.projektM223.model.Reservation;

public interface ReservationRepository extends MongoRepository<Reservation, String> {
    Reservation findByPublicKey(String publicKey);
    Reservation findByPrivateKey(String privateKey);
}
