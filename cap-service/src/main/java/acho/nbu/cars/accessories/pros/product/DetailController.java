package acho.nbu.cars.accessories.pros.product;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/details")
public class DetailController {
	@Autowired
	private DetailService detailService;
	//
	@Autowired
	private MaterialService materialService;
	//
	@RequestMapping("/list")
	public String getAllDetails(@Valid Detail detail, BindingResult result, Model model) {
		
		List<Detail> details = detailService.getAllDetails();
		model.addAttribute("details", details);

		String title = "Списък с всички детайли";
        model.addAttribute("title", title);
		
		return "list-details";
	}
	
	@RequestMapping("/{id}")
	public Detail getDetail(@PathVariable("id") Long id) {
		return detailService.getDetail(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "add")
    public String addDetailForm(Model model) {
		
		//
		List<Material> materials = materialService.getAllMaterials();
		model.addAttribute("materials", materials);
		//
        model.addAttribute("title", "Добави детайл");
        model.addAttribute("detail", new Detail());
        return "add-detail";
    }
	
	@RequestMapping(method=RequestMethod.POST, value="add")
    public String processDetailForm(@ModelAttribute Detail detail) {
        detailService.addDetail(detail);
        return "index";
    }
	
	@RequestMapping(method=RequestMethod.PUT, value="/")
	public void updateDetail(@RequestBody Detail detail, @PathVariable Long id) {
		detailService.updateDetail(detail);
	}
	
	@RequestMapping(value="/delete/{id}")
	public String deleteProgram(@PathVariable("id") Long id) {
        detailService.deleteDetail(id);
        return "redirect:/";
    }
	
}
