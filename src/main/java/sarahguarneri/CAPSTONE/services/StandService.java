package sarahguarneri.CAPSTONE.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sarahguarneri.CAPSTONE.entities.Stand;
import sarahguarneri.CAPSTONE.exceptions.NotFoundException;
import sarahguarneri.CAPSTONE.payloads.stand.NewStandDTO;
import sarahguarneri.CAPSTONE.repositories.StandDAO;

import java.util.List;
import java.util.UUID;

@Service
public class StandService {

    @Autowired
    private StandDAO standDAO;

    public List<Stand> getAllStands(){
        return standDAO.findAll();
    }

    public Stand save(NewStandDTO body){
        Stand newStand = new Stand();

        newStand.setCost(body.cost());
        newStand.setDimensions(body.dimensions());

        return standDAO.save(newStand);
    }

    public Stand findById(UUID id){
        return standDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Stand findByIdAndUpdate(UUID id, Stand body){
        Stand found = findById(id);

        found.setDimensions(body.getDimensions());
        found.setCost(body.getCost());

        return standDAO.save(found);
    }

    public void findByIdAndDelete(UUID id){
        Stand found = findById(id);
        standDAO.delete(found);
    }
}
