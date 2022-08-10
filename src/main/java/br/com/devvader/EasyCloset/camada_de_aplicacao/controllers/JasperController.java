package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;

@RestController
@RequestMapping("/v1/relatorios")
public class JasperController {

    @Autowired
    private Connection connection;

    @GetMapping
    public String minhaConexao(Model model) {
        model.addAttribute("conn", connection != null ? "Conexão ok!" : "Ops... sem conexão");
        return connection != null ? "Conexão ok!" : "Ops... sem conexão";
    }
}
