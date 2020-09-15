package dw.elh.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;

import dw.elh.service.MenuService;

public class BaseController {
	@Autowired
	MenuService menuService;
	
	protected boolean loggedIn(HttpServletRequest request) {
		HttpSession sesion = request.getSession(false);
		return !ObjectUtils.isEmpty(sesion)
				&& !ObjectUtils.isEmpty(sesion.getAttribute("login"))
				&& sesion.getAttribute("login").equals("true");
	}
	protected void preparaModel(ModelMap modelo) {
		modelo.addAttribute("menus", menuService.getMenu());
	}
}
