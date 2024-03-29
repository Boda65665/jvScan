package org.example.jvscan.Controllers;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import node.entity.Entity;
import org.example.jvscan.Services.NodeApiServise;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    NodeApiServise nodeApiServise = new NodeApiServise();
    @GetMapping
    public String scanGet(){
        return "main";
    }
    @PostMapping
    public String scanPost(HttpServletRequest request){
        String scanData = request.getParameter("scanTxt");
        int scanObj = Integer.parseInt(request.getParameter("scanObj"));
        switch (scanObj){
            case 1:return "redirect:/block/"+scanData;
            case 2:return "redirect:/transaction/"+scanData;
            case 3:return "redirect:/address/"+scanData;
            default:return "main";
        }

    }
    @GetMapping("block/{idData}")
    public String searchBlock(@PathVariable String idData, Model model){
        Entity.Block block = null;
        try {
            int numberBlock = Integer.parseInt(idData);
            block=nodeApiServise.getBlock(numberBlock);
        }
        //search по хэш
        catch (Exception err){
            block=nodeApiServise.getBlock(idData);
        }
        model.addAttribute("block", block);
        return (block==null)?"not_found":"block_found";
    }
    @GetMapping("transaction/{hash}")
    public String searchTransaction(@PathVariable String hash,Model model){
        Entity.Transaction transaction = nodeApiServise.getTransaction(hash);
        model.addAttribute("transaction",transaction);
        return (transaction==null)?"not_found":"transaction_found";
    }
    @GetMapping("address/{addressString}")
    public String searchAddress(@PathVariable String addressString,Model model){
        Entity.Address address = nodeApiServise.getAddress(addressString);
        model.addAttribute("address",address);
        return (address==null)?"not_found":"address_found";
    }
}
