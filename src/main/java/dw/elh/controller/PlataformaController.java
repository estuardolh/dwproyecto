package dw.elh.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlataformaController {
	@GetMapping("/")
	public String inicio(ModelMap modelo
			, HttpServletRequest httpRequest) {
		return "inicio";
	}
}