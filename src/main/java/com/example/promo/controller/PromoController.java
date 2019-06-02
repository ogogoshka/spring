package com.example.promo.controller;

import com.example.promo.model.Bonus;
import com.example.promo.model.Promo;
import com.example.promo.repository.BonusRepository;
import com.example.promo.repository.PromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PromoController {

    @Autowired
    private PromoRepository promoRepository;

    @Autowired
    private BonusRepository bonusRepository;

    @GetMapping("/{promoId}")
    public String getBonusesByPromoId(
            @PathVariable Long promoId,
            @RequestParam(name = "filter", required = false, defaultValue = "") String filter,
            Model model) {

        Promo promo = promoRepository.getOne(promoId);
        model.addAttribute("promoId", promoId);
        model.addAttribute("promoName", promo.getNamePromo());

        Iterable<Bonus> bonuses = bonusRepository.findBonusByPromo(promo);
        int totalBonusCounts = 0;
        for (Bonus bonus : bonuses) {
            totalBonusCounts += bonus.getInStock();
        }
        model.addAttribute("totalBonusCounts", totalBonusCounts);

        if (filter != null && !filter.isEmpty()) {
            model.addAttribute("filter", filter);
            bonuses = bonusRepository.findBonusByPromoAndBonusNameContaining(promo, filter);
        } else {
            bonuses = bonusRepository.findBonusByPromoAndInStockGreaterThan(promo, 0);
        }
        model.addAttribute("bonuses", bonuses);

        return "index";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> updateBonusCount(
            @RequestParam(name = "bonusId", required = false, defaultValue = "") Long bonusId,
            @RequestParam(name = "promoId", required = false, defaultValue = "") Long promoId) {
        Map<String, String> records = new HashMap<>();

        Bonus currentBonus = bonusRepository.getOne(bonusId);
        currentBonus.setInStock(currentBonus.getInStock() - 1);
        bonusRepository.save(currentBonus);
        records.put("bonusName", currentBonus.getBonusName());
        records.put("bonusCount", String.valueOf(currentBonus.getInStock()));

        Promo promo = promoRepository.getOne(promoId);
        Iterable<Bonus> bonuses = bonusRepository.findBonusByPromo(promo);

        int totalBonusCounts = 0;
        if (!((List<Bonus>) bonuses).isEmpty()) {
            for (Bonus b : bonuses) {
                totalBonusCounts += b.getInStock();
            }
            records.put("totalBonusCount", String.valueOf(totalBonusCounts));
        }

        return records;
    }
}