package dw.elh.controller;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlataformaController {
	@GetMapping("/")
	public String inicio(
			Model modelo
			, HttpServletRequest httpRequest) throws NoSuchAlgorithmException {
		return "inicio";
	}
}