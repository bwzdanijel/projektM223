package projekt.m223.projektM223.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import projekt.m223.projektM223.model.ReservationModel;
import projekt.m223.projektM223.service.ReservationService;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/create")
    public String getCreateReservationPage(Model model) {
        model.addAttribute("newReservation", new ReservationModel());
        return "create_reservation_page";
    }

    @PostMapping("/create")
    public String createReservation(@ModelAttribute ReservationModel reservation, Model model) {
        String publicCode = generateRandomCode(20);
        String privateCode = generateRandomCode(20);
        reservation.setPublicCode(publicCode);
        reservation.setPrivateCode(privateCode);
        ReservationModel savedReservation = reservationService.saveReservation(reservation);
        model.addAttribute("reservation", savedReservation);
        model.addAttribute("publicCode", publicCode);
        model.addAttribute("privateCode", privateCode);
        return "reservation_details";
    }

    @PostMapping("/byPublicKey")
    public String getReservationByPublicKey(@RequestParam("publicCode") String publicCode, Model model) {
        ReservationModel reservation = reservationService.findByPublicCode(publicCode);
        if (reservation != null) {
            model.addAttribute("reservation", reservation);
            return "show_reservation_detail";
        } else {
            return "error_page"; // Handle not found scenario
        }
    }

    @PostMapping("/byPrivateKey")
    public String getReservationByPrivateKey(@RequestParam("privateCode") String privateCode, Model model) {
        ReservationModel reservation = reservationService.findByPrivateCode(privateCode);
        if (reservation != null) {
            model.addAttribute("reservation", reservation);
            return "edit_reservation_page";
        } else {
            return "error_page";
        }
    }

    @GetMapping("/edit/{privateCode}")
    public String showUpdateForm(@PathVariable("privateCode") String privateCode, Model model) {
        ReservationModel reservation = reservationService.findByPrivateCode(privateCode);
        if (reservation != null) {
            model.addAttribute("reservation", reservation);
            return "edit_reservation_page";
        } else {
            return "error_page";
        }
    }

    @PostMapping("/update/{privateCode}")
    public String updateReservation(@PathVariable("privateCode") String privateCode, @ModelAttribute("reservation") ReservationModel reservation, Model model) {
        ReservationModel updatedReservation = reservationService.updateReservationByPrivateCode(privateCode, reservation);
        if (updatedReservation != null) {
            model.addAttribute("reservation", updatedReservation);
            return "show_reservation_detail";
        } else {
            return "error_page";
        }
    }


    @GetMapping("/delete/{privateCode}")
    public String deleteReservation(@PathVariable("privateCode") String privateCode) {
        ReservationModel reservation = reservationService.findByPrivateCode(privateCode);
        if (reservation != null) {
            reservationService.deleteReservation(reservation);
            return "deleted_reservation_page";
        } else {
            return "error_page";
        }
    }


    private String generateRandomCode(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int choice = (int) (Math.random() * 2);
            if (choice == 0) {
                int randomChar = (int) (Math.random() * 26) + 'a';
                sb.append((char) randomChar);
            } else {
                int randomChar = (int) (Math.random() * 10) + '0';
                sb.append((char) randomChar);
            }
        }
        return sb.toString();
    }
}
