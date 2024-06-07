package projekt.m223.projektM223.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import projekt.m223.projektM223.model.ReservationModel;

@Repository
public interface ReservationRepository extends MongoRepository<ReservationModel, String> {
    ReservationModel findByPublicCode(String publicCode);
    ReservationModel findByPrivateCode(String privateCode);
}
