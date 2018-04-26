package me.afua.moviedirectorexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.Binding;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
public class MainController {
    @Autowired
    DirectorRepository directorRepo;

    @Autowired
    MovieRepository movieRepo;

    @RequestMapping("/")
    public String showIndex(Model model)
    {

//      Adding a new object in case a user wants to enter new director details
        model.addAttribute("aDirector",new Director());
        model.addAttribute("directorList",directorRepo.findAll());
        return "index";
    }

    @RequestMapping("/newdirector")
    public String addNewDirector(Model model)
    {
        model.addAttribute("aDirector",new Director());
//        return "directorfragments::addDirector";
        return "directorform";
    }

    @PostMapping("/savedirector")
    public String saveDirector(@Valid @ModelAttribute("aDirector") Director director, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "index";
        }
        directorRepo.save(director);
        return "redirect:/";
    }

    @RequestMapping("/editDirector")
    public String editDirector(HttpServletRequest request, Model model)
    {
        //Edit details about the director. Use the addDirector fragment.
        long id = new Long (request.getParameter("id"));
        model.addAttribute("aDirector",directorRepo.findById(id).get());
//        return "directorfragments::addDirector";
        return "directorform";
    }

    @RequestMapping("/newmovie")
    public String addMovie(Model model, HttpServletRequest request)
    {


        //Get a director ID (if there is one)
        String directorID = request.getParameter("directorid");

        Movie theMovie = new Movie();

        //        Associate a movie with a director if the director's ID is passed as a parameter
        if(directorID!=null)
            theMovie.setTheDirector(directorRepo.findById(new Long(directorID)).get());

        model.addAttribute("aMovie",theMovie);

//        return "moviefragments::addmovie";
        return "movieform";
    }


    @RequestMapping("/editmovie")
    public String editMovie(HttpServletRequest request, Model model)
    {
        //Edit details about the director. Use the addDirector fragment.
        long id = new Long (request.getParameter("id"));
        model.addAttribute("aMovie",movieRepo.findById(id).get());
//        return "directorfragments::addDirector";
        return "movieform";
    }

    @RequestMapping("/savemovie")
    public String saveMovie(@Valid @ModelAttribute("aMovie") Movie movie, BindingResult result)
    {
        //Save the movie that information was added about in /newmovie
        movieRepo.save(movie);
        return "redirect:/";
    }

    @RequestMapping("/showdirectors")
    public String showDirectors(Model model)
    {
        model.addAttribute("directorList",directorRepo.findAll());
        return "showdirectordetails";
    }

    @RequestMapping("/showmovies")
    public String showMovies(Model model)
    {
        model.addAttribute("movieList",movieRepo.findAll());
        return "showmoviedetails";
    }

}
