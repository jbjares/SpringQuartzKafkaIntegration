package eu.vital.iot.services;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/gateway")
public class GatewayController {

		
		@RequestMapping(value="/exec/{param}", method = RequestMethod.GET)
		@ResponseBody
		public String getMovie(@PathVariable String param, ModelMap model) {
			return "list";
		}
		
	
}
