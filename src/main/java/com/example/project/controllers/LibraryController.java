package com.example.project.controllers;


import com.example.project.dao.LibraryRepository;
import com.example.project.entity.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/libraries")
public class LibraryController {
    @Autowired
    private LibraryRepository libraryRepository;

     @GetMapping("")
    public String getAllLibraries(Model model) {
        model.addAttribute("libraries", libraryRepository.findAll());
        
        // Calculate and add the book count to the model
         Long bookCount = libraryRepository.count(); // Using Spring Data JPA's count() method
        model.addAttribute("bookCount", bookCount != null ? bookCount : 0L); // Set default as 0L
        
        return "libraries";
    }

    
  

    @GetMapping("/add")
    public String addLibraryForm(Model model) {
        model.addAttribute("library", new Library());
        return "add-library";
    }

    @PostMapping("/add")
    public String addLibrary(@ModelAttribute("library") Library library, BindingResult result) {
         validateLibraryFields(library, result);
        
        if (result.hasErrors()) {
            return "add-library";
        }

        
        libraryRepository.save(library);
        return "redirect:/libraries";
    }

    @GetMapping("/edit/{id}")
    public String editLibraryForm(@PathVariable Long id, Model model) {
        Library library = libraryRepository.findById(id)
                                  .orElseThrow(() -> new IllegalArgumentException("Invalid library ID"));
        model.addAttribute("library", library);
        return "edit-library";
    }

    @PostMapping("/edit/{id}")
    public String editLibrary(@PathVariable Long id, @ModelAttribute("library") Library library, BindingResult result) {
        validateLibraryFields(library, result);
        
        
        if (result.hasErrors()) {
            return "edit-library";
        }
        
        
        library.setId(id);
        libraryRepository.save(library);
        return "redirect:/libraries";
    }

    @GetMapping("/delete/{id}")
    public String deleteLibrary(@PathVariable Long id) {
        libraryRepository.deleteById(id);
        return "redirect:/libraries";
    }
    
    private void validateLibraryFields(Library library, BindingResult result) {
        if (library.getName().trim().isEmpty()) {
            result.rejectValue("name", "error.name", "Field cannot be empty");
        }

        if (library.getLocation().trim().isEmpty()) {
            result.rejectValue("location", "error.location", "Field cannot be empty");
        }
    }
}