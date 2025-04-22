package com.meuRh.meuRh.controller;

import com.meuRh.meuRh.model.Candidato;
import com.meuRh.meuRh.model.Vaga;
import com.meuRh.meuRh.repository.CandidatoRepository;
import com.meuRh.meuRh.repository.VagaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class VagaController {

    @Autowired
    private VagaRepository vagaRepository;
    @Autowired
    private CandidatoRepository candidatoRepository;

    @RequestMapping(value = "/cadastrarVaga", method = RequestMethod.GET)
    public String form(){
        return "vaga/formVaga";
    }

    @RequestMapping(value = "/cadastrarVaga", method = RequestMethod.POST)
    public String form(@Valid Vaga vaga, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique os campos...");
            return "redirect:/cadastrarVaga";
        }
        vagaRepository.save(vaga);
        attributes.addFlashAttribute("mensagem", "Vaga cadastrada com sucesso!");
        return "redirect:/cadastrarVaga";
    }

    @RequestMapping(value = "/vagas")
    public ModelAndView listarVagas(){
        ModelAndView modelAndView = new ModelAndView("vaga/listaVaga");
        Iterable<Vaga> vagas = vagaRepository.findAll();
        modelAndView.addObject("vagas", vagas);
        return modelAndView;
    }

    @RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
    public ModelAndView detalhesVaga(@PathVariable("codigo") int codigo){
        Vaga vaga = vagaRepository.findByCodigo(codigo);
        ModelAndView modelAndView = new ModelAndView("vaga/detalhesVaga");
        modelAndView.addObject("vaga", vaga);
        Iterable<Candidato> candidatos = candidatoRepository.findByVaga(vaga);
        modelAndView.addObject("candidatos", candidatos);
        return modelAndView;
    }

    //adicionar candidato à vaga
    @RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
    public String detalhesVagaPost(@PathVariable("codigo") int codigo, @Valid Candidato candidato,
                                   BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique os campos obrigatórios");
            return "redirect:/{codigo}";
        }

        if(candidatoRepository.findByRg(candidato.getRg()) != null){
            attributes.addFlashAttribute("mensagem_erro", "RG já cadastrado");
            return "redirect:/{codigo}";
        }

        Vaga vaga = vagaRepository.findByCodigo(codigo);
        candidato.setVaga(vaga);
        candidatoRepository.save(candidato);
        attributes.addFlashAttribute("mensagem", "Candidato adicionado com sucesso!");
        return "redirect:/{codigo}";
    }

    @RequestMapping(value = "/deletarVaga")
    public String deletarVaga(int codigo){
        Vaga vaga = vagaRepository.findByCodigo(codigo);
        vagaRepository.delete(vaga);
        return "redirect:/vagas";
    }

    @RequestMapping(value = "/deletarCandidato")
    public String deletarCandidato(String rg){
        Candidato candidato = candidatoRepository.findByRg(rg);
        Vaga vaga = candidato.getVaga();
        String codigo = "" + vaga.getCodigo();
        candidatoRepository.delete(candidato);
        return "redirect:/" + codigo;
    }

    //mostrar formulário de edição de vaga
    @RequestMapping(value = "/editar-vaga", method = RequestMethod.GET)
    public ModelAndView editarVaga(int codigo){
        Vaga vaga = vagaRepository.findByCodigo(codigo);
        ModelAndView modelAndView = new ModelAndView("vaga/update-vaga");
        modelAndView.addObject("vaga", vaga);
        return modelAndView;
    }

    //editar a vaga em si
    @RequestMapping(value = "/editar-vaga", method = RequestMethod.POST)
    public String updateVaga(@Valid Vaga vaga, BindingResult result, RedirectAttributes attributes){
        vagaRepository.save(vaga);
        attributes.addFlashAttribute("success", "Vaga alterada com sucesso!");

        String codigo = "" + vaga.getCodigo();
        return "redirect:/" + codigo;
    }
}
