package sarahguarneri.CAPSTONE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sarahguarneri.CAPSTONE.entities.Stand;
import sarahguarneri.CAPSTONE.payloads.stand.NewStandDTO;
import sarahguarneri.CAPSTONE.payloads.stand.NewStandResponseDTO;
import sarahguarneri.CAPSTONE.services.StandService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/stand")
public class StandController {

    @Autowired
    private StandService standService;

    @GetMapping
    public List<Stand> getStands(){
        return standService.getAllStands();
    }

    @GetMapping("/{standId}")
    public Stand getStanById(@PathVariable UUID id){
        return standService.findById(id);
    }

    @PostMapping
    public NewStandResponseDTO saveStand(@RequestBody @Validated NewStandDTO body){
        Stand newStand = standService.save(body);
        return new NewStandResponseDTO(newStand.getId());
    }

    @PutMapping("/{standId}")
    public Stand findAndUpdate(@PathVariable UUID id, @RequestBody Stand body){
        return standService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{standId}")
    public void findAndDelete(@PathVariable UUID id){
        standService.findByIdAndDelete(id);
    }
}
