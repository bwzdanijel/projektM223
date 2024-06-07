package projekt.m223.projektM223.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationModel {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String from;
    private String to;
    private int room;
    private String comment;
    private String[] memberList;
    private String publicCode;
    private String privateCode;
}
