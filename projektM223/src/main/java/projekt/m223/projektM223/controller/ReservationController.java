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

    @GetMapping("/reservation")
    public String reservationForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "reservation";
    }

    @PostMapping("/reservation")
    public String reservationSubmit(@ModelAttribute Reservation reservation, @RequestParam("date") String dateString, Model model) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateString);
        reservation.setDate(date);

        Reservation savedReservation = reservationService.createReservation(reservation);
        model.addAttribute("reservation", savedReservation);
        return "result";
    }

    @GetMapping("/reservation/view")
    public String viewReservation(@RequestParam("publicKey") String publicKey, Model model) {
        Reservation reservation = reservationService.getReservationByPublicKey(publicKey);
        if (reservation != null) {
            model.addAttribute("reservation", reservation);
            return "viewReservation";
        } else {
            model.addAttribute("error", "Reservation not found.");
            return "error";
        }
    }

    @GetMapping("/reservation/edit")
    public String editReservationForm(@RequestParam("privateKey") String privateKey, Model model) {
        Reservation reservation = reservationService.getReservationByPrivateKey(privateKey);
        if (reservation != null) {
            model.addAttribute("reservation", reservation);
            return "editReservation";
        } else {
            model.addAttribute("error", "Reservation not found.");
            return "error";
        }
    }

    @PostMapping("/reservation/edit")
    public String editReservationSubmit(@RequestParam("privateKey") String privateKey, @ModelAttribute Reservation reservation, @RequestParam("date") String dateString, Model model) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateString);
        reservation.setDate(date);

        Reservation updatedReservation = reservationService.updateReservationByPrivateKey(privateKey, reservation);
        if (updatedReservation != null) {
            model.addAttribute("reservation", updatedReservation);
            return "result";
        } else {
            model.addAttribute("error", "Failed to update reservation.");
            return "error";
        }
    }

}
