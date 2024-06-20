package projekt.m223.projektM223.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projekt.m223.projektM223.model.ReservationModel;
import projekt.m223.projektM223.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    @Autowired
    private final ReservationService reservationService;

    @GetMapping("/create")
    public String getCreateReservationPage(Model model) {
        model.addAttribute("newReservation", new ReservationModel());
        return "create_reservation_page";
    }

    @PostMapping("/create")
    public String createReservation(@ModelAttribute("newReservation") ReservationModel reservation, Model model) {
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

    @GetMapping("/createAnother")
    public String getCreateAnotherReservationPage(Model model) {
        model.addAttribute("newReservation", new ReservationModel());
        return "edit_reservation_page";
    }

    @PostMapping("/createAnother")
    public String createAnotherReservation(@ModelAttribute("newReservation") ReservationModel reservation, Model model) {
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
            return "error";
        }
    }

    @PostMapping("/update/{privateCode}")
    public String updateReservation(@PathVariable String privateCode, @ModelAttribute("reservation") ReservationModel reservation, Model model) {
        ReservationModel existingReservation = reservationService.findByPrivateCode(privateCode);
        if (existingReservation == null) {
            return "error_page";
        }
        existingReservation.setDate(reservation.getDate());
        existingReservation.setFrom(reservation.getFrom());
        existingReservation.setTo(reservation.getTo());
        existingReservation.setRoom(reservation.getRoom());
        existingReservation.setComment(reservation.getComment());
        existingReservation.setMemberList(reservation.getMemberList());
        reservationService.saveReservation(existingReservation);
        model.addAttribute("reservation", existingReservation);
        return "reservation_details";
    }

    @GetMapping("/{privateCode}/edit")
    public String editReservationByPrivateCode(@PathVariable String privateCode, Model model) {
        ReservationModel reservation = reservationService.findByPrivateCode(privateCode);
        if (reservation == null) {
            return "error_page";
        }
        model.addAttribute("reservation", reservation);
        return "edit_reservation_page";
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
