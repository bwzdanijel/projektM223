package projekt.m223.projektM223.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import projekt.m223.projektM223.model.Reservation;
import projekt.m223.projektM223.service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/start.html")
    public String startPage() {
        return "start";
    }

    @GetMapping("/create_reservation_page")
    public String showCreateReservationPage() {
        return "create_reservation_page"; // No need to include .html suffix
    }


}
