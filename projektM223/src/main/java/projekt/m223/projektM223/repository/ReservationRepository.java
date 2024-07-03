package projekt.m223.projektM223.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import projekt.m223.projektM223.model.ReservationModel;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends MongoRepository<ReservationModel, String> {
    ReservationModel findByPublicCode(String publicCode);
    ReservationModel findByPrivateCode(String privateCode);

    @Query("{ 'room' : ?0, 'date' : ?1, '$or' : [ { 'from' : { '$lt' : ?3 }, 'to' : { '$gt' : ?2 } } ] }")
    List<ReservationModel> findConflictingReservations(int room, Date date, String from, String to);
}
