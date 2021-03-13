package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.DTO.PetDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = dtoToPet(petDTO);
        Customer owner = null;
        //check if it has any owner
        //if it's true, assign it to pet instance

        if((Long) petDTO.getOwnerId() != 0) {
            owner = customerService.getCustomer(petDTO.getOwnerId());
            pet.setOwner(owner);
        }
        pet = petService.savePet(pet);

        if(owner != null){
            System.out.println("OWNER ID IS " + owner.getId());
            owner.addPet(pet);
        }

        return petToDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.getPet(petId);
        return petToDTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<PetDTO> listDTO = new ArrayList<>();
        List<Pet> petList = petService.getAllPets();

        for(Pet p : petList){
            PetDTO dto = petToDTO(p);
            listDTO.add(dto);
        }
        return listDTO;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<PetDTO> listDTO = new ArrayList<>();
//        Customer customer = customerService.getCustomerByPetId(ownerId);
        List<Pet> petList = petService.getPetsByOwnerId(ownerId);


        for(Pet p : petList){
            PetDTO dto = petToDTO(p);
            listDTO.add(dto);
        }

        return listDTO;
    }

    private PetDTO petToDTO(Pet pet){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        if(pet.getOwner() != null){
            petDTO.setOwnerId(pet.getOwner().getId());
        }
        return petDTO;
    }

    private Pet dtoToPet(PetDTO petDTO){
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }
}
